package com.example.activity.smartparking;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class DefineImageView extends AppCompatImageView {

    float width,height,location_x,location_y;

    private Paint mPaint ,paint;
    private Matrix matrix;

    public DefineImageView(Context context) {
        this(context,null);
        init(context,null);
    }

    public DefineImageView(Context context, AttributeSet attributes){
        this(context,null,0);
        init(context,attributes);
    }

    public DefineImageView(Context context,AttributeSet attributes,int stylrAttra){
        super(context,attributes,stylrAttra);
        init(context,attributes);
        paint =new Paint();
        paint.setAntiAlias(true);
        matrix = new Matrix();
    }

    private void init(Context context,AttributeSet attributeSet){
//        if(Builder.VERSION.SDK_INK < 18){
//            setLayerType(View.LAYER_TYPE_SOFTWARE,null);
//        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        location_x = getX();
        location_y = getY();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            super.onDraw(canvas);
            return;
        }
        if (drawable instanceof BitmapDrawable) {
            paint.setShader(initBitmapShader((BitmapDrawable) drawable));//将着色器设置给画笔
            canvas.drawCircle(width/2, height/2, Math.min(width,height)/2, paint);//使用画笔在画布上画圆
            return;
        }
        super.onDraw(canvas);
    }

    /**
     * 获取ImageView中资源图片的Bitmap，利用Bitmap初始化图片着色器,通过缩放矩阵将原资源图片缩放到铺满整个绘制区域，避免边界填充
     */
    private BitmapShader initBitmapShader(BitmapDrawable drawable) {
        Bitmap bitmap = drawable.getBitmap();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = Math.max(width / bitmap.getWidth(), height / bitmap.getHeight());
        matrix.setScale(scale, scale);//将图片宽高等比例缩放，避免拉伸
        bitmapShader.setLocalMatrix(matrix);
        return bitmapShader;
    }
}
