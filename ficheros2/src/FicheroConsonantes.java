
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FicheroConsonantes {

	static int crearMenu() {
		int op = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\tMENÚ\n\t====");
		System.out.println("1.Crear fichero frase.");
		System.out.println("2.Crear ficheros vocales y consonantes.");
		System.out.println("3.Visualizar fichero frase.");
		System.out.println("4.Visualizar fichero vocales.");
		System.out.println("5.Visualizar fichero consonantes.");
		System.out.println("6.Fin.");
		do {
			System.out.print("\nTeclee opción(1/6): ");
			op = sc.nextInt();
		} while (op < 1 || op > 6);
		return op;
	}

	static void crearFicheroTxt() {
		System.out.println("\n\tCREAR FICHERO FRASE\n");
		int car=' ';
		try {
			FileWriter esc=new FileWriter("D:\\Java-practica\\ficheros2\\src\\Fichero1.txt"); //TODO aprender esto
			System.out.print("Teclear texto a grabar: ");
			while (car!='*') {
				esc.write(car);
				car=(char) System.in.read();
			}
			esc.close();
		} catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	
	static void crearFicheroVC() {
		System.out.println("\n\tCREAR FICHERO VOCALES Y CONSONANTES\n");
		int car=' ';
		System.out.println("\n\tCREANDO FICHEROS...\n\t");
		try {
			FileReader lector=new FileReader("D:\\Java-practica\\ficheros2\\src\\Fichero1.txt");
			FileWriter escritorVocales= new FileWriter("D:\\Java-practica\\ficheros2\\src\\Fichero1.txt");
			FileWriter escritorConsonantes=new FileWriter("D:\\Java-practica\\ficheros2\\src\\Fichero1.txt");
			car=lector.read();
			while(car!=-1) {
				if(Character.isLetter(car)) {
				switch (Character.toLowerCase((char)car)) {
				case 'a','e','i','o','u':
					escritorVocales.write(car);
					break;
				default:
					escritorConsonantes.write(car);
					break;
				}
				}
				car=lector.read();
			}
			lector.close();
			escritorConsonantes.flush();
			escritorConsonantes.close();
			escritorVocales.flush();
			escritorVocales.close();
		}catch(IOException io){
			System.out.println(io.getMessage());
		}
		System.out.println("\n\tFICHEROS CREADOS.\n\t");
		
			
	}
	
	static void visualizarFicheros(int opcionFichero) {
		
		int car=' ';
		String texto="";
		switch(opcionFichero) {
		case 3:
			try {
				System.out.println("\n\tVISUALIZAR FICHERO FRASE\n");
				FileReader lectorFrase=new FileReader("D:\\Java-practica\\ficheros2\\src\\Fichero1.txt");
				car=lectorFrase.read();
				while(car!=-1) {
					texto+=(char)car;
					car=lectorFrase.read();
				}
				System.out.println("La frase leida es: "+texto);
				lectorFrase.close();
			}catch(IOException io) {
				System.out.println(io.getMessage());
			}
			break;
		case 4:
			System.out.println("\n\tVISUALIZAR FICHERO VOCALES\n");
			try {
				FileReader lectorVocales=new FileReader("D:\\Java-practica\\ficheros2\\src\\Fichero1.txt");
				car=lectorVocales.read();
				while(car!=-1) {
					texto+=(char)car;
					car=lectorVocales.read();
				}
				System.out.println("Las vocales leidas son: "+texto);
				lectorVocales.close();
			}catch(IOException io) {
				System.out.println(io.getMessage());
			}
			break;
		case 5:
			System.out.println("\n\tVISUALIZAR FICHERO CONSONANTES\n");
			try {
				FileReader lectorConsonantes=new FileReader("D:\\Java-practica\\ficheros2\\src\\Fichero1.txt");
				car=lectorConsonantes.read();
				while(car!=-1) {
					texto+=(char)car;
					car=lectorConsonantes.read();
				}
				System.out.println("Las consonantes leidas son: "+texto);
				lectorConsonantes.close();
			}catch(IOException io) {
				System.out.println(io.getMessage());
			}
			break;
		}
	}
	
	static void fin() {
		System.out.println("\n\tFIN DEL PROGRAMA\n\t=================");
	}
	
	public static void main(String[] args) {
		int opcionElegida=0;
		do {
			opcionElegida = crearMenu();
			switch (opcionElegida) {
			case 1:
				crearFicheroTxt();
				break;
			case 2:
				crearFicheroVC();
				break;
			case 3:
				visualizarFicheros(opcionElegida);
				break;
			case 4:
				visualizarFicheros(opcionElegida);
				break;
			case 5:
				visualizarFicheros(opcionElegida);
				break;

			default:
				fin();
				break;
			}
		}while(opcionElegida!=6);
	}
}