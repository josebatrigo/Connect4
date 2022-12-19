package com.aetxabao.connect4;

import static com.aetxabao.connect4.Tablero.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TableroTest {

    @Test
    @Order(1)
    void testConstructor() {
        char[][] m = {
                {X, O, X, O, L, L},
                {X, O, L, L, L, L},
                {X, O, X, O, O, X},
                {O, X, O, O, X, X},
                {O, X, X, O, L, L},
                {O, O, L, L, L, L},
                {X, X, O, L, L, L}
        };
        Tablero t = new Tablero(m);
        assertEquals(m,t.getMatriz(),"La matriz interna debe apuntar a la matriz pasada en el constructor.");
        int ancho = m.length;
        int alto = m[0].length;
        assertEquals(ancho, t.getAncho(), "El ancho debe ser definido, no confundir con el alto.");
        assertEquals(alto, t.getAlto(), "El alto debe ser definido, no confundir con el ancho.");
        int contador = 27;
        assertEquals(contador, t.getContador(), "El contador debe ser igual a las fichas del tablero");
        assertEquals(X, t.getTurno(), "Es el turno del que tiene menos fichas");
    }

    @Test
    @Order(2)
    void testIniciaTurno() {
        int cnt1 = 0;
        int cnt2 = 0;
        Tablero t = new Tablero();
        for (int i = 0; i < 10; i++) {
            t.iniciaTurno();
            if (t.getTurno() == O){
                cnt1++;
            }
            if (t.getTurno() == X){
                cnt2++;
            }
        }
        assertNotEquals(cnt1,0,"Debe salir alguna vez '" + O + "'");
        assertNotEquals(cnt2,0,"Debe salir alguna vez '" + X + "'");
    }

    @Test
    @Order(3)
    void testCambiaTurno() {
        char[][] m = {
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {O, L, L, L, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L}
        };
        Tablero t = new Tablero(m);
        t.cambiaTurno();
        assertEquals(O, t.getTurno(), "Del turno de '" + X + "' pasa al de '" + O + "'");
        t.cambiaTurno();
        assertEquals(X, t.getTurno(), "Del turno de '" + O + "' pasa al de '" + X + "'");
    }


    @Test
    @Order(4)
    void testEstaColumnaLibre() {
        char[][] m = {
                {O, X, O, X, O, X},
                {O, X, O, X, O, X},
                {O, X, O, X, O, X},
                {X, O, X, O, X, O},
                {O, X, O, X, O, X},
                {O, X, O, X, O, X},
                {O, X, O, X, O, X}
        };
        Tablero t = new Tablero(m);
        for (int i = 0; i < m.length; i++) {
            assertFalse(t.estaColumnaLibre(i), "La columna no está libre si no se puede introducir una ficha.");
        }
        int r = (int)(Math.random()*m.length);
        m[r][m[0].length-1] = L;
        assertTrue(t.estaColumnaLibre(r), "La columna está libre si se puede introducir una ficha.");
    }

    @Test
    @Order(5)
    void testInsertar() {
        Tablero t = new Tablero();
        char[][] m = t.getMatriz();
        for (int i = 0; i < t.getAncho(); i++) {
            for (int j = 0; j < t.getAlto(); j++) {
                t.inserta(t.getTurno(),i);
                assertEquals(t.getTurno(), m[i][j], "Inserta rellenando todo el tablero");
                t.cambiaTurno();
            }
        }
        char[][] m1 = {
                {L, L, L, L, L, L},
                {O, L, L, L, L, L},
                {O, O, L, L, L, L},
                {O, O, O, L, L, L},
                {O, O, O, O, L, L},
                {O, O, O, O, O, L},
                {O, O, O, O, O, O},
        };
        Tablero t1 = new Tablero(m1);
        for (int i = 0; i < m[0].length; i++) {
            t1.inserta(X,i);
            assertEquals(X, m1[i][i], "Inserta una diagonal hacia arriba en el tablero");
        }
    }

    @Test
    @Order(6)
    void testEstaLleno() {
        char[][] m1 = {
                {O, X, O, X, O, X},
                {O, X, O, X, O, X},
                {O, X, O, X, O, L},
                {X, O, X, O, X, O},
                {O, X, O, X, O, X},
                {O, X, O, X, O, X},
                {O, X, O, X, O, X}
        };
        Tablero t1 = new Tablero(m1);
        assertFalse(t1.estaLleno(), "Si quedan huecos no está lleno.");
        t1.inserta(X,2);
        assertTrue(t1.estaLleno(), "Tras insertar actualizar contador.");
    }

    @Test
    @Order(11)
    void testGanaHorizontal1() {
        char[][] m = {
                {O, L, L, L, L, L},
                {O, L, L, L, L, L},
                {O, L, L, L, L, L},
                {O, L, L, L, L, L},
                {X, L, L, L, L, L},
                {X, L, L, L, L, L},
                {X, L, L, L, L, L}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(O), "Fila A gana '" + O + "'");
        assertFalse(t.gana(X), "Fila A no gana '" + X + "'");
    }

    @Test
    @Order(12)
    void testGanaHorizontal2() {
        char[][] m = {
                {O, X, L, L, L, L},
                {X, O, L, L, L, L},
                {O, O, L, L, L, L},
                {X, O, L, L, L, L},
                {O, O, L, L, L, L},
                {X, X, X, L, L, L},
                {O, X, L, L, L, L}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(O), "Fila B gana '" + O + "'");
        assertFalse(t.gana(X), "Fila B no gana '" + X + "'");
    }

    @Test
    @Order(13)
    void testGanaHorizontal3() {
        char[][] m = {
                {O, X, X, L, L, L},
                {X, O, X, L, L, L},
                {O, X, O, L, L, L},
                {X, O, O, L, L, L},
                {O, X, O, L, L, L},
                {X, O, O, L, L, L},
                {O, X, X, L, L, L}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(O), "Fila C gana '" + O + "'");
        assertFalse(t.gana(X), "Fila C no gana '" + X + "'");
    }

    @Test
    @Order(14)
    void testGanaHorizontal4() {
        char[][] m = {
                {O, X, O, O, X, L},
                {X, O, X, X, X, L},
                {O, X, O, L, L, L},
                {X, O, X, O, L, L},
                {O, X, O, O, X, L},
                {X, O, X, O, X, L},
                {O, X, O, O, X, L}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(O), "Fila D gana '" + O + "'");
        assertFalse(t.gana(X), "Fila D no gana '" + X + "'");
    }

    @Test
    @Order(15)
    void testGanaHorizontal5() {
        char[][] m = {
                {O, X, O, X, O, X},
                {O, X, O, X, O, X},
                {O, X, O, X, O, X},
                {X, O, X, O, O, L},
                {O, X, O, X, L, L},
                {O, X, O, X, L, L},
                {O, X, O, X, L, L}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(O), "Fila E gana '" + O + "'");
        assertFalse(t.gana(X), "Fila E no gana '" + X + "'");
    }

    @Test
    @Order(16)
    void testGanaHorizontal6() {
        char[][] m = {
                {O, X, O, X, O, X},
                {O, X, O, X, O, X},
                {O, X, O, X, O, L},
                {X, O, X, O, X, X},
                {O, X, O, X, O, X},
                {O, X, O, X, O, X},
                {O, X, O, X, O, X}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(X), "Fila F gana '" + X + "'");
        assertFalse(t.gana(O), "Fila F no gana '" + O + "'");
    }

    @Test
    @Order(21)
    void testGanaVertical1() {
        char[][] m = {
                {O, X, O, O, O, O},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {X, L, L, L, L, L},
                {X, L, L, L, L, L},
                {X, L, L, L, L, L}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(O), "Columna 0 gana '" + O + "'");
        assertFalse(t.gana(X), "Columna 0 no gana '" + X + "'");
    }

    @Test
    @Order(22)
    void testGanaVertical2() {
        char[][] m = {
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {X, O, O, O, O, L},
                {L, L, L, L, L, L},
                {X, L, L, L, L, L},
                {X, L, L, L, L, L},
                {X, L, L, L, L, L}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(O), "Columna 2 gana '" + O + "'");
        assertFalse(t.gana(X), "Columna 2 no gana '" + X + "'");
    }

    @Test
    @Order(23)
    void testGanaVertical3() {
        char[][] m = {
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {O, O, O, O, L, L},
                {X, L, L, L, L, L},
                {X, L, L, L, L, L},
                {X, L, L, L, L, L}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(O), "Columna 3 gana '" + O + "'");
        assertFalse(t.gana(X), "Columna 3 no gana '" + X + "'");
    }

    @Test
    @Order(24)
    void testGanaVertical4() {
        char[][] m = {
                {X, L, L, L, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {X, L, L, L, L, L},
                {X, L, L, L, L, L},
                {O, X, O, O, O, O}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(O), "Columna 6 gana '" + O + "'");
        assertFalse(t.gana(X), "Columna 6 no gana '" + X + "'");
    }

    @Test
    @Order(31)
    void testGanaDiagonalArriba1() {
        char[][] m = {
                {X, O, X, L, L, L},
                {O, X, O, L, L, L},
                {X, O, X, L, L, L},
                {O, X, O, X, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(X), "Diagonal arriba 1 gana '" + X + "'");
        assertFalse(t.gana(O), "Diagonal arriba 1 no gana '" + O + "'");
    }

    @Test
    @Order(32)
    void testGanaDiagonalArriba2() {
        char[][] m = {
                {X, O, X, L, L, L},
                {O, X, O, X, L, L},
                {X, O, X, O, X, L},
                {O, X, O, O, X, X},
                {O, L, L, L, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(X), "Diagonal arriba 2 gana '" + X + "'");
        assertFalse(t.gana(O), "Diagonal arriba 2 no gana '" + O + "'");
    }

    @Test
    @Order(33)
    void testGanaDiagonalArriba3() {
        char[][] m = {
                {X, L, L, L, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {O, X, O, O, X, L},
                {X, O, X, O, X, L},
                {O, X, O, X, O, L},
                {X, O, X, X, O, O},
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(O), "Diagonal arriba 3 gana '" + O + "'");
        assertFalse(t.gana(X), "Diagonal arriba 3 no gana '" + X + "'");
    }

    @Test
    @Order(34)
    void testGanaDiagonalArriba4() {
        char[][] m = {
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L},
                {O, X, O, L, L, L},
                {X, O, X, O, L, L},
                {O, X, O, X, L, L},
                {X, O, X, O, L, L},
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(O), "Diagonal arriba 4 gana '" + O + "'");
        assertFalse(t.gana(X), "Diagonal arriba 4 no gana '" + X + "'");
    }

    @Test
    @Order(41)
    void testGanaDiagonalAbajo1() {
        char[][] m = {
                {O, X, O, X, L, L},
                {X, O, X, L, L, L},
                {O, X, L, L, L, L},
                {X, L, L, L, L, L},
                {O, L, L, L, L, L},
                {L, L, L, L, L, L},
                {L, L, L, L, L, L}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(X), "Diagonal abajo 1 gana '" + X + "'");
        assertFalse(t.gana(O), "Diagonal abajo 1 no gana '" + O + "'");
    }

    @Test
    @Order(42)
    void testGanaDiagonalAbajo2() {
        char[][] m = {
                {L, L, L, L, L, L},
                {X, X, X, L, L, L},
                {O, X, O, X, L, L},
                {X, O, X, O, L, L},
                {O, X, O, L, L, L},
                {O, O, L, L, L, L},
                {O, L, L, L, L, L},
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(O), "Diagonal abajo 2 gana '" + O + "'");
        assertFalse(t.gana(X), "Diagonal abajo 2 no gana '" + X + "'");
    }

    @Test
    @Order(43)
    void testGanaDiagonalAbajo3() {
        char[][] m = {
                {O, O, L, L, L, L},
                {X, X, X, O, O, L},
                {O, X, O, X, L, L},
                {X, O, X, O, X, X},
                {X, X, O, O, X, L},
                {O, O, O, X, L, L},
                {X, O, X, L, L, L},
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(X), "Diagonal abajo 3 gana '" + X + "'");
        assertFalse(t.gana(O), "Diagonal abajo 3 no gana '" + O + "'");
    }

    @Test
    @Order(44)
    void testGanaDiagonalAbajo4() {
        char[][] m = {
                {X, O, X, O, X, X},
                {X, X, O, O, X, L},
                {O, O, O, X, L, L},
                {X, O, X, O, L, L},
                {X, O, L, L, L, L},
                {X, X, X, O, O, L},
                {O, X, O, O, L, L}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.gana(X), "Diagonal abajo 4 gana '" + X + "'");
        assertFalse(t.gana(O), "Diagonal abajo 4 no gana '" + O + "'");
    }

    @Test
    @Order(51)
    void testEstaFinalizado1() {
        char[][] m = {
                {X, O, X, O, X, L},
                {X, X, O, O, X, L},
                {O, O, O, X, L, L},
                {X, O, X, O, L, L},
                {X, O, L, L, L, L},
                {X, X, X, O, O, L},
                {O, X, O, O, L, L}
        };
        Tablero t = new Tablero(m);
        assertFalse(t.estaFinalizado(), "Si no hay 4 en raya no está finalizado");
    }

    @Test
    @Order(52)
    void testEstaFinalizado2() {
        char[][] m = {
                {X, O, X, O, X, X},
                {X, X, O, O, X, L},
                {O, O, O, X, L, L},
                {X, O, X, O, L, L},
                {X, O, L, L, L, L},
                {X, X, X, O, O, O},
                {O, X, O, O, L, L}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.estaFinalizado(), "Si hay 4 en raya está finalizado");
    }

    @Test
    @Order(53)
    void testEstaFinalizado3() {
        char[][] m = {
                {O, X, O, X, O, X},
                {O, X, O, X, O, X},
                {O, X, O, X, O, X},
                {X, O, X, O, X, O},
                {O, X, O, X, O, X},
                {O, X, O, X, O, X},
                {O, X, O, X, O, X}
        };
        Tablero t = new Tablero(m);
        assertTrue(t.estaFinalizado(), "Si el tablero está lleno está finalizado");
    }

}