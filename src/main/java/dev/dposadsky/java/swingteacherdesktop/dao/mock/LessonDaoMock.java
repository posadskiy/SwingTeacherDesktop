package dev.dposadsky.java.swingteacherdesktop.dao.mock;

import dev.dposadsky.java.swingteacherdesktop.commons.mock.values.TaskCategoryMockValues;
import dev.dposadsky.java.swingteacherdesktop.dao.LessonDao;
import dev.dposadsky.java.swingteacherdesktop.tables.Lesson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dev.dposadsky.java.swingteacherdesktop.commons.mock.values.LessonMockValues.*;

public class LessonDaoMock implements LessonDao {
    @Override
    public void addLesson(Lesson lesson) throws SQLException {

    }

    @Override
    public void deleteLesson(Lesson lesson) throws SQLException {

    }

    @Override
    public void deleteLesson(int id) throws SQLException {

    }

    @Override
    public Lesson getLesson(int id) throws SQLException {
        Lesson lesson = new Lesson();
        lesson.setId(ID);
        lesson.setIdTaskCategory(TaskCategoryMockValues.ID);
        lesson.setLessonName(LESSON_NAME);
        lesson.setLessonNumber(LESSON_NUMBER);
        return lesson;
    }

    @Override
    public List<Lesson> getAllLessons() throws SQLException {
        List<Lesson> lessons = new ArrayList<>();
        Lesson lesson = getLesson(ID);
        lessons.add(lesson);
        return lessons;
    }

    @Override
    public List<Lesson> getLessonsByCategory(int category) throws SQLException {
        return getAllLessons();
    }
}
