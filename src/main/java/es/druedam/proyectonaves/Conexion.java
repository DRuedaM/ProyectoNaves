package es.druedam.proyectonaves;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Conexion
{
    private static String apiURL = "http://localhost:8080/api-lasnaves";

    public static String sendQuery(String direccion)
    {
        try
        {
            URL url = new URL(apiURL+direccion);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-length", "0");
            int responseCode = con.getResponseCode();

            if(responseCode == 200)
            {
                System.out.println("Get request is sent to URL: " + url + " response code " + responseCode);
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while((inputLine = in.readLine()) != null)
                {
                    System.out.println(inputLine);
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

    public static void createAlumno(Invitacion alumno, int numInvitaciones)
    {
        try
        {
            String json = new Gson().toJson(alumno);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiURL + "/save"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200)
            {
                System.out.println("Alumno creado");
                int cantidadCodigos = selectCantidadCodigos(alumno.getCorreo());
                for(int i = 0 ; i < numInvitaciones ;i++)
                {
                    createCodigo(new Codigo(
                            alumno.getCorreo(),
                            alumno.getCorreo() + alumno.getNombre_alumno() + alumno.getCurso() + (i+cantidadCodigos)));
                }

            }
            else
            {
                System.out.println("Error al crear el alumno " + response.statusCode());
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static int selectCantidadCodigos(String correo)
    {
        try
        {
            URL url = new URL(apiURL+"/count/"+correo);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-length", "0");
            int responseCode = con.getResponseCode();

            if(responseCode == 200)
            {
                System.out.println("Get request is sent to URL: " + url + " response code " + responseCode);
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while((inputLine = in.readLine()) != null)
                {
                    System.out.println(inputLine);
                    response.append(inputLine);
                }
                in.close();
                return Integer.parseInt(response.toString());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public static void createCodigo(Codigo codigo)
    {
        try
        {
            String json = new Gson().toJson(codigo);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiURL + "/save-codigos"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200)
            {
                System.out.println("Codigos creados!");
            }
            else
            {
                System.out.println("Error al crear el codigo " + response.statusCode());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public static void deleteAlumno(String correo)
    {
        try
        {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiURL+"/"+correo))
                    .DELETE()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200)
            {
                System.out.println("Alumno eliminado");
            }
            else
            {
                System.out.println("Error al eliminar el alumno " + response.statusCode());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<Invitacion> leerAlumnos()
    {
        Conexion nuevaConexion = new Conexion();
        ArrayList<Invitacion> listaInvitaciones = new ArrayList<>();

        try
        {
            String consulta = Conexion.sendQuery("");
            Type listType = new TypeToken<ArrayList<Invitacion>>(){}.getType();
            listaInvitaciones = new Gson().fromJson(consulta, listType);
            for(Invitacion alumno: listaInvitaciones)
            {
                System.out.println(alumno.getCorreo());
                System.out.println(alumno.getNombre_alumno());
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listaInvitaciones;
    }

    public static ArrayList<Codigo> recogerAlumnos()
    {
        ArrayList<Codigo> listaInvitaciones = new ArrayList<>();
        ArrayList<String> listaCodigosPorAlumno = new ArrayList<>();
        try
        {
            String correos = sendQuery("/find-distinct-correos");
            String[] listaCorreo = new Gson().fromJson(correos, String[].class);
            System.out.println(listaCorreo.length);
            for (String correo : listaCorreo)
            {
                System.out.println(correo);
                URL url2 = new URL(apiURL + "/find-codigos/" + correo);
                HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
                con2.setRequestMethod("GET");
                con2.setRequestProperty("Content-length", "0");
                int responseCode2 = con2.getResponseCode();

                if (responseCode2 == 200)
                {
                    System.out.println("Get request is sent to URL: " + url2 + " response code " + responseCode2);
                    BufferedReader in2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
                    String inputLine2;
                    StringBuilder response2 = new StringBuilder();
                    while ((inputLine2 = in2.readLine()) != null)
                    {
                        listaCodigosPorAlumno.add(inputLine2);
                    }
                    in2.close();
                    listaInvitaciones.add(new Codigo(correo, new ArrayList<>(listaCodigosPorAlumno)));
                    listaCodigosPorAlumno.clear();
                }
            }
            return listaInvitaciones;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void borrarBaseDeDatos()
    {
        try
        {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiURL+"/delete-all"))
                    .DELETE()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200)
            {
                System.out.println("Base de datos borrada");
            }
            else
            {
                System.out.println("Error al eliminar la base de datos " + response.statusCode());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
