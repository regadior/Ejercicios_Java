package listas;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
//una lista la puedo trabajar como una tabla
//arrayList, array de string
// la lista es un array din�mico.
// aqu� no hace falta dar tama�o inicial, va aumentando 
// a medida que aumentamos los registros.
// si borras un elemento del medio no pasa nada.
// puedes mostrar las listas con un for, un while con iterante o visualizar de golpe
// puedes coger sublistas.
public class Listas{
	public static void main (String[]args) {
		Scanner e= new Scanner(System.in);
		String p=null, pBus=null;
		int ini=0,fin=0;
		/************************definici�n de la lista********************************/
		List<String> lista= new ArrayList<String>(); //se pone el <String> para que no pille el arrayList gen�rico.
		//entre las llaves se pone el tipo de dato o incluso objetos.
		/************************llenado de la lista********************************/
		System.out.println("Teclee palabra (fin para terminar): ");
		p=e.nextLine();
		while(!p.equalsIgnoreCase("fin")) {
			lista.add(p);
			System.out.println("Teclee palabra (fin para terminar): ");
			p=e.nextLine();
		}
		System.out.println("Lista creada => "+lista); //entre corchetes
		System.out.println("\nTama�o de la lista => "+lista.size()+"\n\n");
		/************************visualizado de la lista********************************/
		//con un �ndice
		for(int i=0;i< lista.size();i++)
			System.out.println(lista.get(i)); //get para sacarlos
		System.out.println("\n\n");
		//con iterator
		Iterator<String> it=lista.iterator();
		while(it.hasNext()) //m�todo booleano que devuelve verdadero mientras en el siguiente elemento encuentre algo.
			System.out.println(it.next());
		/************************modificaci�n de la lista********************************/
		System.out.println("\nNueva palabra: ");
		p=e.nextLine();
		System.out.println("\nPosición: ");
		ini=e.nextInt();
		lista.set(ini, p);
		e.nextLine();
		visualizarLista(lista);
		visualizarListaIterator(lista);
		/********************borrar elemento de la lista********************************/
		System.out.println("Posición del elemento a borrar?: ");
		ini=e.nextInt();
		e.nextLine(); 
		lista.remove(ini); //borrar� el primero que encuentre con ese valor.
		System.out.println("Palabra a borrar: ");
		p=e.nextLine();
		e.nextLine(); 
		lista.remove(p);
		System.out.println("\nVisualizar lista para ver lo borrado.");
		visualizarLista(lista);
		/********************visualizar una sublista********************************/
		System.out.println("Inicio de la lista: ");
		ini=e.nextInt();
		System.out.println("Final de la lista: ");
		fin=e.nextInt();
		if(fin>lista.size())
			fin=lista.size(); //si te pasas te pones al final, pero puede estar fuera de rango como cualquier array corriente.
		e.nextLine(); //la posici�n de inicio es incluida y la de fin es excluida
		System.out.println("\n"+lista.subList(ini, fin));
		/********************Buscar un elemento en lista********************************/
		System.out.println("Palabra a buscar: ");
		pBus=e.nextLine();
		if(lista.contains(pBus))
			System.out.println("\nLa palabra SI está en la lista");
		else			
			System.out.println("\nLa palabra NO está en la lista");
		/********************Comprobar si la lista est� llena********************************/
		if(lista.isEmpty())
			System.out.println("La lista SI está vacía (primera).");
		else
			System.out.println("La lista NO está vacía (primera).");
		lista.clear();//borramos todos los elementos de la lista if(lista.isEmpty()).
		if(lista.isEmpty())
			System.out.println("La lista SI está vacía (segunda).");
		else
			System.out.println("La lista NO está vacía (segunda).");
		e.close();
	}
	static void visualizarLista(List<String> lista) {
		System.out.println("\n\tCon �ndice.\n");
		for(int i=0;i<lista.size();i++)
			System.out.println(lista.get(i));
		System.out.println("\n\n");
	}
	static void visualizarListaIterator(List<String> lista) {
		System.out.println("\n\tCon iterator.\n");
		Iterator<String> it=lista.iterator();
		while(it.hasNext()) //m�todo booleano que devuelve verdadero mientras en el siguiente elemento encuentre algo.
			System.out.println(it.next());
	}

}

