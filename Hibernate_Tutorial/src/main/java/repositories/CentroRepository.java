package repositories;

import entities.Centro;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CentroRepository implements Repository<Centro> {
    private final Session session;

    public CentroRepository(Session session) {
        this.session = session;
    }

    @Override
    public void save(Centro centro) {
        session.beginTransaction();
        session.save(centro);
        System.out.println("Centro guardado correctamente con el id:" + centro.getCentroId());
        session.getTransaction().commit();
    }

    @Override
    public List<Centro> findAll() {
        session.beginTransaction();
        // nota: de la base de datos recuperame todos los centros ("FROM centros"), y la clase a la cual queremos transformar los datos (Centro.class)
        List<Centro> centros = session.createQuery("FROM centros", Centro.class).getResultList();
        session.getTransaction().commit();
        return centros;
    }

    @Override
    public Centro findOneById(long id) {
        session.beginTransaction();
        // nota: de la base de datos recuperame todos los centros por id("FROM centros WHERE centroId=:id"), y la clase a la cual queremos transformar los datos (Centro.class) y lo convertimos en un único resultado (.getSingleResult())
        Centro centro = session.createQuery("FROM centros WHERE centroId=:id", Centro.class).setParameter("id", id).getSingleResult();
        session.getTransaction().commit();
        return centro;
    }

    @Override
    public void update(Centro centro) {
        session.beginTransaction();
        session.update(centro);
        System.out.println("Centro actualizado correctamente con el id:" + centro.getCentroId());
        session.getTransaction().commit();
    }

    @Override
    public void delete(Centro centro) {
        session.beginTransaction();
        session.delete(centro);
        System.out.println("Centro eliminado correctamente con el id:" + centro.getCentroId());
        session.getTransaction().commit();
    }
}
