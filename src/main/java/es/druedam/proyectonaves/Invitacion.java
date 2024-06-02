package es.druedam.proyectonaves;

import java.util.ArrayList;

public class Invitacion
{

    private String correo;
    private String nombre_alumno;
    private String curso;
    private ArrayList<String> listaCodigos;
    private String codigoUnico;
    private boolean validado;
    private boolean enviado;
    private String fecha_validacion;

    public Invitacion(String correo, String nombre, String curso, String codigo, boolean validado, boolean enviado, String fecha_validacion)
    {
        this.correo = correo;
        this.nombre_alumno = nombre;
        this.curso = curso;
        this.codigoUnico = codigo;
        this.validado = validado;
        this.enviado = enviado;
        this.fecha_validacion = fecha_validacion;
    }


    public Invitacion(String correo,  ArrayList<String> codigo) {
        this.correo = correo;
        this.listaCodigos = codigo;
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

    public ArrayList<String> getListaCodigos() {
        return listaCodigos;
    }

    public void setListaCodigos(ArrayList<String> listaCodigos) {
        this.listaCodigos = listaCodigos;
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

    public String getCodigoUnico() {
        return codigoUnico;
    }
}
