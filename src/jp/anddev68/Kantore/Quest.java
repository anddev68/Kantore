package jp.anddev68.Kantore;

import java.util.Random;

public class Quest {

	char op;	//演算子
	int x1;	//項1
	int x2;	//項2
	
	
	public void create(){
		Random r = new Random();
		x1 = r.nextInt(100);
		x2 = r.nextInt(100);
		
		if(r.nextInt() %2 == 0){
			op='+';
		}else{
			op='-';
		}
		
	}
	
	
	public String getQuest(){
		return ""+x1+op+x2;
	}
	
	public boolean isCollect(String str){
		String str2 = "";
		
		switch(op){
		case '+':
			str2 = ""+(x1+x2);
			break;
		case '-':
			str2 = ""+(x1-x2);
			break;
		}
		
		if(str.contentEquals(str2)) return true;
		
		return false;
	}
}
