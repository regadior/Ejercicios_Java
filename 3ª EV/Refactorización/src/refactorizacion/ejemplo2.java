package refactorizacion;

public class ejemplo2 {

	public static String parImpar(int nume) {
		String c="";
		if(nume%2 == 0)
			c="Par";
		else
			c="Impar";
		return c;
	}
	/**
	 * 
	 * @param x
	 */
	private static void imprime(int x) {
		System.out.println("El número es: "+parImpar(x));
	}
	public static void main(String[] args) {
		int x=5, y=3, z=4;
		imprime(x);
		imprime(y);
		imprime(z);
	}

	

}