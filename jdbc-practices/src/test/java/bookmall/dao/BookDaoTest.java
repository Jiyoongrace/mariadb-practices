package bookmall.dao;

import bookmall.vo.BookVo;
import bookmall.vo.CategoryVo;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDaoTest {
    private static int count = 0;
    private static CategoryVo mockCategoryVo = new CategoryVo();
    private static BookVo mockBookVo = new BookVo();

    private static CategoryDao categoryDao = new CategoryDao();
    private static BookDao bookDao = new BookDao();

    @BeforeAll
    public static void setUp() {
        mockCategoryVo.setName("컴퓨터/IT");
        categoryDao.insert(mockCategoryVo);

        count = bookDao.findAll().size();
    }

    @Test
    @Order(1)
    public void testInsert() {
        mockBookVo.setTitle("토비의 스프링");
        mockBookVo.setPrice(20000);
        mockBookVo.setCategoryNo(mockCategoryVo.getNo());
        bookDao.insert(mockBookVo);

        assertNotNull(mockBookVo.getNo());
    }

    @Test
    @Order(2)
    public void testFindAll() {
        assertEquals(count + 1, bookDao.findAll().size());
    }

    @AfterAll
    public static void cleanUp() {
        bookDao.deleteByNo(mockBookVo.getNo());
        categoryDao.deleteByNo(mockCategoryVo.getNo());
    }
}
