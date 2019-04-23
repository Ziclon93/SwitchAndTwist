package com.example.ziclon.switchtwist;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private MainThread thread;
    private ManagerEscena mE;
    private int nivell;


    public GamePanel(Context context, int nivell) {
        super(context);

        getHolder().addCallback(this);
        this.nivell = nivell;

        Dades.Context_Actual = context;

        if(nivell == 0){
            mE = new ManagerEscena();
        }else{
            mE = new ManagerEscena(nivell);
        }
        setFocusable(true);
    }

    public void FiExec(boolean ex){

        thread.setResume(ex);

        if(ex){
            thread.start();
        }else {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {


        thread = new MainThread(getHolder(), this,this.nivell);
        thread.setResume(true);
        thread.start();
    }



    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        while(true){
            try {
                thread.setResume(false);
                thread.join();
                break;
            } catch(Exception e) {e.printStackTrace(); }
        }
    }


    public void update(float l) {
        mE.update(l);
    }

    public void setDirection(int dir){
        mE.setDirection(dir);
    }

    @Override
    public void draw(Canvas canvas) {

        super.draw(canvas);
        mE.draw(canvas);

    }
    public void Switch(){
        mE.Switch();
    }

    public void Twist() { mE.Twist(); }

    public void reset() {
        mE.Reset();
        thread.setResume();
    }

    public void setPause(){
        thread.setPause();
    }


    public void setResume() {
        thread.setResume();
    }

    public boolean getPause(){
        return thread.getPause();
    }

    public void nextEscena(){
        mE.nextEscena();
    }

    public boolean isGameWin(){
        return mE.isGameWin();
    }

    public boolean isGameOver(){
        return mE.isGameOver();
    }

    public boolean isTabacTrobat(){ return mE.isTabacTrobat();}


}
