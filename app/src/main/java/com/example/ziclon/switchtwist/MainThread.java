package com.example.ziclon.switchtwist;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainThread extends Thread {

    public static final int MAX_FPS = 30;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running, mHandlerMostrat;
    private boolean paused = false;

    public static Canvas canvas;

    private final Handler mHandler = new Handler();

    private int nivell;

    void setResume(boolean running) {
        this.running = running;
    }

    MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel, int nivell) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.nivell = nivell;
        this.gamePanel = gamePanel;
        this.mHandlerMostrat = false;
    }

    public void setHandler(boolean mHandlerMostrat) {
        this.mHandlerMostrat = mHandlerMostrat;
    }

    void setPause() {
        this.paused = true;
    }

    void setResume() {
        this.paused = false;
    }

    boolean getPause() {
        return paused;
    }

    public void run() {

        long startTime = System.nanoTime();
        long timeSpeed;
        long timeMillis;
        long waitTime;
        int framecount = 0;
        long totaltime = 0;
        long targetTime = 1000 / MAX_FPS;


        while (running) {
            timeSpeed = System.nanoTime() - startTime;
            startTime = System.nanoTime();
            canvas = null;


            try {
                if (!paused) {
                    canvas = this.surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        this.gamePanel.update(timeSpeed / 10000000.0f);
                        this.gamePanel.draw(canvas);

                        if (this.gamePanel.isGameWin() && !mHandlerMostrat) {
                            mHandler.post(ejecutarWin);
                        } else if (this.gamePanel.isGameOver() && !mHandlerMostrat) {
                            mHandler.post(ejecutarLose);
                        }

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000;
            waitTime = targetTime - timeMillis;

            try {
                if (waitTime > 0) {
                    this.sleep(waitTime);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            totaltime += System.nanoTime() - startTime;
            framecount++;
            if (framecount == MAX_FPS) {
                framecount = 0;
                totaltime = 0;
            }
        }

    }

    final Runnable ejecutarWin = new Runnable() {
        public void run() {
            showDialogWin();
        }
    };

    final Runnable ejecutarLose = new Runnable() {
        public void run() {
            showDialogLose();
        }
    };

    public void showDialogLose() {
        this.setPause();

        final Dialog dialogLose = new Dialog(Dades.Context_Actual);

        //fondo transparente!
        dialogLose.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialogLose.setCanceledOnTouchOutside(false);

        dialogLose.setCancelable(false); //no podrem treure el dialog amb boto enrere del movil

        dialogLose.setContentView(R.layout.dialog_game_lose);

        Button reset = (Button) dialogLose.findViewById(R.id.bttResetLose);
        Button decline = (Button) dialogLose.findViewById(R.id.bttnDeclineLose);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePanel.reset();
                dialogLose.dismiss();
                mHandlerMostrat = false;
                setResume();
            }
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLose.dismiss();
                setResume(false);
                mHandlerMostrat = false;
                Activity act = (Activity) gamePanel.getContext();
                act.onBackPressed();
            }
        });


        dialogLose.show();
        mHandlerMostrat = true;

    }


    public void showDialogWin() {

        this.setPause();

        boolean isTabacTrobat = this.gamePanel.isTabacTrobat();
        final Dialog dialogWin = new Dialog(Dades.Context_Actual);

        //fondo transparente!
        dialogWin.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogWin.setCanceledOnTouchOutside(false);
        dialogWin.setCancelable(false); //no podrem treure el dialog amb boto enrere del movil

        dialogWin.setContentView(R.layout.dialog_game_win);

        Button reset = (Button) dialogWin.findViewById(R.id.bttResetWin);

        Button next = (Button) dialogWin.findViewById(R.id.bttNextNivell);

        ImageView image = (ImageView) dialogWin.findViewById(R.id.imgTabac);

        if (this.nivell >= 9) {
            next.setEnabled(false);
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePanel.nextEscena();
                dialogWin.dismiss();
                mHandlerMostrat = false;
                setResume();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePanel.reset();
                dialogWin.dismiss();
                mHandlerMostrat = false;
                setResume();
            }
        });

        if (isTabacTrobat) {
            image.setImageResource(R.drawable.tobacco);
        } else {
            image.setImageResource(R.drawable.tobacco_bn);
        }

        dialogWin.show();
        mHandlerMostrat = true;

    }
}
