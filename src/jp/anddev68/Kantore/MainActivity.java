package jp.anddev68.Kantore;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
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

	Timer timer;			//	タイマー処理用
	Handler handler;		//	ハンドラ―
	MainActivity activity;
	
	
	Button button1;		// View切り替えボタン
	Button button2;
	ArrayList<Button> buttons;	//	電卓のボタン
	
	TextView tv_timer;	//	残り時間
	TextView tv_quest;	//	問題文
	TextView tv_score;	//	点数
	
	ImageView iv_cross;		//	正解アニメーション
	ImageView iv_circle;	//	不正解アニメーション
	
	HeaderView headerView;
	
	ViewFlipper viewFlipper;	//	Viewを切り替えるやつ
	
	Quest quest;	//	問題作成用
	int score;		//	点数
	
	
	/* 計算機のコールバックインタフェース */
	OnClickListener calcListener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			Button btn = (Button) v;
			String str = (String) btn.getText();
			TextView tv = (TextView) findViewById(R.id.result);
			String str2 = (String) tv.getText();
			
			if(str.contentEquals("AC")){
				//	空にする
				tv.setText("0");
			}else if( str.contentEquals("ENTER") ){
				//	問題文の正誤判定
				if( quest.isCollect(str2)){
					//	スコア追加
					score+=10;
					tv_score.setText(""+score);
					//	アニメーション追加
					startCircleAnimation();
				}else{
					//	アニメーション追加
					startCrossAnimation();
				}
				
				//	問題再作成
				quest.create();
				tv_quest.setText(quest.getQuest());
				tv.setText("");
				
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
	OnClickListener changeViewListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.change_calc:	//	電卓に切り替え
				viewFlipper.showPrevious();
				break;
			case R.id.change_memo:	//	メモ帳に切り替え
				viewFlipper.showNext();
				break;
			}
			
		}
	
		
	};
	
	/* 毎秒ごとのタイマー処理 */
	TimerTask timerTask = new TimerTask(){

		@Override
		public void run() {
			handler.post(new Runnable(){

				@Override
				public void run() {
					int i = Integer.decode((String) tv_timer.getText());
					i--;
					if(i>=0){
						//	時間内なら減らす
						tv_timer.setText(""+i);
						
						//	艦隊を進める
						headerView.advanceKantai(Integer.parseInt((String) tv_timer.getText()));
						
					}else{
						//	タイムオーバー
						new AlertDialog.Builder(activity)
						.setTitle("YourScore:"+score)
						.setPositiveButton("OK", new DialogInterface.OnClickListener(){

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent = new Intent(activity,TitleActivity.class);
								activity.startActivity(intent);
								
							}


							
						})
						.show();
						
						
						timer.cancel();
					}
					
					
					
					
				}
				
				
			});
			
		}
		
		
	};
	
	/* 正解アニメーション */
	private void startCircleAnimation(){
		iv_circle.setVisibility(View.VISIBLE);
		AlphaAnimation anim = new AlphaAnimation(1f,0.0f);
		anim.setDuration(1000);
		iv_circle.setAnimation(anim);
		anim.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationEnd(Animation arg0) {
				iv_circle.setVisibility(View.INVISIBLE);				
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO 自動生成されたメソッド・スタブ
				
			}

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO 自動生成されたメソッド・スタブ
				
			}
			
		});		
	}
	
	/* 不正解アニメーション */
	private void startCrossAnimation(){
		iv_cross.setVisibility(View.VISIBLE);
		AlphaAnimation anim = new AlphaAnimation(1f,0.0f);
		anim.setDuration(1000);
		iv_cross.setAnimation(anim);
		anim.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationEnd(Animation arg0) {
				iv_cross.setVisibility(View.INVISIBLE);				
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO 自動生成されたメソッド・スタブ
				
			}

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO 自動生成されたメソッド・スタブ
				
			}
			
		});		
	}
	

	
	
	
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
		
		tv_timer = (TextView) findViewById(R.id.time);
		tv_quest = (TextView) findViewById(R.id.quest);
		tv_score = (TextView) findViewById(R.id.score);
		
		headerView = (HeaderView) findViewById(R.id.headerView1);
		
		iv_cross = (ImageView) findViewById(R.id.cross);
		iv_circle = (ImageView) findViewById(R.id.circle);
		
		timer = new Timer();
		handler = new Handler();
		timer.schedule(timerTask, 100,1000);
		activity = this;
		
		quest = new Quest();
		quest.create();
		tv_quest.setText(quest.getQuest());
		
		score = 0;
		
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
