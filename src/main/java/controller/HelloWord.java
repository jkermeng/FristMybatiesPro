package controller;

import com.alibaba.fastjson.JSONObject;
import dao.IExcel;
import dao.IGoods;
import org.apache.ibatis.session.SqlSession;
import pojo.Classification;
import server.MybatiseConnetiong;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/test")
public class HelloWord extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession myBatise = MybatiseConnetiong.getMyBatise();
        IGoods mapper = myBatise.getMapper(IGoods.class);
        Classification classification = mapper.selectClassificationByCid(1);
        Object o = JSONObject.toJSON(classification);
        resp.getWriter().write(o.toString());
        String test = req.getParameter("test");
        System.out.println(test);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
