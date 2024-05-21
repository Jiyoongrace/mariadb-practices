package bookshop.dao;

import bookshop.vo.BookVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookDAO {
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

    public int insert(BookVO vo) {
        int result = 0;

        try (
            Connection connection = getConnection();
            PreparedStatement pstmt1 = connection.prepareStatement("insert into book(title, author_no) values(?, ?)");
            PreparedStatement pstmt2 = connection.prepareStatement("select last_insert_id() from dual");

        ) {
            pstmt1.setString(1, vo.getTitle());
            pstmt1.setLong(2, vo.getAuthorNo());
            result = pstmt1.executeUpdate();

            ResultSet rs = pstmt2.executeQuery();
            vo.setNo(rs.next() ? rs.getLong(1) : null);
            rs.close();

        } catch (SQLException e) {
            System.out.println("error: " + e);
        }

        return result;
    }

    public List<BookVO> findAll() {
        List<BookVO> result = new ArrayList<>();

        try (
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement("select a.no, a.title, a.status, b.name from book a, author b where a.author_no = b.no order by no desc");
            ResultSet rs = pstmt.executeQuery();
        ) {
            while(rs.next()) {
                Long no = rs.getLong(1);
                String title = rs.getString(2);
                String status = rs.getString(3);
                String authorName = rs.getString(4);

                BookVO vo = new BookVO();
                vo.setNo(no);
                vo.setTitle(title);
                vo.setStatus(status);
                vo.setAuthorName(authorName);

                result.add(vo);
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        }

        return result;
    }

    public int deleteByNo(Long no) {
        int result = 0;

        try (
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement("delete from book where no =?");
        ) {
            pstmt.setLong(1, no);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }

        return result;

    }

    public Integer update(Long no, String status) {
        int result = 0;

        try (
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement("update book set status =? where no =?");
        ) {
            pstmt.setString(1, status);
            pstmt.setLong(2, no);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }

        return result;
    }
}
