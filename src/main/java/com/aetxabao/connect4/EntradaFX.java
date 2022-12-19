package com.aetxabao.connect4;

import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import static com.aetxabao.connect4.SalidaFX.*;

public class EntradaFX {

    public static int getColumna(Event e) {
        int columna = -1;
        if (e instanceof MouseEvent) {
            columna = EntradaFX.getColumna(((MouseEvent)e).getSceneX(), ((MouseEvent)e).getSceneY());
        }else if (e instanceof KeyEvent) {
            columna = EntradaFX.getColumna(((KeyEvent)e).getCode());
        }
        return columna;
    }

    private static int getColumna(KeyCode code) {
        int v = -1;
        if (code.isDigitKey()) {
            int n = Integer.parseInt(code.getChar());
            if (n>=1 && n<=7) {
                v = n-1;
            }
        }
        return v;
    }

    private static int getColumna(double x, double y) {
        int v = -1;
        if (x>AX && x<AX+WIDTH && y>AY && y<AY+HEIGHT){
            v = ((int)x-(int)AX) / (int)W;
        }
        return v;
    }

}
