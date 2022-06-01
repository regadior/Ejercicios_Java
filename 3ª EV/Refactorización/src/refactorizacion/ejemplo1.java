package refactorizacion;
/**
 * 
 * @author DA-1
 *
 */
public class ejemplo1 {
	/**
	 * suma
	 * Método al que se le pasan 2 números enteros y devuelve la suma.
	 * @param x
	 * @param y
	 * @return
	 */
	public static int suma(int x, int y) {
		return x+y;
	}
	
	public static void main(String[] args) {
		int x=5, y=3, z=5, total;
		total=suma(x,y);
		imprime(total);
		total=suma(x,z);
		imprime(total);
		total=suma(y,z);
		imprime(total);
	}
	/**
	 * imprime
	 * imprime por pantalla la cantidad total
	 * @param total, numero entero
	 */
	private static void imprime(int total) {
		System.out.println("La suma es: "+total);
	}

}
