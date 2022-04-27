package mant_02;
import java.io.IOException;
import java.io.RandomAccessFile;

import mantenimiento.Alumno;
import mantenimiento.Teclado;
public class MantArt_01{
	static final String ruta="C:\\Ejercicios_Java\\Binarios aleatorios\\src\\mant_02\\";
	String menu(int tm) throws IOException{
		Teclado t = new Teclado();
		switch(tm){
		case 0:
			System.out.println("\n\tMENU\n\t====\n");
			System.out.println("A.- Altas.");
			System.out.println("B.- Bajas.");
			System.out.println("M.- Modificaciones.");
			System.out.println("C.- Consultas. ");
			System.out.println("L.- Listados.");
			System.out.println("F.- Fin.");
			break;
		case 1:
			System.out.println("\n\tLISTADOS\n\t========\n");
			System.out.println("G.- General.");
			System.out.println();
			System.out.println();
			System.out.println("V.- Volver a Men�. ");
			break;
		}
		System.out.print("\n\n\tTeclee opci�n:  ");
		String op = t.leerString();
		return op;
	}
	public static void main(String []args) {
		MantArt_01 ma= new MantArt_01(); //instancio la propia clase para moverme por los m�todos de la propia clase ahorr�ndome los static
		//evitando conflictos con los nombres de otras clases ya que especificas qu� clase es.
		String opcion;
		boolean fin=false;
		RandomAccessFile fs=new RandomAccessFile (ruta+"articulos.dat","rw"); // si lo abres de rw y no existe cuando lo lees lo crea
		fs.close(); // as� te aseguras con certeza de que el fichero existe.-
		while (!fin) {
			opcion = ma.menu(0);
			if(opcion.equalsIgnoreCase("A")) ma.altas();
			//if(opcion.equalsIgnoreCase("B")) ma.bajas();
			if(opcion.equalsIgnoreCase("M")) ma.modificaciones();
			if(opcion.equalsIgnoreCase("C")) ma.consultas();
			if(opcion.equalsIgnoreCase("L")) {
				do {
					opcion =ma.menu(1).toUpperCase();
					switch(opcion) {
					case "G":
						ma.listados(0);
						break;
					case "A":
						ma.listados(1);
						break;
					case "S":
						ma.listados(2);
						break;
					}				
				}while(!opcion.equals("V"));
			}
			if(opcion.equalsIgnoreCase("F")) {
				ma.fin();
				fin=true; //solamente est� a verdadero cuando pulso f y salgo del while.
			}
		}
	}
	void altas()throws IOException{
		Articulo av = new Articulo (0," ",0,0,0,0,' '),a = new Articulo();
		Teclado t = new Teclado();
		int codigo=0;
		String denominacion=null;
		double stockAct= 0, stockMin= 0,stockMax = 0;
		float precio=0;
		char otro=' ';
		RandomAccessFile fich = new RandomAccessFile(ruta+"articulos.dat","rw");
		System.out.println("\n\tALTAS\n\t");
		do {
			System.out.print("C�digo (0 = Fin) ");
			codigo= t.leerInt();
		}while(codigo==Integer.MIN_VALUE);
		while(codigo!=0) { // si no quieres salir no tecleas cero.
			fich.seek(codigo * av.tamano());
			a.leerDeArchivo(fich);
			if(a.getCodigo()!=0)
				System.out.println("\n\tEl registro ya existe.....\n");
			else {
				do {
					System.out.print("Denominaci�n.....");
					denominacion=t.leerString();
				}while(denominacion.length()>20);
				do {
					System.out.print("Stock actual.....");
					stockAct=t.leerDouble();
				}while(stockAct==Double.MIN_VALUE);
				do {
					do {
						System.out.print("Stock m�nimo.....");
						stockAct=t.leerDouble();
					}while(stockMin==Double.MIN_VALUE);
					do {
						System.out.print("Stock m�ximo.....");
						stockMax=t.leerDouble();
					}while(stockMax==Double.MIN_VALUE);
				}while(stockMax<stockMin);
				do {
					System.out.print("Precio.....");
					precio=t.leerFloat();
				}while(precio<0);					
				do {
					System.out.println("Confirmar alta (s/n)");
					otro=Character.toLowerCase(t.leerChar());
				}while(otro!='s' && otro!='n');
				if(otro=='s') {
					a= new Articulo(codigo,denominacion,stockAct,stockMin,stockMax,precio,' ');
					if(codigo * a.tamano()>fich.length())
						fich.seek(fich.length()); // como queda mucho recorrido te pones en la posicion "final"
					while(codigo*a.tamano()>fich.length()) //mientras la direcci�n sea mayor que la longitud
						av.grabarEnArchivo(fich); //como no puede haber espacios se crean registros en blanco (av es el archivo vac�o)
					fich.seek(codigo * a.tamano()); // ya se dej� de cumplir el while por lo que ya estoy posicionado.
					a.grabarEnArchivo(fich);
				}
			}				
			do {
				System.out.print("C�digo (0 = Fin) ");
				codigo= t.leerInt();
			}while(codigo==Integer.MIN_VALUE);	
		}
		fich.close();		
	}
	void consultas() throws IOException{
		Articulo a = new Articulo (0," ",0,0,0,0,' ');
		Teclado t = new Teclado();
		int codBus=0;
		System.out.println("\n\tCONSULTAS\n\t");
		RandomAccessFile fich = new RandomAccessFile(ruta+"articulos.dat","r");	
		do {
			System.out.print("\nTeclee c�digo del art�culo ");
			codBus= t.leerInt();
		}while(codBus==Integer.MIN_VALUE);
		fich.seek(codBus * a.tamano());
		a.leerDeArchivo(fich);
		if(a.getCodigo()!=0)
			a.mostrarDatos(2);
		else
			System.out.println("\nNo existe ning�n art�culo con el n�mero: "+codBus);
		fich.close();
		System.out.println("\n Teclee <Intro> para continuar.");
		System.in.read();
		t.leerSalto();
	}

	void fin () {
		System.out.println("\nFIN DEL PROGRAMA\n=================");
	}
}