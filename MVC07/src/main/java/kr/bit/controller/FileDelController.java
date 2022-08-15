package kr.bit.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

public class FileDelController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath();
		
		int num = Integer.parseInt(request.getParameter("num"));
		String filename = request.getParameter("filename");
		
		//filename의 한글깨짐방지.
		filename = URLEncoder.encode(filename,"UTF-8");
		
		//filename의 +기호 변경.
		filename = filename.replace("+", " ");
		
		//파일을 삭제하려면 파일의 물리적 경로가 필요. + 해당경로를 하나의 객체로 만들기.
		String  UPLOAD_DIR="file_repo";
		String uploadPath=request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;
		File file = new File(uploadPath+"\\"+filename);
		
		//파일의 존재여부를 확인하고 삭제하기. -> 실제 물리적인 디렉토리에서 삭제.
		if(file.exists()) {
			file.delete();
			System.out.println("디렉토리에서 파일이 삭제되었습니다.");
		}
		
		//디렉토리에서 삭제했다면 DB에서도 null처리 하기(Update).
		MemberDAO dao = new MemberDAO();
		dao.memberDeleteFile(num);
		
		
		return "redirect:"+ctx+"/memberContent.do?num="+num;
	}

	
	
}
