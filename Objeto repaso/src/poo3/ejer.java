package poo3;

import java.util.Scanner;

public class ejer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        persona[] p = new persona[5];
        for (int i = 0; i < p.length; i++) {
            System.out.println("Introduce el nombre: ");
            p[i].setNombre(sc.nextLine());
            System.out.println("Introduce la edad: ");
            p[i].setEdad(sc.nextInt());

        }
    }
}
