/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cms;

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
        System.out.println("Welcome " + user.getUsername() + ". Your current role is: " + user.getRole());
        switch (user.getRole()) {
            case "Admin":
                printAdminMenu(sc, user, true);
                break;
            case "Office":
                printOfficeMenu(sc, user, true);
                break;
            case "Lecturer":
                printLecturerMenu(sc, user, true);
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    private static void printAdminMenu(Scanner sc, User user, Boolean userlogged) throws Exception {
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
                    deleteUser(sc);
                    break;
                case 4:
                    changeUsernameAndPassword(sc, user);
                    break;
                case 5:
                    userlogged = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private static void printOfficeMenu(Scanner sc, User user, Boolean userlogged) throws Exception {
        while (userlogged) {
            System.out.println("\n");
            System.out.println("1 - generate all reports");
            System.out.println("2 - change username and password");
            System.out.println("3 - logoff");

            int options = Integer.parseInt(sc.next());

            switch (options) {
                case 1:
                    Reports reports = new Reports(sc);
                    reports.generateAllReports();
                    break;
                case 2:
                    changeUsernameAndPassword(sc, user);
                    break;
                case 3:
                    userlogged = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private static void printLecturerMenu(Scanner sc, User user, Boolean userlogged) throws Exception {
        while (userlogged) {
            System.out.println("\n");
            System.out.println("1 - generate my report");
            System.out.println("2 - change username and password");
            System.out.println("3 - logoff");

            int options = Integer.parseInt(sc.next());
            switch (options) {
                case 1:
                    Reports reports = new Reports(sc);
                    reports.generateLectureReport();
                    break;
                case 2:
                    changeUsernameAndPassword(sc, user);
                    break;
                case 3:
                    userlogged = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    //method to read the inputs and create the User object
    private static void addNewUser(Scanner sc) throws Exception {
        System.out.println("Inform an username");
        String username = sc.next();

        System.out.println("Inform a password");
        String password = sc.next();

        System.out.println("Inform a role (Office or Lecturer)");
        String role = sc.next();

        User newUser = new User(username, password, role);

        if (newUser.getRole().equals("Office") || newUser.getRole().equals("Lecturer")) {
            DBConnector db = new DBConnector();
            db.addUser(newUser);

            System.out.println("User " + newUser.getUsername() + " added");
        } else {
            System.out.println("Role not valid");
        }
    }

    private static void modifyUser(Scanner sc) throws Exception {
        System.out.println("Inform the user id");
        int userId = Integer.parseInt(sc.next());

        DBConnector db = new DBConnector();

        User user = db.getUser(userId);

        if (user != null) {
            System.out.println("Inform the new username");
            String newUsername = sc.next();

            System.out.println("Inform the new password");
            String newPassword = sc.next();

            user.setUsername(newUsername);
            user.setPassword(newPassword);

            db.updateUser(user);

            System.out.println("User " + user.getUsername() + " updated");
        } else {
            System.out.println("User not found");
        }
    }

    private static void deleteUser(Scanner sc) throws Exception {
        System.out.println("Inform the user id");
        int userId = Integer.parseInt(sc.next());

        DBConnector db = new DBConnector();

        User user = db.getUser(userId);

        if (user != null) {
            db.deleteUser(user.getUser_id());

            System.out.println("User " + user.getUsername() + " deleted");
        } else {
            System.out.println("User not found");
        }
    }

    private static void changeUsernameAndPassword(Scanner sc, User user) throws Exception {
        System.out.println("Inform the new username");
        String newUsername = sc.next();

        System.out.println("Inform the new password");
        String newPassword = sc.next();

        user.setUsername(newUsername);
        user.setPassword(newPassword);

        DBConnector db = new DBConnector();

        db.updateUser(user);
        System.out.println("Username and password updated");
    }
}
