package kr.bit.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileAddController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			//파일을 업로드할 폴더 이름
			String  UPLOAD_DIR="file_repo";
			
			//폴더의 위치는 webapp아래에다가 만들고 그 경로를 찾아가기 위한 변수.-> 업로드 폴더의 실제 경로를 구하기.
			//request.getServletContext -> 요청정보의 서블릿 등록정보를 가져오기.
			//getRealPath -> 실제경로가져오기.
			//File.separator //구분자 + UPLOAD_DIR 
			String uploadPath=request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;
			
			//업로드할 경로를 실제적으로 정의하여 변수만들기. 업로드 경로를 하나의 객체로 만든것. 경로가 있는지없는지판단을 위해.
			File currentDirPath = new File(uploadPath);
			
			//경로가 있는지 없는지 판단.
			if(!currentDirPath.exists()) { //경로가 존재하지 않는다면
				currentDirPath.mkdir(); //경로를 만들기.
			}
			
			
			//파일 업로드에 필요한 api. -> commons-fileupload, commons-io
			//파일을 업로드 받을시 임시로 저장할 경로 설정.
			
			//파일저장을 가능하게 해주는 객체 생성. DiskFileItemFactory->파일 저장을 가능하게 해주는 기능들을 모아놓은 클래스.
			DiskFileItemFactory factory = new DiskFileItemFactory(); 
			//파일저장 경로 설정.
			factory.setRepository(currentDirPath);
			//파일저장공간의 용량 설정.
			factory.setSizeThreshold(1024*1024);
			
			//파일이름 설정을 위한 변수 생성.
			String fileName = null;
			
			//클라이언트가 업로드를 할때 request에 파일이름......등이 넘어온다. -> 요청시에 같이 넘어옴. 
			//이러한 정보들을 쉽게 꺼내올 수 있게 해주는 클래스. 이후 파일 업로드 경로를 설정
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			//예외가 발생할 수 있으니 예외처리.
			try {
				
				//클라이언트의 파일 업로드시 request에 담겨 넘어옵니다. 
				//이때 reqeust안에 있는 파일정보를 쉽게 배열형태로 가져올 수 있게해주는 메서드가 ServletFileUpload클래스의 parseRequest.
				//reqeust에있는 파일정보를 list형태로 변환하여 items에 저장 배열 하나하나를 FileItems라고 부름. 이후 반복문으로 꺼내기.
				List<FileItem> items = upload.parseRequest(request);
				
				//반복문을 이용해서 items에 있는 각 FileItem타입의 배열을 하나씩 꺼내기.
				for(int i=0;i<items.size();i++) {
					//fileItems에는 하나의 파일정보가 담겨있다.
					FileItem fileItems = items.get(i);
					
					//fileItems에는 파일이 넘어오나 파라메터도 넘어올 수 있습니다. ajax를 사용하지 않을 시에.
					//isFormField메서드는 데이터가 파라메터 데이터인지 체크해주는 메서드.
					if(fileItems.isFormField()) { // 파라메터 데이터인지.
						//데이터의 이름과 값을 스트링형태로 출력. getString 메서드는 인코딩을 정할 수 있습니다.
						System.out.println(fileItems.getFieldName()+"="+fileItems.getString("utf-8"));
						
					}else { //파일 데이터인지.
						//파일이 정상적으로 들어왔는지 체크.
						if(fileItems.getSize()>0) { //파일이 있다면. -> 파일이 임시 저장소에 잘 들어가 있가면.
							//fileItems에는 하나의 FileItems타입의 배열 파일이 들어있습니다.
							//getName메서드는 전체 경로 가져오기. 
							//lastIndexof는 뒤에서부터 소괄호 안에있는 문자가 있는 다음인덱스를 리턴.
							//해당 부분을 저장.
							int idx=fileItems.getName().lastIndexOf("\\"); // \\는 윈도우의 경우.
							
							//os가 윈도우가 아닌 경우의 수도 생각.
							if(idx==-1) { //-1인경우는 \\인식하지 못하고 파일이름을 찾지 못했을때
								//리눅스의 경우 / 로 경로 구분.
								idx=fileItems.getName().lastIndexOf("/");
							}
							//fileItems에는 파일이 담겨있는데 getName를 해서 경로와 이름전부를 가져오고
							//subString메서드를 이용하여 인자로 있는 idx(//or\)인덱스에서 1을 더하여 파일이름이있는 인덱스를 리턴
							fileName=fileItems.getName().substring(idx+1); //-> 파일 이름.
							
							//임시파일저장경로에다가 파일을 만들기.
							
							//파일을 실제로 만들기 위해 파일 객체 생성. 이때 임시 저장 경로에서 옮겨서 저장.
							File uploadFile = new File(currentDirPath+"\\"+fileName);
							
							//파일을 만들었는대 기존에 있는 파일이름일 수 있기에 중복체크해주기.
							if(uploadFile.exists()) { //존재한다면
								//중복이 된다면 reName해줘야 합니다.
								//currentTimeMillis메서드를 이용하여 시분초를 가져와서 _바를 넣고 거기에 다시 fileName를 붙여서 다시 FileName에 저장.
								fileName = System.currentTimeMillis()+"_"+fileName;
								//다시 업로드 작업하기.
								uploadFile = new File(currentDirPath+"\\"+fileName);
							}
							//write는 업로드된 파일을 디스크에 저장해주는 메서드 -> 임시경로에서 새로운경로에 파일쓰기.
							//임시경로가 새로운경로.
							fileItems.write(uploadFile);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//업로드한 파일을 새로운 경로에 파일쓰기를 했다면 ajax에 파일 이름을 리턴해주기 -> DB에 저장하기위해.
			response.setContentType("text/html;charset=euc-kr"); //파일깨짐방지.
			response.getWriter().print(fileName);
		return null;
	}

}














