package kr.narp.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//어노테어션(@)이 있다면 전처리가 먼저 됩니다.-> Spring Container
@Controller
public class MemberController {

	//개별로 어노테이션으로 요청이왔을시에 처리.
	@RequestMapping("/memberList.do")
	public String memberList() {
		
		return "memberList";
	}
	
	@RequestMapping("/memberInsert.do")
	public String memberInsert() {
		
		return "redirect:/memberInsert";
	}
	
	@RequestMapping("/memberRegister.do")
	public String memberRegister() {
		
		return "memberRegister";
	}
	
}
