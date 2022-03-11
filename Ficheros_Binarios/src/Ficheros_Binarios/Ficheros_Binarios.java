package Ficheros_Binarios;
import java.io.EOExecton;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
public class Ficheros_Binarios {
    static final String ruta="C:\\Java-practica\\Ficheros_Binarios\\src\\Ficheros_Binarios";
    static int menu(){
        Scanner sc = new Scanner(System.in);
        int op=0;
        System.out.println("MENU");
        System.out.println("1.Crear Fichero");
        System.out.println("2.Listar Fichero");
        System.out.println("3.Buscar Numero");
        System.out.println("4.Buscar Numero y Veces Que Se Repite");
        System.out.println("5.Fin");
        do {
            System.out.print("Teclea una opcion del 1 a 5-->");
            op = sc.nextInt();
        } while (op<1 || op>5);
        sc.close();
        return op;

    }

    static void CrearFichero(){
        Scanner entrada = new Scanner(System.in);
        int numero=0;
        try { 
            FileOutputStream archivo = new FileOutputStream(ruta+"numeros.dat");
            ObjectOutputStream salida=new ObjectOutputStream(archivo);
            System.out.println("Teclea numeros 999 para finalizar");
            while (numero!=999) {
                numero=entrada.nextInt();
            }
            entrada.close();


            
        } catch () {
           
        }
    }

    public static void main(String[] args) {
        int opcion =0;
        opcion=menu();
        switch(opcion){
            case 1:
                CrearFichero();
            case 2:

            case 3:

            case 4:

            case 5:

        }
        
    }
}
