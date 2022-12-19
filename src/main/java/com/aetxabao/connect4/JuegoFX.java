package com.aetxabao.connect4;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.Objects;

import static com.aetxabao.connect4.SalidaFX.CANVAS_WIDTH;
import static com.aetxabao.connect4.SalidaFX.CANVAS_HEIGHT;

public class JuegoFX extends Application {
    private static boolean jugando;
    private static Tablero tablero;
    private static GraphicsContext gc;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        setUp(stage);
        portada();
        stage.show();
    }

    private void setUp(Stage stage) {
        stage.setTitle( "Conecta4" );
        stage.setResizable(false);

        Group root = new Group();
        Scene scene = new Scene( root );
        stage.setScene( scene );

        Image icon16 = new Image(Objects.requireNonNull(JuegoFX.class.getResourceAsStream("icon16.png")));
        Image icon32 = new Image(Objects.requireNonNull(JuegoFX.class.getResourceAsStream("icon32.png")));
        Image icon64 = new Image(Objects.requireNonNull(JuegoFX.class.getResourceAsStream("icon64.png")));
        Image icon128 = new Image(Objects.requireNonNull(JuegoFX.class.getResourceAsStream("icon128.png")));
        stage.getIcons().addAll(icon16, icon32, icon64, icon128);

        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.getChildren().add( canvas );

        gc = canvas.getGraphicsContext2D();

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                stage.close();
            }
            manejaEvento(e);
        });

        scene.setOnMousePressed(JuegoFX::manejaEvento);
    }

    private static void portada() {
        jugando = false;
        SalidaFX.pintaInicio(gc);
    }

    private static void manejaEvento(Event e) {
        if (!jugando) {
            inicio();
        }else{
            juega(EntradaFX.getColumna(e));
        }
    }

    private static void inicio() {
        jugando = true;
        tablero = new Tablero();
        tablero.iniciaTurno();
        SalidaFX.pinta(gc, tablero.getMatriz(), tablero.getTurno());
    }

    private static void juega(int columna) {
        if ( tablero.estaColumnaLibre(columna) ){
            tablero.inserta(tablero.getTurno(), columna);
            if ( !tablero.estaFinalizado() ) {
                tablero.cambiaTurno();
                SalidaFX.pinta(gc, tablero.getMatriz(), tablero.getTurno());
            } else {
                fin();
            }
        }
    }

    private static void fin() {
        char jugador = tablero.getTurno();
        char[][] m = tablero.getMatriz();
        if (tablero.gana(jugador)){
            SalidaFX.pintaGana(gc, m, jugador);
        }else{
            SalidaFX.pintaEmpate(gc, m);
        }
        jugando = false;
    }

}
