package jp.anddev68.Kantore;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;


/**
 * タイトル画面のアクティビティー
 * スタートボタン押下後、ゲームを開始する
 * その他必要に応じて追加すること
 * 
 * レイアウト定義ファイルはactivity_title.xmlにある
 * 
 * 
 * @author 2011e_000
 *
 */
public class TitleActivity extends Activity {

	ImageButton button_start;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);

		button_start = (ImageButton) findViewById(R.id.imageButton1);
		
		
		/* タイトル画面にアニメーションを施す処理 */
		final Handler handler = new Handler();	//	ハンドラ―作成
		Timer timer = new Timer();	//	タイマー作成
	
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				
				handler.post(new Runnable(){
					
					@Override
					public void run() {
						// タイマーで処理させる内容
					
						//	ボタンを点滅させる処理
						if( button_start.getVisibility() == View.VISIBLE)
							button_start.setVisibility(View.INVISIBLE);
						else
							button_start.setVisibility(View.VISIBLE);
					}
				
				});
			}
			
		}, 0,1000);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	
	
	

}
