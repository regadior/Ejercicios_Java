//cuenta cuantas vocales hay en total (recorre el String con charAt).
import java.util.Scanner;
public class recorrerString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("introduce el texto que quieras leer: ");
        String texto = sc.nextLine();
        int contador=0;
        for(int i=0;i<texto.length();i++){
            if (texto.charAt(i)=='a'||
                texto.charAt(i)=='e' || 
                texto.charAt(i)=='i' || 
                texto.charAt(i)=='o' || 
                texto.charAt(i)=='u') {
                    contador++;
            }
        }
        System.out.println("Hay un total de "+contador+" Vocales");
    }
}
