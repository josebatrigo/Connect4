package com.aetxabao.connect4;

import static com.aetxabao.connect4.Tablero.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SalidaTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @Order(1)
    void testPinta1() {
        int i = 0;
        String[] a = {
                "###################################",
                "#            CONECTA 4            #",
                "###################################",
                "                                   ",
                "F  |   |   |   |   |   |   |   |  F",
                "E  |   |   |   |   |   |   |   |  E",
                "D  |   |   |   |   |   |   |   |  D",
                "C  |   |   |   |   |   |   |   |  C",
                "B  |   |   |   |   |   |   |   |  B",
                "A  |   |   |   |   |   |   |   |  A",
                "   -----------------------------   ",
                "   | 1 | 2 | 3 | 4 | 5 | 6 | 7 |   ",
                "                                   "
                };
        Tablero t = new Tablero();
        char[][] m = t.getMatriz();
        Salida.pinta(m);
        Scanner sc = new Scanner(outputStreamCaptor.toString());
        sc.useDelimiter(Pattern.compile("[\\r\\n;]+"));
        sc.next();//salta limpiaPantalla
        while (sc.hasNext()) {
            assertEquals(a[i++].trim(),sc.next().trim(),"Debe coincidir exactamente, espacios incluidos.");
        }
    }

    @Test
    @Order(2)
    void testPinta2() {
        int i = 0;
        char[][] m = {
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {O, L, L, L, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L}
        };
        String[] a = {
                "###################################",
                "#            CONECTA 4            #",
                "###################################",
                "                                   ",
                "F  |   |   |   |   |   |   |   |  F",
                "E  |   |   |   |   |   |   |   |  E",
                "D  |   |   |   |   |   |   |   |  D",
                "C  |   |   |   |   |   |   |   |  C",
                "B  |   |   |   |   |   |   |   |  B",
                "A  |   |   |   | O |   |   |   |  A",
                "   -----------------------------   ",
                "   | 1 | 2 | 3 | 4 | 5 | 6 | 7 |   ",
                "                                   "
        };
        Salida.pinta(m);
        Scanner sc = new Scanner(outputStreamCaptor.toString());
        sc.useDelimiter(Pattern.compile("[\\r\\n;]+"));
        sc.next();//salta limpiaPantalla
        while (sc.hasNext()) {
            assertEquals(a[i++].trim(),sc.next().trim(),"Debe coincidir exactamente, espacios incluidos.");
        }
    }

    @Test
    @Order(3)
    void testPinta3() {
        int i = 0;
        char[][] m = {
                {X, O, X, O, L, L},
                {X, O, L, L, L, L},
                {X, O, X, O, O, X},
                {O, X, O, O, X, X},
                {O, X, X, O, L, L},
                {O, O, L, L, L, L},
                {X, X, O, L, L, L}
        };
        String[] a = {
                "###################################",
                "#            CONECTA 4            #",
                "###################################",
                "                                   ",
                "F  |   |   | X | X |   |   |   |  F",
                "E  |   |   | O | X |   |   |   |  E",
                "D  | O |   | O | O | O |   |   |  D",
                "C  | X |   | X | O | X |   | O |  C",
                "B  | O | O | O | X | X | O | X |  B",
                "A  | X | X | X | O | O | O | X |  A",
                "   -----------------------------   ",
                "   | 1 | 2 | 3 | 4 | 5 | 6 | 7 |   ",
                "                                   "
        };
        Salida.pinta(m);
        Scanner sc = new Scanner(outputStreamCaptor.toString());
        sc.useDelimiter(Pattern.compile("[\\r\\n;]+"));
        sc.next();//salta limpiaPantalla
        while (sc.hasNext()) {
            assertEquals(a[i++].trim(),sc.next().trim(),"Debe coincidir exactamente, espacios incluidos.");
        }
    }

}