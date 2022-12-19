package com.aetxabao.connect4;

public class Juego {

    private static Tablero tablero;
    private static Entrada entrada;

    public static void main(String[] args) {
        Juego.run();
    }

    public static void run() {
        inicio();
        juega();
        fin();
    }

    private static void inicio() {
        tablero = new Tablero();
        entrada = new Entrada();
        Salida.pintaInicio();
        Entrada.esperaInicio();
    }

    private static void juega() {
        char turno;
        int columna;
        boolean estaLibre;
        char[][] m = tablero.getMatriz();
        int ancho = m.length;
        tablero.iniciaTurno();
        Salida.pinta(m);
        while ( true ) {
            turno = tablero.getTurno();
            columna = entrada.getColumna(turno, ancho) - 1;
            estaLibre = tablero.estaColumnaLibre(columna);
            if (estaLibre) {
                tablero.inserta(turno, columna);
                if (!tablero.estaFinalizado()) {
                    tablero.cambiaTurno();
                    Salida.pinta(tablero.getMatriz());
                } else {
                    break;
                }
            }
        }
    }

    private static void fin() {
        char jugador = tablero.getTurno();
        char[][] m = tablero.getMatriz();
        if (tablero.gana(jugador)){
            Salida.pintaGana(m, jugador);
        }else{
            Salida.pintaEmpate(m);
        }
    }

}
