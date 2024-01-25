package com.mycompany.hibernatefinal.main;

import com.mycompany.hibernatefinal.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.mycompany.hibernatefinal.model.Autores;

public class HibernateConnectionDemo {

    public static void main(String[] args) {
        System.out.println("Iniciando conexion a MYSQL con Hibernate");
        Session session = HibernateUtil.get().openSession();

        session.close();
        System.out.println("Finalizando la conexion a MYSQL con Hibernate");
    }
}
