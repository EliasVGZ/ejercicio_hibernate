package com.mycompany.hibernatefinal.model;

import javax.persistence.*;

@Entity
@Table(name = "Telefonos")
public class Telefono {
    @Id
    @Column(name = "DniAutor", unique = true)
    private String dniAutor;

    @Column(name = "NumeroTf")
    private String numeroTelefono;

    // Relación 1-1 con Autores
    @OneToOne
    @MapsId
    @JoinColumn(name = "DniAutor")
    private Autores autor;

    // Constructores, getters y setters

    public Telefono() {
    }

    public Telefono(String dniAutor, String numeroTelefono, Autores autor) {
        this.dniAutor = dniAutor;
        this.numeroTelefono = numeroTelefono;
        this.autor = autor;
    }

    public String getDniAutor() {
        return dniAutor;
    }

    public void setDniAutor(String dniAutor) {
        this.dniAutor = dniAutor;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores autor) {
        this.autor = autor;
    }
}
