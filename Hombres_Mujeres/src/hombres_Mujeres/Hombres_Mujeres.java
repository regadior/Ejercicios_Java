package hombres_Mujeres;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
public class Hombres_Mujeres {
	static final String ruta="C:\\Java-practica\\Hombres_Mujeres\\src\\hombres_Mujeres\\";
	static int menu(Scanner sc) {
		int opcion=0;
		System.out.println("1.-Altas Personas");
		System.out.println("2.-Ficheros hombres y mujeres");
		System.out.println("3.-Personas con  mayor y menor edad");
		System.out.println("4.-Personas con edad por encima de la media");
		System.out.println("5.-Visualizar personas");
		System.out.println("6.-Visualizar hombres");
		System.out.println("7.-Visualizar mujeres");
		System.out.println("8.-FIN");
		do{
			System.out.println("introduzca opcion");
			opcion=sc.nextInt();
		}while(opcion<1|opcion>8);/*mientras cond verdadera se repite bucle*/
		return opcion;
	}
	static void altasPersonas (Scanner sc) {
		String nombre="";
		String edad=null; //me da eror como entero nose pq
		char sexo=' ';
		boolean correcto=true;
		System.out.println("ALTAS PERSONAS");
		try {
			FileWriter esc= new FileWriter(ruta+"Personas.txt",true); //queremos ir a�adiendo sobre las anteriores personas registradas
			BufferedWriter besc=new BufferedWriter(esc);
			do {
				System.out.println("introduzca nombre");
				nombre=sc.nextLine();
			}while(nombre.length()>15);

			while(!nombre.equalsIgnoreCase("fin")){

				do {
					correcto = true;
					System.out.println("Introduzca edad");
					edad=sc.next();
					try {
						Integer.parseInt(edad);
					}catch(NumberFormatException nfe) {correcto=false;}
				}while(!correcto);

				do {
					System.out.println("Introduzca sexo");
					sexo= Character.toUpperCase((char)System.in.read());
					while(System.in.read()!='\n');
				}while (sexo!='V' && sexo!='M');


				besc.write(nombre);
				besc.newLine();

				besc.write(edad);
				besc.newLine();

				besc.write(sexo);
				besc.newLine();
				sc.nextLine();
				do {
					System.out.println("introduzca nombre(fin para acabar)");
					nombre=sc.nextLine();
				}while(nombre.length()>15);

			}//fin while m�s externo

			besc.close();
		}catch(IOException ioe) {}
	}

	static void hombresMujeres(Scanner entrada) {

		String nombre="";
		//char sexo=' ';//como char no me lo lee
		String sexo="";
		String edad=""; 
		System.out.println("FICHEROS HOMBRES Y MUJERES");
		try {
			FileReader red = new FileReader(ruta+"Personas.txt");
			BufferedReader bred= new BufferedReader(red);
			//instancio ficheros hombres y mujeres
			FileWriter fescH = new FileWriter(ruta+"hombres.txt",false);
			BufferedWriter escH=new BufferedWriter(fescH);
			FileWriter fescM = new FileWriter(ruta +"mujeres.txt",false);
			BufferedWriter escM =new BufferedWriter(fescM);
			nombre=bred.readLine();
			while(nombre!=null) {
				edad=bred.readLine();
				sexo=bred.readLine();
				if(sexo.equalsIgnoreCase("V")) {//escribir fichero hombres
					escH.write(nombre);
					escH.newLine();
					escH.write(edad);
					escH.newLine();
					escH.write(sexo);
					escH.newLine();
				} //antes tenia else, lo cambi� por si
				if(sexo.equalsIgnoreCase("M")) {//mujerees
					escM.write(nombre);
					escM.newLine();
					escM.write(edad);
					escM.newLine();
					escM.write(sexo);
					escM.newLine();
				}
				nombre=bred.readLine();
			}
			bred.close();
			escH.close();
			escM.close();
		}catch(IOException ioe) {}
	}
	static void visualizar(int opcion) {//posible problema
		String fichero=null, nombre="",edad="";
		String sexo="";
		switch(opcion) {
		case 5:
			fichero=ruta+"Personas.txt";
			System.out.println("Visualizando Personas");
			break;
		case 6:
			fichero= ruta+"hombres.txt";
			System.out.println("Visualizando hombres");
			break;
		case 7:
			fichero=ruta+"mujeres.txt";
			System.out.println("Visualizando mujeres");
		}
		try {
			FileReader listado = new FileReader (fichero);
			BufferedReader bListado= new BufferedReader(listado);

			nombre=bListado.readLine();
			while(nombre!=null) {
				edad=bListado.readLine();
				sexo=bListado.readLine();
				//hemos le�do ya un registro
				System.out.println("Nombre\t"+nombre+"\tedad\t"+edad+"\tsexo\t"+sexo);
				nombre=bListado.readLine();
			}
			bListado.close();

		}catch(IOException ioe) {}
	}
	static void fin() {
		System.out.println("FIN PROGRAMA!");
	}
	static void mayorMenor() {
		String nombre="",sexo="",edad=null,pMayor=null,pMenor=null,sMayor="",sMenor="";
		int mayor=0;
		int menor=Integer.MAX_VALUE;
		try {
			FileReader MayMen = new FileReader(ruta+"Personas.txt");
			BufferedReader bMayMen = new BufferedReader(MayMen);

			nombre=bMayMen.readLine();
			while(nombre!=null){
				edad=bMayMen.readLine();
				sexo=bMayMen.readLine();
				//Hemos l�ido ya un registro
				if(Integer.parseInt(edad)>mayor) {
					//System.out.println("Estoy en mayor");
					mayor=Integer.parseInt(edad);
					pMayor=nombre;
					sMayor = sexo;
				}
				if(Integer.parseInt(edad)<menor) {
					menor=Integer.parseInt(edad);
					pMenor=nombre;
					sMenor=sexo;
				}
				nombre=bMayMen.readLine();			

			}
		}catch(IOException ioe) {}
		System.out.println("Personas con mayor edad=\t"+pMayor+"\tcon\t"+mayor+"\tde\tsexo"+sMayor);
		System.out.println("Personas con menor edad=\t"+pMenor+"\tcon\t"+menor+"\tde \tsexo"+sMenor);
	}
	static void edadMaymedia() {
		
		String nombre="",sexo="",edad=null; 
		int masMedia=0;
		System.out.println("Personas con mayor edad que la media\t"+media());
		try {
			FileReader mayMedia = new FileReader(ruta+"Personas.txt");
			BufferedReader bMayMedia = new BufferedReader(mayMedia);
			nombre=bMayMedia.readLine();
			while(nombre!=null) {
				edad=bMayMedia.readLine();
				sexo=bMayMedia.readLine();
				if(Integer.parseInt(edad)>media()) { 
					//A lo mejor estas variables no son necesarias, pero igualmente ser�a el motivo de error?
	
					System.out.println("Nombre\t"+nombre+"\tedad\t"+edad+"\tsexo\t"+sexo);
				}
				nombre=bMayMedia.readLine();
			}
		bMayMedia.close();
		}catch(IOException ioe) {}
	}

	static int media() {
		//conte registros en un m�todo aparte
		String nombre="",edad="",sexo="";
		int sumaEdades=0; //acumulador
		int media=0,cr = 0;
		try {
			FileReader media_red= new FileReader(ruta+"Personas.txt");
			BufferedReader bMedia_red = new BufferedReader(media_red);
			nombre=bMedia_red.readLine();
			while(nombre!=null) {
				edad=bMedia_red.readLine();
				sexo=bMedia_red.readLine();
				//l�imos ya un registro
				sumaEdades=sumaEdades+Integer.parseInt(edad);
				cr++;
				nombre=bMedia_red.readLine();
			}
			media=sumaEdades/cr;
			bMedia_red.close();
		}catch(IOException ioe) {}
		return media;
		
	}
	static int numRegistros() {
		String nombre="",edad="",sexo="";
		int nr=0; //num registros
		try {
			FileReader numreg = new FileReader(ruta+"Personas.txt");
			BufferedReader Bnumreg =new BufferedReader(numreg);
			nombre=Bnumreg.readLine();
			while(nombre!=null) {
				edad=Bnumreg.readLine();
				sexo=Bnumreg.readLine();
				nr++;
				nombre=Bnumreg.readLine();
			}
			Bnumreg.close();

		}catch(IOException ioe) {}
		return nr;
	}

	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		int opcion=0;
		do {
			opcion=menu(sc);
			switch(opcion) {
			case 1:
				altasPersonas(sc);
				break;
			case 2:
				hombresMujeres(sc);
				break;
			case 3:
				mayorMenor();
				break;
			case 4:
				edadMaymedia(); //error NumberFormatException
				break;
			case 5,6,7:
				visualizar(opcion); //error para hombres y mujeres
			break;
			//case 8:
				//modificarPersonas();
			default:
				fin();

			}

		}while(opcion!=8);

		/*media=>sumar edades/nregistros en un fichero de lectura*/
		
		
		/*A�adir opcion modificar personas*/
		/*a trav�s de un programa no podr�amos,entonces ingeniarlo, para poder hacerlo*/
		/*Array objetos=cRear clase persona
		 * El tama�o le damos seg�n el n�mero de registros,para ello crear un m�todo para num registros*/
		/*Suponemos que no hay dos nombres iguales.*/
		/*si no modificar persona, pues pedir si modificar otra persona, confirmar modificaciones*/
		/*abro fichero, seg�n leer cargamos objeto persona ...
		 * Cuando no quiero m�s registros, tenemos que pasar el vector al registro�?*/
	}
}