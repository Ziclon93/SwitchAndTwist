package com.example.ziclon.switchtwist;

import android.graphics.Canvas;

import java.util.ArrayList;

public class ManagerEscena {

    private ArrayList<Escena> Escenes = new ArrayList<>();
    int ESCENA_ACTIVA;
    private Controller ctr;

    ManagerEscena() {
        ctr = ((Controller) Controller.getContext());
        setEscenaActiva();
        addEscenes();
    }

    ManagerEscena(int nivell) {
        ctr = ((Controller) Controller.getContext());
        ESCENA_ACTIVA = nivell - 1;
        addEscenes();
    }

    private void setEscenaActiva() {
        Boolean[] nivells = ctr.getProgress();
        Boolean trobat = false;

        int i = 0;
        while (i < ctr.getNivells() - 1 && !trobat) {
            if (!nivells[i]) {
                ESCENA_ACTIVA = i;
                trobat = true;
            } else {
                i++;
            }
        }
        if (!trobat) {
            ESCENA_ACTIVA = 0;
        }

    }

    private void addEscenes() {
        int nNivells = ctr.getNivells();
        for (int i = 0; i < nNivells; i++) {
            Escenes.add(new EscenaGameplay(i + 1));
        }
    }

    public void update(float l) {
        Escenes.get(ESCENA_ACTIVA).uptade(l);

    }

    void nextEscena() {
        ESCENA_ACTIVA++;
    }

    boolean isGameWin() {
        return Escenes.get(ESCENA_ACTIVA).isGameWin();
    }

    boolean isGameOver() {
        return Escenes.get(ESCENA_ACTIVA).isGameOver();
    }

    boolean isTabacTrobat() {
        return Escenes.get(ESCENA_ACTIVA).isTabacTrobat();
    }

    public void draw(Canvas canvas) {

        Escenes.get(ESCENA_ACTIVA).draw(canvas);
    }

    void setDirection(int dir) {
        Escenes.get(ESCENA_ACTIVA).setDirection(dir);
    }

    public void end() {
        Escenes.get(ESCENA_ACTIVA).finalitzar();
    }

    void Switch() {
        Escenes.get(ESCENA_ACTIVA).Switch();
    }

    void Twist() {
        Escenes.get(ESCENA_ACTIVA).Twist();
    }

    void Reset() {
        Escenes.get(ESCENA_ACTIVA).Reset();
    }


}
