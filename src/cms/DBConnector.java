package cms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT c.course_name AS courseName,\n" +
                                                                    "m.module_name AS moduleName,\n" +
                                                                    "COUNT(e.enrollment_id) AS numberOfStudents, \n" +
                                                                    "l.lecturer_name AS lecturerName,\n" +
                                                                    "r.room_name AS roomName\n" +
                                                                    "FROM courses c\n" +
                                                                    "INNER JOIN modules m on m.course_id = c.course_id\n" +
                                                                    "LEFT JOIN enrollments e on e.course_id = c.course_id AND e.module_id = m.module_id\n" +
                                                                    "INNER JOIN lecturers l on m.lecturer_id = l.lecturer_id\n" +
                                                                    "LEFT JOIN room r on m.module_id = r.module_id\n" +
                                                                    "GROUP BY c.course_name, m.module_name, l.lecturer_name, r.room_name\n" +
                                                                    "ORDER BY c.course_name;");
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
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT s.student_id AS studentId,\n" +
                                                                    "s.student_name AS studentName,\n" +
                                                                    "s.student_surname AS studentSurname,\n" +
                                                                    "c.course_name AS courseName,\n" +
                                                                    "m.module_name AS moduleName,\n" +
                                                                    "g.grade AS grade,\n" +
                                                                    "g.completed AS moduleCompleted\n" +
                                                                    "FROM students s\n" +
                                                                    "INNER JOIN enrollments e on s.student_id = e.student_id\n" +
                                                                    "INNER JOIN courses c on c.course_id = e.course_id\n" +
                                                                    "INNER JOIN modules m on m.module_id = e.module_id\n" +
                                                                    "INNER JOIN grades g on g.grade_id = e.grade_id; ");
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
}
