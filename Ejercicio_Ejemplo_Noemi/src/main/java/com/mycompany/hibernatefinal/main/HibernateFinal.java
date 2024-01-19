/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hibernatefinal.main;
import com.mycompany.hibernatefinal.model.Empleados;
import com.mycompany.hibernatefinal.util.HibernateUtil;
import org.hibernate.Session;
import java.util.Date;
/**
 *
 * @author a22alejandrofc
 */
public class HibernateFinal {

    public static void main(String[] args) {
        Empleados emp = new Empleados();

		
		//Get Session
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//start transaction
		session.beginTransaction();
		//Save the Model object
		session.save(emp);
		//Commit transaction
		session.getTransaction().commit();
		
		//terminate session factory, otherwise program won't end
		HibernateUtil.getSessionFactory().close();
    }
}
