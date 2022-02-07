
//crea un programa que intorduzcas numeros en una mtriz 3x3 y diga cuales son impares y cuales son pares y su posicion

import java.util.Scanner;
public class par_imp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int matriz[][] = new int[2][2];
        for(int i=0; i<4;i++){
            for(int j=0;j<4;j++){
                System.out.println("Introduce un numero en la posición: "+i+"/"+j);
                int num = sc.nextInt();
            }
        }
    }
}
