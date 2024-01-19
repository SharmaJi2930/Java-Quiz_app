package com.quiz.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_quizzes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int scoreAcheived;
    
    @Column(nullable = false)
    private int scoreOutOf;

    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

	public UserQuiz(int scoreAchieved, int scoreOutOf, LocalDateTime timestamp, User user) {
		super();
		this.scoreAcheived = scoreAchieved;
		this.scoreOutOf = scoreOutOf;
		this.timestamp = timestamp;
		this.user = user;
	}
    
    
}
