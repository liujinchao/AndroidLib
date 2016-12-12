package com.widget.androidlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liujc.androidtools.R;

/**
 * �����ƣ�${primary_type_name}
 * �����ߣ�Create by liujc
 * ����ʱ�䣺Create on 2016/11/1 14:11
 * ������TODO
 * ����޸�ʱ�䣺2016/11/1 14:11
 * �޸��ˣ�Modify by liujc
 */
public class SuperTextView extends RelativeLayout {

    private Context mContext;
    private int defaultBgColor = 0xFFFFFFFF;//Ĭ�ϱ�����ɫ
    private int defaultLineColor = 0xFFE8E8E8;//�ߵ�Ĭ����ɫ
    //private int defaultLineColor = 0xFF535353;//����Ĭ����ɫ
    private int defaultLinePadding = 0;//�ߵ����ұ߾�

    private ImageView leftIconIV;//���ͼ��
    private ImageView rightIconIV;//�ұ�ͼ��
    private CheckBox rightCheckBox;//�ұ�checkbox
    private Drawable rightCheckBoxBg;//checkBox�ı���

    private TextView leftTV;//���textView
    private TextView centerTV;//�м�textView
    private TextView rightTV;//�ұ�textView

    private TextView leftTopTV;//���ϵ�textView
    private TextView leftBottomTV;//���µ�textView
    private TextView leftBottomTV2;//���µڶ���textView


    private Drawable leftIconRes;//���ͼ����Դ
    private Drawable rightIconRes;//�ұ�ͼ����Դ
    private String leftTextString;//�����ʾ������
    private String centerTextString;//�м���ʾ������
    private String rightTextString;//�ұ���ʾ������
    private String leftTopTextString;//������ʾ������
    private String leftBottomTextString;//������ʾ������
    private String leftBottomTextString2;//���µڶ�����ʾ������


    private int defaultPadding = 0;//Ĭ�ϱ߾�

    private int topLineMargin;//�ϱ��ߵ����ұ߾�
    private int bottomLineMargin;//�±��ߵ����ұ߾�
    private int bothLineMargin;//�����ߵ����ұ߾�

    private int leftIconMarginLeft;//���ͼ�����߾�

    private int leftTVMarginLeft;//������ֵ���߾�

    private int leftTopMarginLeft;//�������ֵ���߾�
    private int leftBottomMarginLeft;//�������ֵ���߾�
    private int leftBottomMarginLeft2;//���µڶ������ֵ���߾�

    private int rightTVMarginRight;//�ұ����ֵ��ұ߾�
    private int rightIconMarginRight;//�ұ�ͼ����ұ߾�
    private int rightCheckBoxMarginRight;//�ұ�checkBox���ұ߾�
    private boolean showCheckBox;//�Ƿ���ʾ�ұ�ѡ���
    private boolean isChecked;//�Ƿ�Ĭ��ѡ��

    private int defaultSize = 0;//Ĭ�������С

    private int leftTVSize;//������������С
    private int leftTopTVSize;//�������������С
    private int leftBottomTVSize;//�������������С
    private int leftBottomTVSize2;//���µڶ������������С
    private int rightTVSize;//�ұ����������С
    private int centerTVSize;//�м����������С


    private int defaultColor = 0xFF373737;//����Ĭ����ɫ

    private int backgroundColor;//������ɫ
    private int leftTVColor;//���������ɫ
    private int leftTopTVColor;//����������ɫ
    private int leftBottomTVColor;//����������ɫ
    private int leftBottomTVColor2;//���µڶ���������ɫ
    private int rightTVColor;//�ұ�������ɫ
    private int centerTVColor;//�м�������ɫ

    private boolean isSingLines = true;//�Ƿ�����ʾ   Ĭ�ϵ���
    private int maxLines = 1;//��༸��    Ĭ����ʾһ��
    private int maxEms = 10;//��༸����    Ĭ����ʾ10������

    private static final int NONE = 0;
    private static final int TOP = 1;
    private static final int BOTTOM = 2;
    private static final int BOTH = 3;
    private static final int DEFAULT = BOTTOM;

    private boolean useRipple;

    private int lineType;
    private LayoutParams centerBaseLineParams, topLineParams, bottomLineParams, leftImgParams, leftTextParams, centerTextParams, leftTopTextParams, leftBottomParams,
            leftBottomParams2, rightTextParams, rightImgParams, rightCheckBoxParams;

    private OnSuperTextViewClickListener onSuperTextViewClickListener;


    public SuperTextView(Context context) {
        super(context);
    }

    public SuperTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        defaultLinePadding = dip2px(context, 16);
        defaultPadding = dip2px(context, 16);
        defaultSize = sp2px(context, 14);
        getAttr(attrs);

        initLayout();

    }

    /**
     * ��ȡ�Զ�������ֵ
     *
     * @param attrs
     */
    private void getAttr(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.SuperTextView);

        ////////�������ֻ���ͼƬ��Դ////////
        leftIconRes = typedArray.getDrawable(R.styleable.SuperTextView_sLeftIconRes);
        rightIconRes = typedArray.getDrawable(R.styleable.SuperTextView_sRightIconRes);
        rightCheckBoxBg = typedArray.getDrawable(R.styleable.SuperTextView_sRightCheckBoxRes);

        leftTextString = typedArray.getString(R.styleable.SuperTextView_sLeftTextString);
        centerTextString = typedArray.getString(R.styleable.SuperTextView_sCenterTextString);
        rightTextString = typedArray.getString(R.styleable.SuperTextView_sRightTextString);

        leftTopTextString = typedArray.getString(R.styleable.SuperTextView_sLeftTopTextString);
        leftBottomTextString = typedArray.getString(R.styleable.SuperTextView_sLeftBottomTextString);
        leftBottomTextString2 = typedArray.getString(R.styleable.SuperTextView_sLeftBottomTextString2);

        showCheckBox = typedArray.getBoolean(R.styleable.SuperTextView_sRightCheckBoxShow, false);
        isChecked = typedArray.getBoolean(R.styleable.SuperTextView_sIsChecked, false);
        useRipple = typedArray.getBoolean(R.styleable.SuperTextView_sUseRipple, false);

        lineType = typedArray.getInt(R.styleable.SuperTextView_sLineShow, DEFAULT);

        /////////����view�ı߾�////////
        topLineMargin = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sTopLineMargin, defaultLinePadding);
        bottomLineMargin = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sBottomLineMargin, defaultLinePadding);
        bothLineMargin = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sBothLineMargin, defaultLinePadding);

        leftIconMarginLeft = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftIconMarginLeft, defaultPadding);
        leftTVMarginLeft = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftTextMarginLeft, defaultPadding);

        leftTopMarginLeft = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftTopTextMarginLeft, defaultPadding);
        leftBottomMarginLeft = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftBottomTextMarginLeft, defaultPadding);
        leftBottomMarginLeft2 = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftBottomTextMarginLeft2, defaultPadding);
        rightTVMarginRight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sRightTextMarginRight, defaultPadding);
        rightIconMarginRight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sRightIconMarginRight, defaultPadding);
        rightCheckBoxMarginRight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sRightCheckBoxMarginRight, defaultPadding);
        //////����������ɫ////////
        backgroundColor = typedArray.getColor(R.styleable.SuperTextView_sBackgroundColor, defaultBgColor);
        leftTVColor = typedArray.getColor(R.styleable.SuperTextView_sLeftTextColor, defaultColor);
        leftTopTVColor = typedArray.getColor(R.styleable.SuperTextView_sLeftTopTextColor, defaultColor);
        leftBottomTVColor = typedArray.getColor(R.styleable.SuperTextView_sLeftBottomTextColor, defaultColor);
        leftBottomTVColor2 = typedArray.getColor(R.styleable.SuperTextView_sLeftBottomTextColor2, defaultColor);
        rightTVColor = typedArray.getColor(R.styleable.SuperTextView_sRightTextColor, defaultColor);
        centerTVColor = typedArray.getColor(R.styleable.SuperTextView_sCenterTextColor, defaultColor);
        //////���������С////////
        leftTVSize = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftTextSize, defaultSize);
        leftTopTVSize = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftTopTextSize, defaultSize);
        leftBottomTVSize = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftBottomTextSize, defaultSize);
        leftBottomTVSize2 = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftBottomTextSize2, defaultSize);
        rightTVSize = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sRightTextSize, defaultSize);
        centerTVSize = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sCenterTextSize, defaultSize);

        ///////����textView������///////////
        isSingLines = typedArray.getBoolean(R.styleable.SuperTextView_sIsSingLines, isSingLines);
        maxLines = typedArray.getInt(R.styleable.SuperTextView_sMaxLines,maxLines);
        maxEms =  typedArray.getInt(R.styleable.SuperTextView_sMaxEms,maxEms);
        typedArray.recycle();
    }

    /**
     * ��ʼ������
     */
    private void initLayout() {

        initSuperTextView();
        initCenterBaseLine();

        if (leftIconRes != null) {
            initLeftIcon();
        }
        if (leftTopTextString != null) {
            initLeftTopText();
        }
        if (leftBottomTextString != null) {
            initLeftBottomText();
        }
        if (leftBottomTextString2 != null) {
            initLeftBottomText2();
        }
        if (leftTextString != null) {
            initLeftText();
        }
        if (centerTextString != null) {
            initCenterText();
        }
        if (rightIconRes != null) {
            initRightIcon();
        }
        if (rightTextString != null) {
            initRightText();
        }
        if (showCheckBox) {
            initRightCheckBox();
        }

        switch (lineType) {
            case NONE:
                break;
            case TOP:
                initTopLine(topLineMargin);
                break;
            case BOTTOM:
                initBottomLine(bottomLineMargin);
                break;
            case BOTH:
                initTopLine(bothLineMargin);
                initBottomLine(bothLineMargin);
                break;
        }
    }


    /**
     * ��ʼ���ϱߵ���
     */
    private void initTopLine(int lineMargin) {
        View topLine = new View(mContext);
        topLineParams = new LayoutParams(LayoutParams.MATCH_PARENT, dip2px(mContext, 1));
        topLineParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, TRUE);
        topLineParams.setMargins(lineMargin, 0, lineMargin, 0);
        topLine.setLayoutParams(topLineParams);
        topLine.setBackgroundColor(defaultLineColor);
        addView(topLine);
    }

    /**
     * ��ʼ���±ߵ���
     */
    private void initBottomLine(int lineMargin) {
        View bottomLine = new View(mContext);
        bottomLineParams = new LayoutParams(LayoutParams.MATCH_PARENT, dip2px(mContext, 1));
        bottomLineParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, TRUE);
        bottomLineParams.setMargins(lineMargin, 0, lineMargin, 0);
        bottomLine.setLayoutParams(bottomLineParams);
        bottomLine.setBackgroundColor(defaultLineColor);

        addView(bottomLine);
    }

    /**
     * ��ʼ��SuperTextView
     */
    private void initSuperTextView() {

        this.setBackgroundColor(backgroundColor);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSuperTextViewClickListener != null) {
                    onSuperTextViewClickListener.onSuperTextViewClick();
                }
            }
        });

        if (useRipple) {
            this.setBackgroundResource(R.drawable.selector_white);
        }
    }


    /**
     * Ϊ�����������������־��ж�����ʾ����Ҫ���õĻ�׼��
     */
    private void initCenterBaseLine() {
        View view = new View(mContext);
        centerBaseLineParams = new LayoutParams(LayoutParams.MATCH_PARENT, dip2px(mContext, 10));
        centerBaseLineParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        view.setId(R.id.sCenterBaseLineId);
        view.setLayoutParams(centerBaseLineParams);
        addView(view);
    }


    /**
     * ��ʼ�����ͼ��
     */
    private void initLeftIcon() {
        leftIconIV = new ImageView(mContext);
        leftImgParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftImgParams.addRule(ALIGN_PARENT_LEFT, TRUE);
        leftImgParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        setMargin(leftImgParams, leftIconMarginLeft, 0, 0, 0);
        leftIconIV.setScaleType(ImageView.ScaleType.CENTER);
        leftIconIV.setId(R.id.sLeftIconId);
        leftIconIV.setLayoutParams(leftImgParams);
        leftIconIV.setImageDrawable(leftIconRes);
        addView(leftIconIV);
    }

    /**
     * ��ʼ���������
     */
    private void initLeftText() {
        leftTV = new TextView(mContext);
        leftTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftTextParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        leftTextParams.addRule(RelativeLayout.RIGHT_OF, R.id.sLeftIconId);
        setMargin(leftTextParams, leftTVMarginLeft, 0, dip2px(mContext,10), 0);
        leftTV.setId(R.id.sLeftTextId);
        leftTV.setLayoutParams(leftTextParams);
        leftTV.setText(leftTextString);

        setTextViewParams(leftTV,isSingLines,maxLines,maxEms);

        setTextColor(leftTV, leftTVColor);
        setTextSize(leftTV, leftTVSize);
        addView(leftTV);
    }

    /**
     * ����ͨ�õ�textView��ʾЧ������
     * @param textView
     * view
     * @param isSingLines
     * �Ƿ�����ʾ
     * @param maxLines
     * ��ʾ�����
     * @param maxEms
     * �����ʾ���ٸ���
     */
    private void setTextViewParams(TextView textView,boolean isSingLines,int maxLines,int maxEms) {
        textView.setSingleLine(isSingLines);
        textView.setMaxLines(maxLines);
        textView.setMaxEms(maxEms);
        textView.setEllipsize(TextUtils.TruncateAt.END);
    }

    /**
     * ��ʼ����������
     */
    private void initLeftTopText() {
        leftTopTV = new TextView(mContext);
        leftTopTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftTopTextParams.addRule(RelativeLayout.ABOVE, R.id.sCenterBaseLineId);
        leftTopTextParams.addRule(RelativeLayout.RIGHT_OF, R.id.sLeftIconId);
        setMargin(leftTopTextParams, leftTopMarginLeft, 0, 0, 0);
        leftTopTV.setLayoutParams(leftTopTextParams);
        leftTopTV.setText(leftTopTextString);
        setTextColor(leftTopTV, leftTopTVColor);
        setTextSize(leftTopTV, leftTopTVSize);
        leftTopTV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSuperTextViewClickListener != null) {
                    onSuperTextViewClickListener.onLeftTopClick();
                }
            }
        });
        setTextViewParams(leftTopTV,isSingLines,maxLines,maxEms);
        addView(leftTopTV);
    }

    /**
     * ��ʼ����������
     */
    private void initLeftBottomText() {
        leftBottomTV = new TextView(mContext);
        leftBottomParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftBottomParams.addRule(RelativeLayout.BELOW, R.id.sCenterBaseLineId);
        leftBottomParams.addRule(RelativeLayout.RIGHT_OF, R.id.sLeftIconId);
        setMargin(leftBottomParams, leftBottomMarginLeft, 0, 0, 0);
        leftBottomTV.setLayoutParams(leftBottomParams);
        leftBottomTV.setId(R.id.sLeftBottomTextId);
        leftBottomTV.setText(leftBottomTextString);
        setTextColor(leftBottomTV, leftBottomTVColor);
        setTextSize(leftBottomTV, leftBottomTVSize);
        leftBottomTV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSuperTextViewClickListener != null) {
                    onSuperTextViewClickListener.onLeftBottomClick();
                }
            }
        });
        setTextViewParams(leftBottomTV,isSingLines,maxLines,maxEms);
        addView(leftBottomTV);
    }

    /**
     * ��ʼ�����µڶ�������
     */
    private void initLeftBottomText2() {
        leftBottomTV2 = new TextView(mContext);
        leftBottomParams2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftBottomParams2.addRule(RelativeLayout.BELOW, R.id.sCenterBaseLineId);
        leftBottomParams2.addRule(RelativeLayout.RIGHT_OF, R.id.sLeftBottomTextId);
        setMargin(leftBottomParams2, leftBottomMarginLeft2, 0, 0, 0);
        leftBottomTV2.setLayoutParams(leftBottomParams2);
        leftBottomTV2.setText(leftBottomTextString2);
        setTextColor(leftBottomTV2, leftBottomTVColor2);
        setTextSize(leftBottomTV2, leftBottomTVSize2);
        leftBottomTV2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSuperTextViewClickListener != null) {
                    onSuperTextViewClickListener.onLeftBottomClick2();
                }
            }
        });
        setTextViewParams(leftBottomTV2,isSingLines,maxLines,maxEms);
        addView(leftBottomTV2);
    }

    /**
     * ��ʼ���м�����
     */
    private void initCenterText() {
        centerTV = new TextView(mContext);
        centerTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        centerTextParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        centerTV.setLayoutParams(centerTextParams);
        centerTV.setText(centerTextString);
        setTextColor(centerTV, centerTVColor);
        setTextSize(centerTV, centerTVSize);
        setTextViewParams(centerTV,isSingLines,maxLines,maxEms);
        addView(centerTV);
    }

    /**
     * ��ʼ���ұ�����
     */
    private void initRightText() {
        rightTV = new TextView(mContext);
        rightTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightTextParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        rightTextParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        rightTextParams.addRule(RelativeLayout.RIGHT_OF, R.id.sLeftTextId);
        setMargin(rightTextParams, 0, 0, rightTVMarginRight, 0);
        rightTV.setId(R.id.sRightTextId);
        rightTV.setLayoutParams(rightTextParams);
        rightTV.setText(rightTextString);
        setTextColor(rightTV, rightTVColor);
        setTextSize(rightTV, rightTVSize);
        rightTV.setGravity(Gravity.RIGHT);
        setTextViewParams(rightTV,isSingLines,maxLines,maxEms);
        addView(rightTV);
    }

    /**
     * ��ʼ���ұ�ͼ��
     */
    private void initRightIcon() {
        rightIconIV = new ImageView(mContext);

        rightImgParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        rightImgParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        rightImgParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);

        setMargin(rightImgParams, 0, 0, rightIconMarginRight, 0);
        rightIconIV.setLayoutParams(rightImgParams);
        rightIconIV.setImageDrawable(rightIconRes);

        addView(rightIconIV);
    }

    /**
     * ��ʼ���ұ�ѡ���
     */
    private void initRightCheckBox() {
        rightCheckBox = new CheckBox(mContext);

        rightCheckBoxParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        rightCheckBoxParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        rightCheckBoxParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        setMargin(rightCheckBoxParams, 0, 0, rightCheckBoxMarginRight, 0);
        rightCheckBox.setLayoutParams(rightCheckBoxParams);
        if (rightCheckBoxBg != null) {
            rightCheckBox.setGravity(CENTER_IN_PARENT);
            rightCheckBox.setButtonDrawable(rightCheckBoxBg);
        }
        rightCheckBox.setChecked(isChecked);
        addView(rightCheckBox);
    }

    private void setMargin(LayoutParams params, int left, int top, int right, int bottom) {
        params.setMargins(left, top, right, bottom);
    }

    /**
     * ����view�ı߾�
     *
     * @param view   view����
     * @param left   ��߱߾�
     * @param top    �ϱ߱߾�
     * @param right  �ұ߱߾�
     * @param bottom �±߱߾�
     */
    private void setPadding(View view, int left, int top, int right, int bottom) {
        view.setPadding(left, top, right, bottom);
    }

    /**
     * �������ֵ������С
     *
     * @param textView textView����
     * @param size     ���ִ�С
     */
    private void setTextSize(TextView textView, int size) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    /**
     * �������ֵ���ɫ
     *
     * @param textView textView����
     * @param color    ������ɫ
     */
    private void setTextColor(TextView textView, int color) {
        textView.setTextColor(color);
    }

    //////////���⹫���ķ���///////////////

    /**
     * �������ͼ��
     *
     * @param leftIcon ���ͼ��
     * @return ���ض���
     */
    public SuperTextView setLeftIcon(Drawable leftIcon) {
        leftIconRes = leftIcon;
        if (leftIconIV == null) {
            initLeftIcon();
        } else {
            leftIconIV.setImageDrawable(leftIcon);
        }
        return this;
    }

    /**
     * �����ұ�ͼ��
     *
     * @param rightIcon �ұ�ͼ��
     * @return ���ض���
     */
    public SuperTextView setRightIcon(Drawable rightIcon) {
        rightIconRes = rightIcon;
        if (rightIconIV == null) {
            initRightIcon();
        } else {
            rightIconIV.setImageDrawable(rightIcon);
        }
        return this;
    }

    /**
     * ���������ʾ����
     *
     * @param leftString �������
     * @return ���ض���
     */
    public SuperTextView setLeftString(String leftString) {
        leftTextString = leftString;
        if (leftTV == null) {
            initLeftText();
        } else {
            leftTV.setText(leftString);
        }
        return this;
    }

    /**
     * ����������ʾ������
     *
     * @param leftTopString ��������
     * @return ���ض���
     */
    public SuperTextView setLeftTopString(String leftTopString) {
        leftTopTextString = leftTopString;
        if (leftTopTV == null) {
            initLeftTopText();
        } else {
            leftTopTV.setText(leftTopString);
        }
        return this;
    }

    /**
     * ����������ʾ������
     *
     * @param leftBottomString ���µ�һ������
     * @return ���ض���
     */
    public SuperTextView setLeftBottomString(String leftBottomString) {
        leftBottomTextString = leftBottomString;
        if (leftBottomTV == null) {
            initLeftBottomText();
        } else {
            leftBottomTV.setText(leftBottomString);
        }
        return this;
    }

    /**
     * �������µڶ�����ʾ������
     *
     * @param leftBottomString2 ���µڶ�������
     * @return ���ض���
     */
    public SuperTextView setLeftBottomString2(String leftBottomString2) {
        leftBottomTextString2 = leftBottomString2;
        if (leftBottomTV2 == null) {
            initLeftBottomText2();
        } else {
            leftBottomTV2.setText(leftBottomString2);
        }
        return this;
    }

    /**
     * �����ұ���ʾ������
     *
     * @param rightString �ұ�����
     * @return ���ض���
     */
    public SuperTextView setRightString(String rightString) {
        rightTextString = rightString;
        if (rightTV == null) {
            initRightText();
        } else {
            rightTV.setText(rightString);
        }
        return this;
    }

    /**
     * @param checked �Ƿ�ѡ��
     * @return ����ֵ
     */
    public SuperTextView setCbChecked(boolean checked) {
        isChecked = checked;
        if (rightCheckBox == null) {
            initRightCheckBox();
        } else {
            rightCheckBox.setChecked(checked);
        }
        return this;
    }

    /**
     * ����checkbox�ı���ͼ
     *
     * @param drawable drawable����
     * @return ���ض���
     */
    public SuperTextView setCbBackground(Drawable drawable) {
        rightCheckBoxBg = drawable;
        if (rightCheckBox == null) {
            initRightCheckBox();
        } else {
            rightCheckBox.setBackgroundDrawable(drawable);
        }
        return this;
    }

    /**
     * ��ȡcheckbox״̬
     *
     * @return ����ѡ���ǰѡ��״̬
     */
    public boolean getCbisChecked() {
        boolean isChecked = false;
        if (rightCheckBox != null) {
            isChecked = rightCheckBox.isChecked();
        }
        return isChecked;
    }

    /**
     * ����������ֵ���ɫ
     *
     * @param textColor ������ɫֵ
     * @return ���ض���
     */
    public SuperTextView setLeftTVColor(int textColor) {
        leftTVColor = textColor;
        if (leftTV == null) {
            initLeftText();
        } else {
            leftTV.setTextColor(textColor);
        }
        return this;
    }

    /**
     * �����ұ����ֵ���ɫ
     *
     * @param textColor ������ɫֵ
     * @return ���ض���
     */
    public SuperTextView setRightTVColor(int textColor) {
        rightTVColor = textColor;
        if (rightTV == null) {
            initRightText();
        } else {
            rightTV.setTextColor(textColor);
        }
        return this;
    }

    /**
     * �������ϱ����ֵ���ɫ
     *
     * @param textColor ������ɫֵ
     * @return ���ض���
     */
    public SuperTextView setLeftTopTVColor(int textColor) {
        leftTopTVColor = textColor;
        if (leftTopTV == null) {
            initLeftTopText();
        } else {
            leftTopTV.setTextColor(textColor);
        }
        return this;
    }

    /**
     * �������±����ֵ���ɫ
     *
     * @param textColor ������ɫֵ
     * @return ���ض���
     */
    public SuperTextView setLeftBottomTVColor(int textColor) {
        leftBottomTVColor = textColor;
        if (leftBottomTV == null) {
            initLeftBottomText();
        } else {
            leftBottomTV.setTextColor(textColor);
        }
        return this;
    }

    /**
     * �������µڶ������ֵ���ɫ
     *
     * @param textColor ������ɫֵ
     * @return ���ض���
     */
    public SuperTextView setLeftBottomTVColor2(int textColor) {
        leftBottomTVColor2 = textColor;
        if (leftBottomTV2 == null) {
            initLeftBottomText2();
        } else {
            leftBottomTV2.setTextColor(textColor);
        }
        return this;
    }

    //////////����View�ĵ���¼�/////////////////

    /**
     * ����¼�
     *
     * @param listener listener����
     * @return ���ض���
     */
    public SuperTextView setOnSuperTextViewClickListener(OnSuperTextViewClickListener listener) {
        onSuperTextViewClickListener = listener;
        return this;
    }

    /**
     * ����¼��ӿ�
     */
    public static class OnSuperTextViewClickListener {
        public void onSuperTextViewClick() {
        }

        public void onLeftTopClick() {
        }

        public void onLeftBottomClick() {
        }

        public void onLeftBottomClick2() {
        }

    }

    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }
}
