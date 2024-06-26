package cms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author anaclaudiaproenca
 */
public class DBConnector {

    private final String DB_URL = "jdbc:mysql://127.0.0.1";
    private final String USER = "pooa2024";
    private final String PASSWORD = "pooa2024";
    private final String DATABASE = "CMS";

    /**
     * method to get the Course report. Input parameter not defined Output value
     * is the CourseReport class in which contains attributes that represent the
     * Inner joins between Table in MySql
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<CourseReport> getCourseReport() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL + "/" + DATABASE, USER, PASSWORD);
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT c.course_name AS courseName,\n"
                + "m.module_name AS moduleName,\n"
                + "COUNT(e.enrollment_id) AS numberOfStudents, \n"
                + "l.lecturer_name AS lecturerName,\n"
                + "r.room_name AS roomName\n"
                + "FROM courses c\n"
                + "INNER JOIN modules m on m.course_id = c.course_id\n"
                + "LEFT JOIN enrollments e on e.course_id = c.course_id AND e.module_id = m.module_id\n"
                + "INNER JOIN lecturers l on m.lecturer_id = l.lecturer_id\n"
                + "LEFT JOIN room r on m.module_id = r.module_id\n"
                + "GROUP BY c.course_name, m.module_name, l.lecturer_name, r.room_name\n"
                + "ORDER BY c.course_name;");
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        ArrayList<CourseReport> courseReportList = new ArrayList<>();

        while (rs.next()) {
            String courseName = rs.getString("courseName");
            String moduleName = rs.getString("moduleName");
            int numberOfStudents = rs.getInt("numberOfStudents");
            String lecturerName = rs.getString("lecturerName");
            String roomName = rs.getString("roomName");

            courseReportList.add(new CourseReport(courseName, moduleName, numberOfStudents, lecturerName, roomName));
        }

        conn.close();
        return courseReportList;
    }

    /**
     * method to get the Course report. Input parameter not defined Output value
     * is the CourseReport class in which contains attributes that represent the
     * Inner joins between Table in MySql
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<StudentReport> getStudentReport() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL + "/" + DATABASE, USER, PASSWORD);
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT s.student_id AS studentId,\n"
                + "s.student_name AS studentName,\n"
                + "c.course_name AS courseName,\n"
                + "m.module_name AS moduleName,\n"
                + "g.grade AS grade,\n"
                + "g.completed AS moduleCompleted\n"
                + "FROM students s\n"
                + "INNER JOIN enrollments e on s.student_id = e.student_id\n"
                + "INNER JOIN courses c on c.course_id = e.course_id\n"
                + "INNER JOIN modules m on m.module_id = e.module_id\n"
                + "INNER JOIN grades g on g.grade_id = e.grade_id; ");
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        ArrayList<StudentReport> studentReportList = new ArrayList<>();

        while (rs.next()) {
            String studentId = rs.getString("studentId");
            String studentName = rs.getString("studentName");
            String courseName = rs.getString("courseName");
            String moduleName = rs.getString("moduleName");
            int grade = rs.getInt("grade");
            String moduleCompleted = rs.getString("moduleCompleted");

            studentReportList.add(new StudentReport(studentId, studentName, courseName, moduleName, grade, moduleCompleted));
        }

        conn.close();
        return studentReportList;
    }

    /**
     * method to get the Course report. Input parameter not defined Output value
     * is the CourseReport class in which contains attributes that represent the
     * Inner joins between Table in MySql
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<LecturerReport> getLecturerReport() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL + "/" + DATABASE, USER, PASSWORD);
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT l.lecturer_name AS lecturerName,\n"
                + "l.lecturer_name AS lecturerName,\n"
                + "l.lecturer_role AS lecturerRole,\n"
                + "m.module_name as moduleName,\n"
                + "m.module_started AS moduleDateStarted,\n"
                + "COUNT(e.enrollment_id) AS numberOfStudents,\n"
                + "m.module_type_class AS moduleTypeClass\n"
                + "FROM lecturers l\n"
                + "INNER JOIN modules m on l.lecturer_id = m.lecturer_id\n"
                + "LEFT JOIN enrollments e on e.module_id = m.module_id\n"
                + "GROUP BY l.lecturer_name, l.lecturer_role, m.module_name,\n"
                + "m.module_started, m.module_type_class;");
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        ArrayList<LecturerReport> lecturerReportList = new ArrayList<>();

        while (rs.next()) {
            String lecturerName = rs.getString("lecturerName");
            String lecturerRole = rs.getString("lecturerRole");
            String moduleName = rs.getString("moduleName");
            String moduleDateStarted = rs.getString("moduleDateStarted");
            int numberOfStudents = rs.getInt("numberOfStudents");
            String moduleTypeClass = rs.getString("moduleTypeClass");

            lecturerReportList.add(new LecturerReport(lecturerName, lecturerRole, moduleName, moduleDateStarted, numberOfStudents, moduleTypeClass));
        }

        conn.close();
        return lecturerReportList;
    }

    /**
     * Method to getUser or login user. It will make a select in the User table
     * based on the Username and Password and will return the user that exists
     * on the Users table
     *
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public User getUser(String username, String password) throws SQLException {

        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        stmt.execute("USE " + DATABASE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'"); //read data
        rs.next();
        String role = rs.getString("role");
        int userId = rs.getInt("user_id");
        conn.close();
        User user = new User(username, password, role);
        user.setUser_id(userId);

        return user;
    }

    /**
     * Method to get the user by user Id. This method does not need to have the
     * username and password provided, once it has the user_id it will be enough
     * to get the record from the Users table
     *
     * @param userId
     * @return
     * @throws SQLException
     */
    public User getUser(int userId) throws SQLException {

        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        stmt.execute("USE " + DATABASE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE user_id= " + userId); //read data

        if (!rs.next()) {
            return null;
        }
        String role = rs.getString("role");
        String username = rs.getString("username");
        String password = rs.getString("password");

        conn.close();
        User user = new User(username, password, role);
        user.setUser_id(userId);

        return user;
    }

    /**
     * Method to create a new user in the Users table
     *
     * @param user
     * @throws SQLException
     */
    public void addUser(User user) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        stmt.execute("USE " + DATABASE);
        stmt.execute(String.format("INSERT INTO users (username, password, role) VALUES ('%s', '%s', '%s');",
                user.getUsername(), user.getPassword(), user.getRole()));
        conn.close();
    }

    /**
     * Method to update the user in the Users table. It will update only
     * username and password
     *
     * @param user
     * @throws SQLException
     */
    public void updateUser(User user) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        stmt.execute("USE " + DATABASE);
        stmt.execute(String.format("UPDATE users SET username = '%s', password = '%s'"
                + "WHERE user_id = %d ;",
                user.getUsername(), user.getPassword(), user.getUser_id()));
        conn.close();
    }

    /**
     * Delete an user from the Users table by the user id provided. this methods
     * does not take in consideration username and password, as the userid is
     * the primary key of the table
     *
     * @param userId
     * @throws SQLException
     */
    public void deleteUser(int userId) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        stmt.execute("USE " + DATABASE);
        stmt.execute(String.format("DELETE FROM users WHERE user_id = %d ;", userId));
        conn.close();
    }
}
