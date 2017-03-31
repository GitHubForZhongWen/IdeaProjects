package com.zzw.servlet;

import com.zzw.bean.UserInfo;
import com.zzw.dao.ServiceDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by ZZW on 2017/3/26.
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
        // 接收客户端信息

        String username = request.getParameter("username");
        username = URLDecoder.decode(username, "utf-8");
//        username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
        //获取UUIDname得到唯一一个文件名
        String uuidName = UUID.randomUUID().toString() + "_" + username;
        String hashName = Integer.toHexString(uuidName.hashCode());

        String userpswd = request.getParameter("userpswd");
        String usersex = request.getParameter("usersex");
        usersex = URLDecoder.decode(usersex, "utf-8");
//        usersex = new String(usersex.getBytes("ISO-8859-1"), "UTF-8");
        String userphone = request.getParameter("userphone");
        String regtime = request.getParameter("userregtime");
        Timestamp userregtime = Timestamp.valueOf(regtime);
        System.out.println("用户性别--" + usersex);
        UserInfo userInfo = new UserInfo(username, userpswd, usersex, userphone, userregtime,uuidName);
        // 新建服务对象
        ServiceDao serv = new ServiceDao();
        // 验证处理
        boolean registSuccess = serv.register(userInfo);
        if (registSuccess) {
            System.out.println("注册成功");
            String realPath = this.getServletContext().getRealPath("WEB-INF/userData/" + hashName+"_"+username );
            boolean mkdir = new File(realPath).mkdirs();
            if (mkdir) {
                System.out.println("创建基本用户成功");
            } else {
                System.out.println("创建失败");
            }
//            request.getSession().setAttribute("username", username);
            // response.sendRedirect("welcome.jsp");
        } else {
            System.out.print("注册失败");
        }
        // 返回信息到客户端
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(registSuccess);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
