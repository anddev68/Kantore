package jp.anddev68.Kantore;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 上側の艦隊が戦うView
 * 
 * 
 * @author 2011e_000
 *
 */
public class HeaderView extends ImageView{

	//	背景画像
	Bitmap img_background;
	
	//	自分艦隊
	Bitmap img_jikan;
	
	//	敵艦隊
	Bitmap img_tekikan;
	
	float tekikan_x;
	
	
	public HeaderView(Context context) {
		super(context);
		init(context);
	}
	
	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	
	/* 初期化 */
	private void init(Context context){
		Resources r = context.getResources();
		img_background = BitmapFactory.decodeResource(r,R.drawable.background);
		img_jikan = BitmapFactory.decodeResource(r,R.drawable.jikan);
		img_tekikan = BitmapFactory.decodeResource(r,R.drawable.migikan);
		tekikan_x = 0.0f;
	}
	
	@Override
	public void onDraw(Canvas c){
		int x,y;

		//	背景
		c.drawBitmap(Bitmap.createScaledBitmap(img_background, getWidth(), getHeight(), true),
				0, 0, null);
		
		//	自艦描画
		x= getWidth() - img_jikan.getWidth() *3 / 4;
		y= getHeight() - img_jikan.getHeight();
		c.drawBitmap(img_jikan, x, y, null);

		//	敵艦描画
		c.drawBitmap(img_tekikan, tekikan_x, y, null);
	
	
	}
	
	public void advanceKantai(int time){
		//	初期座標
		final int syoki = -100;
		
		//	稼働量
		int size = -syoki + getWidth() - img_jikan.getWidth() *3 / 4;
		
		//	位置
		tekikan_x = -img_tekikan.getWidth() + syoki + size * (30-time)/30;
		
		
		invalidate();
	}
	
	
}
