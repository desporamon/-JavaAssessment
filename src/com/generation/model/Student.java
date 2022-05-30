package com.generation.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//student (subclass - child): the class that inherits from another class
//person (superclass - parent): the class being inherited from
//For student class to access the interface method evaluation -> student class 'implements' eveluation
public class Student
    extends Person
    implements Evaluation

{
    //FIELD ATTRIBUTES
    float PASS_MIN_GRADE = 3.0f;
    private double grade;

    //key is the course ID, value is the enrolled course object
    public final Map<String, Course> enrolledCourse = new HashMap<>();

    //key is the course ID, value is the course grade
    public final Map<String, Double> enrolledCourseGrade = new HashMap<>();

    //key is the course ID, value is the passed course object
    public final Map<String, Course> passedCourse = new HashMap<>();


    //Constructor
    public Student(String id, String name, String email, Date birthDate)
    {
        super(id, name, email, birthDate);
    }


    public void enrollToCourse(Course course)
    {
        //TODO - define grade variable by assigning default grade (0) into the enrolledCourseGrade HashMap
        double grade = 0.0;
        //add the course to the enrolledCourse Hashmap
        //use .put to store & insert each one of those references a key and value pair into the map object
        enrolledCourse.put(course.getCode(), course);
        //add the grade to the enrolledCourseGrade Hashmap
        enrolledCourseGrade.put(course.getCode(), grade);
    }

    //getter and setter grade
    //get and set the individual course grade from the enrolledCourseGrade Hashmap of each stutn instance

    public double getGrade(String courseID)
    {
        double grade = 0.0;
        if (enrolledCourseGrade.containsKey(courseID)) {
            grade = enrolledCourseGrade.get(courseID);
        }
        return grade;
    }

    public void setGrade(String courseID, double grade)
    {
        enrolledCourseGrade.put(courseID, grade);
    }


    public void findPassedCourses() {
        if (enrolledCourseGrade.size() == 0) {
            System.out.println("There are no courses graded yet");
        } else {
            //for loop by looping through all the keys (course ID) in the enrolledCourseGrade Hashmap that has been graded
            for (String courseId : enrolledCourseGrade.keySet()) {
                double courseGrade = getGrade(courseId);
                //if above enrolled course is passed, add to passedCourse HashMap
                if (courseGrade >= PASS_MIN_GRADE) {
                    passedCourse.put(courseId, findCourseById(courseId));
                }
            }//end of for loop
            if (passedCourse.size() == 0) {
                System.out.println("There are no courses passed yet");
            } else {
                System.out.println("You have passed the following courses as follows: ");
                for (String Key : passedCourse.keySet()) {
                    Course course = passedCourse.get(Key);
                    System.out.println(course);
                }//end of for loop to print out passed courses
            }
        }
    }

    public Course findCourseById(String courseId) {
        return enrolledCourse.get(courseId);
    }

    @Override
    public Map<String, Course> getEnrolledCourses()
    {
        return this.enrolledCourse;
    }

    @Override
    public String toString()
    {
        return "Student {" + super.toString() + "}";
    }
}


