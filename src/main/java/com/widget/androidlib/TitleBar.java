package com.widget.androidlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liujc.androidtools.R;

/**
 * 类名称：TitleBar
 * 创建者：Create by liujc
 * 创建时间：Create on 2016/10/25 17:10
 * 描述：自定义组件titleBar
 * 最近修改时间：2016/10/25 17:10
 * 修改人：Modify by liujc
 */
public class TitleBar extends RelativeLayout {
    private String mLeftButtonText;
    private int mLeftButtonTextColor;
    private float mLeftButtonSize;
    private Drawable mLeftButtonImage;
    private String mTitleButtonText;
    private int mTitleButtonTextColor;
    private float mTitleButtonSize;
    private String mRightButtonText;
    private int mRightButtonTextColor;
    private float mRightButtonSize;
    private Drawable mRightButtonImage;
    private TextView mLeftTextView;
    private ImageView mLeftButton;
    private TextView mRightTextView;
    private ImageView mRightButton;

    public TitleBar(Context context) {
        this(context, null);
    }
    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        initView(context);
    }

    private void init(Context context,AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Titlebar);
        mLeftButtonText = typedArray.getString(R.styleable.Titlebar_leftButtonText);
        mLeftButtonTextColor = typedArray.getColor(R.styleable.Titlebar_leftButtonTextColor, Color.WHITE);
        mLeftButtonSize = typedArray.getDimension(R.styleable.Titlebar_leftButtonTextSize, 16);
        mLeftButtonImage = typedArray.getDrawable(R.styleable.Titlebar_leftButtonImage);

        mTitleButtonText = typedArray.getString(R.styleable.Titlebar_titleText);
        mTitleButtonTextColor = typedArray.getColor(R.styleable.Titlebar_titleColor, Color.WHITE);
        mTitleButtonSize = typedArray.getDimension(R.styleable.Titlebar_titleSize, 18);

        mRightButtonText = typedArray.getString(R.styleable.Titlebar_rightButtonText);
        mRightButtonTextColor = typedArray.getColor(R.styleable.Titlebar_rightButtonTextColor, Color.WHITE);
        mRightButtonSize = typedArray.getDimension(R.styleable.Titlebar_rightButtonTextSize,16);
        mRightButtonImage = typedArray.getDrawable(R.styleable.Titlebar_rightButtonImage);

        typedArray.recycle();
    }

    private void initView(Context context) {
        if(mLeftButtonImage == null & mLeftButtonText != null){
            // 当用户没有设置左侧按钮图片并设置了左侧的按钮文本属性时--添加左侧文本按钮
            mLeftTextView = new TextView(context);
            mLeftTextView.setText(mLeftButtonText);
            mLeftTextView.setTextColor(mLeftButtonTextColor);
            mLeftTextView.setTextSize(mLeftButtonSize);
            LayoutParams leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
            addView(mLeftTextView, leftParams);
        }else if(mLeftButtonImage != null){
            // 添加左侧图片按钮
            LayoutParams leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
            mLeftButton = new ImageView(context);
            mLeftButton.setImageDrawable(mLeftButtonImage);
            addView(mLeftButton, leftParams);
        }

        if(mTitleButtonText!=null){
            // 添加中间标题
            TextView titleTextView = new TextView(context);
            titleTextView.setText(mTitleButtonText);
            titleTextView.setTextColor(mTitleButtonTextColor);
            titleTextView.setTextSize(mTitleButtonSize);
            LayoutParams titleTextViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            titleTextViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            addView(titleTextView,titleTextViewParams);
        }

        if(mRightButtonImage == null & mRightButtonText != null){
            // 当用户没有设置右侧按钮图片并设置了左侧的按钮文本属性时--添加右侧文本按钮
            mRightTextView = new TextView(context);
            mRightTextView.setText(mRightButtonText);
            mRightTextView.setTextColor(mRightButtonTextColor);
            mRightTextView.setTextSize(mRightButtonSize);
            LayoutParams rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rightParams.addRule(RelativeLayout.CENTER_VERTICAL);
            addView(mRightTextView,rightParams);
        }else if(mRightButtonImage != null){
            // 添加右侧图片按钮
            LayoutParams rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rightParams.addRule(RelativeLayout.CENTER_VERTICAL);
            mRightButton = new ImageView(context);
            mRightButton.setImageDrawable(mRightButtonImage);
            addView(mRightButton, rightParams);
        }
    }

    /**
     * 在button点击事件接口
     */
    public interface OnButtonClickListener{
        void onLeftClick();
        void onRightClick();
    }

    /**
     * 设置点击事件
     * @param onButtonClickListener
     */
    public void setOnButtonClickListener(final OnButtonClickListener onButtonClickListener) {
        if(onButtonClickListener !=null){
            if(mLeftTextView != null){
                mLeftTextView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClickListener.onLeftClick();
                    }
                });
            }
            if(mLeftButton != null){
                mLeftButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClickListener.onLeftClick();
                    }
                });
            }
            if(mRightTextView != null){
                mRightTextView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClickListener.onRightClick();
                    }
                });
            }
            if(mRightButton != null){
                mRightButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClickListener.onRightClick();
                    }
                });
            }
        }
    }
}
