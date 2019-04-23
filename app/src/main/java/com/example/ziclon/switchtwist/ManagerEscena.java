package com.example.ziclon.switchtwist;

import android.graphics.Canvas;

import java.util.ArrayList;


public class ManagerEscena {

    private ArrayList<Escena> Escenes = new ArrayList<>();
    public int ESCENA_ACTIVA;
    private Controller ctr;


    public ManagerEscena() {
        ctr = ((Controller) Controller.getContext());
        setEscenaActiva();
        addEscenes();
    }
    public ManagerEscena(int nivell) {
        ctr = ((Controller) Controller.getContext());
        ESCENA_ACTIVA = nivell-1;

        addEscenes();
    }


    public void setEscenaActiva(){
        Boolean[] nivells = ctr.getProgress();
        Boolean trobat = false;

        int i = 0;
        while(i < ctr.getNivells()-1 && !trobat){
            if(!nivells[i]){
                ESCENA_ACTIVA = i;
                trobat = true;
            }else{
                i++;
            }
        }
        if(!trobat){
            ESCENA_ACTIVA = 0;
        }

    }

    public void addEscenes(){
        int nNivells = ctr.getNivells();
        for(int i = 0; i < nNivells; i++){
            Escenes.add(new EscenaGameplay(i+1));
        }
    }

    public void update(float l) {
        Escenes.get(ESCENA_ACTIVA).uptade(l);

    }

    public void nextEscena() {
        ESCENA_ACTIVA++;
    }

    public boolean isGameWin(){
        return  Escenes.get(ESCENA_ACTIVA).isGameWin();
    }

    public boolean isGameOver(){
        return Escenes.get(ESCENA_ACTIVA).isGameOver();
    }

    public boolean isTabacTrobat(){return Escenes.get(ESCENA_ACTIVA).isTabacTrobat();}

    public void draw(Canvas canvas) {

        Escenes.get(ESCENA_ACTIVA).draw(canvas);
    }

    public void setDirection(int dir){
        Escenes.get(ESCENA_ACTIVA).setDirection(dir);
    }

    public void end() {
        Escenes.get(ESCENA_ACTIVA).finalitzar();
    }

    public void Switch(){
        Escenes.get(ESCENA_ACTIVA).Switch();
    }

    public void Twist() { Escenes.get(ESCENA_ACTIVA).Twist(); }

    public void Reset() { Escenes.get(ESCENA_ACTIVA).Reset();  }


}
