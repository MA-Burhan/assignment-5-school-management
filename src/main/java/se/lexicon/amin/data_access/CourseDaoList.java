package se.lexicon.amin.data_access;

import se.lexicon.amin.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseDaoList implements CourseDao {

    private static List<Course> courses = new ArrayList<>();


    @Override
    public Course saveCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            return course;
        }

        return null;
    }


    @Override
    public Course findById(int id) {

        for(Course course : courses) {
            if(course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    @Override
    public List<Course> findByName(String name) {

        return courses
                .stream()
                .filter(course -> course.getCourseName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> findByDate(LocalDate date) {

        return courses
                .stream()
                .filter(course -> course.getStartDate().equals(date))
                .collect(Collectors.toList());

    }

    @Override
    public List<Course> findAll() {
        return courses;
    }

    @Override
    public boolean removeCourse(Course course) {
        return courses.remove(course);
    }
}
