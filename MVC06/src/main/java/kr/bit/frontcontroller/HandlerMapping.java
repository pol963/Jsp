package kr.bit.frontcontroller;

import java.util.HashMap;

import kr.bit.controller.Controller;
import kr.bit.controller.MemberContentController;
import kr.bit.controller.MemberDeleteController;
import kr.bit.controller.MemberInsertController;
import kr.bit.controller.MemberListController;
import kr.bit.controller.MemberRegisterController;
import kr.bit.controller.MemberUpdateController;

public class HandlerMapping {

	//변수선언.
	private HashMap<String,Controller> mappings;
	
	//생성자.
	public HandlerMapping() {
		
		//생성자 객체가 생성이될때 해쉬맵 객체 생성.
		mappings = new HashMap<String, Controller>();
		
		//key : 경로 // value : POJOController
		mappings.put("/memberList.do", new MemberListController());
		mappings.put("/memberInsert.do", new MemberInsertController());
		mappings.put("/memberRegister.do", new MemberRegisterController());
		mappings.put("/memberContent.do", new MemberContentController());
		mappings.put("/memberUpdate.do", new MemberUpdateController());
		mappings.put("/memberDelete.do", new MemberDeleteController());
		mappings.put("/memberLogin.do", new MemberLoginController());
		
	}
	
	public Controller getController(String key) {
		
		return mappings.get(key);
		
	}
	
	
	
}
