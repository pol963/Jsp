package kr.narp.myapp;

import org.springframework.web.bind.annotation.RequestMapping;

//스프링프로젝트에서는 기본적으로 jsp프로젝트와 다르게 기본적으로 제공해주는 기능들이 있습니다.
// 1. mapping를 제공(메서드에서 어노테이션으로 바로.) 2.pojo의 갯수감소 3. viewResolver기능을 제공. 4. 프론트컨트롤러 제공.
public class MemberController {

	//RequestMapping 어노테이션은 memberList.do요청이 왔을때 메서드를 실행시키는 것.
	@RequestMapping("/memberList.do")
	public String memberList() {
		
		return "memberList";
	}
	
	@RequestMapping("/memberInsert.do")
	public String memberInsert() {
		
		return "redirect:/memberList.do";
	}
	
	@RequestMapping("/memberRegister.do")
	public String memberRegister() {
		
		return "memberRegister";
	}
	
}
