package entities;

import javax.persistence.*;

@Entity(name = "modulos")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "abreviatura", name = "abreviatura_not_unique"))
public class Modulo {
    @Id
    @Column(name = "modulo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long moduloId;
    private String nombre;
    private String abreviatura;

    public Modulo() {
    }

    public Modulo(String nombre, String abreviatura) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }

    public long getModuloId() {
        return moduloId;
    }

    public void setModuloId(long moduloId) {
        this.moduloId = moduloId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
}
