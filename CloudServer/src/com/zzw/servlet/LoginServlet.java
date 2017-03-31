package com.zzw.servlet;

import com.zzw.dao.ServiceDao;
import com.zzw.utils.CheckMobile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ZZW on 2017/3/26.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收来自手机客户端信息
        String username = request.getParameter("username");
        username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
        String userpass = request.getParameter("password");
        System.out.println(username + "--" + userpass);
        // 新建服务对象
        ServiceDao serv = new ServiceDao();
        // 验证处理
        boolean loged = serv.login(username, userpass);
        if (loged) {
            System.out.print("账号密码匹配");
            request.getSession().setAttribute("username", username);
            // response.sendRedirect("welcome.jsp");
        } else {
            System.out.print("账号密码不匹配");
        }
        // 返回信息到客户端
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String ua = request.getHeader("User-Agent"); //获取的浏览器返回的响应头中的值。
        //判断请求头文件，是浏览器还是移动客户端
        if (!check(request)) {
            out.print("不是移动");
        } else {
//            out.print("用户名：" + username);
//            out.print("密码：" + userpass);
//            out.print("移动端");
            out.print(loged);
            System.out.println(loged+"----");
        }


//        out.print("sdfsfsdfsf");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    /***********************************************************/

    public boolean check(HttpServletRequest request) throws IOException {
        boolean isFromMobile = false;

        HttpSession session = request.getSession();
        //检查是否已经记录访问方式（移动端或pc端）
        if (null == session.getAttribute("ua")/*||""==session.getAttribute("ua")*/) {
            try {
                //获取ua，用来判断是否为移动端访问
                String userAgent = request.getHeader("USER-AGENT").toLowerCase();
                System.out.println(userAgent);
                if (null == userAgent) {
                    userAgent = "";
                }
                isFromMobile = CheckMobile.check(userAgent);
                //判断是否为移动端访问
                if (!userAgent.contains("windows") && !userAgent.contains("linux")/*isFromMobile*/) {
                    System.out.println("移动端访问");
                    session.setAttribute("ua", "mobile");
                } else {
                    System.out.println("pc端访问");
                    session.setAttribute("ua", "pc");
                }
            } catch (Exception e) {
            }
        }/*else{*/
        isFromMobile = session.getAttribute("ua").equals("mobile");
//        }

        return isFromMobile;
    }
}
