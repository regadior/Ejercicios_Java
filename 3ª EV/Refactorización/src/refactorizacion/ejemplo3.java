package refactorizacion;

public class ejemplo3 {
	
	public static void main(String[] args) {
		String cosas="lápiz, goma, hojas, carpetas"; //Extract Constant
		System.out.println(cosas);
		
		String otras_cosas="lápiz, goma, hojas, carpetas";
		System.out.println(otras_cosas);
	}
}