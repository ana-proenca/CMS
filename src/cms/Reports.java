/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author anaclaudiaproenca
 */
public class Reports {

    private DBConnector dbConnector;
    private String fileFormat;
    private boolean fileOutput;

    public Reports(Scanner sc) {
        dbConnector = new DBConnector();

        System.out.println("1-Export file");
        System.out.println("2-Print output console");
        int type = Integer.parseInt(sc.next());

        switch (type) {
            case 1:
                fileOutput = true;
                System.out.println("Inform the report file format you want to save (csv or txt)");
                fileFormat = sc.next();
                break;
            case 2:
                fileOutput = false;
                break;
            default:
                System.out.println("Invalid option");
                throw new AssertionError();
        }
    }

    public void generateAllReports() throws Exception {
        generateCourseReport();
        generateStudentReport();
        generateLectureReport();
    }

    public void generateLectureReport() throws Exception {
        System.out.println("\nGenerating Lecturer Report");
        ArrayList<LecturerReport> lecturerReportList = dbConnector.getLecturerReport();

        if (fileOutput) {
            saveLecturerReport(lecturerReportList, fileFormat);
        } else {
            for (LecturerReport lecturerReport : lecturerReportList) {
                System.out.println(lecturerReport.getLecturerName() + ", " + lecturerReport.getLecturerRole() + ", " + lecturerReport.getModuleName() + ", " + lecturerReport.getModuleDateStarted() + ", " + lecturerReport.getModuleTypeClass() + ", " + lecturerReport.getNumberOfStudents());
            }
        }
    }

    public void generateStudentReport() throws Exception {
        System.out.println("\nGenerating Student Report");
        ArrayList<StudentReport> studentReportList = dbConnector.getStudentReport();

        if (fileOutput) {
            saveStudentReportFile(studentReportList, fileFormat);
        } else {
            for (StudentReport studentReport : studentReportList) {
                System.out.println(studentReport.getStudentName() + ", " + studentReport.getStudentId() + ", " + studentReport.getCourseName() + ", " + studentReport.getModuleEnrolledIn() + ", " + studentReport.getModuleCompleted() + ", " + studentReport.getGrade() + ", " + studentReport.getModuleRepeat());
            }
        }
    }

    public void generateCourseReport() throws Exception {
        System.out.println("\nGenerating Course Report");
        ArrayList<CourseReport> courseReportList = dbConnector.getCourseReport();

        if (fileOutput) {
            saveCourseReportFile(courseReportList, fileFormat);
        } else {
            for (CourseReport courseReport : courseReportList) {
                System.out.println(courseReport.getCourseName() + ", " + courseReport.getModuleName() + ", " + courseReport.getNumberOfStudents() + ", " + courseReport.getLecturerName() + ", " + courseReport.getRoomName());
            }
        }
    }

    private void saveLecturerReport(ArrayList<LecturerReport> lecturerReportList, String fileFormat) {
        String outputFile = "lecturer_report." + fileFormat;

        // Delimiter used in CSV file
        String delimiter = ",";

        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(outputFile, true));

            //Create the header of the file
            br.write("Lecturer Name" + delimiter);
            br.write("Role" + delimiter);
            br.write("Module" + delimiter);
            br.write("Number of Students" + delimiter); // need to add grade
            br.write("Type class" + delimiter); // need the module
            br.newLine();

            for (LecturerReport lecturerReport : lecturerReportList) {
                //Save each row of the report
                br.write(lecturerReport.getLecturerName() + delimiter);
                br.write(lecturerReport.getLecturerRole() + delimiter);
                br.write(lecturerReport.getModuleName() + delimiter);
                br.write(lecturerReport.getNumberOfStudents() + delimiter);
                br.write(lecturerReport.getModuleTypeClass() + delimiter);
                br.newLine(); // Write a new line after each row
            }

            br.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void saveCourseReportFile(ArrayList<CourseReport> courseReportList, String fileFormat) {
        String outputFile = "course_report." + fileFormat;

        // Delimiter used in CSV file
        String delimiter = ",";

        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(outputFile, true));

            //Create the header of the file
            br.write("Module Name" + delimiter);
            br.write("Course Name" + delimiter);
            br.write("Number of Students" + delimiter);
            br.write("Lecturer Name" + delimiter);
            br.write("Room Name" + delimiter);
            br.newLine();

            for (CourseReport courseReport : courseReportList) {
                //Save each row of the report
                br.write(courseReport.getModuleName() + delimiter);
                br.write(courseReport.getCourseName() + delimiter);
                br.write(courseReport.getNumberOfStudents() + delimiter);
                br.write(courseReport.getLecturerName() + delimiter);
                br.write(courseReport.getRoomName() + delimiter);
                br.newLine(); // Write a new line after each row
            }

            br.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void saveStudentReportFile(ArrayList<StudentReport> studentReportList, String fileFormat) {
        String outputFile = "student_report." + fileFormat;

        // Delimiter used in CSV file
        String delimiter = ",";

        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(outputFile, true));

            //Create the header of the file
            br.write("Student Name" + delimiter);
            br.write("Student Number" + delimiter);
            br.write("Course Name" + delimiter);
            br.write("Module enrolled in" + delimiter);
            br.write("Module completed" + delimiter); // need to add grade
            br.write("Grade" + delimiter); // need to add grade
            br.write("Module to repeat" + delimiter); // need the module
            br.newLine();

            for (StudentReport studentReport : studentReportList) {
                //Save each row of the report
                br.write(studentReport.getStudentName() + delimiter);
                br.write(studentReport.getStudentId() + delimiter);
                br.write(studentReport.getCourseName() + delimiter);
                br.write(studentReport.getModuleEnrolledIn() + delimiter);
                br.write(studentReport.getModuleCompleted() + delimiter);
                br.write(studentReport.getGrade() + delimiter);
                br.write(studentReport.getModuleRepeat() + delimiter);
                br.newLine(); // Write a new line after each row
            }

            br.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
