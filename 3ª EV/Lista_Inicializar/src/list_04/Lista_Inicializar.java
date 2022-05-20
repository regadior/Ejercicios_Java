package list_04;
import java.util.List;
import java.util.Arrays;
public class Lista_Inicializar{
	public static void main(String []args) {
		List<String> listaS=Arrays.asList("mesa","silla","casa","silla","lámpara");
		System.out.println("La lista String es = "+listaS);
		List<Integer> listaN=Arrays.asList(23,56,2,8,103,4);
		System.out.println("La lista Integer es = "+listaN);
	}
}