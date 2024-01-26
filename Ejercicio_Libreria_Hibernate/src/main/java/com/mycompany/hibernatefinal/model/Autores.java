package com.mycompany.hibernatefinal.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="AUTORES", uniqueConstraints={@UniqueConstraint(columnNames={"DNIAUTOR"})})
public class Autores {

    @Id
    @Column(name="DNIAUTOR", nullable=false, unique=true)
    private String dniAutor;


    @Column(name="NOMBRE", length=20, nullable=true)
    private String nombre;

    @Column(name="NACIONALIDAD", length=20, nullable=true)
    private String nacionalidad;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libros> libros;

    public Autores(String dniAutor, String nombre, String nacionalidad, List<Libros> libros) {
        this.dniAutor = dniAutor;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.libros = libros;
    }


    public Autores(String dniAutor, String nombre, String nacionalidad) {
        this.dniAutor = dniAutor;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public void addLibro(Libros libro) {
        if (libros == null) {
            libros = new ArrayList<>();
        }
        libros.add(libro);
    }



    public Autores() {

    }

    public String getDniAutor() {
        return dniAutor;
    }

    public void setDniAutor(String dniAutor) {
        this.dniAutor = dniAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public List<Libros> getLibros() {
        return libros;
    }

    public void setLibros(List<Libros> libros) {
        this.libros = libros;
    }
}
