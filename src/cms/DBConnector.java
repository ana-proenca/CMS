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
}
