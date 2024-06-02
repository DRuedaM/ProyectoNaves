package es.druedam.proyectonaves;

public class Codigo
{
    private String correo;
    private String codigo;

    public Codigo(String correo, String codigo)
    {
        this.correo = correo;
        this.codigo = codigo;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCodigo() {
        return codigo;
    }
}
