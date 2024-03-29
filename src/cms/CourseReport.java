/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms;

/**
 * Class CourseReport represents the model of the report from the Inner joins between the table in MySQL
 * @author anaclaudiaproenca
 */
public class CourseReport {
    final String courseName;
    final String moduleName;
    final int numberOfStudents;
    final String lecturerName;
    final String roomName;
    
    /**
     * Constructor that initializes the CourseReport when the class is instantiate.
     * @param courseName
     * @param moduleName
     * @param numberOfStudents
     * @param lecturerName
     * @param roomName 
     */
    public CourseReport(String courseName, String moduleName, int numberOfStudents, String lecturerName, String roomName){
        this.courseName = courseName;
        this.moduleName = moduleName;
        this.numberOfStudents = numberOfStudents;
        this.lecturerName = lecturerName;
        this.roomName = roomName;
    }

    /**
     * method that returns the courseID
     * @return 
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * method that returns the moduleName
     * @return 
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * method that returns the numberOfStudents
     * @return 
     */
    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    /**\
     * method that returns the lecturerName
     * @return 
     */
    public String getLecturerName() {
        return lecturerName;
    }

    /**
     * method that returns the roomName
     * @return 
     */
    public String getRoomName() {
        return roomName;
    }
}