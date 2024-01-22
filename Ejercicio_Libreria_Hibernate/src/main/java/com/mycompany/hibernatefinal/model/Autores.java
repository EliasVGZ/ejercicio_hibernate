package com.mycompany.hibernatefinal.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Autores",
        uniqueConstraints={@UniqueConstraint(columnNames={"DNIAUTOR"})})
public class Autores {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="DNIAUTOR", nullable=false, unique=true, length=11)
    private Long dniAutor;

    @Column(name="NOMBRE", length=20, nullable=true)
    private String nombre;

    @Column(name="NACIONALIDAD", length=20, nullable=true)
    private String nacionalidad;

    // Relación 1-N con Libros
    @OneToMany(mappedBy = "autor")
    private Set<Libros> libros = new HashSet<>();

    // Relación 1-1 con Telefonos
    @OneToOne(mappedBy = "autor", cascade = CascadeType.ALL)
    private Telefono telefono;

    public Long getDniAutor() {
        return dniAutor;
    }

    public void setDniAutor(Long dniAutor) {
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
}
