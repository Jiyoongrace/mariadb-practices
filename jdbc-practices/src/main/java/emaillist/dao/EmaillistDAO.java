package emaillist.dao;

import emaillist.vo.EmaillistVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmaillistDAO {
    public boolean insert(EmaillistVO vo) {
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
            String sql = "insert into emaillist values(null, ?, ?, ?)";
            pstmt = connection.prepareStatement(sql);

            // 4.binding
            pstmt.setString(1, vo.getFirstName());
            pstmt.setString(2, vo.getLastName());
            pstmt.setString(3, vo.getEmail());

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

    public boolean deleteByEmail(String email) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            // 1. JDBC Driver 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            // 2. 연결하기
            String url = "jdbc:mariadb://192.168.0.195:3306/webdb?charset=utf8";
            connection = DriverManager.getConnection(url, "webdb", "webdb");

            // 3. Statement 준비
            String sql = "delete from emaillist where email = ?";
            pstmt = connection.prepareStatement(sql);

            // 4.binding
            pstmt.setString(1, email);

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

    public List<EmaillistVO> findAll() {
        List<EmaillistVO> result = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 1. JDBC Driver 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            // 2. 연결하기
            String url = "jdbc:mariadb://192.168.0.195:3306/webdb?charset=utf8";
            connection = DriverManager.getConnection(url, "webdb", "webdb");

            // 3. Statement 준비하기
            String sql = "select no, first_name, last_name, email from emaillist order by no desc";
            pstmt = connection.prepareStatement(sql);

            // 4. binding
            // 조건이 없어서 주석 처리
//            pstmt.setString(1, "%" + keyword + "%");
//            pstmt.setString(2, "%" + keyword + "%");

            // 5. SQL 실행
            rs = pstmt.executeQuery();

            // 5. 결과 처리
            while (rs.next()) {
                Long no = rs.getLong(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String email = rs.getString(4);

                EmaillistVO vo = new EmaillistVO();
                vo.setNo(no);
                vo.setFirstName(firstName);
                vo.setLastName(lastName);
                vo.setEmail(email);

                result.add(vo);
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

        return result;
    }
}
