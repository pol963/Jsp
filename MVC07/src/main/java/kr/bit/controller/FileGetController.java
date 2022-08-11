package kr.bit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.bytecode.ByteArray;

public class FileGetController implements Controller{

	//파일 다운로드 컨트롤러 구현.
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//content에서 넘겨준 filename받기. 
		String filename = request.getParameter("filename");
		System.out.println(filename);
		
		//다운로드할 파일이 있는 경로의 폴더를 변수로 지정.
		String UPLOAD_DIR = "file_repo";
		
		//실제 물리적인 주소 가져오기.
		String uploadPath=request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;
		
		//실제 디렉토리를 파일 객체로 가져오기.
		File f = new File(uploadPath+"\\"+filename);
		
		//다운로드 준비. 다운로드창을 띄우기.
		//다운로드준비를 위해 클라이언트도 준비를 해야합니다.
		//깨짐방지. 클라이언트로부터 넘어온 이름의 한글이 꺠지지않게.
		filename=URLEncoder.encode(filename,"UTF-8");
		
		//크롬의 경우 다운로드경우 공백의 경우를 +로바꾸어서 저장하는경우가 있습니다. +를 공백으로 변경.
		filename=filename.replace("+", " ");
		
		//클라이언트의 컴퓨터에 다운로드. -> 서버의 응답. 규칙이며 아래 header까지 보통적인 규칙.
		response.setContentLength((int)f.length());
		//데이터의 타입을 알려주기.
		response.setContentType("application/x-msdownload;charset=utf-8");
		//구글크롬이나 인터넷익스플로어에서 파일은 다운로드할때 나타나는 창을 구현. 창->setHeader 
		response.setHeader("Content-Disposition", "attachment;filename="+filename+";");
		//서버에서 클라이언트로 넘겨주는 데이터는 binary 데이터로 넘겨줘야 합니다.
		response.setHeader("Content-Transfer-Encoding", "binary");
		//규칙 
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		
		
		//실제 다운로드 부분 -> IO입출력.
		//폴더에서 파일을 내려보내야 하기때문에 먼저 업로드된 폴더에서 파일을 읽어서 다운로드하기를 준비.
		FileInputStream in = new FileInputStream(f);  //읽기 준비.
		
		//출력스트림 response 서버에서 클라이언트로.
		OutputStream out = response.getOutputStream();
		
		//1024바이트만큼 버퍼링을 이용해서 출력.
		byte[] buffer = new byte[1024];
		
		//반복문 이용.
		while(true) {
			//in에서 지정한 업로드파일을 buffer만큼 읽어서 count에 저장.
			int count = in.read(buffer);
			//count가 -1이 된다는 것은 데이상 데이터가 없다는것.
			if(count == -1) {
				break;
			}
			//모두 count에 담았다면 이후에는 클라이언트에 써주기.
			//buffer에 있는 데이터를 0(처음부터) , count까지.
			out.write(buffer, 0, count); //다운로드가 되는것.
			
		}//while
		in.close();
		out.close();
		
		return null;
	}

}



















