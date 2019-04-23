package com.example.ziclon.switchtwist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;


public class Doll implements GameObject {

    static final private int vel = (int)((Dades.screen_width/12.0)/30.0);
    private Rect dollRect;
    private int color; //0: azul -- 1: rojo
    private int direction;
    /*
    0- quieto
    1- arriba
    2- abajo
    3- derecha
    4- izquierda
    */
    private boolean mooving;
    private Animacio idle;
    private Animacio walkRight;
    private Animacio walkLeft;
    private Point pos_doll;
    private ManagerAnimacio mA;

    public Doll(Rect rectangle, int color,Point pos){
        this.dollRect = rectangle;
        this.color = color;
        this.direction = 0;
        this.mooving = false;
        this.pos_doll = pos;

        ini_anim(0);
    }


    public void ini_anim(int i){

        BitmapFactory bf = new BitmapFactory();

        if(i == 0) {
            Bitmap idleImgB = bf.decodeResource(Dades.Context_Actual.getResources(),R.drawable.doll);
            Bitmap walk1B = bf.decodeResource(Dades.Context_Actual.getResources(),R.drawable.dollblanc);
            Bitmap walk2B = bf.decodeResource(Dades.Context_Actual.getResources(),R.drawable.doll);

            Create_anim(idleImgB,walk1B,walk2B);

        }else {

            Bitmap idleImgR = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr);
            Bitmap walk1R = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollrblanc);
            Bitmap walk2R = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr);

            Create_anim(idleImgR,walk1R,walk2R);
        }
    }


    @Override
    public void draw(Canvas canvas) {
        mA.draw(canvas,this.dollRect);
    }

    public int getDir(){
        return this.direction;
    }

    public void setDirection(int dir){
        if (!this.mooving){
            this.direction = dir;
            this.mooving = true;
        }
        if (this.mooving && (dir== 0)){
            this.direction = dir;
            this.mooving = false;
        }
    }

    public void move(float l){
       int speed = (int)(this.vel * l);
        System.out.println("----------------\n" + speed + " " + l);
        switch(this.direction){
            case 0: // quieto
                if (this.mooving){
                    this.mooving = false;
                }
                break;
            case 1: //arriba
                if (!this.mooving){
                    this.mooving = true;
                }
                this.pos_doll.set(pos_doll.x ,pos_doll.y - speed);

                break;
            case 2: //abajo
                if (!this.mooving){
                    this.mooving = true;
                }
                this.pos_doll.set(pos_doll.x ,pos_doll.y + speed);

                break;
            case 3: //Derecha
                if (!this.mooving){
                    this.mooving = true;
                }
                this.pos_doll.set(pos_doll.x + speed ,pos_doll.y);
                break;
            case 4: //Izquierda
                if (!this.mooving){
                    this.mooving = true;
                }
                this.pos_doll.set(pos_doll.x - speed ,pos_doll.y);
                break;
        }
    }
    public void buttonTwist(int dir){
        this.direction = dir;
        if(!this.mooving){
            this.mooving =true;
        }
    }

    public void colisionConPared(float l){
        int speed = (int)(this.vel * l);
        switch(this.direction){
            case 1: //arriba
                this.pos_doll.set(pos_doll.x ,pos_doll.y + (2*speed));
                this.mooving = false;

                break;
            case 2: //abajo
                this.pos_doll.set(pos_doll.x ,pos_doll.y - (2*speed));
                this.mooving = false;
                break;
            case 3: //Derecha
                this.pos_doll.set(pos_doll.x - (2*speed) ,pos_doll.y);
                this.mooving = false;
                break;
            case 4: //Izquierda
                this.pos_doll.set(pos_doll.x + (speed*2) ,pos_doll.y);
                this.mooving = false;
                break;
        }
    }

    @Override
    public void update() {
        mA.update();
    }

    public void update(Point point, float l) {
        float oldLeft = dollRect.left;

        dollRect.set(point.x - dollRect.width()/2, point.y - dollRect.height()/2, point.x + dollRect.width()/2, point.y + dollRect.height()/2);

        int state = 0;

        if(dollRect.left-oldLeft > 130) {
            state = 1;
        }else if (dollRect.left - oldLeft < 130){
            state = 2;
        }

        if (this.mooving){
            this.move(l);
        }
        mA.playAnimacio(state);
        mA.update();
    }

    public Rect getRect(){
        return dollRect;
    }


    public void Create_anim(Bitmap idleImg,Bitmap walk1,Bitmap walk2){
        idle = new Animacio(new Bitmap[]{idleImg}, 2);

        walkRight = new Animacio(new Bitmap[]{walk1,walk2}, 0.5f);
        walkLeft = new Animacio(new Bitmap[]{walk1,walk2}, 0.5f);

        mA = new ManagerAnimacio(new Animacio[]{idle,walkRight,walkLeft});
    }

    public void Switch(){
        if (color == 0){
            ini_anim(1);
            this.color = 1;
        }
        else{
            ini_anim(0);
            this.color = 0;
        }
    }


    public int getColor(){
        return this.color;
    }

    public void setMovement(boolean movement) {
        this.mooving = movement;
    }

    public void setPos(int x, int y){
        this.pos_doll.set(x,y);
    }
}
