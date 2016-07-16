package com.hyb.quickindex.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class QuickIndexBar extends View {
	private String[] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };
	private Paint paint;
	private int width;
	float cellHeight;


	int lastIndex=-1;//记录上次触摸字母的索引
	private OnQuickIndexBarTouchListener quickIndexBarTouchListener;
	
	public void setQuickIndexBarTouchListener(OnQuickIndexBarTouchListener quickIndexBarTouchListener) {
		this.quickIndexBarTouchListener = quickIndexBarTouchListener;
	}
	public interface OnQuickIndexBarTouchListener{
		void onTouchListener(String letter);
	}
	public QuickIndexBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		init();
	}

	public QuickIndexBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		init();
	}

	public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		
		init();
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		width = getMeasuredWidth();
		cellHeight=getMeasuredHeight() * 1f / indexArr.length;
	}
	
	private void init()
	{
		//Paint.ANTI_ALIAS_FLAG抗锯齿
		paint=new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.WHITE);
		paint.setTextSize(30);
		paint.setTextAlign(Align.CENTER);//设置文字的起点是文字边框底边的中心
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			
		case MotionEvent.ACTION_MOVE:
			float y=event.getY();
			int index=(int)(y / cellHeight);//得到字母对应索引
			if(lastIndex!=index)
			{
				if(index>=0 && index<indexArr.length)
				{
					if(quickIndexBarTouchListener!=null)
					{
						quickIndexBarTouchListener.onTouchListener(indexArr[index]);
					}
				}
				
			}
			lastIndex=index;
			break;
		case MotionEvent.ACTION_UP:
			lastIndex=-1;//重置lastIndex
			break;
		default:
			break;
		}
		invalidate();
		return true;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);	
		float _x=width / 2;		
		float _y=0f;
		for(int i=0;i<indexArr.length;i++)
		{
			Rect bounds = getTextHeight(indexArr[i]);
			if(lastIndex==i)
			{
				paint.setColor(Color.BLACK);
				paint.setTextSize(34);
			}
			else
			{
				paint.setColor(Color.WHITE);
				paint.setTextSize(30);
			}
			//格子高度一半+文本高度一半+position * 格子高度
			_y=cellHeight / 2 + bounds.height() / 2 +i*cellHeight;
			canvas.drawText(indexArr[i], _x,  _y, paint);
		}
	}
	//获取文本高度
	private Rect getTextHeight(String text) 
	{
		Rect bounds=new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		return bounds;
	}
}
