package com.zzw.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzw.domain.Resource;
import com.zzw.util.DaoUtils;
import com.zzw.util.IOUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

@WebServlet(name = "DownServlet", urlPatterns = {"/DownServlet"})
public class DownServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //1.获取要下载的资源id
        String id = request.getParameter("id");
        //2.根据id查找资源
        Resource r = null;
        try {
            String sql = "select * from netdisk where id = ?";
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            r = runner.query(sql, new BeanHandler<Resource>(Resource.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(r==null){
            //3.如果资源不存在提示
            response.getWriter().write("找不到该资源!!!");
            return;
        }else{
            //4.如果存在提供下载
            response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(r.getRealname(),"UTF-8"));
            response.setContentType(this.getServletContext().getMimeType(r.getRealname()));

            String filePath = this.getServletContext().getRealPath(r.getSavepath()+"/"+r.getUuidname());
            InputStream in = new FileInputStream(filePath);
            OutputStream out = response.getOutputStream();

            IOUtils.In2Out(in, out);
            IOUtils.close(in, null);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
