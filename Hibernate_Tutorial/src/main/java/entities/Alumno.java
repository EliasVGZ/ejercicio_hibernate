package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "alumnos")
public class Alumno extends Persona {

    @OneToOne
    @JoinColumn(name = "centro_id", referencedColumnName = "centro_id", foreignKey = @ForeignKey(name = "FK_alumno_centro"))
    private Centro centro;
    // nota: cuando se borre un alumno que se borren todos los modulos // cuando recupere un alumno que recupere todos los modulos
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Modulo> modulos;
    private int edad;

    public Alumno() {
    }

    public Alumno(String nombre, String dni, Centro centro, int edad, ArrayList<Modulo> modulos) {
        super(nombre, dni);
        this.centro = centro;
        this.edad = edad;
        this.modulos = modulos;
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public Alumno(String nombre, String dni, Centro centro, List<Modulo> modulos, int edad) {
        super(nombre, dni);
        this.centro = centro;
        this.modulos = modulos;
        this.edad = edad;
    }

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    // nota: metodo para pasarle al alumno solo un modulo
    public void addModulo(Modulo modulo){
        if (modulos == null){
            modulos = new ArrayList<Modulo>();
        }
        modulos.add(modulo);
    }

}
