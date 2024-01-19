package com.quiz;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.quiz.dao.AnswerDAO;
import com.quiz.dao.QuestionDAO;
import com.quiz.entity.Answer;
import com.quiz.entity.Question;

public class QuestionCRUD {

	
	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	Session session = factory.openSession();
	Scanner sc = new Scanner(System.in);
	
	QuestionDAO quesDao = new QuestionDAO(session);
	AnswerDAO ansDao = new AnswerDAO(session);
	
	public void saveQuestion()
	{
		String level = null;
		sc.nextLine();
		System.out.println("Enter the question: ");
		String text = sc.nextLine();
		
		System.out.println("Choose difficulty level: ");
		System.out.println("1) Easy \n2) Medium \n3) Hard");
		int ch = sc.nextInt();
		
		if(ch==1)
		{
			level = "Easy";
		}
		else if(ch==2)
		{
			level = "Medium";
		}
		else if(ch==3)
		{
			level = "Hard";
		}
		
		sc.nextLine();
		System.out.println("Enter category:");
		String cate = sc.nextLine();
		
		Question ques = new Question(text, level, cate);
		
		quesDao.saveQuestion(ques);
		
		System.out.println("Question Id for future reference when saving answer:");
		System.out.println(ques.getId());
	}
	
	
	public void saveAnswer()
	{
		boolean isCorrect = false;
		sc.nextLine();
		System.out.println("Enter answer: ");
		String text = sc.nextLine();
		
		System.out.println("Is correct?");
		System.out.println("1) Yes \n2) No");
		int ch = sc.nextInt();
		
		if(ch==1)
		{
			isCorrect = true;
		}

		System.out.println("Enter option no: ");
		int optionNo = sc.nextInt();
		
		System.out.println("Enter question id: ");
		Long id = sc.nextLong();
		
		Question question = quesDao.getQuestionById(id);
		
		Answer answer = new Answer(text, isCorrect, optionNo, question);
		
		ansDao.saveAnswer(answer);
	}
}
