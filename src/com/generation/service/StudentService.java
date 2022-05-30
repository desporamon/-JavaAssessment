package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.*;

public class StudentService
{
    private final Map<String, Student> students = new HashMap<>();

    public void subscribeStudent( Student student )
    {
        students.put(student.getId(), student);
    }

    public Student findStudent( String studentId )
    {
        if(students.containsKey(studentId))
        {
            return students.get(studentId);
        }
        return null;
    }

    public void showSummary() {
       for (String studentId : students.keySet()) {
           //instance object of current student
           Student enrollingStudent = students.get(studentId);
           //Access enrolled course and enrolled course grade hashmap
           Map<String, Course> enrolledCourses = enrollingStudent.getEnrolledCourses();
           Map<String, Double> enrolledCoursesGrade = enrollingStudent.enrolledCourseGrade;
           // if HashMap empty, no course print
           if (enrolledCourses.size() == 0) {
               System.out.println("No Enrolled Course yet");
           } else {
               //print enrolled course with corresponding grade
               System.out.println("Enrolled Course");
               //print out map contents by looping through all the keys in the map using key set method
               for (String courseId : enrolledCourses.keySet())
                   System.out.println(enrolledCourses.get(courseId) + " Grade : " + enrolledCoursesGrade.get(courseId));
           }//end of for loop for enrolledCourses + Grade HashMap
       }//end of for loop for students HashMap
    }//End of show summary method

    public void enrollToCourse( String studentId, Course course )
    {
        //if student exists
        if(students.containsKey(studentId))
        {
            //Retrieve an object (student) instance by studentID key from student ID (student ID, student object)
            Student enrollingstudent = this.students.get(studentId);
            enrollingstudent.enrollToCourse(course);
        }
    }//End of enrollToCourse method
}
