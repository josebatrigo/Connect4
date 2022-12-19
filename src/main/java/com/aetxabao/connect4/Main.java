package com.aetxabao.connect4;

/*
mvn package
/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/bin/jpackage --name Connect4 --icon src/main/resources/com/aetxabao/connect4/icon.icns --input target --main-jar Connect4-1.0-SNAPSHOT.jar --main-class com.aetxabao.connect4.Main
 */
public class Main {
    public static void main(String[] args) {
        JuegoFX.main(args);
    }
}
