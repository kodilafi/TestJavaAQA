package APItestforReqres.UpdateTest;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdatedUserPojo {
    @Getter
    private String name;
    @Getter
    private String job;
    @Getter
    private Date updatedAt;
}
