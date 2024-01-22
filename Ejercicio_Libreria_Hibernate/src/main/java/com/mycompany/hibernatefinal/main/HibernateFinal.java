/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hibernatefinal.main;
import com.mycompany.hibernatefinal.model.Autores;
import com.mycompany.hibernatefinal.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author a22alejandrofc
 */
public class HibernateFinal {

    public static void main(String[] args) {
        Autores aut = new Autores();
		aut.setNombre("Elias");
		aut.setDniAutor(Long.valueOf("12312345"));
		aut.setNacionalidad("España");
		
		//Get Session
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//start transaction
		session.beginTransaction();
		//Save the Model object
		session.save(aut);
		//Commit transaction
		session.getTransaction().commit();
		System.out.println("Nombre: "+aut.getNombre());
		
		//terminate session factory, otherwise program won't end
		HibernateUtil.getSessionFactory().close();
    }
}
