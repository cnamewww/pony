package student.DAO;

import student.DBUtil.DBUtil;
import student.student.student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    // 添加学生信息
    public static boolean addStudent(student student) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO students (name, id, major, gender, age) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getId());
            pstmt.setString(3, student.getMajor());
            pstmt.setString(4, student.getGender());
            pstmt.setInt(5, student.getAge());
            int count = pstmt.executeUpdate();
            if (count > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, pstmt, null);
        }
        return flag;
    }

    // 更新学生信息
    public static boolean updateStudent(student student) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE students SET name=?, major=?, gender=?, age=? WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getMajor());
            pstmt.setString(3, student.getGender());
            pstmt.setInt(4, student.getAge());
            pstmt.setString(5, student.getId());
            int count = pstmt.executeUpdate();
            if (count > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, pstmt, null);
        }
        return flag;
    }

    // 删除学生信息
    public static boolean deleteStudent(String id) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM students WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            int count = pstmt.executeUpdate();
            if (count > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, pstmt, null);
        }
        return flag;
    }

    // 查询学生信息
    public static List<student> queryStudents() {
        List<student> students = new ArrayList<student>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT name, id, major, gender, age FROM students";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                student student = new student(
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("major"),
                        rs.getString("gender"),
                        rs.getInt("age")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, stmt, rs);
        }
        return students;
    }

    // 根据学号查询学生信息
    public static student queryStudentById(String id) {
        student student = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT name, id, major, gender, age FROM students WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                student = new student(
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("major"),
                        rs.getString("gender"),
                        rs.getInt("age")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, pstmt, rs);
        }
        return student;
    }
}
