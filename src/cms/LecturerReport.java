/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms;

/**
 *
 * @author anaclaudiaproenca
 */
public class LecturerReport {
    private String lecturerName;
    private String lecturerSurname;
    private String lecturerRole;
    private String moduleName;
    private String moduleDateStarted;
    private int numberOfStudents;
    private String moduleTypeClass;
    
    public LecturerReport(String lecturerName, String lecturerSurname, String lecturerRole, String moduleName, String moduleDateStarted, int numberOfStudents, String moduleTypeClass){
        this.lecturerName = lecturerName;
        this.lecturerSurname = lecturerSurname;
        this.lecturerRole = lecturerRole;
        this.moduleName = moduleName;
        this.moduleDateStarted = moduleDateStarted;
        this.numberOfStudents = numberOfStudents;
        this.moduleTypeClass = moduleTypeClass;
    }

    public String getLecturerName() {
        return lecturerName + " " + lecturerSurname;
    }

    public String getLecturerRole() {
        return lecturerRole;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleDateStarted() {
        return moduleDateStarted;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getModuleTypeClass() {
        return moduleTypeClass;
    }
}
