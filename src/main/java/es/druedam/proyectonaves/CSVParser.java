package es.druedam.proyectonaves;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author David Rueda
 * <p>
 *     Clase que parsea un fichero basandose en la separacion de ; y enviandolo al api para almacenarlo en la base de datos
 * </p>
 */
public class CSVParser
{
    public static void ParseFile(File file)
    {
        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line = "";
            while((line = br.readLine()) != null)
            {
                String[] values = line.split(";");
                Conexion.createAlumno(new Alumno(values[0], values[1], values[2]), Integer.parseInt(values[3]));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
