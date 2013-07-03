package com.ktisha.drawhero;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * User : ktisha
 */
public class DrawingView extends View {

    private Paint paint;
    private Path path;

    public DrawingView(Context context , AttributeSet attrs) {
        super(context, attrs);

        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setColor(Color.BLACK);
        this.paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(5f);

        this.path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                path.lineTo(eventX, eventY);
                break;
            default:
                return false;
        }

        // Schedules a repaint.
        invalidate();
        return true;
    }

    public void clear() {
        path.reset();
        invalidate();
    }

    public void setPaintColor(int color) {
        paint.setColor(color);
    }

    public int getCurrentPaintColor() {
        return paint.getColor();
    }
}