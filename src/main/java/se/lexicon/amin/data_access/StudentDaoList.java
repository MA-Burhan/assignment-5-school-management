package se.lexicon.amin.data_access;

import se.lexicon.amin.Student;
import se.lexicon.amin.data_access.StudentDao;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoList implements StudentDao {

    private static List<Student> students;

    @Override
    public Student saveStudent(Student student) {

        if (!students.contains(student)) {
            students.add(student);
        return student;
        }

        return null;
    }

    @Override
    public Student findByEmail(String email) {
        for (Student student : students) {
            if(student.getEmail().equals(email)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> findByName(String name) {
        List<Student> studentList = new ArrayList<>();
        for (Student student : students) {
            if(student.getName().equals(name)) {
                studentList.add(student);
            }
        }

        if(!studentList.isEmpty()) {
            return studentList;
        } else {
            return null;
        }
    }

    @Override
    public Student findById(int id) {
        for(Student student : students) {
            if(student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public boolean deleteStudent(Student student) {
        return students.remove(student);
    }
}
