
//crea un programa que intorduzcas numeros en una mtriz 3x3 y diga cuales son impares y cuales son pares y su posicion

import java.util.Scanner;
public class par_imp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int matriz[][] = new int[2][2];
        for(int i=0; i<2;i++){
            for(int j=0;j<2;j++){
                System.out.println("Introduce un numero en la posición: "+i+"/"+j);
                matriz[i][j] = sc.nextInt();
            }
        }

        for(int i=0; i<2;i++){
            for(int j=0;j<2;j++){
                if(matriz[i][j]%2==0){
                    System.out.println("Los numero pares son: "+matriz[i][j]+" en la posicion "+i+"/"+j);
                }else{
                    System.out.println("Los numeros impares son: "+matriz[i][j]+" en la posicion "+i+" "+j);
                }
            }
        }
    }
}
