package test;

import cn.jk.App;
import com.alibaba.fastjson.JSONObject;
import dao.IExcel;
import dao.IGoods;
import dao.SidMethodFactory;
import dao.UserMethodFactory;


import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pojo.*;
import relationship.GoodsAndClassification;
import server.MybatiseConnetiong;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JunityTest {

    public void tes9() throws IOException {
        SqlSession myBatise = MybatiseConnetiong.getMyBatise();
        IGoods mapper = myBatise.getMapper(IGoods.class);
        Classification classInstrumentation = mapper.selectClassificationByCid(1);
        System.out.println(classInstrumentation.getCname());
        myBatise.commit();
        myBatise.close();
        System.out.println("success");

    }

    public void tes8() throws IOException {
        SqlSession myBatise = MybatiseConnetiong.getMyBatise();
        IGoods mapper = myBatise.getMapper(IGoods.class);
        List<GoodsAndClassification> classifications = mapper.selectGoodsToManny();
        System.out.println(classifications);
        myBatise.commit();
        myBatise.close();
        System.out.println("success");

    }

    public void test7() throws IOException {
        SqlSession myBatise = MybatiseConnetiong.getMyBatise();
        SidMethodFactory mapper = myBatise.getMapper(SidMethodFactory.class);
        List<ShopCart> shopCarts = mapper.selectShopCartOneToOne();
        System.out.println(shopCarts);
        myBatise.commit();
        myBatise.close();
        System.out.println("success");

    }

    @Test
    public void excelOutFromDatabase(List<Excel> excels) throws IOException, WriteException {
        WritableWorkbook workbook = Workbook.createWorkbook(new File("C:\\Users\\admin\\Desktop\\asb.xls"));
        WritableSheet writableSheet = workbook.createSheet("第一页", 0);
        //根据数据库字段名称变化而变化
        Label Label1 = new Label(0, 0, "id");
        Label Label2 = new Label(1, 0, "name");
        Label Label3 = new Label(2, 0, "age");
        Label Label4 = new Label(3, 0, "gender");
        writableSheet.addCell(Label1);
        writableSheet.addCell(Label2);
        writableSheet.addCell(Label3);
        writableSheet.addCell(Label4);
        for (int i = 0; i < excels.size(); i++) {
            Excel excel = excels.get(i);
            //数据库有字段多少就有多少Label
            writableSheet.addCell(new Label(0, i + 1, excel.getId().toString()));
            writableSheet.addCell(new Label(1, i + 1, excel.getName()));
            writableSheet.addCell(new Label(2, i + 1, excel.getAge().toString()));
            writableSheet.addCell(new Label(3, i + 1, excel.getGender()));
            workbook.write();
            workbook.close();
        }

    }

    public void excelIntoDatabase() throws IOException, BiffException {
        SqlSession myBatise = MybatiseConnetiong.getMyBatise();
        IExcel mapper = myBatise.getMapper(IExcel.class);
        File file = new File("src/main/resources/testnew.xls");
        Workbook workbook = Workbook.getWorkbook(file);
        Sheet[] sheets = workbook.getSheets();
        List<Excel> list = new ArrayList<>();
        for (Sheet sheet : sheets
        ) {
            int rows = sheet.getRows();
            int columns = sheet.getColumns();
            for (int row = 1; row < rows; row++) {
                Excel excel = new Excel();
                for (int column = 0; column < columns; column++) {
                    if (column == 0) {
                        excel.setId(Integer.valueOf(sheet.getCell(column, row).getContents()));
                    } else if (column == 1) {
                        excel.setName(sheet.getCell(column, row).getContents());
                    } else if (column == 2) {
                        excel.setAge(Integer.valueOf(sheet.getCell(column, row).getContents()));
                    } else if (column == 3) {
                        excel.setGender(sheet.getCell(column, row).getContents());
                    }
                }
                list.add(excel);
            }
        }
        mapper.insertMoreExcel(list);
        myBatise.commit();
        myBatise.close();
        workbook.close();
    }

    public void test6() throws IOException {

        SqlSession myBatise = MybatiseConnetiong.getMyBatise();
        SidMethodFactory mapper = myBatise.getMapper(SidMethodFactory.class);
        List<User> list = new ArrayList<>();
        for (int i = 10; i < 14; i++) {
            User u1 = new User(i, "张三");
            list.add(u1);
        }
        mapper.insertMoreUserToSid(list);
        System.out.println(list);

        myBatise.commit();
        myBatise.close();
        System.out.println("success");

    }

    public void test5() throws IOException {

        SqlSession myBatise = MybatiseConnetiong.getMyBatise();
        SidMethodFactory mapper = myBatise.getMapper(SidMethodFactory.class);
        List<Integer> idList = new ArrayList<>();
        idList.add(4);
        idList.add(5);
        More more = new More();
        more.setUid(idList);
        mapper.deleteByMoreUid(more);
        myBatise.commit();
        System.out.println("success");
        myBatise.close();

    }

    public void test4() throws IOException {

        SqlSession myBatise = MybatiseConnetiong.getMyBatise();
        SidMethodFactory mapper = myBatise.getMapper(SidMethodFactory.class);
        List<Integer> idList = new ArrayList<>();
        idList.add(2);
        idList.add(4);
        idList.add(5);
        List<User> users = mapper.selectByMoreUid(idList);
        Object o = JSONObject.toJSON(users);
        System.out.println(users);
        System.out.println(o.toString());
        myBatise.close();

    }

    public void test3() throws IOException {
        SqlSession myBatise = MybatiseConnetiong.getMyBatise();
        SidMethodFactory mapper = myBatise.getMapper(SidMethodFactory.class);
        User user = new User();
//        user.setSid("59222351-3038-11ea-9e4a-484d7eb47a4a");
        List<User> selectsid = mapper.selectsid(user);
        Object o = JSONObject.toJSON(selectsid);
        System.out.println(selectsid);
        System.out.println(o.toString());
        myBatise.close();
    }

    public void test2() throws IOException {
        Users users1 = new Users();
        System.out.println(users1.getUid());
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis_conf.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        UserMethodFactory mapper = sqlSession.getMapper(UserMethodFactory.class);
        Users users = mapper.selectUserByUid(17);
        System.out.println(users);
    }

    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis_conf.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        System.out.println("MyBatis运行成功");
        System.out.println("-------------selectOne--------------");
        UserMethodFactory mapper = sqlSession.getMapper(UserMethodFactory.class);
        Users users = mapper.selectUserByUid(12);
        System.out.println(users);
        System.out.println("-------------selectAll--------------");
        List<Users> list = sqlSession.selectList("selectAll");
        System.out.println(list);
        System.out.println("-------------selectLike--------------");
        Users users2 = sqlSession.selectOne("selectLike", "三");//'%${value}%'方法
//        Users users2 = sqlSession.selectOne("selectLike", "%三%");//#{asd}方法
        System.out.println(users2);
        System.out.println("---------------Insert------------");
        Users users1 = new Users();
        users1.setUname("知识大全");
        users1.setUrname("围棋");
        users1.setUphone("12312333");
        users1.setUpassword("99999");
//        int insertOne = sqlSession.insert("insertOne", users1);
//        System.out.println(insertOne);
        System.out.println("-------------Insert拿自增id--------------");
//        int insertOne = sqlSession.insert("insertOne", users1);
        System.out.println("自增id:  " + users1.getUid());
        System.out.println("-------------Insert拿UUID自增--------------");
        User users3 = new User();
//        users3.setName("张三");
//        int insertUUID = sqlSession.insert("insertUUID", users3);
//        System.out.println(insertUUID);
        System.out.println("-------------Update方法--------------");
        Users users4 = new Users();
        users4.setUname("newNames");
        users4.setUid(25);
        users4.setUpassword("7788");
//        int updateUser = sqlSession.update("updateUser", users4);
//        System.out.println("已更新"+updateUser+"个");
        System.out.println("-------------Delect方法--------------");
//        int deleteByUid = sqlSession.delete("deleteByUid", 25);
//        System.out.println("已删除"+deleteByUid+"个");
        sqlSession.commit();
        sqlSession.close();
    }
}
