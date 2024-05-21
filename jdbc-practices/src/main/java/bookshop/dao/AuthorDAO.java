package bookshop.dao;

import bookshop.vo.AuthorVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private Connection getConnection() throws SQLException {
        Connection connection = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.0.195:3306/webdb?charset=utf8";
            connection = DriverManager.getConnection(url, "webdb", "webdb");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패: " + e);
        }

        return connection;
    }

    public List<AuthorVO> findAll() {
        List<AuthorVO> result = new ArrayList<>();

        try ( // try catch resources
             Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement("select no, name from author");
             ResultSet rs = pstmt.executeQuery(); // 바인딩이 없어서 바로 cleanUp 되도록..
        ) {
            while (rs.next()) {
                Long no = rs.getLong(1);
                String name = rs.getString(2);

                AuthorVO vo = new AuthorVO();
                vo.setNo(no);
                vo.setName(name);

                result.add(vo);
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        }

        return result;
    }

    public int insert(AuthorVO vo) {
        int result = 0;

        try (
             Connection connection = getConnection();
             PreparedStatement pstmt1 = connection.prepareStatement("insert into author(name) values(?)");
             PreparedStatement pstmt2 = connection.prepareStatement("select last_insert_id() from dual");

        ) {
            pstmt1.setString(1, vo.getName());
            result = pstmt1.executeUpdate();

            ResultSet rs = pstmt2.executeQuery();
            vo.setNo(rs.next() ? rs.getLong(1) : null);
            rs.close();

        } catch (SQLException e) {
            System.out.println("error: " + e);
        }

        return result;
    }

    public int deleteByNo(Long no) {
        int result = 0;
        try (
             Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement("delete from author where no = ?");
        ) {
            pstmt.setLong(1, no);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
        return result;
    }
}
