import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//- Crea el juego de hundir la flota. 🚢🌊
        //el mapa inicial
        String[][] arrayMapa1 = {
                {"\uD83C\uDF0A", "\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83C\uDF0A"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83D\uDEA2"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A"}
        };

        String[][] arrayMapa2 = {
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83D\uDEA2"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A","\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83C\uDF0A"},
                {"\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83D\uDEA2", "\uD83C\uDF0A", "\uD83C\uDF0A"}
        };


        System.out.println("Bienvenidos al juego de Hundir la Flota");
        Scanner scanner = new Scanner(System.in);
        int casillaPosicionX;
        int casillaPosicionY;
        String[][] tableroAuxiliar = arrayMapa2;
        int jugador1 = 1;
        int jugador2 = 2;
        int jugadorActivo=jugador2;
        do {
            if (jugadorActivo == jugador1) {
                jugadorActivo=jugador2;
                tableroAuxiliar = arrayMapa1;
                System.out.println("Turno del jugador 2.");
            } else {
                jugadorActivo=jugador1;
                tableroAuxiliar = arrayMapa2;
                System.out.println("Turno del jugador 1.");
            }
            //Pedimos las posiciones por teclado
            System.out.println("Indica dos posiciones. Para abandonar, pon un número mayor que 10.");
            casillaPosicionX = scanner.nextInt();
            casillaPosicionY = scanner.nextInt();


            //Comprobamos si en las posiciones hay barcos
                if (casillaPosicionX>=0 && casillaPosicionX <4 && casillaPosicionY>=0 && casillaPosicionY<5) {
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


        } while (casillaPosicionX <=10 && casillaPosicionY <= 10 && hayBarcos(tableroAuxiliar));

        if (!hayBarcos(tableroAuxiliar)) {
            System.out.println("¡Enhorabuena! Has hundido todos los barcos.");
        }
        System.out.println("Resultados del jugador 1:");dibujarMapa(arrayMapa2);
        System.out.println("Resultados del jugador 2:");dibujarMapa(arrayMapa1);



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
}