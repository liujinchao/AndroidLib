package com.widget.androidlib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class MarqueeTextView extends TextView {

	public MarqueeTextView(Context context) {
		super(context);
	}
	public MarqueeTextView(Context context, AttributeSet attrs){
		super(context,attrs);
	}
	public MarqueeTextView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
	}
	public boolean isFocused(){
		return true;// 返回true，任何时候都处于focused状态，就能跑马
		
	}

}
