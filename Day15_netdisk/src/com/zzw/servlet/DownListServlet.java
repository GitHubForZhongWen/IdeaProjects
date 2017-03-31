package com.zzw.servlet;

import com.zzw.domain.Resource;
import com.zzw.util.DaoUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ZZW on 2017/3/25.
 */
@WebServlet(name = "DownListServlet", urlPatterns = {"/DownListServlet"})
public class DownListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查找数据库中所存在的文件数据
        String sql = "select * from netdisk";
        QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        List<Resource> list = null;
        try {
            //??????????????
            list = runner.query(sql, new BeanListHandler<Resource>(Resource.class));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //存入request域中并带入到jsp进行下载
        request.setAttribute("list",list);
        request.getRequestDispatcher("/DownList.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
