package com.mycompany.hibernatefinal.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AutorDAO {

    private final SessionFactory sessionFactory;

    public AutorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insertarAutor(Autores autor) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(autor);
            transaction.commit();
            System.out.println("Autor insertado correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Error al insertar autor.");
        } finally {
            session.close();
        }
    }
}
