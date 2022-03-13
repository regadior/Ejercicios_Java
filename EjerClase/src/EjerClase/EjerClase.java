package EjerClase;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
public class EjerClase {

    static int menu(){
        Scanner sc = new Scanner(System.in);
        int opcion=0;
		System.out.println("1.-Altas Personas");
		System.out.println("2.-Ficheros hombres y mujeres");
		System.out.println("3.-Personas con  mayor y menor edad");
		System.out.println("4.-Personas con edad por encima de la media");
		System.out.println("5.-Visualizar personas");
		System.out.println("6.-Visualizar hombres");
		System.out.println("7.-Visualizar mujeres");
		System.out.println("8.-FIN");
		do{
			System.out.println("introduzca opcion");
			opcion=sc.nextInt();
			sc.nextLine();
		}while(opcion<1|opcion>8);
        sc.close();
        return opcion;
    } 
    static void altasPersonas(){
        
    }
    public static void main(String[] args) {
        int opcion=0;
        do{
            opcion=menu();
            switch (opcion) {
                case 1:
                    altasPersonas();
                    break;
            
                default:
                    break;
            }

        }while(opcion!=8);
    }
}
