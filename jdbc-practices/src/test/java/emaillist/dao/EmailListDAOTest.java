package emaillist.dao;

import emaillist.vo.EmaillistVO;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmailListDAOTest {

    private static int count = 0;
    @BeforeAll
    public static void setUp() {
        List<EmaillistVO> list = new EmaillistDAO().findAll();
        count = list.size();
    }

    @Test
    @Order(1)
    public void testInsert() {
        EmaillistVO vo = new EmaillistVO();
        vo.setFirstName("둘");
        vo.setLastName("리");
        vo.setEmail("dooly@gmail.com");

        boolean result = new EmaillistDAO().insert(vo);
        assertTrue(result);
    }

    @Test
    @Order(2)
    public void testFindAll() {
        List<EmaillistVO> list = new EmaillistDAO().findAll();
        assertEquals(count + 1, list.size());
    }

    @Test
    @Order(3)
    public void testDeleteByEmail() {
        boolean result = new EmaillistDAO().deleteByEmail("dooly@gmail.com");
        assertTrue(result);
    }

    @AfterAll
    public static void cleanUp() {

    }
}
