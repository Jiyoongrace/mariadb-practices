package bookmall.vo;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class BookVo {
    private Long no;
    @NonNull
    private String title;
    @NonNull
    private int price;
    private Long categoryNo;
}
