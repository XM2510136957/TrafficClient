package com.mad.trafficclient.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public class MyImageView extends ImageView implements ScaleGestureDetector.OnScaleGestureListener,View.OnTouchListener {
    /**
     * 控件宽度
     */
    private int mWidth;
    /**
     * 控件高度
     */
    private int mHeight;
    /**
     * 获取src图片
     */
    private Drawable mDrawable;
    /**
     * 图片宽度
     */
    private int mDrawableWidth;
    /**
     * 图片高度
     */
    private int mDrawableHeight;
    /**
     * 初始化缩放值
     */
    private float mScale;
    /**
     * 双击图片的缩放值
     */
    private float mDoubleClickScale;
    /**
     * 最大的缩放值
     */
    private float mMaxScale;
    /**
     * 最小的缩放值
     */
    private float mMinScale;
    /**
     *手势滑动的监听事件
     */
    private ScaleGestureDetector scaleGestureDetector;
    /**
     * 当前有着缩放值、平移值的矩阵
     */
    private Matrix matrix;

    //短的引用长的，最终调用第三个构造
    public MyImageView(Context context) {
        this(context,null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //给自己添加触摸事件
        setOnTouchListener(this);
        //new一个滑动监听
        scaleGestureDetector = new ScaleGestureDetector(context,this);
        //
        initListener();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        initListener();
    }

    /**
     * 初始化监听事件
     */
    private void initListener() {
        //强制设置模式
        setScaleType(ScaleType.MATRIX);
        //添加观察者
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                //移出观察者
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取控件大小
                mWidth = getWidth();
                mHeight = getHeight();
                //通过getDrawable获取src的图片
                mDrawable = getDrawable();
                if(mDrawable == null){
                    return;
                }
                //获取图片大小
                mDrawableWidth = mDrawable.getIntrinsicWidth();
                mDrawableHeight = mDrawable.getIntrinsicHeight();
                initImageViewSize();
                moveToCenter();
            }
        });
    }

    /**
     *移动控件中间位置
     */
    private void moveToCenter() {
        final float dx = mWidth / 2 - mDrawableWidth / 2;
        final float dy = mHeight / 2 - mDrawableHeight / 2;
        matrix = new Matrix();
        //平移至中心
        matrix.postTranslate(dx, dy);
        //以控件中心作为缩放
        matrix.postScale(mScale, mScale,mWidth / 2, mHeight / 2);
        setImageMatrix(matrix);
    }

    /**
     * 初始化图片大小
     */
    private void initImageViewSize() {
        if(mDrawable == null){
            return;
        }
        //定义缩放值
        float scale = 1.0f;
        //图片宽度大于控件宽度，图片高度小控件高度
        if(mDrawableWidth > mWidth && mDrawableHeight < mHeight){
            scale = mWidth * 1.0f / mDrawableWidth;
            //图片高度大于控件高度，图片宽度小于控件宽度
        }else if(mDrawableHeight > mHeight && mDrawableWidth < mWidth){
            scale = mHeight * 1.0f /mDrawableHeight;
            //图片高度大于控件高度，图片宽度大于控件宽度
        }else if(mDrawableHeight > mHeight && mDrawableWidth > mWidth){
            scale = Math.min(mHeight * 1.0f /mDrawableHeight,mWidth * 1.0f / mDrawableWidth);
            //图片高度小于控件高度，图片宽度小于控件宽度
        }else if(mDrawableHeight < mHeight && mDrawableWidth < mWidth){
            scale = Math.min(mHeight * 1.0f / mDrawableHeight, mWidth * 1.0f / mDrawableWidth);
        }
        mScale = scale;
        mMaxScale = mScale * 8.0f;
        mMinScale = mScale * 0.5f;
    }

    /**
     *
     * @return 当前缩放的值
     */
    private float getmScale(){
        float[] floats = new float[9];
        matrix.getValues(floats);
        return floats[Matrix.MSCALE_X];
    }

    /**
     *
     * @param matrix 矩阵
     * @return matrix的width，height
     */
    private RectF getRectf(Matrix matrix){
        RectF f = new RectF();
        if(mDrawable == null){
            return null;
        }
        f.set(0,0,mDrawableWidth, mDrawableHeight);
        matrix.mapRect(f);
        return f;
    }

    //滑动中
    @Override
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        if(mDrawable == null){
            return true;
        }
        //系统定义的缩放值
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        //获取已经缩放的值
        float scale = getmScale();
        float scaleResult = scale * scaleFactor;
        if(scaleResult >= mMaxScale && scaleFactor > 1.0f){
            scaleFactor = mMaxScale / scale;
        }
        if(scaleResult <= mMinScale && scaleFactor < 1.0f){
            scaleFactor = mMinScale / scale;
        }
        matrix.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());

        RectF f = getRectf(matrix);
        float dx = 0.0f;
        float dy = 0.0f;
        if(f.height() >= mHeight){
            if(f.top > 0){
                dy = -f.top;
            }
            if(f.bottom < mHeight){
                dy = mHeight - f.bottom;
            }
        }
        if(f.width() >= mWidth){
            if(f.left > 0){
                dx = -f.left;
            }
            if(f.right < mWidth){
                dx = mWidth - f.right;
            }
        }
        if(f.width() < mWidth){
            dx = mWidth / 2 - f.right + f.width() / 2;
        }
        if(f.height() < mHeight){
            dy = mHeight / 2 - f.bottom + f.height() / 2;
        }
        matrix.postTranslate(dx, dy);
        setImageMatrix(matrix);
        return true;
    }
    //按下
    @Override
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }
    //抬起
    @Override
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        float scale = getmScale();
        if(scale < mScale){
            matrix.postScale(mScale / scale, mScale / scale, mWidth / 2, mHeight / 2);
            setImageMatrix(matrix);
        }
    }

    private float downX;
    private float downY;
    private float nowMovingX;
    private float nowMovingY;
    private float lastMovedX;
    private float lastMovedY;
    private boolean isFirstMoved = false;
    //触摸事件
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                isFirstMoved = false;
                downX = motionEvent.getX();
                downY = motionEvent.getY();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                isFirstMoved = false;
                break;
            case MotionEvent.ACTION_MOVE:
                nowMovingX = motionEvent.getX();
                nowMovingY = motionEvent.getY();
                if(!isFirstMoved){
                    isFirstMoved = true;
                    lastMovedX = nowMovingX;
                    lastMovedY = nowMovingY;
                }
                float dx = 0.0f;
                float dy = 0.0f;
                RectF rectf = getRectf(matrix);
                //判断滑动方向
                final float scrollX = nowMovingX - lastMovedX;
                //判断滑动方向
                final float scrollY = nowMovingY - lastMovedY;
                //图片高度大于控件高度
                if(rectf.height() > mHeight && canSmoothY()){
                    dy = nowMovingY - lastMovedY;
                }
                //图片宽度大于控件宽度
                if(rectf.width() > mWidth && canSmoothX()){
                    dx = nowMovingX - lastMovedX;
                }
                matrix.postTranslate(dx, dy);
                remedyXAndY(dx, dy);
                lastMovedX = nowMovingX;
                lastMovedY = nowMovingY;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                isFirstMoved = false;
                break;
        }
        return scaleGestureDetector.onTouchEvent(motionEvent);
    }

    /**
     * 判断X方向上能不能滑动
     * @return 可以滑动返回true
     */
    private boolean canSmoothX(){
        RectF rectf = getRectf(matrix);
        if(rectf.left > 0 || rectf.right < getWidth()){
            return  false;
        }
        return true;
    }

    /**
     * 判断y方向上可不可以滑动
     * @return 可以滑动返回true
     */
    private boolean canSmoothY(){
        RectF rectf = getRectf(matrix);
        if(rectf.top > 0 || rectf.bottom < getHeight()){
            return false;
        }
        return  true;
    }

    /**
     * 纠正出界的横和纵线
     * @param dx 出界偏移的横线
     * @param dy 出界偏移的纵线
     */
    private void remedyXAndY(float dx, float dy){
        if(!canSmoothX()){
            matrix.postTranslate(-dx, 0);
        }
        if(!canSmoothY()){
            matrix.postTranslate(0, -dy);
        }
        setImageMatrix(matrix);
    }
}
