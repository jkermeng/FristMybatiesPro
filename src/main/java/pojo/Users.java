package pojo;

import lombok.*;

@Data
@ToString(includeFieldNames=true)
@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Users {
    @NonNull
    private Integer uid;
    private String uname;
    private String urname;
    private String uphone;
    private String ugender;
    private String uemail;
    private String upassword;
    private String uhotword;

}
