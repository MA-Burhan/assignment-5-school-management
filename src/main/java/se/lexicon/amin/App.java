package se.lexicon.amin;

import se.lexicon.amin.data_access.CourseDaoList;
import se.lexicon.amin.data_access.StudentDaoList;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class App 
{

    public static CourseDaoList courseList = new CourseDaoList();
    public static StudentDaoList studentList = new StudentDaoList();




    public static void main( String[] args ) {




    }

    public static void printMeny(){
        System.out.println("1. Create new course\n" +
                "2. Create new student)\n3. Register Student to course.\n4. Unregister student from course\n5.List all students\n6.List all courses");
    }

    public static void createCourse() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Course name: ");
        String name = scanner.nextLine();

        System.out.print("Enter start year: ");
        int year = scanner.nextInt();

        System.out.print("Enter start month (1-12): ");
        int month = scanner.nextInt();

        System.out.print("Enter start day (1-31): ");
        int day = scanner.nextInt();

        System.out.print("Enter course duration (number of weeks):  ");
        int duration = scanner.nextInt();

        courseList.saveCourse(new Course(name, LocalDate.of(year, month, day), duration));

    }

    public static void createStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        studentList.saveStudent(new Student(name, email, address));

    }

    public static void registerStudent(Student student, Course course){

        course.register(student);
    }

    public static void unregisterStudent(Student student, Course course) {

        course.unregister(student);
    }

    //Finding Students and Courses in various ways
    public static Course findById(int id) {
        return courseList.findById(id);
    }

    public static List<Course> findByName(String name) {
        return courseList.findByName(name);
    }

    public static List<Course> findByDate(LocalDate date) {
        return courseList.findByDate(date);
    }


}
