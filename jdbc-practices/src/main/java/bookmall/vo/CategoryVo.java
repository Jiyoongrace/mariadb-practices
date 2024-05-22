package bookmall.vo;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class CategoryVo {
    private Long no;
    @NonNull
    private String name;
}
