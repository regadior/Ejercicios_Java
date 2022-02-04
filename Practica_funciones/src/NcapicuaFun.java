import java.util.Scanner;
public class NcapicuaFun {
    public static void comprobador( int capi) {
        int aux = capi;
        int inverso = 0;
        int sacacifra = 0;
        while(aux!=0){
            sacacifra= aux % 10;
            inverso = inverso * 10 + sacacifra;
            aux = aux / 10;
        }
        if(inverso == capi){
            System.out.println("El numero es capicua:\n"+capi+" = "+inverso);
        }else{
            System.out.println("El numero no es capicua\n"+capi+" = "+inverso);
        }
        return;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Escribe un numero para comprobar si es capicua: ");
         int capi = sc.nextInt();
        comprobador(capi);
       

    }
}
