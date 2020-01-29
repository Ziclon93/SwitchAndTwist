package com.example.ziclon.switchtwist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    private GamePanel panell;
    private Button SwitchBtn, TwistBtn, PauseBtn;
    private Dialog dialog;
    private float firstTouchX, firstTouchY;
    private Controller ctr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Intent i = getIntent();
        int nivell = i.getIntExtra("nivell", 0);

        panell = new GamePanel(this, nivell);
        RelativeLayout pan = (RelativeLayout) findViewById(R.id.PantallaJoc);
        pan.addView(panell);

        SwitchBtn = (Button) findViewById(R.id.Switch);
        TwistBtn = (Button) findViewById(R.id.Twist);
        PauseBtn = (Button) findViewById(R.id.pausa);

        ImageView fondo = (ImageView) findViewById(R.id.fondoST);

        SwitchBtn.setOnClickListener(this);
        TwistBtn.setOnClickListener(this);
        PauseBtn.setOnClickListener(this);

        SwitchBtn.bringToFront();
        TwistBtn.bringToFront();
        PauseBtn.bringToFront();
        fondo.bringToFront();

        ctr = ((Controller) getApplicationContext());
        ctr.playMusic(1);
    }

    public void onResume() {
        super.onResume();
        if (!this.ctr.getMusicaGaming().isPlaying()){
            ctr.playMusic(1);
        }
    }

    public void onPause() {
        super.onPause();
        if (this.ctr.getMusicaGaming().isPlaying()) {
            ctr.stopMusic(1);
        }
    }

    public void onStop() {
        super.onStop();

        if (this.ctr.getMusicaGaming().isPlaying()) {
            ctr.stopMusic(1);
        }
    }

    @Override
    public void onBackPressed() {
        showdialogPausa();
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Switch:
                ctr.playSwitch();
                this.panell.Switch();
                break;
            case R.id.Twist:
                ctr.playTwist();
                this.panell.Twist();
                break;
            case R.id.pausa:
                showdialogPausa();
        }
    }

    private void showdialogPausa() {
        ctr.playBoton();
        panell.setPause();

        dialog = new Dialog(Dades.Context_Actual);
        //fondo transparente!
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //evitar tancar el dialog desde fora.
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_pausa);


        Button exitDialog = (Button) dialog.findViewById(R.id.ExitDP);
        Button Resume = (Button) dialog.findViewById(R.id.setResume);
        Button reset = (Button) dialog.findViewById(R.id.Reset);
        final ToggleButton togglBttnFx = (ToggleButton) dialog.findViewById(R.id.FXs);
        final ToggleButton togglBttnSound = (ToggleButton) dialog.findViewById(R.id.Sounds);

        if (ctr.isFX()) {
            togglBttnFx.setChecked(true);
        } else {
            togglBttnFx.setChecked(false);
        }

        if (ctr.isMusica()) {
            togglBttnSound.setChecked(true);
        } else {
            togglBttnSound.setChecked(false);
        }

        togglBttnFx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller ctr = ((Controller) getApplicationContext());
                if (togglBttnFx.isChecked()) {
                    ctr.onFX();
                } else {
                    ctr.offFX();
                }
            }
        });

        togglBttnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller ctr = ((Controller) getApplicationContext());
                if (togglBttnSound.isChecked()) {
                    ctr.soundMusic();
                } else {
                    ctr.silenceMusic();
                    //ctr.stopMusic(1);
                }
            }
        });

        exitDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                ctr.stopMusic(1);

                panell.FiExec(false);
                finish();

            }
        });

        Resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctr.playBoton();
                panell.setResume();
                dialog.dismiss();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctr.playBoton();
                panell.reset();
                dialog.dismiss();
            }
        });

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    panell.setResume();
                    dialog.dismiss();
                }
                return false;
            }
        });

        dialog.show();
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //Aqui guardamos en una variable privada de clase las coordenadas del primer toque:
                firstTouchX = event.getX();
                firstTouchY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                //comparamos si se ha desplazado mas en el eje x o y
                if ((Math.abs(event.getX() - this.firstTouchX)) > ((Math.abs(event.getY() - this.firstTouchY)))) { //eje X
                    if (event.getX() > this.firstTouchX) {//derecha
                        panell.setDirection(3);
                    } else {//izquierda
                        panell.setDirection(4);
                    }
                } else { //eje Y
                    if (event.getY() > this.firstTouchY) { //abajo
                        panell.setDirection(2);
                    } else {//arriba
                        panell.setDirection(1);
                    }
                }
                break;
        }
        return true;
    }


}

