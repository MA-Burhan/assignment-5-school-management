package se.lexicon.amin;

import se.lexicon.amin.data_access.CourseDaoList;
import se.lexicon.amin.data_access.StudentDaoList;

import java.time.LocalDate;
import java.util.Scanner;


public class App 
{

    public static CourseDaoList courseList = new CourseDaoList();
    public static StudentDaoList studentList = new StudentDaoList();




    public static void main( String[] args )
    {


    }

    public static void printMeny(){
        System.out.println("1. Create new course\n" +
                "2. Create new student)");
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
}
