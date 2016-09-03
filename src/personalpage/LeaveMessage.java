package personalpage;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LeaveMessage extends HttpServlet{

	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws IOException,ServletException{
		System.out.println("doGet:");
		String name = request.getParameter("username");
		String message = request.getParameter("content");
		
		String path=request.getSession().getServletContext().getRealPath("/");//取得绝对路径
		
		File f=new File(path+"message.txt");//File f=new File("message.txt")默认会到eclipse安装路径下，？？
		System.out.println(f.getAbsolutePath());
		
		FileWriter write=new FileWriter(f,true);//true表示追加文件内容
		if(f.exists()){
			write.write(name+":\r\n");
			write.write(message+"\r\n");
			write.close();
		}
		System.out.println(name+"  "+message);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("MessageBoard.jsp");
		dispatcher.forward(request,response);
	}
	
	
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws IOException,ServletException{
		doGet(request,response);
		System.out.println("doPost method");
	}
}
