package com.widget.androidlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.liujc.androidtools.R;


/**
 * 简易带有时间轴的linearlayout
 */
public class CustomTimeLine extends LinearLayout {
    private Bitmap mIcon;
    //line location
    private int lineMarginSide;
    private int lineDynamicDimen;
    //line property
    private int lineStrokeWidth;
    private int lineColor;
    //point property
    private int pointSize;
    private int pointColor;

    //=============================================================paint
    private Paint linePaint;
    private Paint pointPaint;
    //=============================================================其他辅助参数
    //第一个点的位置
    private int firstX;
    private int firstY;
    //最后一个图的位置
    private int lastX;
    private int lastY;
    //默认垂直
    private int curOrientation = VERTICAL;
    private Context mContext;

    //开关
    private boolean drawLine = true;
    private boolean isShowUp = true;//true:从下往上展示   false:从上往下展示

    public CustomTimeLine(Context context) {
        this(context, null);
    }

    public CustomTimeLine(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTimeLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.CustomTimeLine);
        lineMarginSide = attr.getDimensionPixelOffset(R.styleable.CustomTimeLine_line_margin_side, 15);
        lineDynamicDimen = attr.getDimensionPixelOffset(R.styleable.CustomTimeLine_line_dynamic_dimen, 0);
        lineStrokeWidth = attr.getDimensionPixelOffset(R.styleable.CustomTimeLine_line_stroke_width, 10);
        lineColor = attr.getColor(R.styleable.CustomTimeLine_line_color, 0xff3dd1a5);
        pointSize = attr.getDimensionPixelSize(R.styleable.CustomTimeLine_point_size, 15);
        pointColor = attr.getDimensionPixelOffset(R.styleable.CustomTimeLine_point_color, 0xff3dd1a5);

        int iconRes = attr.getResourceId(R.styleable.CustomTimeLine_icon_src, R.drawable.ic_ok);
        BitmapDrawable temp = (BitmapDrawable) context.getResources().getDrawable(iconRes);
        if (temp != null) mIcon = temp.getBitmap();

        curOrientation = getOrientation();
        attr.recycle();
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setDither(true);
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineStrokeWidth);
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setDither(true);
        pointPaint.setColor(pointColor);
        pointPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (drawLine) {
            drawTimeLine(canvas);
        }
    }

    private void drawTimeLine(Canvas canvas) {
        int childCount = getChildCount();
        if (childCount > 0) {
            //大于1，证明至少有2个，也就是第一个和第二个之间连成线，第一个和最后一个分别有点/icon
            if (childCount > 1) {
                switch (curOrientation) {
                    case VERTICAL:
                        drawFirstChildViewVertical(canvas);
                        drawLastChildViewVertical(canvas);
                        drawBetweenLineVertical(canvas);
                        break;
                    case HORIZONTAL:
                        break;
                    default:
                        break;
                }
            }
            else if (childCount == 1) {
                switch (curOrientation) {
                    case VERTICAL:
                        drawFirstChildViewVertical(canvas);
//                        drawLastChildViewVertical(canvas);
                        break;
                    case HORIZONTAL:
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void drawFirstChildViewVertical(Canvas canvas) {
        if (getChildAt(0) != null) {
            int top = 0;
            if (isShowUp){
                top = getChildAt(0).getTop()+getChildAt(0).getHeight()/6;
                firstX = lineMarginSide;
            }else {
                top = getChildAt(0).getTop()+getChildAt(0).getHeight()/2;
                firstX = lineMarginSide + (mIcon.getWidth() >> 1);
            }
            //记录值
//            firstX = lineMarginSide;
            firstY = top + getChildAt(0).getPaddingTop() + lineDynamicDimen;
            //画一个圆
            if (isShowUp){
                canvas.drawBitmap(mIcon, firstX, firstY, null);
            }else {
                canvas.drawCircle(firstX, firstY, pointSize, pointPaint);
            }
        }
    }

    private void drawLastChildViewVertical(Canvas canvas) {
        if (getChildAt(getChildCount() - 1) != null) {
            int top = 0;
            if (isShowUp){
               top = getChildAt(getChildCount() - 1).getTop()+getChildAt(getChildCount() - 1).getHeight()/2;
                lastX = lineMarginSide + (mIcon.getWidth() >> 1);
            }else {
                top = getChildAt(getChildCount() - 1).getTop()+getChildAt(getChildCount() - 1).getHeight()/6;
                lastX = lineMarginSide;
            }
            //记录值
            lastY = top + getChildAt(getChildCount() - 1).getPaddingTop() + lineDynamicDimen;
            //画一个图
            if (isShowUp){
                canvas.drawCircle(lastX, lastY, pointSize, pointPaint);
            }else {
                canvas.drawBitmap(mIcon, lastX, lastY, null);
            }
        }
    }

    private void drawBetweenLineVertical(Canvas canvas) {
        for (int i = 0; i < getChildCount() - 1; i++) {
            //画剩下的
            if (isShowUp){
                canvas.drawLine(lastX, firstY+getChildAt(0).getHeight()/2, lastX, lastY, linePaint);
            }else {
                canvas.drawLine(firstX, firstY, firstX, lastY, linePaint);
            }

            //画了线，就画圆
            if (getChildAt(i) != null && i != 0) {
                int top = getChildAt(i).getTop()+getChildAt(i).getHeight()/2;
                //记录值
                int Y = top + getChildAt(i).getPaddingTop() + lineDynamicDimen;
                if (isShowUp){
                    canvas.drawCircle(lastX, Y, pointSize, pointPaint);
                }else {
                    canvas.drawCircle(firstX, Y, pointSize, pointPaint);
                }

            }
        }
    }
}
