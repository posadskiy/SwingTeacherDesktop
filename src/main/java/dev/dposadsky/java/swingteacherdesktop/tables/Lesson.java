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
@Table(name = "lesson")
public class Lesson implements Serializable {
    
    private static final long serialVersionUID = -5527568548002296042L;
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
 
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "lesson_number")
    private Integer lessonNumber;
    
    @Column(name = "lesson_name")
    private String lessonName;
    
    @Column(name = "id_task_category")
    private Integer idTaskCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(Integer lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Integer getIdTaskCategory() {
        return idTaskCategory;
    }

    public void setIdTaskCategory(Integer idTaskCategory) {
        this.idTaskCategory = idTaskCategory;
    }
   
    @Override
    public String toString() {
        return this.lessonName;
    }
    
}
