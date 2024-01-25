package com.mycompany.hibernatefinal.model;

import org.hibernate.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AutorDAO {

    private final Session session;

    public AutorDAO(Session session) {
        this.session = session;
    }

    public List<Libros> obtenerLibrosDeAutorPorNombre(String nombreAutor) {
        try {
            session.beginTransaction();

            // Crear la consulta HQL para obtener los libros de un autor por nombre
            /*FROM Autores a: Especifica la entidad de la cual queremos obtener los datos, en este caso, la entidad Autores.
            Se utiliza el alias a para referirse a la instancia de Autores en la consulta*/
            String hql = "SELECT libros FROM Autores a JOIN a.libros libros WHERE lower(a.nombre) = :nombreAutor";
            Query query = session.createQuery(hql);
            query.setParameter("nombreAutor", nombreAutor.toLowerCase());

            // Ejecutar la consulta y obtener la lista de libros
            List<Libros> libros = query.list();

            // Imprimir resultados (puedes adaptar esto según tus necesidades)
            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros para el autor '" + nombreAutor + "'.");
            } else {
                System.out.println("Libros del autor '" + nombreAutor + "':");
                for (Libros libro : libros) {
                    System.out.println(" Título: " + libro.getTitulo() + ", Precio: " + libro.getPrecio());
                }
            }

            session.getTransaction().commit();

            return libros;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al obtener los libros del autor.");
            session.getTransaction().rollback();
            return null;
        }
    }





    public void insertarAutor(Autores autor) {
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
        }
    }

}


