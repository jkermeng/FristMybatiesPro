package controller;


import dao.IExcel;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import lombok.SneakyThrows;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.session.SqlSession;
import pojo.Excel;
import server.MybatiseConnetiong;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/upload")
public class InsertExcel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("添加以调用！！");
        String upfile = req.getParameter("upfile");
        String realPath = this.getServletContext().getRealPath("/webapp/WEB-INF/upload");
        PrintWriter writer = resp.getWriter();
        System.out.println(realPath);
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        List<FileItem> fileItems = null;
        try {
            fileItems = new ServletFileUpload(diskFileItemFactory).parseRequest(req);
            for (FileItem item :
                    fileItems) {
//                String name = item.getName().substring(item.getName().lastIndexOf(".") + 1).toLowerCase();
                InputStream inputStream = item.getInputStream();
                excel(inputStream);
//                BufferedInputStream in = new BufferedInputStream(item.getInputStream());// 获得文件输入流
//                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(name)));// 获得文件输出流
//                org.apache.commons.fileupload.util.Streams.copy(in, out, true);// 开始把文件写到指定的上传文件夹

                inputStream.close();
            }
        } catch (FileUploadException | BiffException e) {
            e.printStackTrace();
        }

    }

    public void excel(InputStream inputStream) throws IOException, BiffException {
        SqlSession myBatise = MybatiseConnetiong.getMyBatise();
        IExcel mapper = myBatise.getMapper(IExcel.class);
//        File file = new File("src/main/resources/testnew.xls");
        Workbook workbook = Workbook.getWorkbook(inputStream);
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
}
