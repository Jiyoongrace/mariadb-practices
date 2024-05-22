package bookmall.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderVo {
    private Long no;
    private String number;
    private int payment;
    private String shipping;
    private String status;

    private Long userNo;
}
