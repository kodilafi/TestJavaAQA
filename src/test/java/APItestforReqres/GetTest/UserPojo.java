package APItestforReqres.GetTest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPojo {
    @Getter
    private Integer id;
    @Getter
    private String email;
    @Getter
    private String first_name;
    @Getter
    private String last_name;
    @Getter
    private String avatar;
}
