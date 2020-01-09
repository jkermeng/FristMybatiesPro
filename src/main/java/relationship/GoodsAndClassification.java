package relationship;

import lombok.*;
import pojo.Classification;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(includeFieldNames = true)
public class GoodsAndClassification {
    private Integer gid;
    private String gname;
    private double gprice;
    private Integer gstock;
    private Integer cid;
    private String cname;
}
