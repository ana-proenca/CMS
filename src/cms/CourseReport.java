/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms;

/**
 *
 * @author anaclaudiaproenca
 */
public class CourseReport {
    private String courseName;
    private String moduleName;
    private int numberOfStudents;
    private String lecturerName;
    private String roomName;
    
    public CourseReport(String courseName, String moduleName, int numberOfStudents, String lecturerName, String roomName){
        this.courseName = courseName;
        this.moduleName = moduleName;
        this.numberOfStudents = numberOfStudents;
        this.lecturerName = lecturerName;
        this.roomName = roomName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public String getRoomName() {
        return roomName;
    }
}