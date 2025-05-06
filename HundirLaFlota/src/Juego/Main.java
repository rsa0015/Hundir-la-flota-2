package Juego;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] mapaJugador1 = {
                {"\uD83C\uDF0A", "\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83C\uDF0A"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83D\uDEA2"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A"}
        };

        String[][] mapaJugador2 = {
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83D\uDEA2"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A"},
                {"\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83C\uDF0A", "\uD83C\uDF0A"}
        };

        Tablero tablero1 = new Tablero(mapaJugador1);
        Tablero tablero2 = new Tablero(mapaJugador2);

        Scanner scanner = new Scanner(System.in);
        int jugadorActivo = 2;
        int fila, columna;

        System.out.println("Bienvenidos al juego de Hundir la Flota");

        do {
            jugadorActivo = cambiarTurno(jugadorActivo);
            Tablero tableroEnemigo = (jugadorActivo == 1) ? tablero2 : tablero1;

            int[] posiciones = pedirPosiciones(scanner);
            fila = posiciones[0];
            columna = posiciones[1];

            if (fila >= 0 && fila < 4 && columna >= 0 && columna < 5) {
                boolean acierto = tableroEnemigo.atacar(fila, columna);
                if (acierto) {
                    System.out.println("Tocado.");
                    tableroEnemigo.comprobarHundido();
                } else {
                    System.out.println("Agua.");
                }
            } else {
                System.out.println("Estás fuera del mapa.");
            }

            tableroEnemigo.dibujarMapa();

        } while (fila <= 10 && columna <= 10 && (tablero1.hayBarcos() && tablero2.hayBarcos()));

        System.out.println("¡Fin del juego!");
        if (!tablero1.hayBarcos()) {
            System.out.println("¡Jugador 2 ha ganado!");
        } else if (!tablero2.hayBarcos()) {
            System.out.println("¡Jugador 1 ha ganado!");
        }

        System.out.println("Resultados del jugador 1:");
        tablero2.dibujarMapa();
        System.out.println("Resultados del jugador 2:");
        tablero1.dibujarMapa();
    }

    public static int cambiarTurno(int jugadorActual) {
        if (jugadorActual == 1) {
            System.out.println("Turno del jugador 2.");
            return 2;
        } else {
            System.out.println("Turno del jugador 1.");
            return 1;
        }
    }

    public static int[] pedirPosiciones(Scanner scanner) {
        int[] pos = new int[2];
        System.out.println("Indica fila y columna (0 a 3 y 0 a 4). Para salir, pon un número mayor a 10.");
        pos[0] = scanner.nextInt(); // Fila
        pos[1] = scanner.nextInt(); // Columna
        return pos;
    }
}
