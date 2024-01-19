package com.quiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "answers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean isCorrect;
    
    @Column(nullable = false)
    private int optionNo;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

	public Answer(String text, boolean isCorrect, int optionNo, Question question) {
		super();
		this.text = text;
		this.isCorrect = isCorrect;
		this.optionNo = optionNo;
		this.question = question;
	}
}
