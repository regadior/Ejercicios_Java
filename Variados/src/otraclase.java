import java.util.Scanner;
public class otraclase {
    public static void contadorvocal(String frase, char vocal) {
        int contador=0;
        for(int i =0;i<frase.length();i++){
            if(vocal==frase.charAt(i)){
                contador++;
            }
        }
        System.out.println("Hay un total de "+contador+" / "+vocal);
        return;
    }
    public static void main (String [] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el tesxto que quieres analizar: ");
        String frase = sc.nextLine();
        System.out.print("\nIntrouce la vocal que desea buscar: ");
        char vocal;
        vocal = sc.next().charAt(0);
        contadorvocal(frase,vocal);
        

    }
}
