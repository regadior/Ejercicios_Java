import java.util.Scanner;
import java.text.DecimalFormat; /*propio de las máscaras*/
public class mascara {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DecimalFormat numero = new DecimalFormat("#,###.##"); /*Almohadilla deja espacios en blanco, y si pusiéramos directamente 0 lo rellena a 0*/
        double n=0;
        System.out.print("Teclea número? ");
        n = sc.nextDouble();
        System.out.println("\nNúmero sin máscara => "+n);
        System.out.println("\nNúmero con máscara => "+numero.format(n));

    }
}

