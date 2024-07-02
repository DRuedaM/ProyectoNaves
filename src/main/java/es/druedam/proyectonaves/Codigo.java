package es.druedam.proyectonaves;

import java.util.ArrayList;

/**
 * @author David Rueda
 * <p>
 *     Clase para guardar el texto del codigo y el correo correspondiente
 * </p>
 */
public class Codigo
{
    private String correo;
    private String codigo;

    private ArrayList<String> listaCodigos;

    public Codigo(String correo, String codigo)
    {
        this.correo = correo;
        this.codigo = codigo;
    }

    public Codigo(String correo, ArrayList<String> listaCodigos) {
        this.correo = correo;
        this.listaCodigos = listaCodigos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCodigo() {
        return codigo;
    }

    public ArrayList<String> getListaCodigos() {
        return listaCodigos;
    }
}
