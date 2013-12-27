package jp.anddev68.Kantore;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class PaintLayout extends LinearLayout{

	public PaintLayout(Context context) {
		super(context);
		init(context);
	}
	
	public PaintLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public PaintLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}


	private void init(Context context){
		this.addView(new PaintView(context));
		
	}
	
}
