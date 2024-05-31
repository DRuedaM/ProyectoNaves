package es.druedam.proyectonaves;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager
{
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost:3306/proyectonaves";
    private static String username = "root";
    private static String password = "admin";

    public static void connectDatabase()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            connection.close();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void crearConexion()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }
        catch(ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void cerrarConexion()
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void crearAlumno(Invitacion invitacion, int cantidadInvitaciones)
    {
        crearConexion();
        try
        {
            String query = "INSERT INTO alumno (correo, nombre_alumno, curso) VALUES " +
                    "('" + invitacion.getCorreo() + "','"
                    + invitacion.getNombre() + "','" +
                    invitacion.getCurso() + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            for(int i = 0; i <= cantidadInvitaciones ;i++)
            {
                query = "INSERT INTO codigo (correo, codigo) VALUES " +
                        "('" + invitacion.getCorreo() + "','"
                        + invitacion.getCorreo() + "." + invitacion.getNombre() + "." + invitacion.getCurso() + "')";
                statement = connection.createStatement();
                statement.executeUpdate(query);
            }
            System.out.println("Alumno e invitaciones creadas!");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        cerrarConexion();
    }

    public static void eliminarAlumno(String correo)
    {
        crearConexion();
        try
        {
            String query = "DELETE FROM alumno WHERE correo LIKE '" + correo + "';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        cerrarConexion();
    }

    public static void modificarAlumno(Invitacion invitacion)
    {
        crearConexion();
        try
        {
            String query = "UPDATE alumno SET correo = " + invitacion.getCorreo() + " WHERE correo LIKE '" + invitacion.getCorreo() + "';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        cerrarConexion();
    }

    public static ArrayList<Invitacion> leerAlumnos()
    {
        crearConexion();
        ArrayList<Invitacion> listaInvitaciones = new ArrayList<>();

        try
        {
            String query = "SELECT * FROM alumno INNER JOIN codigo ON alumno.correo = codigo.correo";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next())
            {
                String correo = rs.getString("correo");
                String nombre_alumno = rs.getString("nombre_alumno");
                String curso = rs.getString("curso");
                String codigo = rs.getString("codigo");
                boolean validado = rs.getBoolean("validado");
                boolean enviado = rs.getBoolean("enviado");
                String fecha_validacion = rs.getString("fecha_validacion");
                listaInvitaciones.add(new Invitacion(correo, nombre_alumno, curso, codigo, validado, enviado, fecha_validacion));

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        cerrarConexion();
        return listaInvitaciones;
    }


    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        DatabaseManager.url = url;
    }
}
