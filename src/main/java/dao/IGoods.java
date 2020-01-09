package dao;


import pojo.Classification;
import relationship.GoodsAndClassification;

import java.util.List;

public interface IGoods {
    List<GoodsAndClassification> selectGoodsToManny();

//    Classification selectGoodsToManny2();

    Classification selectClassificationByCid(Integer cid);
}
