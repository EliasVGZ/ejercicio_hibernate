package com.mycompany.hibernatefinal.main;

import com.mycompany.hibernatefinal.model.AutorDAO;
import com.mycompany.hibernatefinal.model.Autores;
import com.mycompany.hibernatefinal.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.mycompany.hibernatefinal.util.HibernateUtil.sessionFactory;


public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        AutorDAO autorDAO = new AutorDAO(sessionFactory);



        while (true) {
            System.out.println("Menú:");
            System.out.println("1- Inserción de nuevas filas");
            System.out.println("    a. Inserción autor");
            System.out.println("    b. Inserción libro");
            System.out.println("0- Salir");
            System.out.print("Seleccione una opción: ");

            try {
                String opcionStr = reader.readLine();
                int opcion = Integer.parseInt(opcionStr);

                switch (opcion) {
                    case 1:
                        System.out.print("Seleccione una subopción (a/b): ");
                        String subopcion = reader.readLine();

                        switch (subopcion) {
                            case "a":
                                Autores nuevoAutor = new Autores();
                                nuevoAutor.setDniAutor("12312323L");
                                nuevoAutor.setNombre("elias");
                                nuevoAutor.setNacionalidad("teis");


                                autorDAO.insertarAutor(nuevoAutor);

                                break;
                            case "b":

                                break;
                            default:
                                System.out.println("Subopción no válida.");
                        }
                        break;
                    case 0:
                        System.out.println("Saliendo del programa.");
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error al leer la entrada. Intente de nuevo.");
            }
        }
    }
}
