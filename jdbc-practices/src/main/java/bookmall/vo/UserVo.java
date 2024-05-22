package bookmall.vo;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class UserVo {
    private Long no;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String phone;
}
