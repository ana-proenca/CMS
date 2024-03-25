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

    public ArrayList<CourseReport> getCourseReport() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL + "/CMS", USER, PASSWORD);
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

    public ArrayList<StudentReport> getStudentReport() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL + "/CMS", USER, PASSWORD);
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT s.student_id AS studentId,\n"
                + "s.student_name AS studentName,\n"
                + "s.student_surname AS studentSurname,\n"
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
            String studentSurname = rs.getString("studentSurname");
            String courseName = rs.getString("courseName");
            String moduleName = rs.getString("moduleName");
            int grade = rs.getInt("grade");
            String moduleCompleted = rs.getString("moduleCompleted");

            studentReportList.add(new StudentReport(studentId, studentName, studentSurname, courseName, moduleName, grade, moduleCompleted));
        }

        conn.close();
        return studentReportList;
    }

    public ArrayList<LecturerReport> getLecturerReport() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL + "/CMS", USER, PASSWORD);
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT l.lecturer_name AS lecturerName,\n"
                + "l.lecturer_surname AS lecturerSurname,\n"
                + "l.lecturer_role AS lecturerRole,\n"
                + "m.module_name as moduleName,\n"
                + "m.module_started AS moduleDateStarted,\n"
                + "COUNT(e.enrollment_id) AS numberOfStudents,\n"
                + "m.module_type_class AS moduleTypeClass\n"
                + "FROM lecturers l\n"
                + "INNER JOIN modules m on l.lecturer_id = m.lecturer_id\n"
                + "LEFT JOIN enrollments e on e.module_id = m.module_id\n"
                + "GROUP BY l.lecturer_name, l.lecturer_surname, l.lecturer_role, m.module_name,\n"
                + "m.module_started, m.module_type_class;");
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        ArrayList<LecturerReport> lecturerReportList = new ArrayList<>();

        while (rs.next()) {
            String lecturerName = rs.getString("lecturerName");
            String lecturerSurname = rs.getString("lecturerSurname");
            String lecturerRole = rs.getString("lecturerRole");
            String moduleName = rs.getString("moduleName");
            String moduleDateStarted = rs.getString("moduleDateStarted");
            int numberOfStudents = rs.getInt("numberOfStudents");
            String moduleTypeClass = rs.getString("moduleTypeClass");

            lecturerReportList.add(new LecturerReport(lecturerName, lecturerSurname, lecturerRole, moduleName, moduleDateStarted, numberOfStudents, moduleTypeClass));
        }

        conn.close();
        return lecturerReportList;
    }

    public User login(String username, String password) throws SQLException {

        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        stmt.execute("USE CMS;");
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'"); //read data
        rs.next();
        String role = rs.getString("role");
        conn.close();
        return new User(username, password, role);
    }

    public void addUser(User user) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.execute("USE CMS;");
            stmt.execute(String.format("INSERT INTO users (username, password, role) VALUES ('%s', '%s', '%s');",
                    user.getUsername(), user.getPassword(), user.getRole()));
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
