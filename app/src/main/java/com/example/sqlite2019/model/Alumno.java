package com.example.sqlite2019.model;

public class Alumno {
    private Integer _id;
    private String nombres;
    private Integer cod;
    private String correo;

    public Alumno() {
    }

    public Alumno(Integer _id, String nombres, Integer cod, String correo) {
        this._id = _id;
        this.nombres = nombres;
        this.cod = cod;
        this.correo = correo;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
