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

    public void agregarLibroAAutorExistente(String dniAutor, Libros nuevoLibro) {
        try {
            session.beginTransaction();

            Autores autor = (Autores) session.get(Autores.class, dniAutor);
            if (autor != null) {
                autor.getLibros().add(nuevoLibro);
                session.saveOrUpdate(autor);
                System.out.println("Libro añadido al autor existente.");
            } else {
                System.out.println("No se encontró un autor con el DNI: " + dniAutor);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }



    public void eliminarLibroPorTitulo(String titulo) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Libros WHERE titulo = :titulo");
            query.setParameter("titulo", titulo);
            Libros libro = (Libros) query.uniqueResult();

            // para borrar, primero tenemos que eliminar las relaciones que tiene ese libro
            if (libro != null) {
                Query deleteQuery = session.createQuery("DELETE FROM Autores a WHERE :libro MEMBER OF a.libros");
                deleteQuery.setParameter("libro", libro);
                deleteQuery.executeUpdate();

                // despues eliminar el libro
                session.delete(libro);
                transaction.commit();
                System.out.println("Libro eliminado correctamente");
            } else {
                System.out.println("No se encontró un libro con el título: " + titulo);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



    public List<Libros> findAllBooks() {
        try {
            session.beginTransaction();


            String hql = "FROM Libros";
            Query query = session.createQuery(hql);


            List<Libros> libros = query.list();


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

    public List<Libros> findByID(int idLibro) {
        try {

            String hql = "FROM Libros WHERE idlibro = :idlibro";
            Query query = session.createQuery(hql);
            query.setParameter("idlibro", idLibro);

            List<Libros> libros = query.list();

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros con el id: " + idLibro);
            } else {
                System.out.println("Libros encontrados con el id '" + idLibro + "':");
                for (Libros libro : libros) {
                    System.out.println("id: " + libro.getIdLibro()+ ", Precio: " + libro.getPrecio());
                }
            }
            return libros;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al realizar la consulta de libros por título.");
            return null;
        }
    }


    public List<Libros> findByTitulo(String titulo) {
        try {

            String hql = "FROM Libros WHERE titulo = :titulo";
            Query query = session.createQuery(hql);
            query.setParameter("titulo", titulo);


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
