package example;

import java.sql.*;

public class UpdateEx02 { // PreparedStatement 적용
    public static void main(String[] args) {
        DeptVO vo = new DeptVO();
        vo.setNo(1L);
        vo.setName("경영지원2");

        boolean result = update(vo);
        System.out.println(result ? "성공" : "실패");
    }

    public static boolean update(DeptVO vo) {
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
            String sql = "update dept set name = ? where no = ?";
            pstmt = connection.prepareStatement(sql);

            // 4. binding
            pstmt.setString(1, vo.getName());
            pstmt.setLong(2, vo.getNo());

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
