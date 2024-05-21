package bookshop.dao;

import bookshop.vo.AuthorVO;
import bookshop.vo.BookVO;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDAOTest {
    private static int count = 0;
    private static AuthorVO mockAuthorVO = new AuthorVO();
    private static BookVO mockBookVO = new BookVO();

    private static AuthorDAO authorDAO = new AuthorDAO();
    private static BookDAO bookDAO = new BookDAO();

    @BeforeAll
    public static void setUp() {
        mockAuthorVO.setName("칼세이건");
        authorDAO.insert(mockAuthorVO);

        count = bookDAO.findAll().size();
    }

    @Test
    @Order(1)
    public void testInsert() {
        mockBookVO.setTitle("코스모스");
        mockBookVO.setAuthorNo(mockAuthorVO.getNo());
        bookDAO.insert(mockBookVO);

        assertNotNull(mockBookVO.getNo());
    }

    @Test
    @Order(2)
    public void testFindAll() {
        assertEquals(count + 1, bookDAO.findAll().size());
    }

    @Test
    @Order(3)
    public void testUpdate() {
        assertEquals(1, bookDAO.update(mockBookVO.getNo(), "대여중"));
    }

    @AfterAll
    public static void cleanUp() {
        bookDAO.deleteByNo(mockBookVO.getNo());
        authorDAO.deleteByNo(mockAuthorVO.getNo());
    }
}
