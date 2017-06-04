import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/4.
 */
public class SubServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String uclass = req.getParameter("uclass");
        String unumber = req.getParameter("unumber");
        String uflag = req.getParameter("uflag");
        String ushe = req.getParameter("ushe");
        String uname = new String(req.getParameter("uname").getBytes("ISO-8859-1"), "utf-8");
        if (uname == null) {
            uname = "";
        }
        uclass = (uclass == null ? "" : uclass);
        unumber = (unumber == null ? "" : unumber);
        uflag = (uflag == null ? "" : uflag);
        ushe = (ushe == null ? "" : ushe);
//        获取文件路径
        String path = req.getRealPath("/pic/");
        String yuan_path = req.getRealPath("28.jpg");

//        判断文件是否存在
        File file = new File("path");
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
        String tm = new Date().getTime() + "" + ".jpg";
        String first_param = uclass + unumber + " " + uname;

        new ShuiYin().mark(yuan_path, (path + "/" + tm), Color.black, first_param, uflag, ushe);

        RequestDispatcher dispatcher = req.getRequestDispatcher("maketr.html");
        Cookie cookie = new Cookie("resultPath", tm);
        cookie.setMaxAge(60 * 10);
        resp.addCookie(cookie);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
