package com.generation.java;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.Map;
import java.util.Scanner;

public class Main
{
    public static void main( String[] args )
            throws ParseException
    {
        //create new instance of studentService and courseService
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();

        //get user input
        Scanner scanner = new Scanner( System.in );
        int option;
        do
        {
            //call showMainMenu method to display menu. Update the option variable with user input
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch ( option )
            {
                //calls the register student method, with studentService object and scanner input as arguments
                case 1:
                    registerStudent( studentService, scanner );
                    break;
                case 2:
                    findStudent( studentService, scanner );
                    break;
                case 3:
                    gradeStudent( studentService, scanner );
                    break;
                case 4:
                    enrollCourse( studentService, courseService, scanner );
                    break;
                case 5:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 6:
                    showCoursesSummary( courseService, scanner );
                    break;
                case 7:
                    showPassedCourses( studentService, scanner );
                    break;
            }
        }
        while ( option != 8 );
    }

    //Option 4: declare enrollCourse method which takes in 3 values
    private static void enrollCourse( StudentService studentService, CourseService courseService,
                                               Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        //instantiate StudentService (student) & retrieve specific student object with studentId
        Student student = studentService.findStudent( studentId );
        if ( student == null ) //student object doesn't exist
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println( student );
        System.out.println( "Insert course ID" );
        String courseId = scanner.next();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }
        System.out.println( course );
        courseService.enrollStudent(courseId, student);

        studentService.enrollToCourse( studentId, course );
        System.out.println( "Student with ID: " + studentId + " enrolled successfully to " + courseId );

    }


    //Option 6: Show Courses Summary
    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }

    //Option 5: Show Students Summary
    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
        studentService.showSummary();
    }

    //Option 3: Grade Student
    private static void gradeStudent( StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        //returning Student Object
        Student enrollingStudent = studentService.findStudent(studentId);
        if(enrollingStudent== null){
            System.out.println("Student ID not found");
        } else {
            //HashMap of enrolled course
            Map<String, Course> enrolledCourses = enrollingStudent.getEnrolledCourses();
            if (enrolledCourses.size() == 0) {
                // if no course enrolled
                System.out.println("Please enroll to a course before grading");
            } else { // grade

                //display course enrolled
                for (String courseId : enrollingStudent.enrolledCourse.keySet()) {
                    System.out.println(enrollingStudent.enrolledCourse.get(courseId));
                }

                //Get input from user to give you the Course ID
                System.out.println("Enter Course code to be graded: ");
                String courseId = scanner.next();

                if (enrolledCourses.containsKey(courseId)) {

                    System.out.println("Enter grade to the course: ");
                    double grade = scanner.nextDouble();

                    if(grade>=1 && grade<=6 ){

                        enrollingStudent.setGrade(courseId, grade);

                        System.out.println("grade assigned");
                    }
                    else {	System.out.println("grade out of range, input grade between 1-6");
                    }
                } else {
                    System.out.println("Course ID is not in your enrolled course");
                }
            }
        }
    }


    //Option 2: Find Student
    private static void findStudent( StudentService studentService, Scanner scanner )
    {
        //when user selects option 2, findStudent method is called.
        //1. Creates a student object and assigns it the output of getStudentInformation method
        Student student = getStudentInformation( studentService, scanner );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );
        }
    }

    //Option 1: register student
    private static void registerStudent( StudentService studentService, Scanner scanner ) throws ParseException {
       Student student = PrinterHelper.createStudentMenu( scanner );
       studentService.subscribeStudent( student );
    }

    private static Student getStudentInformation( StudentService studentService, Scanner scanner )
    {
        //when this method is called, programme will prompt user for ID and store it in studentID String
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        //create a student object and assign it the output of findStudent method
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Student not found" );
        }
        return student;
    }

    //Option 7: Show Passed Courses
    private static void showPassedCourses(StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();

        //returning Student Object
        Student student = studentService.findStudent(studentId);
        if(student == null)
        {
            System.out.println("Student ID is not found ");
        } else {
            student.findPassedCourses();
        }
    }
}
