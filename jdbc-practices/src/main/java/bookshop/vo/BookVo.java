package bookshop.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookVo {
    private Long no;
    private String title;
    private String status;
    private Long authorNo;
    private String authorName;
}
