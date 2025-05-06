package Juego;

public class Tablero {

    private String[][] mapa;

    public Tablero(String[][] mapa) {
        this.mapa = mapa;
    }

    public void dibujarMapa() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.printf(mapa[i][j]);
            }
            System.out.println();
        }
    }

    public boolean atacar(int x, int y) {
        if (mapa[x][y].equals("\uD83D\uDEA2")) {
            mapa[x][y] = "\uD83E\uDDE8"; // Tocado
            return true;
        } else {
            mapa[x][y] = "\uD83D\uDCA7"; // Agua
            return false;
        }
    }

    public boolean hayBarcos() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j].equals("\uD83D\uDEA2")) {
                    return true;
                }
            }
        }
        return false;
    }

    public void comprobarHundido() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                boolean noMeSalgoX = i + 2 < mapa.length;
                boolean noMeSalgoY = j + 2 < mapa[i].length;

                boolean hundidoHorizontal = noMeSalgoY &&
                        mapa[i][j].equals("\uD83E\uDDE8") &&
                        mapa[i][j + 1].equals("\uD83E\uDDE8") &&
                        mapa[i][j + 2].equals("\uD83E\uDDE8");

                boolean hundidoVertical = noMeSalgoX &&
                        mapa[i][j].equals("\uD83E\uDDE8") &&
                        mapa[i + 1][j].equals("\uD83E\uDDE8") &&
                        mapa[i + 2][j].equals("\uD83E\uDDE8");

                if (hundidoHorizontal || hundidoVertical) {
                    System.out.println("Hundido.");
                }
            }
        }
    }

    public String[][] getMapa() {
        return mapa;
    }
}
