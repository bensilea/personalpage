package test1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadFile extends HttpServlet{
	
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintStream out= new PrintStream(response.getOutputStream());
		out.println("button clicked page"+"</br>");
		
		String tempFileName = (String) request.getSession().getId();
        // create the tempfile.
        File temp = new File("c:/", tempFileName);
        FileOutputStream o = new FileOutputStream(temp);
        long start=System.currentTimeMillis();
        System.out.println(start);
        if (request.getContentLength() > 297) {
            // write theupload content to the temp file.
            InputStream in = request.getInputStream();
            byte b[] = new byte[1024];
            int n;
            while ((n = in.read(b)) != -1) {
                o.write(b, 0, n);
            }
            o.close();
            in.close();
            // read the tempfile.
            RandomAccessFile random = new RandomAccessFile(temp, "r");
            // read Line2 tofind the name of the upload file.
            int second = 1;
            String secondLine = null;
            while (second <= 2) {
                secondLine = random.readLine();
                second++;
            }
 
            // get the lastlocation of the dir char.'\\'.
            int position = secondLine.lastIndexOf('\\');
            // get the nameof the upload file.
            String fileName = secondLine.substring(position + 1,
                    secondLine.length() - 1);
            fileName = new String( fileName.getBytes("ISO-8859-1") , "UTF-8");//解决乱码
            // relocate tothe head of file.
            random.seek(0);
            // get thelocation of the char.'Enter' in Line4.
            long forthEndPosition = 0;
            int forth = 1;
            while ((n = random.readByte()) != -1 && (forth <= 4)) {
                if (n == '\n') {
                    forthEndPosition = random.getFilePointer();
                    forth++;
                }
            }
            File realFile = new File("c:/", fileName);
            RandomAccessFile random2 = new RandomAccessFile(realFile, "rw");
            // locate the endposition of the content.Count backwards 6 lines.
            random.seek(random.length());
            long endPosition = random.getFilePointer();
            long mark = endPosition;
            int j = 1;
            while ((mark >= 0) && (j <= 6)) {
                mark--;
                random.seek(mark);
                n = random.readByte();
                if (n == '\n') {
                    endPosition = random.getFilePointer();
                    j++;
                }
            }
            // locate to thebegin of content.Count for 4 lines's end position.
            random.seek(forthEndPosition);
            long startPoint = random.getFilePointer();
            // read the realcontent and write it to the realFile.
            while (startPoint < endPosition - 1) {
                n = random.readByte();
                random2.write(n);
                startPoint = random.getFilePointer();
            }
            random2.close();
            random.close();
            // delete the tempfile.
            temp.delete();
            System.out.println("File uploadsuccess!");
        } else {
            System.out.println("Nofile!");
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时"+(end-start));
    }
}
