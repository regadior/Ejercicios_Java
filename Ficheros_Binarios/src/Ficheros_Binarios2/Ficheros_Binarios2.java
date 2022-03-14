package Ficheros_Binarios2;

public class Ficheros_Binarios2 {
    public static void main(String[] args) {
        int matriz[][] = new int[5][5];

        for (int i=0; i < matriz.length; i++) {
            for (int j=0; j < matriz[i].length; j++) {
              matriz[i][j] = (int) (Math.random()*9+1);
            }
        }

        for (int i=0; i < matriz.length; i++) {
            for (int j=0; j < matriz[i].length; j++) {
                System.out.println(matriz[i][j]);
            }
        }
        System.out.println();
    }
}
