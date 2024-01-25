package com.mycompany.hibernatefinal.model;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.List;

public class LibrosDAO {

    private final Session session;

    public LibrosDAO(Session session) {
        this.session = session;
    }


    public List<Libros> findAllBooks() {
        try {
            session.beginTransaction();

            // Crear la consulta HQL para obtener todos los libros
            String hql = "FROM Libros";
            Query query = session.createQuery(hql);

            // Ejecutar la consulta y obtener la lista de libros
            List<Libros> libros = query.list();

            // Imprimir resultados (puedes adaptar esto según tus necesidades)
            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros en la base de datos.");
            } else {
                System.out.println("Todos los libros en la base de datos:");
                for (Libros libro : libros) {
                    System.out.println("ID: " + libro.getIdLibro() + ", Título: " + libro.getTitulo() + ", Precio: " + libro.getPrecio());
                }
            }

            session.getTransaction().commit();

            return libros;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al obtener todos los libros.");
            session.getTransaction().rollback();
            return null;
        }
    }


    public List<Libros> findByTitulo(String titulo) {
        try {

            String hql = "FROM Libros WHERE titulo = :titulo";
            Query query = session.createQuery(hql);
            query.setParameter("titulo", titulo);

            // Ejecutar la consulta y obtener la lista de libros
            List<Libros> libros = query.list();


            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros con el título: " + titulo);
            } else {
                System.out.println("Libros encontrados con el título '" + titulo + "':");
                for (Libros libro : libros) {
                    System.out.println("Título: " + libro.getTitulo() + ", Precio: " + libro.getPrecio());
                }
            }

            return libros;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al realizar la consulta de libros por título.");
            return null;
        }
    }


    public void insertarLibro(Libros libro) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(libro);
            transaction.commit();
            System.out.println("Libro insertado correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Error al insertar libro.");
        }
    }
}
