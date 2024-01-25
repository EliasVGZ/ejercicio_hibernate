package repositories;

import entities.Alumno;
import entities.Alumno;
import org.hibernate.Session;

import java.util.List;

public class AlumnoRepository implements Repository<Alumno>{

    private final Session session;

    public AlumnoRepository(Session session) {
        this.session = session;
    }

    @Override
    public void save(Alumno alumno) {
        session.beginTransaction();
        session.save(alumno);
        System.out.println("Alumno guardado correctamente con el id:" + alumno.getPersonaId());
        session.getTransaction().commit();
    }

    @Override
    public List<Alumno> findAll() {
        session.beginTransaction();
        // nota: de la base de datos recuperame todos los alumnos ("FROM alumnos"), y la clase a la cual queremos transformar los datos (Alumno.class)
        List<Alumno> alumnos = session.createQuery("FROM alumnos", Alumno.class).getResultList();
        session.getTransaction().commit();
        return alumnos;
    }

    @Override
    public Alumno findOneById(long id) {
        session.beginTransaction();
        // nota: de la base de datos recuperame todos los alumnos por id("FROM alumnos WHERE personaId=:id"), y la clase a la cual queremos transformar los datos (Alumno.class) y lo convertimos en un único resultado (.getSingleResult())
        Alumno alumno = session.createQuery("FROM alumnos WHERE personaId=:id", Alumno.class).setParameter("id", id).getSingleResult();
        session.getTransaction().commit();
        return alumno;
    }

    @Override
    public void update(Alumno alumno) {
        session.beginTransaction();
        session.update(alumno);
        System.out.println("Alumno actualizado correctamente con el id:" + alumno.getPersonaId());
        session.getTransaction().commit();
    }

    @Override
    public void delete(Alumno alumno) {
        session.beginTransaction();
        session.delete(alumno);
        System.out.println("Alumno eliminado correctamente con el id:" + alumno.getPersonaId());
        session.getTransaction().commit();
    }
}
