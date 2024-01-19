package com.quiz;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.quiz.dao.UserQuizDAO;
import com.quiz.entity.User;

public class Quiz {

    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    Session session = factory.openSession();
    Scanner sc = new Scanner(System.in);

    UserQuizDAO quizDao = new UserQuizDAO(session);

    public void takeQuiz(User user) {
        String cat;
        System.out.println("Enter category: ");
        List<String> distinctCategory = session.createQuery("select distinct category from Question").getResultList();
        int selection;
        do {
            int index = 0;
            for (String item : distinctCategory) {
                index++;
                System.out.println(index + ") " + item);
            }
            selection = sc.nextInt();
            if (selection > distinctCategory.size())
                System.out.println("Please Enter Valid Choice");
        } while (selection > distinctCategory.size());
        cat = distinctCategory.get(selection - 1);

        String level;

        do {
            System.out.println("Enter difficulty level: ");
            System.out.println("""
                    1) Easy\s
                    2) Medium\s
                    3) Hard""");

            int ch = sc.nextInt();
            level = switch (ch) {
                case 1 -> "easy";
                case 2 -> "medium";
                case 3 -> "hard";
                default -> "";
            };
            if (level.isEmpty())
                System.out.println("Please Enter Valid Choice");
        } while (level.isEmpty());

        quizDao.takeQuiz(user, cat, level);

    }

    public void getScore(User user) {
        quizDao.getScore(user);
    }
}
