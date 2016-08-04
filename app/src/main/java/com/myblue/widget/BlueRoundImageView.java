package com.myblue.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

/**
 * Desc:
 * Created by wangdexin on 2016/8/3.
 */
public class BlueRoundImageView extends ImageView{
    private static final String TAG = "BlueRoundImageView";
    private static final int ROUND = 6;
    private LinearGradient lg1;

    private Random mRandom = new Random();

    private int width;
    private int height;

    private int[] doughnutColors = {Color.parseColor("#42e7e0"),  Color.parseColor("#00ffffff")};
    private Paint mGradientPaint;

    private Paint mPaint;

    public BlueRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BlueRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BlueRoundImageView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mGradientPaint = new Paint();

        int rgb = Color.rgb(randomRgb(), randomRgb(), randomRgb());
        lg1 = new LinearGradient(0,0,300,560, rgb,Color.TRANSPARENT, Shader.TileMode.MIRROR);
        mGradientPaint.setShader(lg1);
        mGradientPaint.setAntiAlias(true);
    }

    private void initDraw() {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        final Rect rect = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(rect);

        int roundPx = dp2px(ROUND);
        canvas.drawRoundRect(rectF, roundPx, roundPx, mGradientPaint);
        this.setImageBitmap(bitmap);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w - getPaddingLeft() - getPaddingRight();
        height = h - getPaddingTop()-getPaddingBottom();
        initDraw();
    }

    private int randomRgb(){
        int random = mRandom.nextInt(255);
        return random;
    }


    /**
     * 绘制圆角矩形图片
     * @author caizhiming
     */
    @Override
    protected void onDraw(Canvas canvas) {
       /* Drawable drawable = getDrawable();
        if (null != drawable) {
            long start = System.currentTimeMillis();
            Bitmap bitmap = drawableToBitamp(drawable);
            bitmap = getRoundBitmap(bitmap, dp2px(ROUND));
            final Rect rectSrc = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final Rect rectDest = new Rect(0,0,getWidth(),getHeight());
            mPaint.reset();

            canvas.drawBitmap(bitmap, rectSrc, rectDest, mPaint);
            if (!bitmap.isRecycled()){
                bitmap.recycle();
            }

            Log.d("xx", "=====onDraw=====time======="+(System.currentTimeMillis() - start));
        } else {
            super.onDraw(canvas);
        }*/
        super.onDraw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (null != drawable) {
            long start = System.currentTimeMillis();
            Bitmap bitmap = drawableToBitamp(drawable);
            bitmap = getRoundBitmap(bitmap, dp2px(ROUND));
            final Rect rectSrc = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final Rect rectDest = new Rect(0,0,getWidth(),getHeight());
            mPaint.reset();

            canvas.drawBitmap(bitmap, rectSrc, rectDest, mPaint);
            if (!bitmap.isRecycled()){
                bitmap.recycle();
            }

            Log.d("xx", "===Round==draw=====time======="+(System.currentTimeMillis() - start));
        } else {
            super.draw(canvas);
        }

    }

    /**
     * drawable转bitmap
     *
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitamp(Drawable drawable)
    {
        if (drawable instanceof BitmapDrawable)
        {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }
    /**
     * 获取圆角矩形图片方法
     * @param bitmap
     * @param roundPx,一般设置成14
     * @return Bitmap
     * @author caizhiming
     */
    private Bitmap getRoundBitmap(Bitmap bitmap, int roundPx) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;

        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        mPaint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        mPaint.setColor(color);

        canvas.drawRoundRect(rectF, roundPx, roundPx, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, mPaint);
        canvas.drawRoundRect(rectF, roundPx, roundPx, mGradientPaint);
        return output;


    }

    public int dp2px(int dpVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }


    public static void resizeImageViewOnScreenSize(View view, int numColumns, int horizontalSpacing, int zoomX, int zoomY) {
        if (view == null) {
            return;
        }
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
    //    layoutParams.width = mDisplayMetrics.widthPixels / numColumns - horizontalSpacing * (numColumns - 1);
        layoutParams.height = layoutParams.width * zoomX / zoomY;
    }
}
