package com.example.ziclon.switchtwist;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class ManagerObstacles {

    //higjer index = lower on screen = higher y value
    private ArrayList<ArrayList<Obstacle>> obstacles;
    private ArrayList<ArrayList<Obstacle>> interacts;

    private int posI;
    private int posJ;

    private int ObstacleHeight;
    private long startTime;
    private boolean tabacTrobat;

    MediaPlayer sonidoTabaco;
    private Controller ctr;

    public ManagerObstacles(int ObstacleHeight,int nivell) {
        this.ObstacleHeight = ObstacleHeight;

        startTime = System.currentTimeMillis();

        obstacles = new ArrayList<>();
        interacts = new ArrayList<>();

        this.tabacTrobat = false;

        repartirObstacles(nivell);
    }

    private void repartirObstacles(int nivell){

        String map = getLvl(nivell,false);


        int dim = this.ObstacleHeight;
        int x = 0;
        int y = 0;
        int col = 0;

        ArrayList<Obstacle> listaO = new ArrayList<Obstacle>();
        ArrayList<Obstacle> listaI = new ArrayList<Obstacle>();

        for(int it = 0; it < map.length(); it++){
            char c = map.charAt(it);
            /*
            P = pared
            V = laser Rojo
            B = laser Azul
            T = tabaco
            E = end level


            W = arriba azul
            S = abajo azul
            D = derecha azul
            A = izquierda azul

            I = arriba rojo
            K = abajo rojo
            L = derecha rojo
            J = izquierda rojo

            C = Boton verde

            1 = Pared roja
            2 = Pared azul


            direcciones de los twist:
            1- arriba
            2- abajo
            3- derecha
            4- izquierda

            X = Posición inicial doll

             */

            switch(c){
                case 'P':
                    ObstacleParet paret = new ObstacleParet(Dades.color_parets,x,y,x+dim,y+dim);
                    listaO.add(paret);
                    listaI.add(null);
                    break;

                case '1':
                    paret = new ObstacleParet(Dades.color_Vermell,x,y,x+dim,y+dim);
                    listaO.add(paret);
                    listaI.add(null);
                    break;

                case '2':
                    paret = new ObstacleParet(Dades.color_Blau,x,y,x+dim,y+dim);
                    listaO.add(paret);
                    listaI.add(null);
                    break;

                case 'C':
                    ObstacleSuelo suelo = new ObstacleSuelo(Dades.color_suelo, x,y,x+dim,y+dim);
                    listaO.add(suelo);
                    ObstacleBotoVerd botoVerd= new ObstacleBotoVerd(x,y,x+dim,y+dim);
                    listaI.add(botoVerd);
                    break;
                case 'V':
                    suelo = new ObstacleSuelo(Dades.color_suelo, x,y,x+dim,y+dim);
                    listaO.add(suelo);
                    ObstacleLaser laserVermell = new ObstacleLaser(Dades.color_Vermell,x + ((Dades.screen_width/12)/6),y + ((Dades.screen_width/12)/6),x+dim - ((Dades.screen_width/12)/6),y+dim - ((Dades.screen_width/12)/6));
                    listaI.add(laserVermell);
                    break;
                case 'B':
                    suelo = new ObstacleSuelo(Dades.color_suelo, x,y,x+dim,y+dim);
                    listaO.add(suelo);
                    ObstacleLaser laserBlau = new ObstacleLaser(Dades.color_Blau,x + ((Dades.screen_width/12)/6),y + ((Dades.screen_width/12)/6),x+dim - ((Dades.screen_width/12)/6),y+dim - ((Dades.screen_width/12)/6));
                    listaI.add(laserBlau);
                    break;
                case 'N':
                    suelo = new ObstacleSuelo(Dades.color_suelo, x,y,x+dim,y+dim);
                    listaO.add(suelo);
                    ObstacleLaser laserGris = new ObstacleLaser(Dades.color_Gris,x + ((Dades.screen_width/12)/6),y + ((Dades.screen_width/12)/6),x+dim - ((Dades.screen_width/12)/6),y+dim - ((Dades.screen_width/12)/6));
                    listaI.add(laserGris);
                    break;
                case 'T':
                    suelo = new ObstacleSuelo(Dades.color_suelo, x,y,x+dim,y+dim);
                    listaO.add(suelo);
                    ObstacleTabac tabac = new ObstacleTabac(x,y,x+dim,y+dim);
                    listaI.add(tabac);
                    break;
                case 'E':
                    ObstacleEnd end = new ObstacleEnd(x,y,x+dim,y+dim);
                    listaO.add(end);
                    listaI.add(null);
                    break;
                case 'W':
                    ObstacleTwist arribaB = new ObstacleTwist(0,Dades.color_Blau,x,y,x+dim,y+dim);
                    listaO.add(arribaB);
                    listaI.add(null);
                    break;
                case 'S':
                    ObstacleTwist abajoB = new ObstacleTwist(1,Dades.color_Blau,x,y,x+dim,y+dim);
                    listaO.add(abajoB);
                    listaI.add(null);
                    break;
                case 'D':
                    ObstacleTwist derechaB = new ObstacleTwist(2,Dades.color_Blau,x,y,x+dim,y+dim);
                    listaO.add(derechaB);
                    listaI.add(null);
                    break;
                case 'A':
                    ObstacleTwist izquierdaB = new ObstacleTwist(3,Dades.color_Blau,x,y,x+dim,y+dim);
                    listaO.add(izquierdaB);
                    listaI.add(null);
                    break;
                case 'I':
                    ObstacleTwist arribaR = new ObstacleTwist(0,Dades.color_Vermell,x,y,x+dim,y+dim);
                    listaO.add(arribaR);
                    listaI.add(null);
                    break;
                case 'K':
                    ObstacleTwist abajoR = new ObstacleTwist(1,Dades.color_Vermell,x,y,x+dim,y+dim);
                    listaO.add(abajoR);
                    listaI.add(null);
                    break;
                case 'L':
                    ObstacleTwist derechaR = new ObstacleTwist(2,Dades.color_Vermell,x,y,x+dim,y+dim);
                    listaO.add(derechaR);
                    listaI.add(null);
                    break;
                case 'J':
                    ObstacleTwist izquierdaR = new ObstacleTwist(3,Dades.color_Vermell,x,y,x+dim,y+dim);
                    listaO.add(izquierdaR);
                    listaI.add(null);
                    break;
                case 'O':
                    suelo = new ObstacleSuelo(Dades.color_suelo, x,y,x+dim,y+dim);
                    listaO.add(suelo);
                    listaI.add(null);
                    break;
                case 'X':
                    this.posI = x;
                    this.posJ = y;
                    suelo = new ObstacleSuelo(Dades.color_suelo, x,y,x+dim,y+dim);
                    listaO.add(suelo);
                    listaI.add(null);

                    break;

            }

            x = x + dim;
            col = it % 12;
            if(col == 11){
                x = 0;
                y = y + dim;
                obstacles.add(listaO);
                interacts.add(listaI);
                listaO = new ArrayList<Obstacle>();
                listaI = new ArrayList<Obstacle>();
            }
        }
    }

    public void repartirObstaclesBotoVerd(int nivell, boolean verdActivat){
        String map = map = getLvl(nivell,verdActivat);

        obstacles.clear();
        interacts.clear();


        int dim = this.ObstacleHeight;
        int x = 0;
        int y = 0;
        int col = 0;

        ArrayList<Obstacle> listaO = new ArrayList<Obstacle>();
        ArrayList<Obstacle> listaI = new ArrayList<Obstacle>();

        for(int it = 0; it < map.length()-1; it++){
            char c = map.charAt(it+1);
            /*
            P = pared
            V = laser Rojo
            B = laser Azul
            T = tabaco
            E = end level


            W = arriba azul
            S = abajo azul
            D = derecha azul
            A = izquierda azul

            I = arriba rojo
            K = abajo rojo
            L = derecha rojo
            J = izquierda rojo

            C = pulsador Verde



            direcciones de los twist:
            1- arriba
            2- abajo
            3- derecha
            4- izquierda

            X = Posición inical doll

             */

            switch(c){

                case 'P':
                    ObstacleParet paret = new ObstacleParet(Dades.color_parets,x,y,x+dim,y+dim);
                    listaO.add(paret);
                    listaI.add(null);
                    break;
                case 'C':
                    ObstacleSuelo suelo = new ObstacleSuelo(Dades.color_suelo, x,y,x+dim,y+dim);
                    listaO.add(suelo);
                    ObstacleBotoVerd botoVerd= new ObstacleBotoVerd(x,y,x+dim,y+dim);
                    listaI.add(botoVerd);
                    break;
                case 'V':
                    suelo = new ObstacleSuelo(Dades.color_suelo, x,y,x+dim,y+dim);
                    listaO.add(suelo);
                    ObstacleLaser laserVermell = new ObstacleLaser(Dades.color_Vermell,x + ((Dades.screen_width/12)/6),y + ((Dades.screen_width/12)/6),x+dim - ((Dades.screen_width/12)/6),y+dim - ((Dades.screen_width/12)/6));
                    listaI.add(laserVermell);
                    break;
                case 'B':
                    suelo = new ObstacleSuelo(Dades.color_suelo, x,y,x+dim,y+dim);
                    listaO.add(suelo);
                    ObstacleLaser laserBlau = new ObstacleLaser(Dades.color_Blau,x + ((Dades.screen_width/12)/6),y + ((Dades.screen_width/12)/6),x+dim - ((Dades.screen_width/12)/6),y+dim - ((Dades.screen_width/12)/6));
                    listaI.add(laserBlau);
                    break;
                case 'N':
                    suelo = new ObstacleSuelo(Dades.color_suelo, x,y,x+dim,y+dim);
                    listaO.add(suelo);
                    ObstacleLaser laserGris = new ObstacleLaser(Dades.color_Gris,x + ((Dades.screen_width/12)/6),y + ((Dades.screen_width/12)/6),x+dim - ((Dades.screen_width/12)/6),y+dim - ((Dades.screen_width/12)/6));
                    listaI.add(laserGris);
                    break;
                case 'T':
                    suelo = new ObstacleSuelo(Dades.color_suelo, x,y,x+dim,y+dim);
                    listaO.add(suelo);
                    ObstacleTabac tabac = new ObstacleTabac(x,y,x+dim,y+dim);
                    listaI.add(tabac);
                    break;
                case 'E':
                    ObstacleEnd end = new ObstacleEnd(x,y,x+dim,y+dim);
                    listaO.add(end);
                    listaI.add(null);
                    break;
                case 'W':
                    ObstacleTwist arribaB = new ObstacleTwist(0,Dades.color_Blau,x,y,x+dim,y+dim);
                    listaO.add(arribaB);
                    listaI.add(null);
                    break;
                case 'S':
                    ObstacleTwist abajoB = new ObstacleTwist(1,Dades.color_Blau,x,y,x+dim,y+dim);
                    listaO.add(abajoB);
                    listaI.add(null);
                    break;
                case 'D':
                    ObstacleTwist derechaB = new ObstacleTwist(2,Dades.color_Blau,x,y,x+dim,y+dim);
                    listaO.add(derechaB);
                    listaI.add(null);
                    break;
                case 'A':
                    ObstacleTwist izquierdaB = new ObstacleTwist(3,Dades.color_Blau,x,y,x+dim,y+dim);
                    listaO.add(izquierdaB);
                    listaI.add(null);
                    break;
                case 'I':
                    ObstacleTwist arribaR = new ObstacleTwist(0,Dades.color_Vermell,x,y,x+dim,y+dim);
                    listaO.add(arribaR);
                    listaI.add(null);
                    break;
                case 'K':
                    ObstacleTwist abajoR = new ObstacleTwist(1,Dades.color_Vermell,x,y,x+dim,y+dim);
                    listaO.add(abajoR);
                    listaI.add(null);
                    break;
                case 'L':
                    ObstacleTwist derechaR = new ObstacleTwist(2,Dades.color_Vermell,x,y,x+dim,y+dim);
                    listaO.add(derechaR);
                    listaI.add(null);
                    break;
                case 'J':
                    ObstacleTwist izquierdaR = new ObstacleTwist(3,Dades.color_Vermell,x,y,x+dim,y+dim);
                    listaO.add(izquierdaR);
                    listaI.add(null);
                    break;
                case 'O':
                    suelo = new ObstacleSuelo(Dades.color_suelo, x,y,x+dim,y+dim);
                    listaO.add(suelo);
                    listaI.add(null);
                    break;
                case 'X':
                    suelo = new ObstacleSuelo(Dades.color_suelo, x,y,x+dim,y+dim);
                    listaO.add(suelo);
                    listaI.add(null);

                    break;

            }

            col = it % 12;
            x = x + dim;
            if(col == 11){
                x = 0;
                y = y + dim;
                obstacles.add(listaO);
                interacts.add(listaI);
                listaO = new ArrayList<Obstacle>();
                listaI = new ArrayList<Obstacle>();
            }
        }
    }

    ArrayList<Integer> getPosInicialDoll(){
        ArrayList<Integer> p = new ArrayList<Integer>();
        p.add(this.posI);
        p.add(this.posJ);
        return p;
    }

    private String getLvl(int nivell,boolean botoVerd){
        String fileName = "";
        if(botoVerd){
            fileName = "lvl_" + nivell + "_verd"+ ".txt";
        }else {
            fileName = "lvl_" + nivell + ".txt";
        }
        AssetManager assetManager = Dades.Context_Actual.getAssets();
        String result="";

        try{
            InputStream is = assetManager.open(fileName);
            Scanner sc = new Scanner(is);
            while(sc.hasNextLine()){
                result += sc.nextLine();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return result.substring(1);
    }

    public void draw(Canvas canvas){

        for(ArrayList<Obstacle> ob: obstacles) {
            for (Obstacle o: ob){
                o.draw(canvas);
            }
        }
        for(ArrayList<Obstacle> ob: interacts) {
            for (Obstacle o: ob){
                if (o!= null){
                    o.draw(canvas);
                }
            }
        }
    }

    ArrayList<ArrayList<Obstacle>> getObstacles(){
        return this.obstacles;
    }

    ArrayList<ArrayList<Obstacle>> getInteracts(){
        return this.interacts;
    }

    void tabacTrobat(Obstacle o){

        this.tabacTrobat = true;
        for(int i = 0; i < this.interacts.size(); i++) {
            for(int p=0; p < this.interacts.get(i).size() ; p++) {
                if (this.interacts.get(i).get(p) != null) {
                    if (this.interacts.get(i).get(p).equals(o)) {
                        this.interacts.get(i).set(p,null);
                    }
                }
            }
        }
    }

    boolean isTabacTrobat(){
        return this.tabacTrobat;
    }

}
