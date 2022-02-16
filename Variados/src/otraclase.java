import java.util.Scanner;
public class otraclase {
    static void contadorvocal(String frase, char vocal, int contador) {
        for(int i =0;i<frase.length();i++){
            if(vocal==frase.charAt(i)){
                contador++;
            }
        }
        System.out.println(contador+vocal);
        return;
    }
    public static void main (String [] args){
        int contador =0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el tesxto que quieres analizar: ");
        String frase = sc.nextLine();
        System.out.print("\nIntrouce la vocal que desea buscar: ");
        char vocal;
        vocal = sc.next().charAt(0);
        contadorvocal(frase, vocal, contador);

    }
}
