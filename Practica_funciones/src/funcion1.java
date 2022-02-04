import java.util.Scanner;
public class funcion1 {

    public static String saludo(String nombre) {
        String saludo= "Buenos dias "+nombre;
        return saludo;
    }
    public static String error(String nombre) {
        System.out.print("Error");
        return nombre;
    }

    public static void verificar(String nombre, String contraseña ) {
        String validuser = "Luis";
        String validpass = "1234";
            
        if(validuser.equals(nombre) && validpass.equals(contraseña)){
            System.out.println(saludo(nombre));
            return;
        }
        System.out.print(error("Señor "+nombre));
    }

    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         System.out.print("Introduce tu usuario: ");
         String nombre = sc.nextLine();
         System.out.print("Intorduce tu contraseña numerica: ");
         String contraseña = sc.nextLine();

        verificar(nombre,contraseña);
    }
}