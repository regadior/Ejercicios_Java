package refactorizacion;

public class ejemplo3 {
	
	public static void main(String[] args) {
		String cosas="l�piz, goma, hojas, carpetas"; //Extract Constant
		System.out.println(cosas);
		
		String otras_cosas="l�piz, goma, hojas, carpetas";
		System.out.println(otras_cosas);
	}
}