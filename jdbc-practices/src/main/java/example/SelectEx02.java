package example;

import java.sql.*;

public class SelectEx02 { // PreparedStatement 적용
    public static void main(String[] args) {
        search("pat");
    }
    public static void search(String keyword) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 1. JDBC Driver 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            // 2. 연결하기
            String url = "jdbc:mariadb://localhost:3306/employees?charset=utf8";
            connection = DriverManager.getConnection(url, "hr", "hr");

            // 3. Statement 준비하기
            String sql =
                    "select emp_no, first_name, last_name" +
                            " from employees" +
                            " where first_name like ?" +
                            " or last_name like ?";
            pstmt = connection.prepareStatement(sql);

            // 4. binding
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");

            // 5. SQL 실행
            rs = pstmt.executeQuery();

            // 5. 결과 처리
            while (rs.next()) {
                Long empNo = rs.getLong(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                System.out.println(empNo + " : " + firstName + " : " + lastName);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패: " + e);
        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (pstmt!= null) {
                    pstmt.close();
                }
                if (rs!= null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
