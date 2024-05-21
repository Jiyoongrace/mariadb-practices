//package bookshop;
//
//import bookshop.dao.AuthorDAO;
//import bookshop.dao.BookDAO;
//import bookshop.example.Book;
//import bookshop.vo.BookVO;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class BookShopApp {
//
//    private static AuthorDAO authorDAO = new AuthorDAO();
//    private static BookDAO bookDAO = new BookDAO();
//
//    public static void installDB() {
//        bookDAO.deleteAll();
//        authorDAO.deleteAll();
//
//        Book[] books = new Book[10];
//
////        books[0] = new Book( 1, "트와일라잇", "스테파니메이어" );
////        books[1] = new Book( 2,"뉴문", "스테파니메이어" );
////        books[2] = new Book( 3,"이클립스","스테파니메이어");
////        books[3] = new Book( 4,"브레이킹던","스테파니메이어");
////
////        books[4] = new Book( 5,"아리랑","조정래");
////
////        books[5] = new Book( 6,"젊은그들","김동인");
////
////        books[6] = new Book( 7,"아프니깐 청춘이다","김난도");
////
////        books[7] = new Book( 8,"귀천","천상병");
////
////        books[8] = new Book( 9,"태백산맥","조정래");
////
////        books[9] = new Book( 10,"풀하우스","원수연");
//    }
//
//    public static void main(String[] args) {
//
//        installDB();
//        Scanner scanner = new Scanner(System.in);
//        displayBookInfo();
//
//        System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
//        int num = scanner.nextInt();
//        scanner.close();
//
//        // (1) 입력된 번호에 맞는 책을 찾아 대여 되었음(상태코드=0)을 체크 합니다.
//        for (Book book : books) {
//            if (num == book.getBookNo()) {
//                book.rent();
//                break;
//            }
//        }
//
//        // (2) Book 객체의 정보를 출력
//        System.out.println("*****도서 정보 출력하기******");
//        displayBookInfo(books);
//    }
//
//    private static void displayBookInfo() {
//        List<BookVO> list = bookDAO.findAll();
//        for (BookVO vo : list) {
//
//        }
//    }
//}
