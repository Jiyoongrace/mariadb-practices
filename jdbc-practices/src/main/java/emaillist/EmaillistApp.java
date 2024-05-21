package emaillist;

import emaillist.dao.EmaillistDAO;
import emaillist.vo.EmaillistVO;

import java.util.List;
import java.util.Scanner;

public class EmaillistApp {
    private static Scanner scanner = new Scanner(System.in);
    private static EmaillistDAO emaillistDAO = new EmaillistDAO();
    public static void main(String[] args) {

        while (true) {
            System.out.print("(l)ist (d)elete (i)nsert (q)uit > ");
            String command = scanner.nextLine();

            if("l".equals(command)) {
                doList();
            } else if("d".equals(command)) {
                doDelete();
            } else if("i".equals(command)) {
                doInsert();
            } else if("q".equals(command)) {
                break;
            }
        }
        if(scanner != null) {
            scanner.close();
        }
    }

    private static void doInsert() {
        System.out.print("성: ");
        String firstName = scanner.nextLine();

        System.out.print("이름: ");
        String lastName = scanner.nextLine();

        System.out.print("이메일: ");
        String email = scanner.nextLine();

        EmaillistVO vo = new EmaillistVO();
        vo.setFirstName(firstName);
        vo.setLastName(lastName);
        vo.setEmail(email);

        emaillistDAO.insert(vo);

        doList();
    }

    private static void doDelete() {
        System.out.print("이메일: ");
        String email = scanner.nextLine();

        emaillistDAO.deleteByEmail(email);

        doList();
    }

    private static void doList() {
        List<EmaillistVO> list = emaillistDAO.findAll();
        for(EmaillistVO vo : list) {
            System.out.println(vo.getFirstName() + " : " + vo.getLastName() + " : " + vo.getEmail());
        }
    }
}
