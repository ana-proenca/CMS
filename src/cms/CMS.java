/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cms;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author anaclaudiaproenca
 */
public class CMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            User userLogin = login(sc);

            // TODO code application logic here
            DBConnector db = new DBConnector();
            printOptionsMenu(sc, userLogin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static User login(Scanner sc) throws Exception {
        System.out.println("Inform an username");
        String username = sc.next();

        System.out.println("Inform a password");
        String password = sc.next();

        DBConnector db = new DBConnector();

        User user = db.getUser(username, password);

        return user;
    }

    private static void printOptionsMenu(Scanner sc, User user) throws Exception {
        switch (user.getRole()) {
            case "Admin":
                printAdminMenu(sc, true);
                break;
            case "Office":
                printOfficeMenu(sc, true);
                break;
            default:
                throw new AssertionError();
        }
    }

    private static void printAdminMenu(Scanner sc, Boolean userlogged) throws Exception {
        while (userlogged) {
            System.out.println("\n");
            System.out.println("1 - add new user");
            System.out.println("2 - modify an user");
            System.out.println("3 - delete an user");
            System.out.println("4 - change username and password");
            System.out.println("5 - logoff");

            int options = Integer.parseInt(sc.next());

            switch (options) {
                case 1:
                    addNewUser(sc);
                    break;
                case 2:
                    modifyUser(sc);
                    break;
                case 3:

                    break;
                case 4:
                    userlogged = false;
                    break;
                case 5:
                    userlogged = false;
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private static void printOfficeMenu(Scanner sc, Boolean userlogged) throws Exception {
        while (userlogged) {
            System.out.println("\n");
            System.out.println("1 - generate all reports");
            System.out.println("2 - change username and password");
            System.out.println("3 - logoff");

            int options = Integer.parseInt(sc.next());

            switch (options) {
                case 1:
                    generateAllReports();
                    break;
                case 2:

                    break;
                case 3:
                    userlogged = false;
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    //method to read the inputs and create the User object
    private static void addNewUser(Scanner sc) throws Exception {
        System.out.println("Inform an username");
        String username = sc.next();

        System.out.println("Inform a password");
        String password = sc.next();

        System.out.println("Inform a role (Office or Lecturer");
        String role = sc.next();

        User newUser = new User(username, password, role);

        if (newUser.getRole().equals("Office") || newUser.getRole().equals("Lecturer")) {
            DBConnector db = new DBConnector();
            db.addUser(newUser);
        } else {
            System.out.println("Role not valid");
        }
    }

    private static void modifyUser(Scanner sc) throws Exception {
        System.out.println("Inform the current username");
        String currentUsername = sc.next();

        System.out.println("Inform the current password");
        String currentPassword = sc.next();

        DBConnector db = new DBConnector();

        User user = db.getUser(currentUsername, currentPassword);

        if (user != null) {
            System.out.println("Inform the new username");
            String newUsername = sc.next();

            System.out.println("Inform the new password");
            String newPassword = sc.next();

            user.setUsername(newUsername);
            user.setPassword(newPassword);

            db.updateUser(user);
        } else {
            System.out.println("User not found");
        }
    }

    private static void generateAllReports() throws Exception {
        DBConnector db = new DBConnector();

        System.out.println("\nGenerating Course Report");
        ArrayList<CourseReport> courseReportList = db.getCourseReport();

        for (CourseReport courseReport : courseReportList) {
            System.out.println(courseReport.getCourseName() + ", " + courseReport.getModuleName() + ", " + courseReport.getNumberOfStudents() + ", " + courseReport.getLecturerName() + ", " + courseReport.getRoomName());
        }

        System.out.println("\nGenerating Student Report");
        ArrayList<StudentReport> studentReportList = db.getStudentReport();

        for (StudentReport studentReport : studentReportList) {
            System.out.println(studentReport.getStudentName() + ", " + studentReport.getCourseName() + ", " + studentReport.getModuleName() + ", " + studentReport.getGrade() + ", " + studentReport.getModuleCompleted());
        }

        System.out.println("\nGenerating Lecturer Report");
        ArrayList<LecturerReport> lecturerReportList = db.getLecturerReport();

        for (LecturerReport lecturerReport : lecturerReportList) {
            System.out.println(lecturerReport.getLecturerName() + ", " + lecturerReport.getLecturerRole() + ", " + lecturerReport.getModuleName() + ", " + lecturerReport.getModuleDateStarted() + ", " + lecturerReport.getModuleTypeClass() + ", " + lecturerReport.getNumberOfStudents());
        }
    }
}
