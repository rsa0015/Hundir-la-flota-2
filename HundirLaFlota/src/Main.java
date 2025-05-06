import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String[][] arrayMapa1 = {
                {"\uD83C\uDF0A", "\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83C\uDF0A"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83D\uDEA2"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A"}
        };

        String[][] arrayMapa2 = {
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83D\uDEA2"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A"},
                {"\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83C\uDF0A", "\uD83C\uDF0A"}
        };

        System.out.println("Bienvenidos al juego de Hundir la Flota");
        Scanner scanner = new Scanner(System.in);
        int casillaPosicionX;
        int casillaPosicionY;
        String[][] tableroAuxiliar = arrayMapa2;
        int jugadorActivo = 2; // Comienza el jugador 2
        do {
            jugadorActivo = cambiarTurno(jugadorActivo, tableroAuxiliar); // Cambiar turno
            tableroAuxiliar = (jugadorActivo == 1) ? arrayMapa1 : arrayMapa2; // Seleccionar el mapa adecuado
            int[] posiciones = pedirPosiciones(scanner); // Pedir posiciones
            casillaPosicionX = posiciones[0];
            casillaPosicionY = posiciones[1];

            if (casillaPosicionX >= 0 && casillaPosicionX < 4 && casillaPosicionY >= 0 && casillaPosicionY < 5) {
                if (atacarMapa(tableroAuxiliar, casillaPosicionX, casillaPosicionY) == false) {
                    System.out.println("Agua.");
                    tableroAuxiliar[casillaPosicionX][casillaPosicionY] = "\uD83D\uDCA7";
                } else {
                    System.out.println("Tocado.");
                    tableroAuxiliar[casillaPosicionX][casillaPosicionY] = "\uD83E\uDDE8";
                    esHundido(tableroAuxiliar);
                }
            } else {
                System.out.println("Estás fuera del mapa.");
            }

            dibujarMapa(tableroAuxiliar);

        } while (casillaPosicionX <= 10 && casillaPosicionY <= 10 && hayBarcos(tableroAuxiliar));

        if (!hayBarcos(tableroAuxiliar)) {
            System.out.println("¡Enhorabuena! Has hundido todos los barcos.");
        }

        System.out.println("Resultados del jugador 1:");
        dibujarMapa(arrayMapa2);
        System.out.println("Resultados del jugador 2:");
        dibujarMapa(arrayMapa1);
    }

    /**
     * Esta función dibuja el mapa cuando le pasamos por parámetro un array
     * @param arrayMapa Este parámetro es el mapa con los barcos dibujados
     */
    public static void dibujarMapa(String [][] arrayMapa){
        //recorremos el array fila a fila y columna a columna para pintarlo
        for (int i = 0; i < arrayMapa.length; i++) {
            for (int j = 0; j < arrayMapa[i].length; j++) {
                System.out.printf(arrayMapa[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Esta función, con unos parámetros introducidos por teclado, comprueba si en la casilla había un barco o no, y devuelve un valor verdadero o falso.
     * @param arrayMapa Este parámetro es un array del mapa predibujado
     * @param casillaPosicionX Este valor es el número correspondiente a la fila
     * @param casillaPosicionY Este valor es el número de casilla del mapa
     * @return Se devuelve un valor verdadero o falso
     */
    public static boolean atacarMapa (String arrayMapa[][], int casillaPosicionX, int casillaPosicionY) {
        if (arrayMapa[casillaPosicionX] [casillaPosicionY] == "\uD83D\uDEA2") {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Esta función comprueba si en el mapa quedan barcos o no
     * @param arrayMapa Este parámetro es el mapa que ha ido siendo atacado y redibujado
     * @return Se devuelve un valor verdero o falso
     */
    public static boolean hayBarcos (String [][]arrayMapa) {
        int barcos=0;
        for (int i = 0; i < arrayMapa.length; i++) {
            for (int j = 0; j < arrayMapa[i].length; j++) {
                if (arrayMapa[i][j].equals("\uD83D\uDEA2")) {
                    //Si hay barcos añade valor a la variable
                    barcos++;
                }
            }
        }
      return barcos >=1;
    }

    /**
     * Esta función comprueba si se ha hundido un barco y si se sale del límite
     * @param arrayMapa Le pasamos por parámetro el array del mapa
     */
    public static void esHundido (String [][]arrayMapa) {
        for (int i = 0; i < arrayMapa.length; i++) {
            for (int j = 0; j < arrayMapa[i].length; j++) {
                boolean comprobarNoMeSalgoX = i+1 < 4 && i+2<4 ;
                boolean comprobarNoMeSalgoY = j+1 < 5 && j+2<5 ;
                if(comprobarNoMeSalgoY && comprobarNoMeSalgoX) {

                    boolean comprobarHundidoHorizontal = arrayMapa[i][j].equals("\uD83E\uDDE8") &&
                            arrayMapa[i][j + 1].equals("\uD83E\uDDE8") &&
                            arrayMapa[i][j + 2].equals("\uD83E\uDDE8");
                    boolean comprobarHundidoVertical = arrayMapa[i][j].equals("\uD83E\uDDE8") &&
                            arrayMapa[i + 1][j].equals("\uD83E\uDDE8") &&
                            arrayMapa[i + 2][j].equals("\uD83E\uDDE8");

                    if (comprobarHundidoHorizontal || comprobarHundidoVertical) {
                        System.out.println("Hundido.");
                    }
                }
            }
        }
    }

    // Método para cambiar de turno
    public static int cambiarTurno(int jugadorActivo, String[][] tableroAuxiliar) {
        if (jugadorActivo == 1) {
            System.out.println("Turno del jugador 2.");
            return 2;  // Cambia al jugador 2
        } else {
            System.out.println("Turno del jugador 1.");
            return 1;  // Cambia al jugador 1
        }
    }

    // Método para pedir posiciones
    public static int[] pedirPosiciones(Scanner scanner) {
        int[] posiciones = new int[2];
        System.out.println("Indica dos posiciones. Para abandonar, pon un número mayor que 10.");
        posiciones[0] = scanner.nextInt(); // Fila
        posiciones[1] = scanner.nextInt(); // Columna
        return posiciones;
    }

}