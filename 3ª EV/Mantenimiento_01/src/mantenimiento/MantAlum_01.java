package mantenimiento;
import java.io.IOException;
import java.io.RandomAccessFile;
public class MantAlum_01{
	static final String ruta="D:\\Ejercicios_Java\\3ª EV\\textos\\";
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
			System.out.println("A.- Aprobados.");
			System.out.println("S.- Suspensos.");
			System.out.println("V.- Volver a Menú. ");
			break;
		}
		System.out.print("\n\n\tTeclee opción:  ");
		String op = t.leerString();
		return op;
	}
	public static void main(String []args) throws IOException{
		MantAlum_01 ma= new MantAlum_01(); //instancio la propia clase para moverme por los métodos de la propia clase ahorrándome los static
		//evitando conflictos con los nombres de otras clases ya que especificas qué clase es.
		String opcion;
		boolean fin=false;
		RandomAccessFile fs=new RandomAccessFile (ruta+"alumnos.dat","rw"); // si lo abres de rw y no existe cuando lo lees lo crea
		fs.close(); // así te aseguras con certeza de que el fichero existe.-
		while (!fin) {
			opcion = ma.menu(0);
			if(opcion.equalsIgnoreCase("A")) ma.altas();
			if(opcion.equalsIgnoreCase("B")) ma.bajas();
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
				fin=true; //solamente está a verdadero cuando pulso f y salgo del while.
			}
		}
	}
	void altas()throws IOException{
		Alumno av = new Alumno (0," ",0,0,' '),a = new Alumno();
		Teclado t = new Teclado();
		int numero;
		String nombre;
		float nota1,nota2;
		RandomAccessFile fich = new RandomAccessFile(ruta+"alumnos.dat","rw");
		System.out.println("\n\tALTAS\n\t");
		do {
			System.out.print("Número (0 = Fin) ");
			numero= t.leerInt();
		}while(numero==Integer.MIN_VALUE);
		while(numero!=0) { // si no quieres salir no tecleas cero.
			fich.seek(numero * av.tamano());
			a.leerDeArchivo(fich);
			if(a.getNumero()!=0)
				System.out.println("\n\tEl registro ya existe.....\n");
			else {
				do {
					System.out.print("Nombre.....");
					nombre=t.leerString();
				}while(nombre.length()>25);
				do {
					System.out.print("Nota1.....");
					nota1=t.leerFloat();
				}while(nota1==Float.MIN_VALUE ||nota1<1||nota1>10);
				do {
					System.out.print("Nota2.....");
					nota2=t.leerFloat();
				}while(nota2<1||nota2>10);
				a= new Alumno(numero,nombre,nota1,nota2,' ');
				if(numero * a.tamano()>fich.length())
					fich.seek(fich.length()); // como queda mucho recorrido te pones en la posicion "final"
				while(numero*a.tamano()>fich.length()) //mientras la dirección sea mayor que la longitud
					av.grabarEnArchivo(fich); //como no puede haber espacios se crean registros en blanco (av es el archivo vacío)
				fich.seek(numero * a.tamano()); // ya se dejó de cumplir el while por lo que ya estoy posicionado.
				a.grabarEnArchivo(fich);
			}
			a=new Alumno();
			do {
				System.out.print("Número (0 = Fin) ");
				numero= t.leerInt();
			}while(numero==Integer.MIN_VALUE);
		}
		fich.close();
	}
	void bajas() throws IOException{
		Alumno a = new Alumno (0," ",0,0,' ');
		Teclado t = new Teclado();
		int numBus=0;
		char confirmar;
		RandomAccessFile fich = new RandomAccessFile(ruta+"alumnos.dat","rw");
		System.out.println("\n\tBAJAS\n\t");
		do {
			System.out.print("\nTeclee número del alumno ");
			numBus= t.leerInt();
		}while(numBus==Integer.MIN_VALUE);
		fich.seek(numBus *a.tamano());
		a.leerDeArchivo(fich);
		if(a.getNumero()==0)
			System.out.println("\n\tEl alumno con el número "+numBus+" no existe.....\n");
		else {
			a.mostrarDatos(2);
			do {
				System.out.println("\n\tDesea borrar el registro (s/n)....\n");
				confirmar = Character.toLowerCase(t.leerChar());
			}while (confirmar!='s' || confirmar!='n');
			if (confirmar == 's') {
				a= new Alumno(0," ",0,0,' ');
				fich.seek(numBus *a.tamano());
				a.grabarEnArchivo(fich);
				System.out.println("\n\t Registro borrado correctamente.....");
			}
		}
		fich.close();
		System.out.println("\n Teclee <Intro> para continuar.");
		System.in.read();
		t.leerSalto();	
	}
	void modificaciones() throws IOException{
		Alumno a = new Alumno (0," ",0,0,' ');
		Teclado t = new Teclado();
		int numBus=0,cm=0;
		char otro;
		String nombreN;
		float nota1N,nota2N;
		RandomAccessFile fich = new RandomAccessFile(ruta+"alumnos.dat","rw");
		do {
			System.out.println("\n\tMODIFICACIONES\n\t");
			do {
				System.out.print("\nTeclee número del alumno ");
				numBus= t.leerInt();
			}while(numBus==Integer.MIN_VALUE);
			fich.seek(numBus * a.tamano());
			a.leerDeArchivo(fich);
			if(a.getNumero()==0)
				System.out.println("\n\tEl alumno con el número "+numBus+" no existe.....\n");
			else {
				nombreN=a.getNombre();
				nota1N=a.getNota1();
				nota2N=a.getNota2();
				a.mostrarDatos(1);
				do {
					do {
						System.out.println("Teclee campo a modifcar (s/n)");
						cm=t.leerInt();
					}while(cm<1||cm>3);
					switch(cm){
					case 1:
						do {
							System.out.println("Teclee el nuevo nombre");
							nombreN=t.leerString();
						}while (nombreN.length()>25);
						break;
					case 2:
						do {
							System.out.println("Teclee primera nota");
							nota1N=t.leerFloat();
						}while (nota1N<1||nota1N>10);
						break;
					default:
						do {
							System.out.println("Teclee segunda nota");
							nota2N=t.leerFloat();
						}while (nota2N<1||nota2N>10);
						break;
					}
					do {
						System.out.println("Otro campo a modificar (s/n)");
						otro=Character.toLowerCase(t.leerChar());
					}while(otro!='s' && otro!='n');
				}while(otro=='s');
				do {
					System.out.println("Confirmar modificaciones (s/n)");
					otro=Character.toLowerCase(t.leerChar());
				}while(otro!='s' && otro!='n');
				if(otro=='s') {
					a= new Alumno(numBus,nombreN,nota1N,nota2N,' ');
					fich.seek(numBus*a.tamano());
					a.grabarEnArchivo(fich);
				}
			}
			do {
				System.out.println("Modificar otro alumno (s/n)");
				otro=Character.toLowerCase(t.leerChar());
			}while(otro!='s' && otro!='n');
		}while(otro=='s');
		fich.close();
	}
	void consultas() throws IOException{
		Alumno a = new Alumno (0," ",0,0,' ');
		Teclado t = new Teclado();
		int numBus=0;
		System.out.println("\n\tCONSULTAS\n\t");
		RandomAccessFile fich = new RandomAccessFile(ruta+"alumnos.dat","r");	
		do {
			System.out.print("\nTeclee número del alumno ");
			numBus= t.leerInt();
		}while(numBus==Integer.MIN_VALUE);
		fich.seek(numBus * a.tamano());
		a.leerDeArchivo(fich);
		if(a.getNumero()!=0)
			a.mostrarDatos(2);
		else
			System.out.println("\nNo existe ningún alumno con el número: "+numBus);
		fich.close();
		System.out.println("\n Teclee <Intro> para continuar.");

		System.in.read();
		t.leerSalto();
	}
	void listados(int tl) throws IOException{
		Alumno a = new Alumno (0," ",0,0,' ');
		Teclado t = new Teclado();
		boolean fin =false;
		final int LINEAS= 5; //valor 5 de nombre LINEAS por convenio es mayuscula.
		int ca=0,cs=0,cp=0,cl=LINEAS+1 ; //ca cont aprobados cp cont páginas cs cont suspensos y cl cont líneas vale 6 para saltar
		RandomAccessFile fich = new RandomAccessFile(ruta+"alumnos.dat","r");
		do {
			while(!fin && cl<LINEAS) {
				if(a.getNumero() != 0) {
					switch(tl) {
					case 0:
						if(a.getApto()=='S') {
							a.mostrarDatos(3);						
							ca++;
						}else {
							a.mostrarDatos(0);
							cs++;
						}
						cl++;
						break;
					case 1:
						if(a.getApto()=='S') {
							a.mostrarDatos(0);
							cl++;
						}
						break;
					case 2:
						if(a.getApto()=='N') {
							a.mostrarDatos(0);
							cl++;
						}
						break;
					}
				}
				fin=a.leerDeArchivo(fich);
			}
			if(cl==LINEAS) {
				System.out.println("\n\t Pulse <Intro> para continuar");
				t.leerSalto();
			}
			if(!fin) {
				System.out.println("\t\tLISTADO\t\tPag.: "+ ++cp+"\n\t\t=======\n");
				System.out.println("Número\tNombre\tNota1\t\tNota2\tApto");
				System.out.println("-------------------------------------------------------------");
				cl=0;
			}	

		}while(!fin);
		fich.close();
		if (tl==0) {
			System.out.println("Total de alumnos aprobados: "+ca);
			System.out.println("Total de alumnos suspensos: "+cs);
		}		
	}
	void fin() {
		System.out.println("\n\tFIN DEL PROGRAMA\n\t================");
	}
}