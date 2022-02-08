
//Crea un programa que genero numeros randon en un intervalo definido por el usuario y jugar a encontrarlo preguntar para repetir y contar aciertos

import java.util.Scanner;
public class numero_ran {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int contador=0;
        String seguir="S";
        do{
            int sup =0;
            int num = 0;
            System.out.println("Introduce el limite inferior");
            int inf = sc.nextInt();
            do{
                System.out.println("Introduce el limite superior");
                sup = sc.nextInt();
            }while(inf>sup);
            int rand = (int) (Math.random()*(inf+sup));
            System.out.println(rand);
            do{
                System.out.print("Teclea un numero para intentar encontrarlo: ");
                num = sc.nextInt();
            }while(num!=rand);
            if (num==rand) {
                System.out.println("Has encontrado el numero: "+rand);
                contador++;
                System.out.println("Llevas "+contador+" aciertos");
            } 
            System.out.println("Quieres continuar S / N");
            seguir=sc.next();
        }while(seguir.equals("S"));
    }
}
