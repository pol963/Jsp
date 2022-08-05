package kr.bit.frontcontroller;

import java.util.HashMap;

import kr.bit.controller.Controller;
import kr.bit.controller.MemberContentController;
import kr.bit.controller.MemberDeleteController;
import kr.bit.controller.MemberInsertController;
import kr.bit.controller.MemberListController;
import kr.bit.controller.MemberLoginController;
import kr.bit.controller.MemberLogoutController;
import kr.bit.controller.MemberRegisterController;
import kr.bit.controller.MemberUpdateController;

public class HandlerMapping {

	//변수선언.
	private HashMap<String,Controller> mappings;
	
	//생성자. 호출될때 mappings에 key와 value값들이 저장.
	public HandlerMapping() {
		
		//생성자 객체가 생성이될때 해쉬맵 객체 생성.
		mappings = new HashMap<String, Controller>();
		
		//key : 경로 // value : POJOController --> put메서드를 이용하여 넣어주기. memberList.do 요청잉면 ListController가 호출.
		mappings.put("/memberList.do", new MemberListController());
		mappings.put("/memberInsert.do", new MemberInsertController());
		mappings.put("/memberRegister.do", new MemberRegisterController());
		mappings.put("/memberContent.do", new MemberContentController());
		mappings.put("/memberUpdate.do", new MemberUpdateController());
		mappings.put("/memberDelete.do", new MemberDeleteController());
		mappings.put("/memberLogin.do", new MemberLoginController());  
		mappings.put("/memberLogout.do", new MemberLogoutController());
		
	}
	
	//넘겨주는 메서드. mapping안에 있는값중 인자로받은key값에 해당하는 value를 리턴.
	public Controller getController(String key) { //key에는 memberList.do와같은 key값이 들어옵니다.
		
		//get메서드로인해 key에 해당하는 값(Controller)을 frontController에게 리턴하여줍니다. 
		//key에대한 Member''controller()가 호출이되고 그값이 리턴.
		return mappings.get(key); 
		
	}
	
	
	
}
