package APItestforReqres.RegisterTest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterPojo {
    @Getter
    private String email;
    @Getter
    private String password;
}
