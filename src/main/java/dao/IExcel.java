package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Excel;

import java.util.List;

public interface IExcel {
    void insertMoreExcel(@Param(value = "list") List<Excel> list);
}
