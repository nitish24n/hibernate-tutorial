package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create a session
		Session session = factory.getCurrentSession();
		
		try {
						
			// start a transaction
			session.beginTransaction();
			
			//query the students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
	  		//display the students
			displayStudents(theStudents);
			
			//query students : last name = 'Doe'
			
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(theStudents);
			
			//query students : last name = 'Doe' or first name = 'Donald'
			
			theStudents = 
					session.createQuery("from Student s where"
							+" s.firstName='Donald' or s.lastName='Doe'").getResultList();
			System.out.println("\n\nStudents who have first name Donald or last name Doe");
			displayStudents(theStudents);
			
			//query students where emil LIKE '%Do'
			theStudents = session.createQuery("from Student where email LIKE '%gmail.com'").getResultList();
			System.out.println("\n\nStudents who have 'Do' in email");
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
