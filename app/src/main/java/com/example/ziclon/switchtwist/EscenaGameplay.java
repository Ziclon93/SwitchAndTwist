package com.example.ziclon.switchtwist;

import android.app.Dialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;


public class EscenaGameplay implements Escena {

    private Rect r = new Rect();

    private Dialog dialog;

    private Controller ctr;

    private Doll doll;
    private Point pos_doll;
    private ManagerObstacles mO;

    private Colision colision ;

    private boolean Doll_movement = false;
    private boolean GameOver = false;
    private boolean gameWin = false;
    private boolean again = false;
    private boolean tabacTrobat = false;
    private boolean verdActivat = false;


    private int nivell;

    private long GameOver_time;

    MediaPlayer sonidoTabaco;
    MediaPlayer sonidoNextLevel;
    MediaPlayer sonidoGameOver;
    MediaPlayer sonidoColisionParet;

    public EscenaGameplay(int nivell){

        this.nivell = nivell;
        aux_inici();
        ctr = ((Controller) Controller.getContext());

    }

    private void aux_inici() {
        mO = new ManagerObstacles((Dades.screen_width/12),this.nivell);
        ArrayList<Integer> p = mO.getPosInicialDoll();
        pos_doll = new Point((p.get(0)+((Dades.screen_width/12)/2)),(p.get(1)+((Dades.screen_width/12)/2)));
        doll = new Doll(new Rect(0,0,Dades.screen_width/16,Dades.screen_width/16), 0,this.pos_doll);
        this.colision = new Colision(this.doll, this.mO.getObstacles(), this.mO.getInteracts());
    }

    @Override
    public void Reset() {
        aux_inici();
        this.gameWin = false;
        this.GameOver = false;
    }

    @Override
    public void uptade(float l) {
        if(!GameOver) {
            doll.update(pos_doll, l);
            int col = colision.colision();
            switch (col) {
                case 1:
                    ctr.playColisioParet();
                    this.doll.colisionConPared(l);
                    break;
                case 2:
                    ctr.playGameOver();
                    GameOver = true;
                    GameOver_time = System.currentTimeMillis();
                    break;
                case 3:
                    ctr.playTabac();
                    mO.tabacTrobat(this.colision.getObsActual());
                    tabacTrobat = true;
                    break;
                case 4:
                    ctr.playNextLevel();
                    this.gameWin = true;
                    ctr = ((Controller) Controller.getContext());
                    ctr.updateProgress(nivell);
                    if (tabacTrobat) {
                        ctr.updateProgressTabac(nivell);
                    }
                    break;
                case 6:
                    verdActivat = !verdActivat;
                    //mO.botoVerd(verdActivat);
                    break;
                default:
                    if (GameOver && System.currentTimeMillis() - GameOver_time >= 2000) {
                        Reset();
                        GameOver = false;
                    }
                    break;
            }
        }
    }

    @Override
    public boolean isGameWin(){
        return this.gameWin;
    }

    @Override
    public boolean isGameOver(){
        return this.GameOver;
    }

    @Override
    public boolean isTabacTrobat(){return this.mO.isTabacTrobat();}

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        mO.draw(canvas);
        doll.draw(canvas);

    }

    @Override
    public void finalitzar() {
        //ManagerEscena.ESCENA_ACTIVA = 0;
    }

    public void Switch(){
        Obstacle p = this.colision.getObjetoAct();

        if (!(p instanceof ObstacleParet)){
            this.doll.Switch();
        }
        else {
            if (((p.getColor() == Dades.color_Blau) && (this.doll.getColor() != Dades.color_Blau)) ||
                    ((p.getColor() == Dades.color_Vermell) && (this.doll.getColor() != Dades.color_Vermell))) {

            } else {
                this.doll.Switch();
            }
        }
    }

    public void Twist() {
        this.colision.colisionTwist();
    }

    public void setDirection(int dir){
        this.doll.setDirection(dir);
    }
}
