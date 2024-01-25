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




public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Session session = HibernateUtil.get().openSession();
        AutorDAO autorDAO = new AutorDAO(session);
        LibrosDAO librosDAO = new LibrosDAO(session);



        while (true) {
            System.out.println("Menú:");
            System.out.println("1- Inserción de nuevas filas");
            System.out.println("    a. Inserción autor");
            System.out.println("    b. Inserción libro");
            System.out.println("2- Borrado de filas");
            System.out.println("    a. Borrado libro");
            System.out.println("    b. Borrado autor");
            System.out.println("3- Consultas");
            System.out.println("    a. Introduciendo el título de un libro visualice sus datos");
            System.out.println("    b. Introduciendo el nombre de un autor visualice sus libros");
            System.out.println("    c. Visualización de todos los Libros");
            System.out.println("    d. Visualización de todos los autores con sus libros");
            System.out.println("4- Fin");
            System.out.print("Seleccione una opción: ");

            try {
                String opcionStr = br.readLine();
                int opcion = Integer.parseInt(opcionStr);

                switch (opcion) {
                    case 1:
                        System.out.print("Seleccione una subopción (a/b): ");
                        String subopcion = br.readLine();

                        switch (subopcion) {
                            case "a":
                                Autores nuevoAutor = new Autores();
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

                                nuevoAutor.setDniAutor(dni);
                                nuevoAutor.setNombre(nombre);
                                nuevoAutor.setNacionalidad(nac);

                                // Preguntar al usuario si quiere añadir un libro al autor
                                System.out.println("¿Quieres añadir un libro al autor? (Si/No)");
                                String respuesta = br.readLine().toLowerCase();

                                if (respuesta.equals("si") || respuesta.equals("sí")) {
                                    Libros nuevoLibro = new Libros();
                                    System.out.println("Título del libro:");
                                    nuevoLibro.setTitulo(br.readLine());
                                    System.out.println("Precio del libro:");
                                    nuevoLibro.setPrecio(br.readLine());

                                    // Añadir el libro al autor
                                    nuevoAutor.addLibro(nuevoLibro);
                                }

                                autorDAO.insertarAutor(nuevoAutor);
                                break;



                            case "b":
                                System.out.println("Nombre del libro");
                                String nombreLibro = br.readLine();
                                System.out.println("Precio libro");
                                String precio = br.readLine();

                                Libros libro = new Libros(nombreLibro, precio);

                                librosDAO.insertarLibro(libro);

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
                                // Código para borrado de libro
                                // ...
                                break;

                            case "b":
                                // Código para borrado de autor
                                // ...
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
                                // Después de seleccionar la opción "a" en el menú de consultas
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
                                // Código para visualización de todos los autores con sus libros
                                // ...
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
