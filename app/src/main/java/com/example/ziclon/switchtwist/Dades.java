package com.example.ziclon.switchtwist;

import android.content.Context;
import android.graphics.Color;


public class Dades {

    public static int screen_width;
    public static int screen_height;


    public static Context Context_Actual;
    public static int color_parets = Color.rgb(69,90,100);
    public static int color_Vermell = Color.rgb(255,0,0); //vermell
    public static int color_Blau = Color.rgb(0,0,255); // blau
    public static int color_Gris = Color.rgb(60,60,60); // gris oscuro

    public static int color_end = Color.rgb(0,0,0); //negre
    public static int color_final = Color.rgb(50,50,50);
    public static int color_suelo = Color.rgb(192,192,192);

    public static boolean isFirstStart = true;
    public static String Movement = "Per poder moure's per la pantalla només has d'arrossegar a " +
            "DOLL cap a on vols donar-li la direcció. Un cop donada la direcció no es podrà " +
            "canviar fins que toqui una paret!";
    public static String Personatge = "Aquest és en DOLL el nostre encantador personatge que " +
            "t'acompanyarà durant l'aventura!";
    public static String Switch = "Per evitar els obstacles al teu cami hauras de canviar al color" +
            "correcte!";
    public static String Twist = "Si vols canviar la teva direcció, hauras de fer-ne ús!";
    public static String Tabac = "Aconsegueix-ne tants com puguis!";
    public static int color_verd = Color.rgb(0,255,0);
}
