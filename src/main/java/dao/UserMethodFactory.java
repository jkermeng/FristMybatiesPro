package dao;

import pojo.Users;

public interface UserMethodFactory {
    public Users selectUserByUid(int uid);
}
