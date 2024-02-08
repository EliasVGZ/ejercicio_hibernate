package com.mycompany.hibernatefinal.main;

import com.mycompany.hibernatefinal.model.AutorDAO;
import com.mycompany.hibernatefinal.model.Autores;
import com.mycompany.hibernatefinal.model.Libros;
import com.mycompany.hibernatefinal.model.LibrosDAO;
import com.mycompany.hibernatefinal.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Session session = HibernateUtil.get().openSession();
    static AutorDAO autorDAO = new AutorDAO(session);
    static LibrosDAO librosDAO = new LibrosDAO(session);


    public static void main(String[] args) {


        while (true) {
            System.out.println("Menú:");
            System.out.println("1- Inserción de nuevas filas");
            System.out.println("    a. Inserción autor");
            System.out.println("    b. Inserción libro");
            System.out.println("    c. Relacion autor-libro");
            System.out.println("2- Borrado de filas");
            System.out.println("    a. Borrado libro");
            System.out.println("    b. Borrado autor");
            System.out.println("3- Consultas");
            System.out.println("    a. Introduciendo el título de un libro visualice sus datos");
            System.out.println("    b. Introduciendo el nombre de un autor visualice sus libros");
            System.out.println("    c. Visualización de todos los Libros");
            System.out.println("    d. Visualización de todos los autores con sus libros (si tienen)");
            System.out.println("4- Fin");
            System.out.print("Seleccione una opción: ");

            try {
                String opcionStr = br.readLine();
                int opcion = Integer.parseInt(opcionStr);

                switch (opcion) {
                    case 1:
                        System.out.print("Seleccione una subopción (a/b/c): ");
                        String subopcion = br.readLine();

                        switch (subopcion) {
                            case "a":
                                String dni;
                                do {
                                    System.out.println("Dni del autor: ");
                                    dni = br.readLine();
                                    if (!validaDNI_Exp(dni)) {
                                        System.out.println("DNI no válido. Debe tener 8 números y una letra.");
                                    }
                                } while (!validaDNI_Exp(dni));

                                System.out.println("Escribe nombre autor");
                                String nombre = br.readLine();
                                System.out.println("Nacionalidad ");
                                String nac = br.readLine();

                                Autores nuevoAutor = new Autores(dni, nombre, nac);

                                System.out.println("¿Quieres añadir un libro al autor? (Si/No)");
                                String respuesta = br.readLine().toLowerCase();

                                if (respuesta.equals("si") || respuesta.equals("sí")) {

                                    System.out.println("Título del libro:");
                                    String titulo = br.readLine();
                                    System.out.println("Precio del libro:");
                                    String precio = br.readLine();

                                    Libros nuevoLibro = new Libros(titulo, precio);
                                    nuevoAutor.addLibro(nuevoLibro);

                                    System.out.println("Autor y Libro añadido con exito");

                                }

                                autorDAO.insertarAutor(nuevoAutor);
                                break;



                            case "b":
                                System.out.println("Nombre del libro");
                                String nombreLibro = br.readLine();
                                System.out.println("Precio libro");
                                String precio = br.readLine();

                                Libros libro = new Libros(nombreLibro, precio);

                                System.out.println("¿Quieres añadir un autor al libro? (Si/No)");
                                String respuesta2 = br.readLine().toLowerCase();

                                if (respuesta2.equals("si") || respuesta2.equals("sí")) {

                                    System.out.println("Elige una opcion: ");
                                    System.out.println("Opcion 1 - agregar un autor nuevo");
                                    System.out.println("Opcion 2 - agregar libro a un autor que ya existe");
                                    int opcionAutores = Integer.parseInt(br.readLine());

                                    if(opcionAutores == 1){
                                        String dniA;
                                        do {
                                            System.out.println("Dni del autor: ");
                                            dniA = br.readLine();
                                            if (!validaDNI_Exp(dniA)) {
                                                System.out.println("DNI no válido. Debe tener 8 números y una letra.");
                                            }
                                        } while (!validaDNI_Exp(dniA));
                                        System.out.println("Nombre Autor: ");
                                        String nombreA = br.readLine();
                                        System.out.println("Nacionalidad: ");
                                        String nacA = br.readLine();

                                        Autores autor = new Autores(dniA, nombreA, nacA);
                                        libro.addAutor(autor);

                                        System.out.println("Libro y Autor añadido con exito");


                                    }else if(opcionAutores == 2){

                                        List<Autores> listaAutores = autorDAO.obtenerAutores();

                                        System.out.println("Lista de autores existentes:");
                                        for (Autores autor : listaAutores) {
                                            System.out.println("- " + autor.getNombre() + " (DNI: " + autor.getDniAutor() + ")");
                                        }
                                        System.out.println("Introduce el DNI del autor existente:");
                                        String dniAutorExistente = br.readLine();

                                        Autores autorExiste = null;

                                        for (Autores autor : listaAutores) {
                                            if(autor.getDniAutor().equals(dniAutorExistente)){
                                                autorExiste = autor;
                                                System.out.println("Autor existe");
                                                break;
                                            }
                                        }

                                        if(autorExiste != null){
                                            libro.addAutor(autorExiste);

                                            autorDAO.agregarLibroAAutorExistente(dniAutorExistente, libro);

                                            System.out.println("Libro y autor relacionado con exito");
                                        }else{
                                            System.out.println("No se encontro ningun autor con ese dni");
                                        }
                                    }


                                }
                                librosDAO.insertarLibro(libro);

                                break;

                            case "c":
                                System.out.println("Relacionar autor con libro existente");
                                System.out.println("DNI del autor: ");
                                String dniAutorExistente = br.readLine();

                                System.out.println("ID del libro: ");
                                int idLibro = Integer.parseInt(br.readLine());

                                List<Libros> librosList = librosDAO.findByID(idLibro);

                                if (!librosList.isEmpty()) {
                                    Libros libroExistente = librosList.get(0);

                                    autorDAO.agregarLibroAAutorExistente(dniAutorExistente, libroExistente);
                                } else {
                                    System.out.println("No se encontró un libro con el ID: " + idLibro);
                                }
                                break;



                            default:
                                System.out.println("Subopción no válida.");
                        }
                        break;

                    case 2:
                        System.out.print("Seleccione una subopción (a/b): ");
                        String subopcionBorrado = br.readLine();

                        switch (subopcionBorrado) {
                            case "a":
                                System.out.println("Inserte Titulo del libro que quieres eliminar");
                                String nomLibro = br.readLine();
                                librosDAO.eliminarLibroPorTitulo(nomLibro);
                                break;

                            case "b":
                                System.out.println("Eliminar Autor");
                                System.out.println("Escribe dni del autor");
                                String dniaut = br.readLine();

                                autorDAO.eliminarAutorPorDNI(dniaut);
                                break;

                            default:
                                System.out.println("Subopción no válida.");
                        }
                        break;

                    case 3:
                        System.out.print("Seleccione una subopción (a/b/c/d): ");
                        String subopcionConsulta = br.readLine();

                        switch (subopcionConsulta) {
                            case "a":

                                System.out.println("Introduzca el titulo del libro:");
                                String tituloLibro = br.readLine();

                                librosDAO.findByTitulo(tituloLibro);

                                break;

                            case "b":
                                System.out.println("Escribe nombre del autor para ver sus libros");
                                String nombreAut = br.readLine();
                                autorDAO.obtenerLibrosDeAutorPorNombre(nombreAut);
                                break;

                            case "c":
                                librosDAO.findAllBooks();
                                break;

                            case "d":
                                autorDAO.visualizarAutoresConLibros();
                                break;

                            default:
                                System.out.println("OPCION NO VALIDA");
                        }
                        break;

                    case 4:
                        System.out.println("Saliendo del programa.");
                        System.exit(0);

                    default:
                        System.out.println("OPCION NO VALIDA    .");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error al leer la entrada. Intente de nuevo.");
            }
        }

    }


    public static boolean validaDNI_Exp(String DNI) {
        boolean respuesta = false;
        if (DNI.matches("^[0-9]{8}[TtRrWwAaGgMmYyFfPpDdXxBbNnJjZzSsQqVvHhLlCcKkEe]{1}$")) {
            respuesta = true;
        }
        return respuesta;
    }
}
