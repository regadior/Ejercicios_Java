/*Crea una aplicación que nos pida un día de la semana y 
que nos diga si es un dia laboral o no. Usa un switch para ello. */


import java.util.Scanner;
public class switch1 {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int repetir =1;
      System.out.println("introduce el nombre de el dia para ver si es laboral: ");
      String dia = sc.nextLine();
      switch (dia) {
        case "Lunes":
        case "Martes":
        case "Miercoles":
        case "Jueves":
        case "Viernes":
          System.out.println("Es un dia laboral");
          break;
        case "Sabado":
        case "Domingo":
        System.out.println("No es un dia laboral");
        break;
        default:
        System.out.println("Introsuce un dia valido de la semana con la priemra vocal en mayuscula");
          break;
      }
  }  
}
