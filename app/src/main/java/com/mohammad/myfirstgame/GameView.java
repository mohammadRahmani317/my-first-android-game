package com.mohammad.myfirstgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class GameView extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Random random = new Random();

    private float circleX = 300f;
    private float circleY = 600f;
    private final float radius = 90f;
    private int score = 0;

    public GameView(Context context) {
        super(context);
        setBackgroundColor(Color.rgb(245, 245, 245));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.DKGRAY);
        paint.setTextSize(54f);
        canvas.drawText("Score: " + score, 40f, 80f, paint);

        paint.setColor(Color.rgb(46, 204, 113));
        canvas.drawCircle(circleX, circleY, radius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return true;
        }

        float dx = event.getX() - circleX;
        float dy = event.getY() - circleY;

        if ((dx * dx) + (dy * dy) <= radius * radius) {
            score++;
            moveCircle();
            invalidate();
        }

        return true;
    }

    private void moveCircle() {
        int width = Math.max(getWidth(), 300);
        int height = Math.max(getHeight(), 500);

        int minX = (int) radius;
        int maxX = Math.max(minX + 1, width - (int) radius);
        int minY = 160;
        int maxY = Math.max(minY + 1, height - (int) radius);

        circleX = minX + random.nextInt(maxX - minX);
        circleY = minY + random.nextInt(maxY - minY);
    }
}
