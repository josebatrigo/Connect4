package com.aetxabao.connect4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Entrada {

    private final Scanner teclado;

    public Entrada() {
        teclado = new Scanner(System.in);
    }

    public int getColumna(char jugador, int ancho) {
        int v = -1;
        while (v < 1 || v > ancho) {
            System.out.printf("Jugador '%c', columna: ", jugador);
            v = teclado.nextInt();
        }
        teclado.nextLine();
        return v;
    }

    public static void esperaInicio(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            in.readLine();
        } catch (IOException ignored) {}
    }

}
