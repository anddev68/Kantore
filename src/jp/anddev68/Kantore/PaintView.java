package jp.anddev68.Kantore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * お絵かき用View
 * 
 * 2013/12/08 update
 * 
 * pathとbitmapを再作成することで対処。
 * この方法が正しいのかどうかはわからない。
 * 
 * 
 * 2013/12/05 update
 * 
 * deleteBitmapがうまくいっていない。
 * 削除の処理を修正する必要がある。
 * 
 * 
 * @author 2011e_000
 *
 */
public class PaintView extends View{

	//	描画パス
	Path path;
	
	//	タッチされた座標
	float posX,posY;
	
	//	色
	int color;
	
	public static Bitmap bitmap;
	
	
	
	public PaintView(Context context){
		super(context);
		color = Color.BLACK;
	}
	
	public PaintView(Context context, AttributeSet attrs) {
		super(context, attrs);
		color = Color.BLACK;
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public PaintView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		color = Color.BLACK;
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	
	@Override
	public void onDraw(Canvas c){
		super.onDraw(c);
		
		if(bitmap!=null){
			c.drawBitmap(bitmap,0,0,null);
		}
		
		
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(color);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(6);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeJoin(Paint.Join.ROUND);
		if( path!= null){
			c.drawPath(path, paint);
		}
	
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent me){
		
		
		posX = me.getX();
		posY = me.getY();
		
		switch( me.getAction() ){
		case MotionEvent.ACTION_DOWN:
			path = new Path();
			path.moveTo(posX, posY);
			break;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(posX, posY);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			path.lineTo(posX, posY);
			//	前回のBimapを保存
			setDrawingCacheEnabled(true);
			bitmap = Bitmap.createBitmap(getDrawingCache());
			setDrawingCacheEnabled(false);
			invalidate();
			break;
		
		}
		
		
		return true;
	}
	
	
	//	ペンの色を変更する
	public void changePen(int color){
		this.color = color;
	}
	
	//	Bitmapの削除
	public void deleteBitmap(){
		bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
		path = new Path();
		invalidate();
	}
	
	

}
