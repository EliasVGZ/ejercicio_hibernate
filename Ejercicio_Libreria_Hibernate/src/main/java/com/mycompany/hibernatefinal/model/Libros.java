package com.mycompany.hibernatefinal.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Libros",uniqueConstraints={@UniqueConstraint(columnNames={"IDLIBRO"})})

public class Libros {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDLIBRO", nullable=false, unique=true, length=11)
    private int idLibro;

    @Column(name="TITULO", length=20, nullable=true)
    private String titulo;

    @Column(name="PRECIO", length=20, nullable=true)
    private String precio;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autores> autores;

    public Libros(String titulo, String precio) {
        this.titulo = titulo;
        this.precio = precio;
    }

    public Libros() {

    }

    public Libros(int idLibro, String titulo, String precio, List<Autores> autores) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.precio = precio;
        this.autores = autores;
    }

    public void addAutor(Autores autor) {
        if (autores == null) {
            autores = new ArrayList<>();
        }
        autores.add(autor);
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
