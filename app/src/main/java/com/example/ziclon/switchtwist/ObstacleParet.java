package com.example.ziclon.switchtwist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class ObstacleParet extends Obstacle {

    private Bitmap img;

    public ObstacleParet(int color, int x_inici, int y_inici, int x_final, int y_final) {
        super(color, x_inici, y_inici, x_final, y_final);
        img = BitmapFactory.decodeResource(Dades.Context_Actual.getResources(), R.drawable.brick);
    }

    @Override
    public Obstacle getObstacle() {
        return this;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(img, null, obstacle, null);
    }


}
