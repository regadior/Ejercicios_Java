package mant_03;
import java.io.IOException;
import java.io.RandomAccessFile;
public class MantProv_01{
	static final String ruta="D:\\Ejercicios_Java\\3ª EV\\textos\\";
	String menu() throws IOException {
		Teclado t = new Teclado();
		String op=null;
		System.out.println("\n\tMENU\n\t====\n");
		System.out.println("A.- Altas.");
		System.out.println("B.- Bajas.");
		System.out.println("M.- Modificaciones.");
		System.out.println("C.- Consultas. ");
		System.out.println("L.- Listados.");
		System.out.println("F.- Fin.");
		do {
			System.out.print("\n\n\tTeclee opci�n: (A/B/M/C/L/F) ");
			op=t.leerString().toUpperCase();
		}while(op.indexOf("ABMCLF")==0);
		return op;
	}

	public static void main(String []args) throws IOException {
		MantProv_01 mp= new MantProv_01();
		String op;
		RandomAccessFile fich=new RandomAccessFile (ruta+"provincias.dat","rw"); // si lo abres de rw y no existe cuando lo lees lo crea
		fich.close(); // as� te aseguras con certeza de que el fichero existe.-
		do {
			op=mp.menu();
			switch(op) {
			case "A": mp.altas(); break;
			case "B": mp.bajas(); break;
			case "M": mp.modificaciones(); break;
			case "C": mp.consultas(); break;
			case "L": mp.listado(); break;
			default: mp.fin(); break;
			}
		}while(op!="F");

	}
	void altas()throws IOException{
		Provincia pv= new Provincia (0," ",0,0,' '),p = new Provincia();
		Teclado t = new Teclado();
		int numero=0,poblacion;
		String nombre=null;
		float superficie=0;
		char costa=' ',confirmar=' ';
		RandomAccessFile fich = new RandomAccessFile(ruta+"provincias.dat","rw");
		System.out.println("\n\tALTAS\n\t=====");
		do {
			System.out.print("N�mero (0 = Fin): ");
			numero= t.leerInt();
		}while(numero==Integer.MIN_VALUE);
		while(numero!=0) { // si no quieres salir no tecleas cero.
			fich.seek(numero * pv.tamano()); //me posiciono
			p.leerDeArchivo(fich); //leo el fichero
			if(p.getNumero()!=0)
				System.out.println("\n\tEl registro ya existe.....\n");
			else {
				do {
					System.out.print("Nombre.....: ");
					nombre=t.leerString();
				}while(nombre.length()>22);
				do {
					System.out.print("Superficie....(Km\u00B2): ");
					superficie=t.leerFloat();
				}while(superficie==Float.MIN_VALUE);
				do {
					System.out.print("Poblaci�n.....: ");
					poblacion=t.leerInt();
				}while(poblacion<0);	
				do {
					System.out.print("Costa.....(S/N): ");
					costa=Character.toUpperCase(t.leerChar());
				}while(costa!='S' && costa!='N');
				do {
					System.out.println("Confirmar alta (s/n): ");
					confirmar=Character.toLowerCase(t.leerChar());
				}while(confirmar!='s' && confirmar!='n');
				if(confirmar=='s') {
					p= new Provincia(numero,nombre,superficie,poblacion,costa);
					if(numero * p.tamano()>fich.length())
						fich.seek(fich.length()); // como queda mucho recorrido te pones en la posicion "final"
					while(numero*p.tamano()>fich.length()) //mientras la direcci�n sea mayor que la longitud
						pv.grabarEnArchivo(fich); //como no puede haber espacios se crean registros en blanco (av es el archivo vac�o)
					fich.seek(numero * p.tamano()); // ya se dej� de cumplir el while por lo que ya estoy posicionado.
					p.grabarEnArchivo(fich);
				}

				do {
					System.out.print("N�mero (0 = Fin): ");
					numero= t.leerInt();
				}while(numero==Integer.MIN_VALUE);
			}
		}
		fich.close();		
	}
	void consultas() throws IOException{
		Provincia pv= new Provincia (0," ",0,0,' ');
		Teclado t= new Teclado();
		int numBus=0;
		System.out.println("\n\tCONSULTAS\n\t=========");
		RandomAccessFile fich= new RandomAccessFile(ruta+"provincias.dat","r");
		do {
			System.out.println("Teclee n�mero a buscar (0 para finalizar): ");
			numBus=t.leerInt();
		}while(numBus==Integer.MIN_VALUE);
		fich.seek(numBus*pv.tamano());
		pv.leerDeArchivo(fich);
		if(pv.getNumero()!=0)
			pv.mostrarDatos(2);
		else
			System.out.println("\nNo existe ninguna provincia con el n�mero: "+numBus);
		fich.close();
		System.out.println("\n Teclee <Intro> para continuar.");
		t.leerSalto();
	}
	void bajas()throws IOException{
		Provincia p= new Provincia(0," ",0,0,' ');
		Teclado t= new Teclado();
		int numBus=0;
		char confirmar=' '; 
		System.out.println("\n\tBAJAS\n\t=====");
		RandomAccessFile fich= new RandomAccessFile(ruta+"provincias.dat","rw");
		do {
			System.out.println("Teclee n�mero de provincia: ");
			numBus=t.leerInt();
		}while(numBus==Integer.MIN_VALUE);
		fich.seek(numBus*p.tamano());
		p.leerDeArchivo(fich);
		if(p.getNumero()==0)
			System.out.println("La provincia con el n�mero "+numBus+" no existe en el fichero.");
		else {
			p.mostrarDatos(0);
			do {
				System.out.println("Desea confirmar el borrado: ");
				confirmar=Character.toLowerCase(t.leerChar());
			}while(confirmar!='s' && confirmar!='n');
			if(confirmar =='s')		 {	
				p= new Provincia(0,"",0,0,' ');
				fich.seek(numBus*p.tamano());
				p.grabarEnArchivo(fich);
				System.out.println("Registro borrado correctamente.......");
			}

			fich.close();
			System.out.println("\n Teclee <Intro> para continuar.");
			t.leerSalto();
		}
	}
	void modificaciones()throws IOException{
		Provincia p= new Provincia(0," ",0,0,' ');
		Teclado t= new Teclado();
		int numBus=0,poblacionN=0,cm=0;
		String nombreN=null;
		float superficieN=0;
		char costaN=' ',otro=' ';
		System.out.println("\n\tMODIFICACIONES\n\t==============");
		RandomAccessFile fich= new RandomAccessFile(ruta+"provincias.dat","rw");
		do {
			do {
				System.out.println("Teclee n�mero de la provincia a modificar: ");
				numBus=t.leerInt();
			}while(numBus==Integer.MIN_VALUE);
			fich.seek(numBus*p.tamano());
			p.leerDeArchivo(fich);
			if(p.getNumero()==0)
				System.out.println("La provincia con n�mero: "+numBus+" no existe en el fichero.");
			else {
				nombreN=p.getNombre();
				superficieN=p.getSuperficie();
				poblacionN=p.getPoblacion();
				costaN=p.getCosta();
				p.mostrarDatos(1);
				System.out.println("Teclee campo a modificar(1-4): ");
				cm=t.leerInt();
				switch(cm) {
				case 1:
					do {
						System.out.println("Teclee el nuevo nombre: ");
						nombreN=t.leerString();
					}while(nombreN.length()>22);
					break;
				case 2:
					do {
						System.out.println("Teclee la nueva superficie (Km\u00B2): ");
						superficieN=t.leerFloat();
					}while(superficieN==Float.MIN_VALUE);
					break;
				case 3:
					do {
						System.out.println("Teclee la nueva poblaci�n: ");
						poblacionN=t.leerInt();
					}while(poblacionN==Integer.MIN_VALUE);
					break;
				case 4:
					do {
						System.out.println("Teclee la nueva costa: ");
						costaN=Character.toUpperCase(t.leerChar());
					}while("SN".indexOf(costaN)==-1);
				}
				do {
					System.out.println("Otro campo a modificar (s/n)");
					otro=Character.toLowerCase(t.leerChar());
				}while(otro!='s' && otro!='n');
			}while(Character.toLowerCase(otro)=='s');
			do {
				System.out.println("Confirmar modificaciones (s/n)�?");
				otro=Character.toLowerCase(t.leerChar());
			}while(otro!='s' && otro!='n');
			if(otro=='s') {
				fich.seek(numBus*p.tamano());				
				p=new Provincia(numBus,nombreN,superficieN,poblacionN,costaN);
				p.grabarEnArchivo(fich);
			}		
			do {
				System.out.println("Modificar otra provincia (s/n)");
				otro=Character.toLowerCase(t.leerChar());
			}while(otro!='s' && otro!='n');
		}while(otro=='s');
		fich.close();
		System.out.println("\n Teclee <Intro> para continuar.");
		t.leerSalto();
	}
	void listado()throws IOException{
		Provincia p= new Provincia(0," ",0,0,' ');
		Teclado t= new Teclado();
		boolean fin=false;
		int lineas=0;
		final int LINEAS=lineas; //el finalbloquea el valor as� que no lo puedes asignar
		int pcon=0,psin=0,cp=0,
				cl=LINEAS+1,pos=1; //cl es lineas +1 pa que la primera vez no imprima los pies
		RandomAccessFile fich= new RandomAccessFile (ruta+"provincias.dat","r");
		System.out.println("\n\tLISTADO\n\t=======");
		do{
			fin=p.leerDeArchivo(fich);
		}while(p.getNumero()==0 && !fin);
		do {
			while(!fin && cl < LINEAS) {
				if(p.getNumero()!=0) {
					p.mostrarDatos(2);
					if(p.getCosta()=='S')
						pcon++;
					else 
						psin++;	
					cl++;
				}
				fich.seek(p.tamano()*pos++);
				fin=p.leerDeArchivo(fich);
			}
			if(cl==LINEAS) {
				System.out.println("Teclee <Intro> para continuar....");
				t.leerSalto();
			}

			if(!fin) {
				System.out.println("\t\tLISTADO\t\tPag.: "+ ++cp+"\n\t\t=============\n");
				System.out.println("N�mero\tNombre\tSuperficie\tPoblaci�n\tCosta\tDens.");
				System.out.println("-------------------------------------------------------------------");
				cl=0;				
			}
		}while(!fin);	
		fich.close();
		System.out.println("Total de provincias con costa..............: "+pcon);
		System.out.println("Total de provincias sin costa..............: "+psin);	
		System.out.println("\n\n\tFIN DEL LISTADO \n");
		System.out.println("\n\t Pulse <Intro> para continuar");	
		t.leerSalto();	
	}

	void fin () {
		System.out.println("\n\tFIN DEL PROGRAMA.\n\t================");
	}
}