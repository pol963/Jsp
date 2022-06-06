package kr.web.util;

//1~10까지의 합을 처리하는 클래스 -> 비즈니스 로직 분리

public class MyUtil {
	public int hap() {
		int sum = 0;
		for(int i = 1; i<=100;i++){
			sum+=i;
		}
		return sum;
		
	}
	
}
