package com.example.ziclon.switchtwist;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;
import android.graphics.PorterDuff;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageButton playbtn, nivelesbtn;
    private boolean audioON = false;

    private boolean actualitzat1 = false;
    private boolean actualitzat2 = false;

    private Button settingsbtn, level1, level2, level3, level4, level5, level6, level7, level8, level9, level10;
    private ImageView tabac_lvl1, tabac_lvl2, tabac_lvl3, tabac_lvl4, tabac_lvl5, tabac_lvl6, tabac_lvl7, tabac_lvl8, tabac_lvl9, tabac_lvl10;

    private Controller ctr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Per borrar el frame on sortia el nom de la app
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        ctr = ((Controller) getApplicationContext());
        ctr.ctrFX();
        ctr.ctrMusica();
        ctr.ctrMusicaGamming();

        playbtn = (ImageButton) findViewById(R.id.jugar_layout);
        settingsbtn = (Button) findViewById(R.id.Settings_act);
        nivelesbtn = (ImageButton) findViewById(R.id.Niveles);

        String fileName;

        settingsbtn.setOnTouchListener(this);
        playbtn.setOnTouchListener(this);
        nivelesbtn.setOnTouchListener(this);

        DisplayMetrics dM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dM);
        Dades.screen_width = dM.widthPixels;
        Dades.screen_height = dM.heightPixels;

        ctr.playMusic(0);

        /*

        if(Dades.isFirstStart) {
            Intent i = new Intent(MainActivity.this, PlayActivity.class);
            startActivity(i);
            Dades.isFirstStart = false;
        }
         */
    }

    //tancar el proces al clicar amb el botó fisic de tirar endarrere.
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        ctr.stopMusic(0);
        /*
        if (ctr.getMusica().isPlaying()){
            ctr.stopMusic(0);
        }
        */
        this.finish();
        System.exit(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        ctr.playMusic(0);
        /*
        ctr.ctrMusica();
        if (!this.ctr.getMusica().isPlaying()){
            ctr.playMusic(0);
        }
        */
        if (actualitzat1) {
            setNivellsEnabled(1, 5);
            setTabacEnabled(1, 5);
        }
        if (actualitzat2) {
            setNivellsEnabled(6, 10);
            setTabacEnabled(6, 10);
        }

    }

    public void onPause() {
        super.onPause();
        if (this.ctr.getMusica().isPlaying()) {
            ctr.stopMusic(0);
        }
    }

    public void onStop() {
        super.onStop();
        if (this.ctr.getMusica().isPlaying()) {
            ctr.stopMusic(0);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.jugar_layout:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        ctr.playBoton();
                        ctr.stopMusic(0);

                        Intent i = new Intent(MainActivity.this, PlayActivity.class);
                        startActivity(i);
                        break;
                }
                break;

            case R.id.Niveles:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        ctr.playBoton();
                        final Dialog dialogNivells = new Dialog(this);

                        dialogNivells(dialogNivells, 1);
                        break;

                }
                break;
            case R.id.Settings_act:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        ctr.playBoton();

                        final Dialog dialogSettings = new Dialog(this);
                        //fondo transparente!
                        dialogSettings.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        //evitar que es tanqui al tocar fora
                        dialogSettings.setCanceledOnTouchOutside(false);

                        dialogSettings.setContentView(R.layout.dialog_options);
                        dialogSettings.show();

                        final ToggleButton togglBttnFx = (ToggleButton) dialogSettings.findViewById(R.id.FX_Sound);
                        final ToggleButton togglBttnSound = (ToggleButton) dialogSettings.findViewById(R.id.song_Sound);

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


                        Button reiniciar = (Button) dialogSettings.findViewById(R.id.Reiniciar);
                        Button exitDialog = (Button) dialogSettings.findViewById(R.id.ExitD);

                        exitDialog.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogSettings.dismiss();
                            }
                        });


                        reiniciar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ctr.playBoton();
                                //sonidoBoton.start();
                                ctr.setIniProgress();
                                ctr.setIniProgressTabac();
                                dialogSettings.dismiss();
                            }
                        });

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
                                    //ctr.playMusic(0);
                                    ctr.soundMusic();
                                } else {
                                    ctr.silenceMusic();
                                    //ctr.stopMusic(0);
                                }
                            }
                        });

                        Button credits = (Button) dialogSettings.findViewById(R.id.Credits);

                        credits.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ctr.playBoton();
                                dialogSettings.dismiss();
                                startActivity(new Intent(MainActivity.this, CreditsActivity.class));
                            }
                        });
                }
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (resultCode) {
            case RESULT_OK:
                audioON = intent.getExtras().getBoolean("Audio");
                break;
        }
    }

    private void dialogNivells(final Dialog dialogNivells, int nivellsIndex) {



        Button next;
        Button home;
        Button close;
        Button forward;

        switch (nivellsIndex){
            case 1:
                //fondo transparente!
                dialogNivells.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                //evitar que es tanqui al tocar fora
                dialogNivells.setCanceledOnTouchOutside(false);

                dialogNivells.setContentView(R.layout.dialog_nivells);
                dialogNivells.show();

                level1 = (Button) dialogNivells.findViewById(R.id.level1);
                level2 = (Button) dialogNivells.findViewById(R.id.level2);
                level3 = (Button) dialogNivells.findViewById(R.id.level3);
                level4 = (Button) dialogNivells.findViewById(R.id.level4);
                level5 = (Button) dialogNivells.findViewById(R.id.level5);

                tabac_lvl1 = (ImageView) dialogNivells.findViewById(R.id.tabac_lvl1);
                tabac_lvl2 = (ImageView) dialogNivells.findViewById(R.id.tabac_lvl2);
                tabac_lvl3 = (ImageView) dialogNivells.findViewById(R.id.tabac_lvl3);
                tabac_lvl4 = (ImageView) dialogNivells.findViewById(R.id.tabac_lvl4);
                tabac_lvl5 = (ImageView) dialogNivells.findViewById(R.id.tabac_lvl5);

                next = (Button) dialogNivells.findViewById(R.id.Next);
                home = (Button) dialogNivells.findViewById(R.id.Home);
                close = (Button) dialogNivells.findViewById(R.id.boto_close_x);
                forward = (Button) dialogNivells.findViewById(R.id.Forward);

                setNivellsEnabled(1, 5);
                setTabacEnabled(1, 5);
                actualitzat1 = true;

                level1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        ctr.stopMusic(0);
                        Intent i = new Intent(MainActivity.this, PlayActivity.class);
                        i.putExtra("nivell", 1);
                        startActivity(i);
                    }
                });

                level2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        ctr.stopMusic(0);
                        Intent i = new Intent(MainActivity.this, PlayActivity.class);
                        i.putExtra("nivell", 2);
                        startActivity(i);
                    }
                });
                level3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        ctr.stopMusic(0);
                        Intent i = new Intent(MainActivity.this, PlayActivity.class);
                        i.putExtra("nivell", 3);
                        startActivity(i);
                    }
                });
                level4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        ctr.stopMusic(0);
                        Intent i = new Intent(MainActivity.this, PlayActivity.class);
                        i.putExtra("nivell", 4);
                        startActivity(i);
                    }
                });
                level5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        ctr.stopMusic(0);
                        Intent i = new Intent(MainActivity.this, PlayActivity.class);
                        i.putExtra("nivell", 5);
                        startActivity(i);
                    }
                });
                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        dialogNivells.dismiss();
                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        dialogNivells.dismiss();
                    }
                });
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        dialogNivells(dialogNivells, 2);
                    }
                });
                forward.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                    }
                });
                break;

            case 2:
                //fondo transparente!
                dialogNivells.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                //evitar que es tanqui al tocar fora
                dialogNivells.setCanceledOnTouchOutside(false);

                dialogNivells.setContentView(R.layout.dialog_nivells2);
                dialogNivells.show();

                level6 = (Button) dialogNivells.findViewById(R.id.level6);
                level7 = (Button) dialogNivells.findViewById(R.id.level7);
                level8 = (Button) dialogNivells.findViewById(R.id.level8);
                level9 = (Button) dialogNivells.findViewById(R.id.level9);
                level10 = (Button) dialogNivells.findViewById(R.id.level10);

                tabac_lvl6 = (ImageView) dialogNivells.findViewById(R.id.tabac_lvl6);
                tabac_lvl7 = (ImageView) dialogNivells.findViewById(R.id.tabac_lvl7);
                tabac_lvl8 = (ImageView) dialogNivells.findViewById(R.id.tabac_lvl8);
                tabac_lvl9 = (ImageView) dialogNivells.findViewById(R.id.tabac_lvl9);
                tabac_lvl10 = (ImageView) dialogNivells.findViewById(R.id.tabac_lvl10);

                next = (Button) dialogNivells.findViewById(R.id.Next2);
                home = (Button) dialogNivells.findViewById(R.id.Home2);
                close = (Button) dialogNivells.findViewById(R.id.boto_close_x2);
                forward = (Button) dialogNivells.findViewById(R.id.Forward2);

                setNivellsEnabled(6, 10);
                setTabacEnabled(6, 10);
                actualitzat2 = true;

                level6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        ctr.stopMusic(0);
                        Intent i = new Intent(MainActivity.this, PlayActivity.class);
                        i.putExtra("nivell", 6);
                        startActivity(i);
                    }
                });

                level7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        ctr.stopMusic(0);
                        Intent i = new Intent(MainActivity.this, PlayActivity.class);
                        i.putExtra("nivell", 7);
                        startActivity(i);
                    }
                });
                level8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        ctr.stopMusic(0);
                        Intent i = new Intent(MainActivity.this, PlayActivity.class);
                        i.putExtra("nivell", 8);
                        startActivity(i);
                    }
                });
                level9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        ctr.stopMusic(0);
                        Intent i = new Intent(MainActivity.this, PlayActivity.class);
                        i.putExtra("nivell", 9);
                        startActivity(i);
                    }
                });
                level10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        ctr.stopMusic(0);
                        Intent i = new Intent(MainActivity.this, PlayActivity.class);
                        i.putExtra("nivell", 10);
                        startActivity(i);
                    }
                });
                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        dialogNivells.dismiss();
                        dialogNivells.dismiss();
                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        dialogNivells.dismiss();
                        dialogNivells.dismiss();
                    }
                });
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                    }
                });
                forward.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctr.playBoton();
                        dialogNivells(dialogNivells, 1);
                    }
                });
        }
    }

    private void dialogNivells2(final Dialog dialogNivells, final Dialog dialogNivells2) {
        //fondo transparente!
        dialogNivells.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        //evitar que es tanqui al tocar fora
        dialogNivells.setCanceledOnTouchOutside(false);

        dialogNivells.setContentView(R.layout.dialog_nivells2);
    }


    public void setNivellsEnabled(int min, int max) {
        Boolean[] progress = ctr.getProgress();
        for (int i = min - 1; i < max; i++) {
            int cas = i + 1;
            switch (cas) {
                case 1:
                    level1.getBackground().clearColorFilter();
                    break;
                case 2:
                    if (progress[cas - 2]) {
                        level2.getBackground().clearColorFilter();
                        level2.setEnabled(true);
                    } else {
                        level2.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                        level2.setEnabled(false);
                    }
                    break;
                case 3:
                    if (progress[cas - 2]) {
                        level3.getBackground().clearColorFilter();
                        level3.setEnabled(true);
                    } else {
                        level3.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                        level3.setEnabled(false);
                    }
                    break;
                case 4:
                    if (progress[cas - 2]) {
                        level4.getBackground().clearColorFilter();
                        level4.setEnabled(true);
                    } else {
                        level4.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                        level4.setEnabled(false);
                    }
                    break;
                case 5:
                    if (progress[cas - 2]) {
                        level5.getBackground().clearColorFilter();
                        level5.setEnabled(true);
                    } else {
                        level5.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                        level5.setEnabled(false);
                    }
                    break;
                case 6:
                    if (progress[cas - 2]) {
                        level6.getBackground().clearColorFilter();
                        level6.setEnabled(true);
                    } else {
                        level6.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                        level6.setEnabled(false);
                    }
                    break;
                case 7:
                    if (progress[cas - 2]) {
                        level7.getBackground().clearColorFilter();
                        level7.setEnabled(true);
                    } else {
                        level7.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                        level7.setEnabled(false);
                    }
                    break;
                case 8:
                    if (progress[cas - 2]) {
                        level8.getBackground().clearColorFilter();
                        level8.setEnabled(true);
                    } else {
                        level8.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                        level8.setEnabled(false);
                    }
                    break;
                case 9:
                    if (progress[cas - 2]) {
                        level9.getBackground().clearColorFilter();
                        level9.setEnabled(true);
                    } else {
                        level9.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                        level9.setEnabled(false);
                    }
                    break;
                case 10:
                    if (progress[cas - 2]) {
                        level10.getBackground().clearColorFilter();
                        level10.setEnabled(true);
                    } else {
                        level10.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                        level10.setEnabled(false);
                    }
            }
        }
    }

    public void setTabacEnabled(int min, int max) {
        Boolean[] progress = ctr.getProgressTabac();
        for (int i = min - 1; i < max; i++) {
            int cas = i + 1;
            switch (cas) {
                case 1:
                    if (progress[i]) {
                        tabac_lvl1.setImageResource(R.drawable.tobacco);
                    } else {
                        tabac_lvl1.setImageResource(R.drawable.tobacco_bn);
                    }
                    break;
                case 2:
                    if (progress[i]) {
                        tabac_lvl2.setImageResource(R.drawable.tobacco);
                    } else {
                        tabac_lvl2.setImageResource(R.drawable.tobacco_bn);
                    }
                    break;
                case 3:
                    if (progress[i]) {
                        tabac_lvl3.setImageResource(R.drawable.tobacco);
                    } else {
                        tabac_lvl3.setImageResource(R.drawable.tobacco_bn);
                    }
                    break;
                case 4:
                    if (progress[i]) {
                        tabac_lvl4.setImageResource(R.drawable.tobacco);
                    } else {
                        tabac_lvl4.setImageResource(R.drawable.tobacco_bn);
                    }
                    break;
                case 5:
                    if (progress[i]) {
                        tabac_lvl5.setImageResource(R.drawable.tobacco);
                    } else {
                        tabac_lvl5.setImageResource(R.drawable.tobacco_bn);
                    }
                    break;
                case 6:
                    if (progress[i]) {
                        tabac_lvl6.setImageResource(R.drawable.tobacco);
                    } else {
                        tabac_lvl6.setImageResource(R.drawable.tobacco_bn);
                    }
                    break;
                case 7:
                    if (progress[i]) {
                        tabac_lvl7.setImageResource(R.drawable.tobacco);
                    } else {
                        tabac_lvl7.setImageResource(R.drawable.tobacco_bn);
                    }
                    break;
                case 8:
                    if (progress[i]) {
                        tabac_lvl8.setImageResource(R.drawable.tobacco);
                    } else {
                        tabac_lvl8.setImageResource(R.drawable.tobacco_bn);
                    }
                    break;
                case 9:
                    if (progress[i]) {
                        tabac_lvl9.setImageResource(R.drawable.tobacco);
                    } else {
                        tabac_lvl9.setImageResource(R.drawable.tobacco_bn);
                    }
                    break;
                case 10:
                    if (progress[i]) {
                        tabac_lvl10.setImageResource(R.drawable.tobacco);
                    } else {
                        tabac_lvl10.setImageResource(R.drawable.tobacco_bn);
                    }
                    break;
            }
        }
    }
}
