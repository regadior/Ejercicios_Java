package Ficheros_Binarios_Persona;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ficheros_Binarios_Persona {
    static final String ruta="C:\\Java-practica\\Ficheros_Binarios\\src\\Ficheros_Binarios_Persona\\";
    static int menu(){
        Scanner sc = new Scanner(System.in);
        boolean comprobador=false;
        int op=0;
        
        while (comprobador) {
            try{
                    System.out.println("\n\tMENÚ\n\t====");
                    System.out.println("1.Altas.");
                    System.out.println("2.Consultas.");
                    System.out.println("3.Listado");
                    System.out.println("4.Fin.");

                    do {
                        System.out.print("Teclea una opción: ");
                        op=sc.nextInt();
                        if (op==1 || op==2 || op==3 || op==4){
                          comprobador= true;  
                            break;
                        }                                           //TODO comprobar que funcione solo el tipo de dato numerico
                    } while (op<1 || op>4);
            } catch(InputMismatchException e){
                System.out.println("Tip ode dato incorrecto");
            }
        }
        sc.nextLine();
        return op;
    }

    static void Altas()  {
        System.out.println("Llega ");
        /*Scanner sc = new Scanner(System.in);
        String nombre="";
        int edad=0;
        String varchar="";
        char sexo=' ';
        DataOutputStream entrada = new DataOutputStream(new FileOutputStream(ruta+"personas.dat"));
        try {
            
        } catch () {
            
        }*/
    }

    static void fin(){
        System.out.println("El programa a finalizado");
    }





    public static void main(String[] args) {
        int opcion =0;
        do{
            opcion=menu();
            switch(opcion){
                case 1:
                Altas();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:
                fin();
                    break;
            }
        }while(opcion!=5);
    }
}
