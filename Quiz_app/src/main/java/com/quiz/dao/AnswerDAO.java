package com.quiz.dao;

import org.hibernate.Session;

import com.quiz.entity.Answer;

public class AnswerDAO {

	private Session session;

	public AnswerDAO(Session session) {
		super();
		this.session = session;
	}

	public void saveAnswer(Answer ans) {
		try {

			session.beginTransaction();

			session.save(ans);

			session.getTransaction().commit();

			System.out.println("Answer saved successfully!!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
