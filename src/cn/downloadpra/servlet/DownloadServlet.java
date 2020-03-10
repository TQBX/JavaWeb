package cn.downloadpra.servlet;

import cn.downloadpra.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @auther Summerday
 */
@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数(文件名称)
        String filename = request.getParameter("filename");
        //使用字节输入流加载文件进内存

        //获取文件服务器路径
        ServletContext context = this.getServletContext();
        String realPath = context.getRealPath("/img/" + filename);

        //用字节流关联
        FileInputStream fs = new FileInputStream(realPath);

        //设置response的响应头

        //获取mime类型
        String mimeType = context.getMimeType("filename");
        //响应头类型
        response.setHeader("content-type",mimeType);

        //解决中文文件名问题
        //获取user-agent请求头
        String agent = request.getHeader("user-agent");
        //使用工具类方法
        String fileName = DownLoadUtils.getFileName(agent, filename);

        //响应头打开方式
        response.setHeader("content-disposition", "attachment;filename"+fileName);

        //将输入流的数据写出到输出流中
        ServletOutputStream sos = response.getOutputStream();
        //定义缓冲数组
        byte[] buff = new byte[1024*8];
        //定义读到的个数
        int len;
        //循环读进缓冲区fs.read(buff)为读到的个数
        while((len = fs.read(buff))!=-1){
            //输出流写
            sos.write(buff,0,len);
        }
        fs.close();

    }
}
