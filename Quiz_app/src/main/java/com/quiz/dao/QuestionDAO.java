package com.quiz.dao;

import java.util.List;

import org.hibernate.Session;

import com.quiz.entity.Question;

public class QuestionDAO {

	private Session session;

	public QuestionDAO(Session session) {
		super();
		this.session = session;
	}
	
	public void saveQuestion(Question question)
	{
		try {
			
			session.beginTransaction();
			
			session.save(question);
			
			session.getTransaction().commit();
			
			System.out.println("Question saved successfully!!");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Question getQuestionById(Long quesId)
	{
		return session.get(Question.class, quesId);
	}
	
	public List<Question> getQuestions(String category, String level)
	{
		return session.createQuery("from Question where difficultyLevel = :lev and "
				+ "category = :cat", Question.class)
		.setParameter("lev", level)
		.setParameter("cat", category)
		.getResultList();
	}
}
