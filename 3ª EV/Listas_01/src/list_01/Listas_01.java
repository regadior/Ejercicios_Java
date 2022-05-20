package list_01;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Listas_01{
	static int menu(Scanner e) {
		int op=0;
		System.out.println("\n\tMENU\n\t====");
		System.out.println("1.- Crear lista");
		System.out.println("2.- Ordenar lista");
		System.out.println("3.- Visualizar lista");
		System.out.println("4.- Fin");
		do {
			try {
				System.out.println("\n\tTeclee opción (1-4): ");
				op=e.nextInt();
			}catch(NumberFormatException nfe) {
				op=Integer.MIN_VALUE;
			}
		}while(op<1||op>4);
		e.nextLine();
		return op;
	}
	static void crear (Scanner e, List<String> l){
		String p=null;
		System.out.println("\n\tCREAR LISTA\n\t==========");
		System.out.println("Teclee palabra (fin para terminar): ");
		p=e.nextLine();
		while(!p.equalsIgnoreCase("fin")) {
			l.add(p);
			System.out.println("Teclee palabra (fin para terminar): ");
			p=e.nextLine();
		}
	}
	static void ordenar (List <String> l){
		System.out.println("\n\tORDENAR LISTA\n\t=============");
		String aux=null;
		if (l.size()>1) { 
			for (int i=0;i<l.size()-1;i++)
				for (int j=i+1;j< l.size();j++)
					if (l.get(i).compareToIgnoreCase(l.get(j))>0) {
						//si fuera de la z a la a es menor que cero.
						aux = l.get(i);
						l.set(i,l.get(j));
						l.set(j, aux);
					}
		}
		else
			System.out.println("La lista debe tener registros para ser ordenada.");
	}
	static void ordenarSort(List <String> l) {
		System.out.println("\n\tOrdenando lista.....\n");
		//Collections.sort(l,Collections.reverseOrder());
		Collections.sort(l);
		System.out.println("\n\tLista ordenada.....\n");
	}
	static List <String> visualizar (List <String> l){
		System.out.println("\n\tVISUALIZAR LISTA\n\t===============");
		for(int i=0;i<l.size();i++)
			System.out.println(l.get(i));
		System.out.println("\n\n");
		System.out.println("\n\n"+l);
		return l;
	}
	static void fin() {
		System.out.println("\n\tFIN DEL PROGRAMA\n\t================");
	}
	public static void main(String[]args) {
		List <String> lista= new ArrayList<String>();
		Scanner e= new Scanner(System.in);
		int op=0;
		do {
			op=menu(e);
			switch(op) {
			case 1:
				crear(e,lista);
				break;
			case 2:
				ordenar(lista);
				break;
			case 3:
				visualizar(lista);
				break;
			default:
				fin();
			}
		}while(op!=4);
	}
}