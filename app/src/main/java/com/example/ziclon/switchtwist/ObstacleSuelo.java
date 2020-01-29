package com.example.ziclon.switchtwist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by ziclon on 10/05/17.
 */

public class ObstacleSuelo extends Obstacle {
    private Bitmap img;

    public ObstacleSuelo(int color, int x_inici, int y_inici, int x_final, int y_final) {
        super(color, x_inici, y_inici, x_final, y_final);
        this.color = color;

        img = BitmapFactory.decodeResource(Dades.Context_Actual.getResources(), R.drawable.suelo);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(img, null, obstacle, null);
    }

    @Override
    public Obstacle getObstacle() {
        return this;
    }

}
