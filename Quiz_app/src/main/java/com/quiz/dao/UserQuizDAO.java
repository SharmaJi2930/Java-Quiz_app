package com.quiz.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.quiz.UserCRUD;
import org.hibernate.Session;

import com.quiz.entity.Answer;
import com.quiz.entity.Question;
import com.quiz.entity.User;
import com.quiz.entity.UserQuiz;

public class UserQuizDAO {

    private final Session session;

    Scanner sc = new Scanner(System.in);

    public UserQuizDAO(Session session) {
        super();
        this.session = session;
    }

    public void takeQuiz(User user, String category, String level) {
        session.beginTransaction();

        int score = 0;
        int countOfQuestions = 0;

        List<Question> questions = session.createQuery("from Question where difficultyLevel = :lev and " + "category = :cat", Question.class).setParameter("lev", level).setParameter("cat", category).getResultList();

        for (Question ques : questions) {
            countOfQuestions++;
            System.out.println("Question: " + ques.getText());

            List<Answer> answers = session.createQuery("from Answer where question = :q", Answer.class).setParameter("q", ques).getResultList();
            //print the answers
            for (Answer ans : answers) {
                System.out.println(ans.getOptionNo() + ") " + ans.getText());
            }

            boolean flag;
            int correctChoice = 0;
            //get the correct answer
            for (Answer ans : answers) {
                flag = ans.isCorrect();
                if (flag) {
                    correctChoice = ans.getOptionNo();
                    break;
                }
            }

            System.out.println("Choose the correct option: ");
            int ch = sc.nextInt();


            if (ch == correctChoice) {
                score++;
            }
        }
        try {
        UserQuiz result = session.createQuery("from UserQuiz where userId = :userId", UserQuiz.class).setParameter("userId", user.getId()).getSingleResult();

        if (result.getScoreAcheived() / result.getScoreOutOf() < score) {
            session.createQuery("DELETE FROM UserQuiz WHERE userId = :userId").setParameter("userId", user.getId()).executeUpdate();
            UserQuiz userQuiz = new UserQuiz(score, countOfQuestions, LocalDateTime.now(), user);
             session.save(userQuiz);
        }
}catch(Exception e) {
            	UserQuiz userQuiz = new UserQuiz(score, countOfQuestions, LocalDateTime.now(), user);
            	             session.save(userQuiz);
            	
}
           
        System.out.println("Score achieved: " + score + "/" + countOfQuestions);

        session.getTransaction().commit();
    }

    public void getScore(User user) {
        try {
            UserQuiz result = session.createQuery("from UserQuiz where userId = :userId", UserQuiz.class).setParameter("userId", user.getId()).getSingleResult();
            System.out.println("Score : " + result.getScoreAcheived() + "/" + result.getScoreOutOf());
        } catch (Exception e) {
        	
            System.out.println("No Past Record Found . Take a Quiz first.");
           
        }
    }
}