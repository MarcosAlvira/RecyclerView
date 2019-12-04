package com.example.recyclerview;

public class Usuario {
    String nombre;
    String apellido;
    String correo;
    private boolean seleccionado;

    public Usuario(String nombre, String apellido, String correo, boolean seleccionado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.seleccionado = seleccionado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellido;
    }

    public void setApellidos(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
