package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create session factory
				SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
				
				// create a session
				Session session = factory.getCurrentSession();
				
				try {
					// Create 3 Student objects
					System.out.println("Create 3 Student objects");
					Student tempStudent1 = new Student("Divya","Kaul","Divaya@gmail.com");
					Student tempStudent2 = new Student("John","Doe","JohnDoe@gmail.com");
					Student tempStudent3 = new Student("Karan","Prakash","KPrakash@gmail.com");
					
					// start a transaction
					session.beginTransaction();
					
					// save the student object
					System.out.println("Saving the student ...");
					session.save(tempStudent1);
					session.save(tempStudent2);
					session.save(tempStudent3);
					
					// commit transaction
					session.getTransaction().commit();
					
					System.out.println("Done!");
				}
				finally {
					factory.close();
				}
				
			}

}
