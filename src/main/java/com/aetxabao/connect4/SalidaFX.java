package com.aetxabao.connect4;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static com.aetxabao.connect4.Tablero.*;

public class SalidaFX {

    public static final double W = 45;
    public static final double AX = W;
    public static final double AY = 100;
    public static final double WIDTH = W*7;
    public static final double HEIGHT = W*6;
    public static final int CANVAS_WIDTH = 405;
    public static final int CANVAS_HEIGHT = 505;
    private static final double V = 31;
    private static final double AT = 30;
    private static final int BORDER = 2;


    private static void limpiaPantalla(GraphicsContext gc) {
        gc.setFill(Color.WHITESMOKE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(BORDER);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        gc.strokeRoundRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT,0, 0);
    }

    public static void pintaInicio(GraphicsContext gc) {
        char[][] m = {
                {'O','X','X','X',' ',' '},
                {'X','O','X','O',' ',' '},
                {'X','O','X','X','O',' '},
                {'O','X','O','O',' ',' '},
                {'O','X','O','X',' ',' '},
                {'O','O','X','O',' ',' '},
                {'X',' ',' ',' ',' ',' '}
        };
        pintaEncabezado(gc, Color.DARKBLUE);
        pintaTablero(gc);
        pintaMatriz(gc,m);
        pintaPieInicio(gc);
    }

    public static void pinta(GraphicsContext gc, char[][] matriz, char jugador) {
        limpiaPantalla(gc);
        pintaEncabezado(gc, Color.WHITE);
        pintaTablero(gc);
        pintaMatriz(gc, matriz);
        pintaPieTurno(gc, jugador);
    }

    public static void pintaGana(GraphicsContext gc, char[][] matriz, char jugador) {
        limpiaPantalla(gc);
        if (jugador == O){
            pintaEncabezado(gc, Color.RED);
        } else if (jugador == X){
            pintaEncabezado(gc, Color.YELLOW);
        }
        pintaTablero(gc);
        pintaMatriz(gc, matriz);
        pintaPieGana(gc, jugador);
    }

    public static void pintaEmpate(GraphicsContext gc, char[][] matriz) {
        limpiaPantalla(gc);
        pintaEncabezado(gc, Color.BLACK);
        pintaTablero(gc);
        pintaMatriz(gc, matriz);
        pintaPieEmpate(gc);
    }

    private static void pintaEncabezado(GraphicsContext gc, Color color) {
        gc.setStroke(Color.BLACK);
        double a = 13;
        double ay = 25;
        gc.setFill(color);
        gc.fillRect(a, a, CANVAS_WIDTH-2*a ,3*ay);
        gc.strokeRoundRect(a, a, CANVAS_WIDTH-2*a ,3*ay, 5,5);
        gc.setFill(Color.WHITE);
        gc.fillRect(2*a, 2*a, CANVAS_WIDTH-4*a ,3*ay-2*a);
        gc.strokeRoundRect(2*a, 2*a, CANVAS_WIDTH-4*a ,3*ay-2*a, 5,5);
        gc.setFill( Color.BLACK );
        gc.setFont( Font.font( "Courier New", FontWeight.NORMAL, 18 ) );
        gc.fillText( "             CONECTA 4             ", a, 4*a );
    }

    private static void pintaTablero(GraphicsContext gc) {
        gc.setFill(Color.DARKBLUE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth((double)BORDER /2);
        gc.fillRect(AX, AY, WIDTH ,HEIGHT);
        gc.strokeRoundRect(AX, AY, WIDTH ,HEIGHT, 0,0);

        gc.fillRect(AX-18, AY+210, 18 ,100);
        gc.fillRect(AX+WIDTH, AY+210, 18 ,100);

        gc.setFill(Color.WHITE);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                gc.fillOval(AX+7+i*W, AY+7+j*W,V,V);
            }
        }

        gc.setFill(Color.BLACK);
        gc.setFont( Font.font( "Monospaced", FontWeight.BOLD, 18 ) );
        for (int i = 0; i < 7; i++) {
            gc.fillText( String.valueOf(i+1), AX+16+i*W, AY+6*W+AT );
        }

    }

    private static void pintaPieInicio(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.setFont( Font.font( "Courier New", FontWeight.NORMAL, 18 ) );
        gc.fillText( "Haz clic en la columna o pulsa 1-7 ", 13, 10*W+0*AT );
        gc.fillText( "Para empezar haz clic o pulsa tecla", 13, 10*W+1*AT );
    }

    private static void pintaMatriz(GraphicsContext gc, char[][] matriz){
        char[][] m = invertir(matriz);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (m[i][j] == O){
                    gc.setFill( Color.RED );
                    gc.fillOval(AX+7+j*W, AY+HEIGHT-W+7-i*W,V,V);
                } else if (m[i][j] == X){
                    gc.setFill( Color.YELLOW );
                    gc.fillOval(AX+7+j*W, AY+HEIGHT-W+7-i*W,V,V);
                }
            }
        }
    }

    private static char[][] invertir(char[][] matriz) {
        char[][] m = new char[matriz[0].length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                m[j][i] = matriz[i][j];
            }
        }
        return m;
    }

    private static void pintaPieTurno(GraphicsContext gc, char turno) {
        gc.setFill(Color.BLACK);
        gc.setFont( Font.font( "Courier New", FontWeight.NORMAL, 18 ) );
        gc.fillText( "       Turno:                      ", 13, 10*W+0*AT );
        gc.fillText( "Haz clic en la columna o pulsa 1-7 ", 13, 10*W+1*AT );
        pintaTurno(gc, turno);
    }

    private static void pintaPieGana(GraphicsContext gc, char turno) {
        gc.setFill(Color.BLACK);
        gc.setFont( Font.font( "Courier New", FontWeight.NORMAL, 18 ) );
        gc.fillText( "        GANA:                      ", 13, 10*W+0*AT );
        gc.fillText( "Para empezar haz clic o pulsa tecla", 13, 10*W+1*AT );
        pintaTurno(gc, turno);
    }

    private static void pintaTurno(GraphicsContext gc, char turno) {
        if (turno == O){
            gc.setFill( Color.RED );
            gc.fillOval(AX+7+3*W, 10*W-W/2,V,V);
        } else if (turno == X){
            gc.setFill( Color.YELLOW );
            gc.fillOval(AX+7+3*W, 10*W-W/2,V,V);
        }
        gc.setStroke(Color.BLACK);
        gc.strokeOval(AX+7+3*W, 10*W-W/2,V,V);
    }

    private static void pintaPieEmpate(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.setFont( Font.font( "Courier New", FontWeight.NORMAL, 18 ) );
        gc.fillText( "               EMPATE              ", 13, 10*W+0*AT );
        gc.fillText( "Para empezar haz clic o pulsa tecla", 13, 10*W+1*AT );
    }

}
