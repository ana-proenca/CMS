/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Report contains methods that will generate reports in both formats
 * (file or console print output).
 *
 * @author anaclaudiaproenca
 */
public class Reports {

    private DBConnector dbConnector;
    private String fileFormat;
    private boolean fileOutput;

    /**
     * Constructor that initializes the DBConnector class.Also Scanner is
 provided in order to read the inputs from the user.
     * the constructor also expects to have an exception, such as when the int parse fails due to an string in the input. 
     * It can be handled to the class that is instantiating the Reports Class.
     * @param sc
     * @throws java.lang.Exception
     */
    public Reports(Scanner sc) throws Exception {
        dbConnector = new DBConnector();
        System.out.println("\n");
        System.out.println("1 - Export file");
        System.out.println("2 - Print output console");
        int type = Integer.parseInt(sc.next());

        //Condition to decide whether the report will be generated as a file or printed in the console output.
        switch (type) {
            case 1:
                fileOutput = true;
                System.out.println("\n");
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

    /**
     * method that generates all reports. This method will be used for the users
     * that have the "Office" as role.
     *The method also expects 3 types of Exception based on the methods that have been called.
     * The exceptions are handled in a friendly way that helps to check where the error is happening
     * 
     * @throws Exception
     */
    public void generateAllReports() throws Exception {
        try {
            generateCourseReport();
            generateStudentReport();
            generateLectureReport();
        } catch (SQLException e) {
            throw new Exception("Error to get the report: " + e);
        }
        catch (IOException e) {
            throw new Exception("Error to save the report in the file: " + e);
        }
          catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Method that generated the Lecturer report. This method reads the values
     * from the CMS database, from a query defined containing Inner Joins
     * between tables. The result will be an array of Lecturer report. This
     * array will be used in order to create the report in a file or printed in
     * the console output.
     * This method expects 3 exceptions (Db, BufferedWriter or general error)
     *
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws Exception
     */
    public void generateLectureReport() throws SQLException, IOException, Exception {
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

    /**
     * Method that generated the Student report. This method reads the values
     * from the CMS database, from a query defined containing Inner Joins
     * between tables. The result will be an array of Students report. This
     * array will be used in order to create the report in a file or printed in
     * the console output.
     * This method expects 3 exceptions (Db, BufferedWriter or general error)
     * 
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws Exception
     */
    public void generateStudentReport() throws SQLException, IOException, Exception {
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

    /**
     * Method that generated the Course report.This method reads the values from
     * the CMS database, from a query defined containing Inner Joins between
     * tables.The result will be an array of Course report. This array will be
     * used in order to create the report in a file or printed in the console
     * output.
     * This method expects 3 exceptions (Db, BufferedWriter or general error)
     *
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws Exception
     */
    public void generateCourseReport() throws SQLException, IOException, Exception {
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

    /**
     * Method to save the file based on the file format provided. This method
     * instantiates the BufferedWriter class. Then before starting iterating the
     * objects in the array, the header of the report is created Afterwards, for
     * each element of the array will be printed by the delimiter defined,
     * (comma)
     *
     * @param lecturerReportList
     * @param fileFormat
     */
    private void saveLecturerReport(ArrayList<LecturerReport> lecturerReportList, String fileFormat) throws IOException {
        String outputFile = "lecturer_report." + fileFormat;

        // Delimiter used in CSV file
        String delimiter = ",";

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
    }

    /**
     * Method to save the file based on the file format provided. This method
     * instantiates the BufferedWriter class. Then before starting iterating the
     * objects in the array, the header of the report is created Afterwards, for
     * each element of the array will be printed by the delimiter defined,
     * (comma)
     *
     * @param courseReportList
     * @param fileFormat
     */
    private void saveCourseReportFile(ArrayList<CourseReport> courseReportList, String fileFormat) throws IOException {
        String outputFile = "course_report." + fileFormat;

        // Delimiter used in CSV file
        String delimiter = ",";

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
    }

    /**
     * Method to save the file based on the file format provided. This method
     * instantiates the BufferedWriter class. Then before starting iterating the
     * objects in the array, the header of the report is created Afterwards, for
     * each element of the array will be printed by the delimiter defined,
     * (comma)
     *
     * @param studentReportList`
     * @param fileFormat
     */
    private void saveStudentReportFile(ArrayList<StudentReport> studentReportList, String fileFormat) throws IOException {
        String outputFile = "student_report." + fileFormat;

        // Delimiter used in CSV file
        String delimiter = ",";

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
    }
}
