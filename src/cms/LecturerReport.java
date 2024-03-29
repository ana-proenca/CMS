/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms;

/**
 * Class LecturerReport represents the model of the report from the Inner joins between the table in MySQL
 * @author anaclaudiaproenca
 */
public class LecturerReport {
    final String lecturerName;
    final String lecturerRole;
    final String moduleName;
    final String moduleDateStarted;
    final int numberOfStudents;
    final String moduleTypeClass;
    
    /**
     * Constructor that initializes the LecturerReport when the class is instantiate.
     * @param lecturerName
     * @param lecturerRole
     * @param moduleName
     * @param moduleDateStarted
     * @param numberOfStudents
     * @param moduleTypeClass 
     */
    public LecturerReport(String lecturerName, String lecturerRole, String moduleName, String moduleDateStarted, int numberOfStudents, String moduleTypeClass){
        this.lecturerName = lecturerName;
        this.lecturerRole = lecturerRole;
        this.moduleName = moduleName;
        this.moduleDateStarted = moduleDateStarted;
        this.numberOfStudents = numberOfStudents;
        this.moduleTypeClass = moduleTypeClass;
    }

    /**
     * method that returns the lecturerName
     * @return 
     */
    public String getLecturerName() {
        return lecturerName;
    }

    /**
     * method that returns the lecturerRole
     * @return 
     */
    public String getLecturerRole() {
        return lecturerRole;
    }

    /**
     * method that returns the moduleName
     * @return 
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * method that returns the moduleDateStarted
     * @return 
     */
    public String getModuleDateStarted() {
        return moduleDateStarted;
    }

    /**
     * method that returns the numberofStudents
     * @return 
     */
    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    /**
     * method that returns the moduleTypeClass
     * @return 
     */
    public String getModuleTypeClass() {
        return moduleTypeClass;
    }
}
