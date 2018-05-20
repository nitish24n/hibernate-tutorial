package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create a session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 1;
			
			//now get a new session and start session
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//Retrieve student based on id : primary key
			System.out.println("\nGetting student with id : "+studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student...");
			
			myStudent.setFirstName("Scooby");
			
			//commit the transaction
			session.getTransaction().commit();
			
			// NEW CODE 
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update emial for all students
			System.out.println("Updating email for all students");
			
			session.createQuery("Update Student set email='foo@gmail.com'").executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
	}

}
