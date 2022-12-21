package com.aetxabao.connect4;
import java.util.Random;

/**
 * @author Joseba Trigo
 */
public class Tablero {

    public final static char O = 'O';
    public final static char X = 'X';
    public final static char L = ' ';
    private final static int W = 7;
    private final static int H = 6;
    private int contador;
    private char turno;
    private final int ancho;
    private final int alto;
    private final char[][] m;
    Random random = new Random();

    public Tablero() {
        contador = 0;
        turno = O;
        ancho = W;
        alto = H;
        m = new char[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                m[i][j] = L;
            }
        }
    }

    public Tablero(char[][] m) {
        int contadorX = 0;
        int contadorO = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == O) {
                    contadorO++;
                }
                else if (m[i][j] == X) {
                    contadorX++;
                }
            }
        }
        contador = contadorX + contadorO;
        if (contadorX > contadorO) {
            turno = O;
        }
        else if (contadorO > contadorX) {
            turno = X;
        }
        ancho = W;
        alto = H;
        this.m = m;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int getContador() {
        return contador;
    }

    public char[][] getMatriz(){
        return m;
    }

    public char getTurno() {
        return turno;
    }

    public void iniciaTurno() {
        //TODO: iniciaTurno
        if (random.nextBoolean()) {
            turno = X;
        }
        else {
            turno = O;
        }

    }

    public void cambiaTurno() {
        if (turno == X) {
            turno = O;
        }
        else {
            turno = X;
        }
    }

    public boolean estaColumnaLibre(int columna) {
        if (columna < 1 || columna > 7) {
            return false;
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == L) {
                    return true;
                }
            }
        }
        return false;
    }

    public void inserta(char ficha, int columna) {
        for (int i = 0; i < m[columna].length; i++) {
            if (m[columna][i] == L) {
                m[columna][i] = ficha;
                contador++;
                break;
            }
        }
    }

    public boolean estaLleno() {
        return contador == alto * ancho;
    }

    public boolean gana(char jugador) {
        boolean b;
        b = ganaHorizontal(jugador);
        b = b || ganaVertical(jugador);
        b = b || ganaDiagonalArriba(jugador);
        b = b || ganaDiagonalAbajo(jugador);
        return b;
    }

    private boolean ganaHorizontal(char jugador) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (hay4Horizontales(j, i, jugador)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hay4Horizontales(int columna, int fila, char jugador){
        for (int i = 0; i < 4; i++) {
            if (m[columna + i][fila] != jugador) {
                return false;
            }
        }
        return true;
    }

    private boolean ganaVertical(char jugador) {
        for (int i = 0; i < m.length - 1; i++) {
            for (int j = 0; j < 3; j++) {
                if (hay4Verticales(i, j, jugador)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hay4Verticales(int columna, int fila, char jugador){
        for (int i = 0; i < 4; i++) {
            if (m[columna][fila + i] != jugador) {
                return false;
            }
        }
        return true;
    }

    private boolean ganaDiagonalArriba(char jugador) {
        int k;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (hay4DiagonalesArriba(i, j, jugador)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hay4DiagonalesArriba(int columna, int fila, char jugador){
        for (int i = 0; i < 4; i++) {
            if (m[columna++][fila++] != jugador) {
                return false;
            }
        }
        return true;
    }

    private boolean ganaDiagonalAbajo(char jugador) {
        for (int i = 0; i < 4; i++) {
            for (int j = m[i].length - 1; j > 2; j--) {
                if (hay4DiagonalesAbajo(i, j, jugador)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hay4DiagonalesAbajo(int columna, int fila, char jugador){
        for (int i = 0; i < 4; i++) {
            if (m[columna++][fila--] != jugador) {
                return false;
            }
        }
        return true;
    }

    public boolean estaFinalizado() {
        //TODO: finalizado
        return false;
    }

}
