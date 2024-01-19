package com.quiz.dao;

import org.hibernate.Session;

import com.quiz.entity.User;

public class UserDAO {
	
	private Session session;

	public UserDAO(Session session) {
		super();
		this.session = session;
	}
	
	public void saveUser(User user)
	{
		try {
			
			session.beginTransaction();
			
			session.save(user);
			
			session.getTransaction().commit();
			
			System.out.println("Account created successfully!!");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public User logIn(String email, String password)
	{
		User user = session.createQuery("from User where email= :em and password = :p ", User.class)
		.setParameter("em", email)
		.setParameter("p", password)
		.getSingleResult();
		
		return user;
	}

}
