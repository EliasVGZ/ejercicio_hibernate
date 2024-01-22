package com.mycompany.hibernatefinal.model;


import javax.persistence.*;

@Entity
@Table(name="Libros",
        uniqueConstraints={@UniqueConstraint(columnNames={"IDLIBRO"})})

public class Libros {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDLIBRO", nullable=false, unique=true, length=11)
    private int idLibro;

    @Column(name="TITULO", length=20, nullable=true)
    private String titulo;

    @Column(name="PRECIO", length=20, nullable=true)
    private String precio;

    // Relación 1-N con Autores
    @ManyToOne
    @JoinColumn(name = "DNIAUTOR")
    private Autores autor;


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
