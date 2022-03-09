package Figura;
import java.util.Scanner;
public class Rombo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean numeroCorrecto = false;
        int FlasToatales; 
        do {
            System.out.print("Introduce el número de filas (natural e impar): ");
            FlasToatales = sc.nextInt();
            if(FlasToatales>0 && FlasToatales%2!=0){
                numeroCorrecto = true;
            }
        } while (!numeroCorrecto);
         
        System.out.println("");
         
        int numFilas = FlasToatales/2 + 1;
                 
        for(int altura=1;altura<=numFilas;altura++){
            //Blancos
            for(int blancos=1;blancos<=numFilas-altura;blancos++){
                System.out.print(" ");
            }
            //Asteriscos
            for(int asteriscos=1;asteriscos<=(2*altura)-1;asteriscos++){
                System.out.print("*");
            }
            System.out.println("");         
        }
         //Mitad inferior
        numFilas--;//Se le resta uno para no duplicar
         
        for(int altura=1;altura<=numFilas;altura++){
            //Blancos
            for(int blancos=1;blancos<=altura;blancos++){
                System.out.print(" ");
            }
            //Asteriscos
            for(int asteriscos=1;asteriscos<=(numFilas-altura)*2 +1;asteriscos++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}


