import config.HibernateUtil;
import entities.Alumno;
import entities.Centro;
import entities.Modulo;
import org.hibernate.Session;
import repositories.AlumnoRepository;
import repositories.CentroRepository;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando conexion a MYSQL con Hibernate");
        Session session = HibernateUtil.get().openSession();

        // ejemploPractica1(session); // nota: creamos un centro con funcionalidades CRUD
        ejemploPractica2(session); // nota: creamos un Alumno con todos sus dependencias

        session.close();
        System.out.println("Finalizando la conexion a MYSQL con Hibernate");
    }

    private static void ejemploPractica2(Session session) {
        // nota: creamos un centro
        Centro centro = new Centro("IES de Teis");
        CentroRepository centroRepository = new CentroRepository(session);
        centroRepository.save(centro);

        // nota: creamos lista modulos
        ArrayList<Modulo> modulos = new ArrayList<>();
        Modulo prog = new Modulo("Programacion", "PR");
        Modulo accesoADatos = new Modulo("Acceso a Datos", "AD");
        Modulo progDispMoviles = new Modulo("Programacion Dispositivos Moviles", "PMDM");
        modulos.add(prog);
        modulos.add(accesoADatos);
        modulos.add(progDispMoviles);

        // nota: creamos un alumno
        Alumno alumno = new Alumno("Brais", "00000000t", centro, 23, modulos);
        AlumnoRepository alumnoRepository = new AlumnoRepository(session);

        // nota: guardamos el alumno
        alumnoRepository.save(alumno);
    }

    private static void ejemploPractica1(Session session) {
        // nota: guardamos un centro
        CentroRepository centroRepository = new CentroRepository(session);
        Centro centro = new Centro("IES de Chapela");
        centroRepository.save(centro);

        // nota: mostramos todos los centros
        System.out.println(centroRepository.findAll());
        // nota: mostramos el centro
        System.out.println(centroRepository.findOneById(1));
        // nota: actualizamos el centro
        centro.setNombre("IES de Chapela 2");
        centroRepository.update(centro);
        // nota: mostramos el centro
        System.out.println(centroRepository.findOneById(1));
        // nota: eliminamos el centro
        centroRepository.delete(centro);
    }


}