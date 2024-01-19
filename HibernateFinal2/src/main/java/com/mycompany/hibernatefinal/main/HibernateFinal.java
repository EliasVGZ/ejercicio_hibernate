/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hibernatefinal.main;
import com.mycompany.hibernatefinal.util.HibernateUtil;
import com.mycompany.hibernatefinal.model.Employee;
import org.hibernate.Session;
import java.util.Date;
/**
 *
 * @author a22alejandrofc
 */
public class HibernateFinal {

    public static void main(String[] args) {
        Employee emp = new Employee();
		emp.setName("Pankaj");
		emp.setRole("CEO");
		emp.setInsertTime(new Date());
		
		//Get Session
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//start transaction
		session.beginTransaction();
		//Save the Model object
		session.save(emp);
		//Commit transaction
		session.getTransaction().commit();
		System.out.println("Employee ID="+emp.getId());
		
		//terminate session factory, otherwise program won't end
		HibernateUtil.getSessionFactory().close();
    }
}
