package bookmall.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartVo {
    private Long userNo;
    private Long bookNo;
    private String bookTitle;
    private int quantity;
}
