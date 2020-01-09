package dao;



import org.apache.ibatis.annotations.Param;
import pojo.More;
import pojo.ShopCart;
import pojo.User;

import java.util.List;

public interface SidMethodFactory {
    List<User> selectsid(User user);

    List<User> selectByMoreUid(@Param(value = "idList") List<Integer> idList);

    void deleteByMoreUid(More more);

    void insertMoreUserToSid(@Param(value = "list") List<User> ulist);
    //“ª∂‘“ª
    List<ShopCart> selectShopCartOneToOne();


}
