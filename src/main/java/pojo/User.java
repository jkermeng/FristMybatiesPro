package pojo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString(includeFieldNames = true)
public class User {
    private Integer sid;
    private String sname;

}
