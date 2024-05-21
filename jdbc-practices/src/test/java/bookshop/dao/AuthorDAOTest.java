package bookshop.dao;

import bookshop.vo.AuthorVO;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorDAOTest {
    private static int count = 0;
    private static AuthorDAO authorDAO = new AuthorDAO();
    private static AuthorVO mockAuthorVO = new AuthorVO();
    @BeforeAll
    public static void setUp() {
        count = authorDAO.findAll().size();
    }

    @Test
    @Order(1)
    public void testInsert() {
        mockAuthorVO.setName("칼세이건");

        authorDAO.insert(mockAuthorVO);
        assertNotNull(mockAuthorVO.getNo());
    }

    @Test
    @Order(2)
    public void testFindAll() {
        assertEquals(count + 1, authorDAO.findAll().size());
    }

    @AfterAll
    public static void cleanUp() {
        authorDAO.deleteByNo(mockAuthorVO.getNo());
    }
}
