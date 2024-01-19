package com.quiz;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.quiz.dao.UserDAO;
import com.quiz.entity.User;

public class UserCRUD {

	SessionFactory factory = new Configuration().configure().buildSessionFactory();
	Session session = factory.openSession();
	Scanner sc = new Scanner(System.in);
	
	UserDAO userDao = new UserDAO(session);
	QuestionCRUD quesCrud = new QuestionCRUD();
	Quiz quiz = new Quiz();
	
	public void createUser()
	{
		String role= null;
		
		System.out.println("Enter User Name: ");
		String userName = sc.nextLine();
		
		System.out.println("Enter password: ");
		String password = sc.next();
		
		System.out.println("Enter email: ");
		String email = sc.next();
		
		System.out.println("What type of account?");
		System.out.println("1) Teacher \n2) Student");
		int ch = sc.nextInt();
		
		if(ch==1)
		{
			role = "Teacher";
		}
		else if(ch==2)
		{
			role = "Student";
		}
		
		User user = new User(userName, password, email, role);
		
		userDao.saveUser(user);	
			
	}
	
	public void logIn()
	{
		System.out.println("Enter email: ");
		String email = sc.next();
		
		System.out.println("Enter password: ");
		String password = sc.next();
		
		User user = userDao.logIn(email, password);
		
		if(user!=null)
		{
			if(user.getRole().equalsIgnoreCase("Teacher"))
			{
				teacherMenu();
			}
			else if(user.getRole().equalsIgnoreCase("Student"))
			{
				studentMenu(user);
			}
		}
		else
		{
			System.out.println("Wrong email or password!!");
		}
	}
	
	public void teacherMenu()
	{
		int ch;
		do {
			System.out.println("Teacher Menu");
			System.out.println("""
                    1) Add a question\s
                    2) Add answers to the question\s
                    6) Log Out""");
			ch = sc.nextInt();
			
			switch(ch)
			{
			case 1:
				quesCrud.saveQuestion();
				break;
				
			case 2:
				quesCrud.saveAnswer();
				break;
			}
		}
		while(ch!=6);
	}
	
	public void studentMenu(User user)
	{
		int ch;
		do {
			System.out.println("Teacher Menu");
			System.out.println("""
                    1) Take Quiz\s
                    2) Score Card\s
                    6) Log Out""");
			ch = sc.nextInt();
			
			switch(ch)
			{
			case 1:
				quiz.takeQuiz(user);
				break;
				
			case 2:
				quiz.getScore(user);
				break;
			}
		}
		while(ch!=6);
	}
	
}
