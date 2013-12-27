package jp.anddev68.Kantore;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;


/**
 * 艦隊トレーニング
 * 
 * 2013/12/25 update
 * 
 * ソースコードはなるべく見やすく書いてるつもりなので許して
 * 
 * 構成
 * -HeaderView
 * -wapper
 *  -calc
 *  -PaintView
 * 
 * 
 * 
 * 
 * @author 2011e_000
 *
 */
public class MainActivity extends Activity implements OnClickListener{

	Button button1;		// View切り替えボタン
	Button button2;
	ArrayList<Button> buttons;	//	電卓のボタン
	
	ViewFlipper viewFlipper;	//	Viewを切り替えるやつ
	
	
	
	
	/* 計算機のコールバックインタフェース */
	final OnClickListener calcListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			Button btn = (Button) v;
			String str = (String) btn.getText();
			TextView tv = (TextView) findViewById(R.id.textView1);
			String str2 = (String) tv.getText();
			
			if(str.contentEquals("AC")){
				//	空にする
				tv.setText("0");
			}else if( str.contentEquals("ENTER") ){
				//	送信処理
			}else{
				//	0なら空にする
				if( str2 == "0") str2 = "";
				//	押されたボタンの内容を追加
				str2+=str;
				tv.setText(str2);
			}
			
		}
		
		
	};	
	
	/* Viewの切り替えインターフェース */
	final OnClickListener changeViewListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			
			
		}
	
		
	};
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button1 = (Button) findViewById(R.id.change_calc);
		button2 = (Button) findViewById(R.id.change_memo);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);
		viewFlipper.addView(new PaintView(this));
		
		buttons = new ArrayList<Button>();
		buttons.add((Button) findViewById(R.id.button1));
		buttons.add((Button) findViewById(R.id.button2));
		buttons.add((Button) findViewById(R.id.button3));
		buttons.add((Button) findViewById(R.id.button4));
		buttons.add((Button) findViewById(R.id.button5));
		buttons.add((Button) findViewById(R.id.button6));
		buttons.add((Button) findViewById(R.id.button7));
		buttons.add((Button) findViewById(R.id.button8));
		buttons.add((Button) findViewById(R.id.button9));
		buttons.add((Button) findViewById(R.id.button10));
		buttons.add((Button) findViewById(R.id.button11));
		buttons.add((Button) findViewById(R.id.button12));
		buttons.add((Button) findViewById(R.id.button13));
		buttons.add((Button) findViewById(R.id.button14));
		for(int i=0; i<buttons.size(); i++){
			buttons.get(i).setOnClickListener(calcListener);
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	


	@Override
	public void onClick(View arg0) {
		
		
		switch(arg0.getId()){
		case R.id.change_calc:	//	電卓に切り替え
			viewFlipper.showPrevious();
			break;
		case R.id.change_memo:	//	メモ帳に切り替え
			viewFlipper.showNext();
			break;
			
			
		}
		
	}
	
	
	
	
	
	
	
	
	

}
