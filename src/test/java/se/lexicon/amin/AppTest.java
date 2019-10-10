package se.lexicon.amin;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.lexicon.amin.data_access.CourseDao;
import se.lexicon.amin.data_access.CourseDaoList;
import se.lexicon.amin.data_access.StudentDaoList;

import java.time.LocalDate;
import java.util.List;


public class AppTest {

    private CourseDaoList courseList;
    private StudentDaoList studentList;

    private Course testCourse1;
    private Course testCourse2;
    private Course testCourse3;
    private Course testCourse4;
    private Course testCourse5;

    private Student testStudent1;
    private Student testStudent2;
    private Student testStudent3;
    private Student testStudent4;
    private Student testStudent5;

    @Before
    public void setup() {
        courseList = new CourseDaoList();
        studentList = new StudentDaoList();

       testCourse1 = new Course("TestCourse1", LocalDate.of(2019, 10, 1), 21);
       testCourse2 = new Course("TestCourse2", LocalDate.of(2019, 10, 2), 22);
       testCourse3 = new Course("TestCourse3", LocalDate.of(2019, 10, 3), 23);
       testCourse4 = new Course("TestCourse4", LocalDate.of(2019, 10, 4), 24);
       testCourse5 = new Course("TestCourse5", LocalDate.of(2019, 10, 5), 25);

       courseList.saveCourse(testCourse1);
       courseList.saveCourse(testCourse2);
       courseList.saveCourse(testCourse3);
       courseList.saveCourse(testCourse4);
       courseList.saveCourse(testCourse5);

       testStudent1 = new Student("TestStudent1", "teststudent1@email.com", "teststudent1 address");
       testStudent2 = new Student("TestStudent2", "teststudent2@email.com", "teststudent2 address");
       testStudent3 = new Student("TestStudent3", "teststudent3@email.com", "teststudent3 address");
       testStudent4 = new Student("TestStudent4", "teststudent4@email.com", "teststudent4 address");
       testStudent5 = new Student("TestStudent5", "teststudent5@email.com", "teststudent5 address");

       studentList.saveStudent(testStudent1);
       studentList.saveStudent(testStudent2);
       studentList.saveStudent(testStudent3);
       studentList.saveStudent(testStudent4);
       studentList.saveStudent(testStudent5);

       testCourse1.register(testStudent1);
       testCourse2.register(testStudent2);
       testCourse3.register(testStudent3);
       testCourse4.register(testStudent4);
       testCourse5.register(testStudent5);
    }

    @After
    public void clean() {
        courseList.findAll().clear();
        studentList.findAll().clear();
    }



    //CourseDaoList

    @Test
    public void testSaveCourse() {

        Course testCourse6 = new Course("TestCourse6", LocalDate.of(2019, 10, 6), 26);

        assertFalse(courseList.findAll().contains(testCourse6));

        courseList.saveCourse(testCourse6);

        assertTrue(courseList.findAll().contains(testCourse6));

    }

    @Test
    public void testSaveCourseAlreadyInList() {

        assertEquals(5, courseList.findAll().size());
        assertNull(courseList.saveCourse(testCourse5));
        assertEquals(5, courseList.findAll().size());

    }


    @Test
    public void testRemoveCourse() {

        assertTrue(courseList.findAll().contains(testCourse1));

        courseList.removeCourse(testCourse1);

        assertFalse(courseList.findAll().contains(testCourse1));

    }

    @Test
    public void testRemoveOnlySpecifiedCourse() {

        courseList.removeCourse(testCourse1);

        //Remaining courses should still be in the list
        assertTrue(courseList.findAll().contains(testCourse2));
        assertTrue(courseList.findAll().contains(testCourse3));
        assertTrue(courseList.findAll().contains(testCourse4));
        assertTrue(courseList.findAll().contains(testCourse5));
    }


    @Test
    public void testFindCourseByExistingId(){

        assertEquals(testCourse1, courseList.findById(testCourse1.getId()));

    }

    @Test
    public void testFindCourseByNonExistingId(){

        assertNull(courseList.findById(23423));

    }

    @Test
    public void testFindCourseByExistingName(){

        //Create another Course with same name as testCourse5
        Course testCourse6 = new Course("TestCourse5", LocalDate.of(2019, 10, 26), 26);
        courseList.saveCourse(testCourse6);

        List<Course> listCourses = courseList.findByName("TestCourse5");

        assertEquals(2, listCourses.size());
        assertTrue(listCourses.contains(testCourse6));
        assertTrue(listCourses.contains(testCourse5));
        assertFalse(listCourses.contains(testCourse4));
    }

    @Test
    public void testFindCourseByNonExistingName() {

        assertTrue(courseList.findByName("blablabla").isEmpty());
    }

    @Test
    public void testFindCourseByExistingDate() {

        //Create another Course with same start date as testCourse1
        Course testCourse6 = new Course("TestCourse6", LocalDate.of(2019, 10, 1), 26);
        courseList.saveCourse(testCourse6);

        List<Course> listCourses = courseList.findByDate(LocalDate.of(2019, 10, 1));

        assertEquals(2, listCourses.size());
        assertTrue(listCourses.contains(testCourse6));
        assertTrue(listCourses.contains(testCourse1));
        assertFalse(listCourses.contains(testCourse3));

    }

    @Test
    public void testFindCourseByNonExistingDate() {

        assertTrue(courseList.findByDate(LocalDate.of(2999, 1, 1)).isEmpty());
    }

    @Test
    public void testFindAllCoursesMethod() {

        assertEquals(5, courseList.findAll().size());
        assertTrue(courseList.findAll().contains(testCourse1));
        assertTrue(courseList.findAll().contains(testCourse2));
        assertTrue(courseList.findAll().contains(testCourse3));
        assertTrue(courseList.findAll().contains(testCourse4));
        assertTrue(courseList.findAll().contains(testCourse5));
    }






    //StudentDaoList

    @Test
    public void testSaveStudent() {

        Student testStudent6 = new Student("TestStudent6", "teststudent6@email.com", "teststudent6 address");

        assertFalse(studentList.findAll().contains(testStudent6));

        studentList.saveStudent(testStudent6);

        assertTrue(studentList.findAll().contains(testStudent6));

    }

    @Test
    public void testSaveStudentAlreadyInList() {

        assertEquals(5, studentList.findAll().size());
        assertNull(studentList.saveStudent(testStudent5));
        assertEquals(5, studentList.findAll().size());

    }

    @Test
    public void testSaveStudentWithAlreadyExistingEmail() {

        assertEquals(5, studentList.findAll().size());

        //Create another Student with same email as testStudent1
        Student testStudent6 = new Student("TestStudent6", "teststudent1@email.com", "teststudent6 address");

        assertNull(studentList.saveStudent(testStudent6));
        assertEquals(5, studentList.findAll().size());
    }

    @Test
    public void testRemoveStudent() {

        assertTrue(studentList.findAll().contains(testStudent1));

        studentList.deleteStudent(testStudent1);

        assertFalse(studentList.findAll().contains(testStudent1));

    }

    @Test
    public void testRemoveOnlySpecifiedStudent() {


        studentList.deleteStudent(testStudent1);

        ////Remaining students should still be in the list
        assertTrue(studentList.findAll().contains(testStudent2));
        assertTrue(studentList.findAll().contains(testStudent3));
        assertTrue(studentList.findAll().contains(testStudent4));
        assertTrue(studentList.findAll().contains(testStudent5));

    }

    @Test
    public void testFindStudentByExistingId(){

        assertEquals(testStudent1, studentList.findById(testStudent1.getId()));

    }

    @Test
    public void testFindStudentByNonExistingId(){

        assertNull(studentList.findById(23423));

    }

    @Test
    public void testFindStudentByExistingEmail(){

        assertEquals(testStudent1, studentList.findByEmail(testStudent1.getEmail()));

    }

    @Test
    public void testFindStudentByNonExistingEmail(){

        assertNull(studentList.findByEmail("blablabla@mail.com"));

    }

    @Test
    public void testFindStudentByExistingName(){

        //Create another Student with same name as testStudent1
        Student testStudent6 =  new Student("TestStudent1", "teststudent6@email.com", "teststudent6 address");
        studentList.saveStudent(testStudent6);

        List<Student> listStudent = studentList.findByName("TestStudent1");

        assertEquals(2, listStudent.size());
        assertTrue(listStudent.contains(testStudent1));
        assertTrue(listStudent.contains(testStudent6));
        assertFalse(listStudent.contains(testStudent2));

    }

    @Test
    public void testFindStudentByNonExistingName() {

        assertTrue(studentList.findByName("blablabla").isEmpty());
    }

    @Test
    public void testFindAllStudentsMethod() {

        assertEquals(5, studentList.findAll().size());
        assertTrue(studentList.findAll().contains(testStudent1));
        assertTrue(studentList.findAll().contains(testStudent2));
        assertTrue(studentList.findAll().contains(testStudent3));
        assertTrue(studentList.findAll().contains(testStudent4));
        assertTrue(studentList.findAll().contains(testStudent5));

    }


    //Course

    @Test
    public void testRegisterStudentToCourse() {

        assertFalse(testCourse1.getStudents().contains(testStudent2));

        testCourse1.register(testStudent2);

        assertTrue(testCourse1.getStudents().contains(testStudent2));
    }

    @Test
    public void testUnregisterStudentFromCourse() {

        assertTrue(testCourse1.getStudents().contains(testStudent1));

        testCourse1.unregister(testStudent1);

        assertFalse(testCourse1.getStudents().contains(testStudent1));
    }

    @Test
    public void testRegisterStudentAlreadyRegisteredToCourse() {

        assertEquals(1, testCourse1.getStudents().size());

        testCourse1.register(testStudent1);

        assertEquals(1, testCourse1.getStudents().size());

    }

    @Test
    public void testSetCourseName(){

        assertEquals("TestCourse1", testCourse1.getCourseName());

        testCourse1.setCourseName("Biologi A");

        assertEquals("Biologi A", testCourse1.getCourseName());

    }


    @Test
    public void testSetCourseStartDate(){

        assertEquals( LocalDate.of(2019, 10, 2), testCourse2.getStartDate());

        testCourse2.setStartDate(LocalDate.of(2020, 1, 1));

        assertEquals(LocalDate.of(2020, 1, 1), testCourse2.getStartDate());

    }

    @Test
    public void testSetCourseDuration(){

        assertEquals(23, testCourse3.getWeekDuration());

        testCourse3.setWeekDuration(33);

        assertEquals(33, testCourse3.getWeekDuration());

    }



    //Student

    @Test
    public void testSetStudentName(){

        assertEquals("TestStudent1", testStudent1.getName());

        testStudent1.setName("Amin");

        assertEquals("Amin", testStudent1.getName());

    }

    @Test
    public void testSetStudentEmail(){

        assertEquals("teststudent2@email.com", testStudent2.getEmail());

        testStudent2.setEmail("amin@email.com");

        assertEquals("amin@email.com", testStudent2.getEmail());


    }

    @Test
    public void testSetStudentAddress(){

        assertEquals("teststudent3 address", testStudent3.getAddress());

        testStudent3.setAddress("amin address");

        assertEquals("amin address", testStudent3.getAddress());

    }


}
