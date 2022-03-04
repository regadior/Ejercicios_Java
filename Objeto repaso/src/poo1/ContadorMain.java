package poo1;
import java.util.Scanner;
public class ContadorMain {
    /*Crea una clase llamada Contador que contenga un único 
    atributo entero llamado cont.
    La clase tendrá los siguientes constructores:
    Constructor por defecto
    Constructor con parámetros para inicializar el contador con 
    un valor no negativo. Si el valor inicial que se recibe es negativo el 
    contador tomará el valor cero como valor inicial.
    Constructor copia.
    Además de los métodos getter y setter, la clase contendrá los métodos:
    incrementar: incrementa el contador en una unidad.
    decrementar: decrementa el contador en una unidad. El contador 
    nunca podrá tener un valor negativo. Si al decrementar se alcanza un 
    valor negativo el contador toma el valor cero.
    Una vez creada la clase, escribe un método main para probar la clase. */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Contador contador1 = new Contador();
        int valor=0;
        System.out.print("Escribe el valor de inicio del contador: ");
        valor=sc.nextInt();
        contador1.setCont(valor);
        System.out.println(contador1.getCont());
    }  
}

