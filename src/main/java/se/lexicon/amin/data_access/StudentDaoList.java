package se.lexicon.amin.data_access;

import se.lexicon.amin.Student;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDaoList implements StudentDao {

    private static List<Student> students = new ArrayList<>();

    @Override
    public Student saveStudent(Student student) {

        if (!students.contains(student)) {

            List<String> listOfAllEmails = students.stream().map(stud -> stud.getEmail()).collect(Collectors.toList());
            String studentEmail = student.getEmail();

            if( !listOfAllEmails.contains(studentEmail) ) {
                students.add(student);
                return student;
            } else {
                return null;
            }

        }

        return null;
    }

    @Override
    public boolean deleteStudent(Student student) {
        return students.remove(student);
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

        return students
                .stream()
                .filter(student -> student.getName().equals(name))
                .collect(Collectors.toList());

    }



    @Override
    public List<Student> findAll() {
        return students;
    }


}
