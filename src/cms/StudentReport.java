/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms;

/**
 * Class StudentReport represents the model of the report from the Inner joins between the table in MySQL
 * @author anaclaudiaproenca
 */
public class StudentReport {

    final String studentId;
    final String studentName;
    final String courseName;
    final String moduleName;
    final int grade;
    final String moduleCompleted;

    /**
     * Constructor that initializes the StudenReport when the class is instantiate.
     * @param studentId
     * @param studentName
     * @param courseName
     * @param moduleName
     * @param grade
     * @param moduleCompleted 
     */
    public StudentReport(String studentId, String studentName, String courseName, String moduleName, int grade, String moduleCompleted) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.moduleName = moduleName;
        this.grade = grade;
        this.moduleCompleted = moduleCompleted;
    }

    /**
     * method that returns the studentId
     * @return 
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * method that returns the studentName
     * @return 
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * method that returns the courseName
     * @return 
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * method that returns the module that the student has been enrolled
     * @return 
     */
    public String getModuleEnrolledIn() {
        return moduleName;
    }

    /**
     * method that returns the student's grade
     * @return 
     */
    public int getGrade() {
        return grade;
    }

    /**
     * method that returns the module that the student has completed
     * @return 
     */
    public String getModuleCompleted() {
        return moduleCompleted.equals("yes") ? moduleName : "";
    }
    
    /**
     * method that returns the module in which the student did not completed, due to the low score in the grade and that it will be required to re-do.
     * @return 
     */
     public String getModuleRepeat() {
        return moduleCompleted.equals("no") ? moduleName : "";
    }
}