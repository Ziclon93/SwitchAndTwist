package com.example.ziclon.switchtwist;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;


public class Controller extends Application{

    private MediaPlayer musica;

    private MediaPlayer musicaGaming;

    private MediaPlayer soTabac;
    private MediaPlayer soTwist;
    private MediaPlayer soSwitch;
    private MediaPlayer soGameOver;
    private MediaPlayer soNextLevel;
    private MediaPlayer soBotons;
    private MediaPlayer soColisioParet;

    private MediaPlayer soMoviment;
    private MediaPlayer soMusicaJoc;
    private MediaPlayer soMusicaMenu;


    private boolean isFXActivated = true;
    private boolean isMusicaActivated = true;

    private int nNivells = 10;//a triar on definir




    public void ctrFX(){
        soBotons = MediaPlayer.create(this,R.raw.button_sound);
        soBotons.setVolume(1.0f,1.0f);

        soSwitch = MediaPlayer.create(this,R.raw.switch_sound);
        soSwitch.setVolume(1.0f,1.0f);

        soTwist = MediaPlayer.create(this,R.raw.twist_sound);
        soTwist.setVolume(1.0f,1.0f);

        soTabac = MediaPlayer.create(this,R.raw.tabaco_sound);
        soTabac.setVolume(1.0f,1.0f);

        soNextLevel = MediaPlayer.create(this,R.raw.next_level_sound);
        soNextLevel.setVolume(1.0f,1.0f);

        soGameOver = MediaPlayer.create(this,R.raw.game_over_sound);
        soGameOver.setVolume(1.0f,1.0f);

        soColisioParet = MediaPlayer.create(this,R.raw.wall_collision_sound);
        soColisioParet.setVolume(1.5f,1.5f);

    }

    public void playBoton() {
        soBotons.start();
    }

    public void playSwitch(){
        soSwitch.start();
    }

    public void playTwist(){
        soTwist.start();
    }

    public void playTabac(){
        soTabac.start();
    }

    public void playNextLevel(){
        soNextLevel.start();
    }

    public void playGameOver(){
        soGameOver.start();
    }

    public void playColisioParet(){
        soColisioParet.start();
    }


    //--------- MUSICA DE FONS -----------------
    public void ctrMusica(){
        musica = MediaPlayer.create(this,R.raw.hooked);
        musica.setLooping(true);
        musica.setVolume(0.7f,0.7f);
    }
    public MediaPlayer getMusica() {
        return musica;
    }

    public void ctrMusicaGamming(){
        musicaGaming = MediaPlayer.create(this,R.raw.running);
        musicaGaming.setLooping(true);
        musicaGaming.setVolume(0.7f,0.7f);
    }

    //--------- ACTIVAR/DESACTIVAR MUSICA I FX ---------------
    public void onFX() {
        soBotons.setVolume(1.0f,1.0f);
        soColisioParet.setVolume(1.5f,1.5f);
        soTabac.setVolume(1.0f,1.0f);
        soTwist.setVolume(1.0f,1.0f);
        soSwitch.setVolume(1.0f,1.0f);
        soGameOver.setVolume(1.0f,1.0f);
        soNextLevel.setVolume(1.0f,1.0f);

        isFXActivated = true;
    }

    public void offFX() {

        soBotons.setVolume(0.0f,0.0f);
        soColisioParet.setVolume(0.0f,0.0f);
        soTabac.setVolume(0.0f,0.0f);
        soTwist.setVolume(0.0f,0.0f);
        soSwitch.setVolume(0.0f,0.0f);
        soGameOver.setVolume(0.0f,0.0f);
        soNextLevel.setVolume(0.0f,0.0f);

        isFXActivated = false;
    }
    public boolean isFX(){
        return isFXActivated;
    }

    public void playMusic(int i){
        if (i== 0){
            musica.start();
            //musica.setVolume(0.7f,0.7f);
        }
        else if (i == 1){
            musicaGaming.start();
            //musicaGaming.setVolume(0.7f,0.7f);
        }
        //isMusicaActivated = true;
    }
    public void stopMusic(int i){

        if (i== 0){
            musica.pause();
        }
        else if (i == 1){
            musicaGaming.pause();
        }
    }

    public void soundMusic(){
        musica.setVolume(0.7f,0.7f);
        musicaGaming.setVolume(0.7f,0.7f);
        isMusicaActivated = true;
    }
    public void silenceMusic(){
        musica.setVolume(0.0f,0.0f);
        musicaGaming.setVolume(0.0f,0.0f);
        isMusicaActivated = false;
    }

    public boolean isMusica(){
        return isMusicaActivated;
    }

    public void setIniProgress(){
        SharedPreferences sharedPref = getSharedPreferences("nivells", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        for(int i = 0; i < nNivells; i++){
            String nivell = "lvl_"+ (i + 1);
            editor.putBoolean(nivell, false);
        }
        editor.apply();
    }
    public void updateProgress(int nivell){
        SharedPreferences sharedPref = getSharedPreferences("nivells", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(("lvl_"+nivell),true);
        editor.apply();
    }
    public Boolean[] getProgress(){
        Boolean[] progress = new Boolean[nNivells];
        SharedPreferences sharedPref = getSharedPreferences("nivells", Context.MODE_PRIVATE);
        for(int i = 0; i < nNivells; i++){
            String nivell = "lvl_"+ (i + 1);
            progress[i] = sharedPref.getBoolean(nivell, false);
        }
        return progress;
    }
    public int getNivells(){
        return nNivells;
    }


    public void setIniProgressTabac(){
        SharedPreferences sharedPref = getSharedPreferences("tabacs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        for(int i = 0; i < nNivells; i++){
            String tabac = "tb"+ (i + 1);
            editor.putBoolean(tabac, false);
        }
        editor.apply();
    }
    public void updateProgressTabac(int nivell){
        SharedPreferences sharedPref = getSharedPreferences("tabacs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(("tb"+nivell), true);
        editor.apply();
    }
    public Boolean[] getProgressTabac(){
        Boolean[] progressTabac = new Boolean[nNivells];
        SharedPreferences sharedPref = getSharedPreferences("tabacs", Context.MODE_PRIVATE);
        for(int i = 0; i < nNivells; i++){
            String nivell = "tb"+ (i + 1);
            progressTabac[i] = sharedPref.getBoolean(nivell, false);
        }
        return progressTabac;
    }


    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
