package com.example.ziclon.switchtwist;

public class ObstacleBotoVerd extends Obstacle {

    public ObstacleBotoVerd(int x_inici, int y_inici, int x_final, int y_final) {
        super(Dades.color_verd, x_inici, y_inici, x_final, y_final);
    }

    @Override
    public void update() {
    }

    @Override
    public Obstacle getObstacle() {
        return this;
    }
}
