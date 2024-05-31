package es.druedam.proyectonaves;

public class Invitacion
{

    private String correo;
    private String nombre;
    private String curso;
    private String codigo;
    private boolean validado;
    private boolean enviado;
    private String fecha_validacion;

    public Invitacion(String correo, String nombre, String curso, String codigo, boolean validado, boolean enviado, String fecha_validacion)
    {
        this.correo = correo;
        this.nombre = nombre;
        this.curso = curso;
        this.codigo = codigo;
        this.validado = validado;
        this.enviado = enviado;
        this.fecha_validacion = fecha_validacion;
    }

    public Invitacion(String correo, String nombre, String curso)
    {
        this.correo = correo;
        this.nombre = nombre;
        this.curso = curso;

    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public String getFecha_validacion() {
        return fecha_validacion;
    }

    public void setFecha_validacion(String fecha_validacion) {
        this.fecha_validacion = fecha_validacion;
    }
}
