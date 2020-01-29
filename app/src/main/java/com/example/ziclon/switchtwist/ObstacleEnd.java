package com.example.ziclon.switchtwist;


public class ObstacleEnd extends Obstacle{

    public ObstacleEnd(int x_inici, int y_inici, int x_final, int y_final){
        super(x_inici,y_inici,x_final,y_final);
        this.color = Dades.color_end;
    }

    @Override
    public void update() {
    }

    @Override
    public Obstacle getObstacle() {
        return null;
    }
}
