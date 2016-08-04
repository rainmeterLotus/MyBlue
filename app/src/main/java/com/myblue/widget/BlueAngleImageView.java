
package com.myblue.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;

import com.myblue.dao.R;

import java.util.Random;

public class BlueAngleImageView extends ImageView {

    private Paint paint;
    private static final int ROUND = 6;
    private int roundWidth = dp2px(ROUND);
    private int roundHeight = dp2px(ROUND);
    private Paint paint2;
    private Paint mGradientPaint;
    private LinearGradient lg1;

    private Random mRandom = new Random();

    private int width;
    private int height;


    public BlueAngleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public BlueAngleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BlueAngleImageView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BlueAngleImageView);
            roundWidth = a.getDimensionPixelSize(R.styleable.BlueAngleImageView_roundAngleWidth,
                    roundWidth);
            roundHeight = a.getDimensionPixelSize(R.styleable.BlueAngleImageView_roundAngleHeight,
                    roundHeight);
            a.recycle();
        } else {
            float density = context.getResources().getDisplayMetrics().density;
            roundWidth = (int) (roundWidth * density);
            roundHeight = (int) (roundHeight * density);
        }

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        paint2 = new Paint();
        paint2.setXfermode(null);

        mGradientPaint = new Paint();

        int rgb = Color.rgb(randomRgb(), randomRgb(), randomRgb());
        lg1 = new LinearGradient(0,0,900,600, rgb,Color.TRANSPARENT, Shader.TileMode.MIRROR);
        mGradientPaint.setShader(lg1);
        mGradientPaint.setAntiAlias(true);
    }

    private int randomRgb(){
        int random = mRandom.nextInt(255);
        return random;
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

    @Override
    public void draw(Canvas canvas) {
        long start = System.currentTimeMillis();
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmap);
        super.draw(canvas2);
        drawLiftUp(canvas2);
        drawRightUp(canvas2);
        drawLiftDown(canvas2);
        drawRightDown(canvas2);
        canvas.drawBitmap(bitmap, 0, 0, paint2);
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        canvas.drawRoundRect(rectF, roundWidth, roundWidth, mGradientPaint);
        bitmap.recycle();
        canvas.restore();
        Log.d("xx", "==1=Angle==draw=====time======="+(System.currentTimeMillis() - start));
    }

    private void drawLiftUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, roundHeight);
        path.lineTo(0, 0);
        path.lineTo(roundWidth, 0);
        path.arcTo(new RectF(0, 0, roundWidth * 2, roundHeight * 2), -90, -90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawLiftDown(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, getHeight() - roundHeight);
        path.lineTo(0, getHeight());
        path.lineTo(roundWidth, getHeight());
        path.arcTo(new RectF(0, getHeight() - roundHeight * 2, 0 + roundWidth * 2, getHeight()),
                90, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawRightDown(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth() - roundWidth, getHeight());
        path.lineTo(getWidth(), getHeight());
        path.lineTo(getWidth(), getHeight() - roundHeight);
        path.arcTo(new RectF(getWidth() - roundWidth * 2, getHeight() - roundHeight * 2,
                getWidth(), getHeight()), 0, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawRightUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth(), roundHeight);
        path.lineTo(getWidth(), 0);
        path.lineTo(getWidth() - roundWidth, 0);
        path.arcTo(new RectF(getWidth() - roundWidth * 2, 0, getWidth(), 0 + roundHeight * 2), -90,
                90);
        path.close();
        canvas.drawPath(path, paint);
    }


    public int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }
}
