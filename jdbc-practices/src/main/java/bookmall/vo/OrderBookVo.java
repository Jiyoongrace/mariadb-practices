package bookmall.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBookVo {
    private Long orderNo;
    private Long bookNo;
    private String bookTitle;
    private int quantity;
    private int price;
}
