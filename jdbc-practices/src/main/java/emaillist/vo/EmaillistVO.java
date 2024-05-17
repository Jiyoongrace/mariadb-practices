package emaillist.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmaillistVO {
    private Long no;
    private String firstName;
    private String lastName;
    private String email;

    @Override
    public String toString() {
        return super.toString();
    }
}
