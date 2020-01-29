package com.example.ziclon.switchtwist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;


public class Doll implements GameObject {

    static final private int vel = (int) ((Dades.screen_width / 12.0) / 30.0);
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
    private Point pos_doll;
    private AnimacioDollManager mA;

    public Doll(Rect rectangle, int color, Point pos) {
        this.dollRect = rectangle;
        this.color = color;
        this.direction = 0;
        this.mooving = false;
        this.pos_doll = pos;

        ini_anim();
    }


    public void ini_anim() {
        Bitmap anim1;
        Bitmap anim2;
        Bitmap anim3;
        Bitmap anim4;

        Animacio idleB;
        Animacio walkUpB;
        Animacio walkDownB;
        Animacio walkRightB;
        Animacio walkLeftB;

        Animacio idleR;
        Animacio walkUpR;
        Animacio walkDownR;
        Animacio walkRightR;
        Animacio walkLeftR;

        BitmapFactory bf = new BitmapFactory();

        anim1 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb3a);
        idleB = new Animacio(new Bitmap[]{anim1}, 0.5f);

        anim1 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr3a);
        idleR = new Animacio(new Bitmap[]{anim1}, 0.5f);

        // ARRIBA
        anim1 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb2a);
        anim2 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb1a);
        anim3 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb4a);
        anim4 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb3a);
        walkUpB = new Animacio(new Bitmap[]{anim1, anim2, anim3, anim4}, 0.5f);

        anim1 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr2a);
        anim2 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr1a);
        anim3 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr4a);
        anim4 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr3a);
        walkUpR = new Animacio(new Bitmap[]{anim1, anim2, anim3, anim4}, 0.5f);


        // ABAJO
        anim1 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb3a);
        anim2 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb4a);
        anim3 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb1a);
        anim4 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb2a);
        walkDownB = new Animacio(new Bitmap[]{anim1, anim2, anim3, anim4}, 0.5f);


        anim1 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr3a);
        anim2 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr4a);
        anim3 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr1a);
        anim4 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr2a);
        walkDownR = new Animacio(new Bitmap[]{anim1, anim2, anim3, anim4}, 0.5f);

        // DERECHA
        anim1 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb3a);
        anim2 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb4d);
        anim3 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb1d);
        anim4 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb2d);
        walkRightB = new Animacio(new Bitmap[]{anim1, anim2, anim3, anim4}, 0.5f);

        anim1 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr3a);
        anim2 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr4d);
        anim3 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr1d);
        anim4 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr2b);
        walkRightR = new Animacio(new Bitmap[]{anim1, anim2, anim3, anim4}, 0.5f);

        // IZQUIERDA
        anim1 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb3a);
        anim2 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb2d);
        anim3 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb1d);
        anim4 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollb4d);
        walkLeftB = new Animacio(new Bitmap[]{anim1, anim2, anim3, anim4}, 0.5f);

        anim1 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr3a);
        anim2 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr2b);
        anim3 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr1d);
        anim4 = bf.decodeResource(Dades.Context_Actual.getResources(), R.drawable.dollr4d);
        walkLeftR = new Animacio(new Bitmap[]{anim1, anim2, anim3, anim4}, 0.5f);


        Animacio[] animacioList = {idleB, walkUpB, walkDownB, walkRightB, walkLeftB,
                idleR, walkUpR, walkDownR, walkRightR, walkLeftR};
        mA = new AnimacioDollManager(animacioList);
    }


    @Override
    public void draw(Canvas canvas) {
        mA.draw(canvas, this.dollRect);
    }

    public int getDir() {
        return this.direction;
    }

    public void setDirection(int dir) {
        if (!this.mooving) {
            this.direction = dir;
            this.mooving = true;
        }
        if (this.mooving && (dir == 0)) {
            this.direction = dir;
            this.mooving = false;
        }
    }

    public void move(float l) {
        int speed = (int) (this.vel * l);
        System.out.println("----------------\n" + speed + " " + l);
        switch (this.direction) {
            case 0: // quieto
                if (this.mooving) {
                    this.mooving = false;
                }
                break;
            case 1: //arriba
                if (!this.mooving) {
                    this.mooving = true;
                }
                this.pos_doll.set(pos_doll.x, pos_doll.y - speed);

                break;
            case 2: //abajo
                if (!this.mooving) {
                    this.mooving = true;
                }
                this.pos_doll.set(pos_doll.x, pos_doll.y + speed);

                break;
            case 3: //Derecha
                if (!this.mooving) {
                    this.mooving = true;
                }
                this.pos_doll.set(pos_doll.x + speed, pos_doll.y);
                break;
            case 4: //Izquierda
                if (!this.mooving) {
                    this.mooving = true;
                }
                this.pos_doll.set(pos_doll.x - speed, pos_doll.y);
                break;
        }
    }

    public void buttonTwist(int dir) {
        this.direction = dir;
        if (!this.mooving) {
            this.mooving = true;
        }
    }

    public void colisionConPared(float l) {
        int speed = (int) (this.vel * l);
        switch (this.direction) {
            case 1: //arriba
                this.pos_doll.set(pos_doll.x, pos_doll.y + (2 * speed));
                this.direction = 0;
                this.mooving = false;

                break;
            case 2: //abajo
                this.pos_doll.set(pos_doll.x, pos_doll.y - (2 * speed));
                this.direction = 0;
                this.mooving = false;
                break;
            case 3: //Derecha
                this.pos_doll.set(pos_doll.x - (2 * speed), pos_doll.y);
                this.direction = 0;
                this.mooving = false;
                break;
            case 4: //Izquierda
                this.pos_doll.set(pos_doll.x + (speed * 2), pos_doll.y);
                this.direction = 0;
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

        dollRect.set(point.x - dollRect.width() / 2, point.y - dollRect.height() / 2, point.x + dollRect.width() / 2, point.y + dollRect.height() / 2);

        int state = 0;

        if (dollRect.left - oldLeft > 130) {
            state = 1;
        } else if (dollRect.left - oldLeft < 130) {
            state = 2;
        }

        if (this.mooving) {
            this.move(l);
        }
        mA.playAnimacio((this.color * 5) + this.direction );
        mA.update();
    }

    public Rect getRect() {
        return dollRect;
    }

    public void Switch() {
        if (color == 0) {
            this.color = 1;
        } else {
            this.color = 0;
        }
    }


    public int getColor() {
        return this.color;
    }

    public void setMovement(boolean movement) {
        this.mooving = movement;
    }

    public void setPos(int x, int y) {
        this.pos_doll.set(x, y);
    }
}
