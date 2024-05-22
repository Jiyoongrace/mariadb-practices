package bookmall.dao;

import bookmall.vo.UserVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://192.168.0.195:3306/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        }

        return conn;
    }


    public void insert(UserVo vo) {
        String sql = "insert into user (name, email, password, phone) values (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getEmail());
            pstmt.setString(3, vo.getPassword());
            pstmt.setString(4, vo.getPhone());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    vo.setNo(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UserVo> findAll() {
        List<UserVo> result = new ArrayList<>();
        String sql = "select no, name, email, password, phone from user";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                UserVo vo = new UserVo(rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("phone"));
                vo.setNo(rs.getLong("no"));
                result.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteByNo(Long no) {
        String sql = "delete from user where no = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, no);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
