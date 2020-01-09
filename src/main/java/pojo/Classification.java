package pojo;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Classification {
    private Integer cid;
    private String cname;
    private List<Goods> goodsList;
}
