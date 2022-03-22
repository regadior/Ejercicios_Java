package Ficheros_Binarios;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

@SuppressWarnings("resource")
public class Ficheros_Binarios {
    static final String ruta = "D:\\Java-practica\\Ficheros_Binarios\\src\\Ficheros_Binarios\\";

    static int menu(Scanner sc) {
        int opcion = 0;
        do {
            System.out.println("\n\tMENÚ\n");
            System.out.println("1.Crear fichero.");
            System.out.println("2.Listar fichero.");
            System.out.println("3.Buscar número.");
            System.out.println("4.Buscar número y veces que se repite.");
            System.out.println("5.Fin de programa.");
            System.out.print("\n\tTeclee opción(1-5)? ");
            opcion = sc.nextInt();
        } while (opcion > 5 || opcion < 1);
        return opcion;

    }

    static void crearFichero() {
        System.out.println("\n\tCREAR FICHERO\n");
        Scanner sc = new Scanner(System.in);
        int numero = 0;
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta + "numeros.dat"));
            System.out.println("Teclee números (999 para finalizar):");
            while (numero != 999) {
                numero = sc.nextInt();
                salida.writeInt(numero);
            }
            salida.close();
        } catch (IOException ioe) {
            System.out.println("El fichero no se puede abrir");
        }
    }

    static void listarFichero() {
        System.out.println("\n\tLISTAR FICHERO\n");
        int numero = 0;
        try {
            ObjectInputStream lector = new ObjectInputStream(new FileInputStream(ruta + "numeros.dat"));
            System.out.println("\nLos números grabados en el fichero son: \n");
            numero = lector.readInt();
            while (numero != 999) {
                System.out.println(numero);
                numero = lector.readInt();
            }
            lector.close();
        } catch (IOException ioe) {
            System.out.println("El fichero no se puede abrir");
        }
    }

    static boolean buscarNumero(Scanner sc) {
        System.out.println("\n\tBUSCAR NÚMERO\n");
        int numeroBuscado = 0, numero = 0;
        boolean encontrado = false;
        System.out.print("Teclee el número a buscar: ");
        numeroBuscado = sc.nextInt();

        try {
            ObjectInputStream lector = new ObjectInputStream(new FileInputStream(ruta + "numeros.dat"));
            numero = lector.readInt();
            while (numero != 999) {
                if (numero == numeroBuscado) {
                    encontrado = true;
                    numero = lector.readInt();
                    break;
                } else {
                    numero = lector.readInt();
                }
            }

        } catch (IOException ioe) {
            System.out.println("El fichero no se puede abrir");
        }
        return encontrado;
    }

    static String buscarNumeroYContar(Scanner sc) {
        System.out.println("\n\tBUSCAR NUMERO Y CONTAR\n");
        int numeroBuscado = 0, repeticiones = 0, numero = 0;
        String respuesta = "";
        System.out.print("Teclee el número a buscar: ");
        numeroBuscado = sc.nextInt();
        try {
            ObjectInputStream lector = new ObjectInputStream(new FileInputStream(ruta + "numeros.dat"));
            numero = lector.readInt();
            while (numero != 999) {
                if (numero == numeroBuscado) {
                    repeticiones++;
                    numero = lector.readInt();
                } else {
                    numero = lector.readInt();
                }
            }

        } catch (IOException ioe) {
            System.out.println("El fichero no se puede abrir");
        }
        if (repeticiones == 0) {
            respuesta = "El número buscado no está grabado";
        } else {
            respuesta = "El número buscado aparece " + repeticiones + " veces";
        }
        return respuesta;
    }

    static void fin() {
        System.out.println("\n\tFin del programa.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcionElegida = 0;

        do {
            opcionElegida = menu(sc);
            switch (opcionElegida) {
                case 1:
                    crearFichero();
                    break;
                case 2:
                    listarFichero();
                    break;
                case 3:
                    if (!buscarNumero(sc)) {
                        System.out.println("El número buscado NO está grabado");
                    } else {
                        System.out.println("El número buscado está grabado");
                    }
                    break;
                case 4:
                    System.out.println(buscarNumeroYContar(sc));
                    break;
                case 5:
                    fin();
                    break;
            }

        } while (opcionElegida != 5);
    }
}