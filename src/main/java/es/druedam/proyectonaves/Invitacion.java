package es.druedam.proyectonaves;

import java.util.ArrayList;

public class Invitacion
{
    private String correo;
    private String nombre_alumno;
    private String curso;
    private String codigo;
    private boolean validado;
    private boolean enviado;
    private String fecha_validacion;

    public Invitacion(String correo, String nombre, String curso, String codigo, boolean validado, boolean enviado, String fecha_validacion)
    {
        this.correo = correo;
        this.nombre_alumno = nombre;
        this.curso = curso;
        this.codigo = codigo;
        this.validado = validado;
        this.enviado = enviado;
        this.fecha_validacion = fecha_validacion;
    }


    public Invitacion(String correo,  String codigo) {
        this.correo = correo;
        this.codigo = codigo;
    }

    public Invitacion(String correo, String nombre, String curso)
    {
        this.correo = correo;
        this.nombre_alumno = nombre;
        this.curso = curso;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getListaCodigos() {
        return codigo;
    }

    public void setListaCodigos(String listaCodigos) {
        this.codigo = listaCodigos;
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

    public String getCodigo() {
        return codigo;
    }
}
