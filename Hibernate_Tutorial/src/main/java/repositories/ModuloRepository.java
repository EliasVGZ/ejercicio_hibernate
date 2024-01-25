package repositories;

import entities.Modulo;
import entities.Modulo;
import org.hibernate.Session;

import java.util.List;

public class ModuloRepository implements Repository<Modulo> {
    private final Session session;

    public ModuloRepository(Session session) {
        this.session = session;
    }

    @Override
    public void save(Modulo modulo) {
        session.beginTransaction();
        session.save(modulo);
        System.out.println("Modulo guardado correctamente con el id:" + modulo.getModuloId());
        session.getTransaction().commit();
    }

    @Override
    public List<Modulo> findAll() {
        session.beginTransaction();
        // nota: de la base de datos recuperame todos los modulos ("FROM modulos"), y la clase a la cual queremos transformar los datos (Modulo.class)
        List<Modulo> modulos = session.createQuery("FROM modulos", Modulo.class).getResultList();
        session.getTransaction().commit();
        return modulos;
    }

    @Override
    public Modulo findOneById(long id) {
        session.beginTransaction();
        // nota: de la base de datos recuperame todos los modulos por id("FROM modulos WHERE moduloId=:id"), y la clase a la cual queremos transformar los datos (Modulo.class) y lo convertimos en un único resultado (.getSingleResult())
        Modulo modulo = session.createQuery("FROM modulos WHERE moduloId=:id", Modulo.class).setParameter("id", id).getSingleResult();
        session.getTransaction().commit();
        return modulo;
    }

    @Override
    public void update(Modulo modulo) {
        session.beginTransaction();
        session.update(modulo);
        System.out.println("Modulo actualizado correctamente con el id:" + modulo.getModuloId());
        session.getTransaction().commit();
    }

    @Override
    public void delete(Modulo modulo) {
        session.beginTransaction();
        session.delete(modulo);
        System.out.println("Modulo eliminado correctamente con el id:" + modulo.getModuloId());
        session.getTransaction().commit();
    }
}
