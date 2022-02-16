
/*Crea una aplicación que nos calcule el área de un circulo, cuadrado o triangulo. 
Pediremos que figura queremos calcular su área y según lo introducido pedirá los valores necesarios para calcular el área. 
Crea un método por cada figura para calcular cada área, este devolverá un número real. 
Muestra el resultado por pantalla

Aquí te mostramos que necesita cada figura:

Circulo: (radio^2)*PI
Triangulo: (base * altura) / 2 */

import java.util.Scanner;
public class metodo_area {
    public static double areaCirculo (double radio){
        System.out.println(Math.pow(radio, 2)*Math.PI);
        return radio;
    }
    public static double areaTriangulo(double base, double altura) {
        System.out.println((base*altura)/2);
        return base;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String eleccion="";
        System.out.print("Introduce el nombre de la figura a la que le quieres calcular el area:  ");
        eleccion = sc.nextLine();
        switch(eleccion){
            case "circulo":
            System.out.print("Introduce el radio de el circulo: ");
            double radio = sc.nextDouble(); 
            areaCirculo(radio);
            break;
            case "triangulo":
            System.out.print("Introduce la base de el trinagulo: ");
            double base = sc.nextDouble();
            System.out.print("Introduce la altura de el trinagulo: ");
            double altura = sc.nextDouble();
            areaTriangulo(base, altura);
            break;
        }
    }
}
