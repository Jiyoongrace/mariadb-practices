package bookmall.dao;

import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
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

    public void insert(OrderVo vo) {
        String sql = "insert into orders (number, payment, shipping, status, user_no) values (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, vo.getNumber());
            pstmt.setInt(2, vo.getPayment());
            pstmt.setString(3, vo.getShipping());
            pstmt.setString(4, vo.getStatus());
            pstmt.setLong(5, vo.getUserNo());
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

    public void insertBook(OrderBookVo vo) {
        String sql = "insert into orders_book (order_no, book_no, quantity, price) values (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, vo.getOrderNo());
            pstmt.setLong(2, vo.getBookNo());
            pstmt.setInt(3, vo.getQuantity());
            pstmt.setInt(4, vo.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OrderVo findByNoAndUserNo(Long no, Long userNo) {
        OrderVo result = null;
        String sql = "select no, number, payment, shipping, status, user_no " +
                "from orders " +
                "where no = ? and user_no = ? ";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, no);
            pstmt.setLong(2, userNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    result = new OrderVo();
                    result.setNo(rs.getLong("no"));
                    result.setNumber(rs.getString("number"));
                    result.setPayment(rs.getInt("payment"));
                    result.setShipping(rs.getString("shipping"));
                    result.setStatus(rs.getString("status"));
                    result.setUserNo(rs.getLong("user_no"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<OrderBookVo> findBooksByNoAndUserNo(Long orderNo, Long userNo) {
        List<OrderBookVo> result = new ArrayList<>();
        String sql = "select ob.order_no, ob.book_no, b.title, ob.quantity, ob.price " +
                "from orders_book ob, book b " +
                "where ob.book_no = b.no " +
                "and ob.order_no = ? " +
                "and exists (select 1 from orders o where o.no = ob.order_no and o.user_no = ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, orderNo);
            pstmt.setLong(2, userNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    OrderBookVo vo = new OrderBookVo();
                    vo.setOrderNo(rs.getLong("order_no"));
                    vo.setBookNo(rs.getLong("book_no"));
                    vo.setBookTitle(rs.getString("title"));
                    vo.setQuantity(rs.getInt("quantity"));
                    vo.setPrice(rs.getInt("price"));
                    result.add(vo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteBooksByNo(Long orderNo) {
        String sql = "delete from orders_book where order_no = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, orderNo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByNo(Long no) {
        String sql = "delete from orders where no = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, no);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}