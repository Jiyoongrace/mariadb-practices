package bookmall.dao;

import bookmall.vo.CartVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
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

    public void insert(CartVo vo) {
        String sql = "insert into cart (user_no, book_no, quantity) values (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, vo.getUserNo());
            pstmt.setLong(2, vo.getBookNo());
            pstmt.setInt(3, vo.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CartVo> findByUserNo(Long userNo) {
        List<CartVo> result = new ArrayList<>();
        String sql = "select c.user_no, c.book_no, b.title, c.quantity " +
                "from cart c, book b " +
                "where c.book_no = b.no and c.user_no = ? ";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, userNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CartVo vo = new CartVo();
                    vo.setUserNo(rs.getLong("user_no"));
                    vo.setBookNo(rs.getLong("book_no"));
                    vo.setBookTitle(rs.getString("title"));
                    vo.setQuantity(rs.getInt("quantity"));
                    result.add(vo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteByUserNoAndBookNo(Long userNo, Long bookNo) {
        String sql = "delete from cart where user_no = ? and book_no = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, userNo);
            pstmt.setLong(2, bookNo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}