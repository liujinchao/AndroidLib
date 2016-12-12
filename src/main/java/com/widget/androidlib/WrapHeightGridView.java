package com.widget.androidlib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 类名称：WrapHeightGridView
 * 创建者：Create by liujc
 * 创建时间：Create on 2016/10/28 09:37
 * 描述：TODO
 * 最近修改时间：2016/10/28 09:37
 * 修改人：Modify by liujc
 */
public class WrapHeightGridView  extends GridView {
    public WrapHeightGridView(Context context) {
        this(context, null);
    }

    public WrapHeightGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WrapHeightGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}

