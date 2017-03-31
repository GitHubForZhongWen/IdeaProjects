package com.zzw.servlet;

import com.zzw.domain.Resource;
import com.zzw.util.DaoUtils;
import com.zzw.util.IOUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ZZW on 2017/3/25.
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        try {
            Map pmap = new HashMap();
            //定义数据访问（上传）的地址
            String uploadUrl = this.getServletContext().getRealPath("WEB-INF/upload");
            //定义数据上传的临时文件缓存地址
            String tempUrl = this.getServletContext().getRealPath("WEB-INF/temp");
            System.out.println("ip--------"+request.getRemoteAddr());
            pmap.put("ip", request.getRemoteAddr());//存储客户机IP地址
            //建立数据工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置内存缓冲去大小
            factory.setSizeThreshold(1024 * 100);
            //设置临时文件位置
            factory.setRepository(new File(tempUrl));
            //建立文件上传核心类
            ServletFileUpload fileUpload = new ServletFileUpload(factory);

            //设置文件名的编码格式，防止文件名乱码
            fileUpload.setHeaderEncoding("utf-8");
            fileUpload.setFileSizeMax(1024 * 1024 * 100);
            fileUpload.setSizeMax(1024 * 1024 * 200);

            if (!fileUpload.isMultipartContent(request)) {
                throw new RuntimeException("文件表单不正确");
            }

            //解析request
            List<FileItem> list = fileUpload.parseRequest(request);
            for (FileItem item : list) {
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String value = item.getString("utf-8");
                    System.out.println(fieldName + "--" + value);
                    //获取描述信息
                    pmap.put(fieldName, value);
                } else {
                    String realName = item.getName();
                    InputStream in = item.getInputStream();
                    //获取UUIDname得到唯一一个文件名
                    String uuidName = UUID.randomUUID().toString() + "_" + realName;
                    String hashName = Integer.toHexString(uuidName.hashCode());
                    pmap.put("realname", realName);
                    pmap.put("uuidname", uuidName);
                    String savepath = "WEB-INF/upload";
                    for (char c : hashName.toCharArray()) {
                        //拼接目录
                        uploadUrl += "/" + c;
                        savepath += "/" + c;
                    }
                    //创建目录
                    new File(uploadUrl).mkdirs();
                    pmap.put("savepath", savepath);
                    OutputStream out = new FileOutputStream(new File(uploadUrl, uuidName));

                    IOUtils.In2Out(in, out);
                    IOUtils.close(in, out);
                    //删除临时文件
                    item.delete();
                }
            }
            //向数据库中添加数据（url地址）
            Resource r = new Resource();
            BeanUtils.populate(r, pmap);

            //将数据插入到数据库中
            String sql ="insert into netdisk values(null,?,?,?,?,null,?)";
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            runner.update(sql,r.getUuidname(),r.getRealname(),r.getSavepath(),r.getIp(),r.getDescription());

            //回主页
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
