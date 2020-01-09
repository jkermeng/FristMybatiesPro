package pojo;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Goods {
    private Integer gid;
    private String gname;
    private double gprice;
    private Integer gstock;
    private Classification classifications;

}
