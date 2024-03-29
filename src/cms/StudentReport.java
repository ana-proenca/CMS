/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms;

/**
 *
 * @author anaclaudiaproenca
 */
public class StudentReport {

    private String studentId;
    private String studentName;
    private String courseName;
    private String moduleName;
    private int grade;
    private String moduleCompleted;

    public StudentReport(String studentId, String studentName, String courseName, String moduleName, int grade, String moduleCompleted) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.moduleName = moduleName;
        this.grade = grade;
        this.moduleCompleted = moduleCompleted;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getModuleEnrolledIn() {
        return moduleName;
    }

    public int getGrade() {
        return grade;
    }

    public String getModuleCompleted() {
        return moduleCompleted.equals("yes") ? moduleName : "";
    }
    
     public String getModuleRepeat() {
        return moduleCompleted.equals("no") ? moduleName : "";
    }
}