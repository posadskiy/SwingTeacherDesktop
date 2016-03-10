/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.tables;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author DPosadsky
 */
@Entity
@Table(name = "task")
public class Task implements Serializable {
    
    private static final long serialVersionUID = -5527566248002296042L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
 
    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "id_lesson")
    private Integer idLesson;
    
    @Column(name = "task_number")
    private Integer taskNumber;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "question")
    private String question;
    
    @Column(name = "answer")
    private String answer;
    
    @Column(name = "imports")
    private String imports;
    
    @Column(name = "id_documentation")
    private Integer idDocumentation;
    
    @Column(name = "difficult")
    private Double difficult;
    
    @Column(name = "rating")
    private Double rating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

        public Integer getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(Integer idLesson) {
        this.idLesson = idLesson;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public Integer getIdDocumentation() {
        return idDocumentation;
    }

    public void setIdDocumentation(Integer idDocumentation) {
        this.idDocumentation = idDocumentation;
    }

    public Double getDifficult() {
        return difficult;
    }

    public void setDifficult(Double difficult) {
        this.difficult = difficult;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
    
    @Override
    public String toString() {
        return this.title;
    }

    public String getImports() {
        return imports;
    }

    public void setImports(String imports) {
        this.imports = imports;
    }
    
}
