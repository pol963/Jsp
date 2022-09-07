package kr.narp.myapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;


//어노테어션(@)이 있다면 전처리가 먼저 됩니다.-> Spring Container
@Controller
public class MemberController {

	//DAO클래스에서 Spring Container메모리로 올려둔 DAO클래스를 AutoWired로 끌어와서 사용.
	@Autowired
	private MemberDAO dao;
	
	//개별로 어노테이션으로 요청이왔을시에 처리.
	@RequestMapping("/memberList.do")
	public String memberList(Model model) { //Model == HttpServletReqeust 객체바인딩에필요.
		
		//Autowired어노테이션에 의해 따로 객체생성(new)를 하지않아도 사용 가능.
		List<MemberVO> list = dao.memberList();
		model.addAttribute("list",list); //객체바인딩.
		
		//스프링은 아래와같은 방법으로 사용X
		//MemberDAO dao = new MemberDAO();
		
		
		
		return "memberList";
	}
	
	@RequestMapping("/memberInsert.do")
	public String memberInsert(MemberVO vo) { //파라메터 수집 -> not request.getpa....내부적으로 처리.
		//jsp와 다르게 메서드에서 바로인자로 받아줄 수 있습니다. -> vo = new memberVO(); 내부적인 처리. memberVO클래스와 회원가입파라메터의 name가 같아야하며,기본생성자가 필수.
		
		//한글깨짐방지(인코딩)
		
		
		//형식적인 cnt 
		int cnt = dao.memberInsert(vo);
		
		
		return "redirect:/memberList.do";
	}
	
	
	
	@RequestMapping("/memberRegister.do")
	public String memberRegister() {
		
		return "memberRegister";
	}
	
}
