/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hibernatefinal.main;
import com.mycompany.hibernatefinal.util.HibernateUtil;
import com.mycompany.hibernatefinal.model.Empleados1;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 *
 * @author a22alejandrofc
 */
public class HibernateJavaConfigMain {
    public static void main(String[] args) {
		Empleados1 emp = new Empleados1();
		
		//Get Session
		SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
		Session session = sessionFactory.getCurrentSession();
		//start transaction
		session.beginTransaction();
		//Save the Model object
		session.save(emp);
		//Commit transaction
		session.getTransaction().commit();
		
		//terminate session factory, otherwise program won't end
		sessionFactory.close();
	}
}
