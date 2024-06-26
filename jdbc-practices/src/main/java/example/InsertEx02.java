package example;

import java.sql.*;

public class InsertEx02 { // PreparedStatement 적용
    public static void main(String[] args) {
        System.out.println(insert("기획1팀"));
        System.out.println(insert("기획2팀"));
    }

    public static boolean insert(String deptName) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            // 1. JDBC Driver 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            // 2. 연결하기
            String url = "jdbc:mariadb://localhost:3306/webdb?charset=utf8";
            connection = DriverManager.getConnection(url, "webdb", "webdb");

            // 3. Statement 준비
            String sql = "insert into dept values(null, ?)";
            pstmt = connection.prepareStatement(sql);

            // 4.binding
            pstmt.setString(1, deptName);

            // 5. SQL 실행
            int count = pstmt.executeUpdate();

            // 6. 결과 처리
            result = count == 1;
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패: " + e);
        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (pstmt!= null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
