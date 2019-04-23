package com.example.ziclon.switchtwist;

import android.graphics.Rect;

import java.util.ArrayList;



public class Colision {
    private Doll doll;
    private ArrayList<ArrayList<Obstacle>> listaObstacles;
    private ArrayList<ArrayList<Obstacle>> listaInteracts;

    private int arrayDollI;
    private int arrayDollJ;

    private Obstacle obsActual;


    public Colision(Doll doll, ArrayList<ArrayList<Obstacle>> c, ArrayList<ArrayList<Obstacle>> i){
        this.doll = doll;

        this.listaObstacles = c;
        this.listaInteracts = i;

        this.arrayDollI = doll.getRect().centerX() / (Dades.screen_width/12);
        this.arrayDollJ = doll.getRect().centerY() / (Dades.screen_width/12);

    }

    public int getActualItabac(){
        return this.arrayDollI;
    }

    public int getActualJtabac(){
        return this.arrayDollJ;
    }

    public Obstacle getObsActual(){
        return this.obsActual;
    }

    private Obstacle getPos(){
        this.arrayDollI = doll.getRect().centerX() / (Dades.screen_width/12);
        this.arrayDollJ = doll.getRect().centerY() / (Dades.screen_width/12);
        Obstacle p = null;

        //primero la colision con objetos de interacts (si colisionamos con un objeto en cualquier caso tiene
        //preferencia la pared

        if (this.listaInteracts.get(arrayDollJ).get(arrayDollI) != null){
            p = this.listaInteracts.get(arrayDollJ).get(arrayDollI);
        }
        if(!(this.listaObstacles.get(arrayDollJ).get(arrayDollI) instanceof ObstacleSuelo)){
            p = this.listaObstacles.get(arrayDollJ).get(arrayDollI);
        }

        int dir = doll.getDir();

        switch (dir){
            case 1:
                if (arrayDollJ > 0) {
                    if (this.listaInteracts.get(arrayDollJ-1).get(arrayDollI) != null) {
                        if (Rect.intersects(this.listaInteracts.get(arrayDollJ - 1).get(arrayDollI).getRect(), doll.getRect())) {
                            p = this.listaInteracts.get(arrayDollJ - 1).get(arrayDollI);
                        }
                    }
                }

                if (arrayDollJ > 0){
                    if ((!(this.listaObstacles.get(arrayDollJ-1).get(arrayDollI) instanceof ObstacleSuelo)) &&
                            (Rect.intersects(this.listaObstacles.get(arrayDollJ-1).get(arrayDollI).getRect(),doll.getRect()))){
                        p = this.listaObstacles.get(arrayDollJ-1).get(arrayDollI);
                    }
                }

                break;
            case 2:
                if (arrayDollJ < 16) {
                    if (this.listaInteracts.get(arrayDollJ+1).get(arrayDollI) != null) {
                        if (Rect.intersects(this.listaInteracts.get(arrayDollJ + 1).get(arrayDollI).getRect(), doll.getRect())) {
                            p = this.listaInteracts.get(arrayDollJ + 1).get(arrayDollI);
                        }
                    }
                }
                if (arrayDollJ < 16){
                    if ((!(this.listaObstacles.get(arrayDollJ+1).get(arrayDollI) instanceof ObstacleSuelo)) &&
                            (Rect.intersects(this.listaObstacles.get(arrayDollJ+1).get(arrayDollI).getRect(),doll.getRect()))){
                        p = this.listaObstacles.get(arrayDollJ+1).get(arrayDollI);
                    }
                }
                break;
            case 3:
                if (arrayDollI < 12) {
                    if (this.listaInteracts.get(arrayDollJ).get(arrayDollI+1) != null) {
                        if (Rect.intersects(this.listaInteracts.get(arrayDollJ).get(arrayDollI + 1).getRect(), doll.getRect())) {
                            p = this.listaInteracts.get(arrayDollJ).get(arrayDollI + 1);
                        }
                    }
                }
                if (arrayDollI < 12){
                    if ((!(this.listaObstacles.get(arrayDollJ).get(arrayDollI+1) instanceof ObstacleSuelo)) &&
                            (Rect.intersects(this.listaObstacles.get(arrayDollJ).get(arrayDollI+1).getRect(),doll.getRect()))){
                        p = this.listaObstacles.get(arrayDollJ).get(arrayDollI+1);
                    }
                }
                break;
            case 4:
                if (arrayDollI > 0) {
                    if (this.listaInteracts.get(arrayDollJ).get(arrayDollI-1) != null) {
                        if (Rect.intersects(this.listaInteracts.get(arrayDollJ).get(arrayDollI - 1).getRect(), doll.getRect())) {
                            p = this.listaInteracts.get(arrayDollJ).get(arrayDollI - 1);

                        }
                    }
                }
                if (arrayDollI > 0){
                    if ((!(this.listaObstacles.get(arrayDollJ).get(arrayDollI-1) instanceof ObstacleSuelo)) &&
                            (Rect.intersects(this.listaObstacles.get(arrayDollJ).get(arrayDollI-1).getRect(),doll.getRect()))){
                        p = this.listaObstacles.get(arrayDollJ).get(arrayDollI-1);
                    }
                }
                break;
        }


        return p;
    }

    public Obstacle getObjetoAct(){
        return this.getPos();
    }

    public int colision() {
        //devolverá 0 si no colisiona, 1 si es una pared, 2 si es una amenaza que puede matarnos, 3 si es tabaco,4 si pasas por encima de un twist y 5 si llegas al final del nivel.
        int valor = 0;

        Obstacle o = this.getPos();

        if (o!=null){

            if (o instanceof ObstacleParet) {
                valor = 1;
            } else if (o instanceof ObstacleLaser) {
                if ((o.getColor() == Dades.color_Vermell && doll.getColor() == 1) || (o.getColor() == Dades.color_Blau && doll.getColor() == 0)) {
                    if (valor != 1) {
                        valor = 0;
                    }
                } else {
                    if (valor != 1) {
                        valor = 2;
                    }
                }
            } else if (o instanceof ObstacleTabac) {
                if (valor != 1) {
                    valor = 3;
                }
            } else if (o instanceof ObstacleEnd) {
                if (valor != 1) {
                    valor = 4;
                }
            } else if (o instanceof ObstacleBotoVerd) {
                if(valor != 1){
                    valor = 6;
                }
            }
        }

        this.obsActual = o;
        return valor;
    }

    public void colisionTwist() {
        Obstacle p = this.getPos();
        if (p instanceof ObstacleTwist) {
            if ((p.getColor() == Dades.color_Vermell && doll.getColor() == 1) || (p.getColor() == Dades.color_Blau && doll.getColor() == 0)) {
                if (((ObstacleTwist) p).getDir() == 0) {//arriba
                    this.doll.buttonTwist(1);
                    this.doll.setPos(p.getRect().centerX(), p.getRect().centerY());
                }
                if (((ObstacleTwist) p).getDir() == 1) {//abajo
                    this.doll.buttonTwist(2);
                    this.doll.setPos(p.getRect().centerX(), p.getRect().centerY());
                }
                if (((ObstacleTwist) p).getDir() == 2) {//derecha
                    this.doll.buttonTwist(3);
                    this.doll.setPos(p.getRect().centerX(), p.getRect().centerY());
                }
                if (((ObstacleTwist) p).getDir() == 3) {//izquierda
                    this.doll.buttonTwist(4);
                    this.doll.setPos(p.getRect().centerX(), p.getRect().centerY());
                }
            }
        }
    }
}

