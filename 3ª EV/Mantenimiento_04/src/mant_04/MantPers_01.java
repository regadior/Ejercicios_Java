package mant_04;
import java.io.IOException;
import java.io.RandomAccessFile;
public class MantPers_01{
	static final String ruta="G:\\Mi unidad\\PROGRAMACI�N\\EJERCICIOS\\3� EV\\textos\\";
	String menu() throws IOException {
		Teclado t = new Teclado();
		System.out.println("\n\tMENU\n\t====\n");
		System.out.println("A.- Altas.");
		System.out.println("B.- Bajas.");
		System.out.println("M.- Modificaciones.");
		System.out.println("C.- Consultas. ");
		System.out.println("L.- Listados.");
		System.out.println("F.- Fin.");
		System.out.print("\n\n\tTeclee opci�n:  ");
		String op =t.leerString();
		return op;
	}
	public static void main(String []args) throws IOException {
		MantPers_01 mp= new MantPers_01();
		String op;
		boolean fin=false;
		RandomAccessFile fich=new RandomAccessFile (ruta+"mantpers.dat","rw"); // si lo abres de rw y no existe cuando lo lees lo crea
		fich.close(); // as� te aseguras con certeza de que el fichero existe.-
		while (!fin) {
			op = mp.menu();
			if(op.equalsIgnoreCase("A")) mp.altas();
			if(op.equalsIgnoreCase("B")) mp.bajas();
			if(op.equalsIgnoreCase("M")) mp.modificaciones();
			if(op.equalsIgnoreCase("C")) mp.consultas();
			if(op.equalsIgnoreCase("L")) mp.listado();
			if(op.equalsIgnoreCase("F")) {
				mp.fin();
				fin=true; //solamente est� a verdadero cuando pulso f y salgo del while.
			}
		}

	}
	void altas() throws IOException{
		RandomAccessFile fich = new RandomAccessFile(ruta+"mantpers.dat","rw");
		Persona pv= new Persona(0,"",0);
		Teclado t = new Teclado();
		int numero,edad=0;
		char confirmar=' ';
		String nombre=null;
		System.out.println("\n\tALTAS\n\t=====");
		do {
			System.out.print("N�mero (0 = Fin): ");
			numero= t.leerInt();
		}while(numero==Integer.MIN_VALUE);
		
		while(numero!=0) { // si no quieres salir no tecleas cero.
			fich.seek(numero * pv.tamano()); //me posiciono 
			pv.leerDeArchivo(fich); //leo el fichero
			if(pv.getNumero()!=0)
				System.out.println("\n\tEl registro ya existe.....\n");			
			else {
				do {
					System.out.print("Nombre.....: ");
					nombre=t.leerString();
				}while(nombre.length()>25);
				do {
					System.out.print("Edad: ");
					edad= t.leerInt();
				}while(edad==Integer.MIN_VALUE);
				do {
					System.out.println("Confirmar alta (s/n): ");
					confirmar=Character.toLowerCase(t.leerChar());
				}while(confirmar!='s' && confirmar!='n');
				if(confirmar=='s') {
					Persona p= new Persona(numero,nombre,edad);
					if(numero * p.tamano()>fich.length())
						fich.seek(fich.length()); // como queda mucho recorrido te pones en la posicion "final"
					while(numero*p.tamano()>fich.length()) //mientras la direcci�n sea mayor que la longitud
						pv.grabarEnArchivo(fich); //como no puede haber espacios se crean registros en blanco (av es el archivo vac�o)
					fich.seek(numero * p.tamano()); // ya se dej� de cumplir el while por lo que ya estoy posicionado.
					p.grabarEnArchivo(fich);
				}
			}		
			do {
				System.out.print("N�mero (0 = Fin): ");
				numero= t.leerInt();
			}while(numero==Integer.MIN_VALUE);
			pv = new Persona(0,"",0);
		}
		fich.close();	
	}
	void consultas()throws IOException{
		Persona p= new Persona(0,"",0);
		Teclado t= new Teclado();
		int numBus=0;
		System.out.println("\n\tCONSULTAS\n\t=========");
		RandomAccessFile fich = new RandomAccessFile(ruta+"mantpers.dat","r");
		do {
			System.out.println("Teclee N�mero de la persona: ");
			numBus=t.leerInt();
		}while(numBus==Integer.MIN_VALUE);
		fich.seek(numBus*p.tamano());
		p.leerDeArchivo(fich);
		if(p.getNumero()==0)
			System.out.println("El registro indicado no existe...");
		else 
			p.mostrarDatos(0);
		fich.close();
		System.out.println("\n Teclee <Intro> para continuar.");
		t.leerSalto();
	}
	void bajas() throws IOException{
		Persona p=new Persona(0,"",0);
		Teclado t= new Teclado();
		int numBus=0;
		char confirmar=' '; 
		System.out.println("\n\tBAJAS\n\t=====");
		RandomAccessFile fich = new RandomAccessFile(ruta+"mantpers.dat","rw");
		do {
			System.out.print("Teclee persona a eliminar: ");
			numBus= t.leerInt();
		}while(numBus==Integer.MIN_VALUE);
		fich.seek(numBus*p.tamano());
		p.leerDeArchivo(fich);
		if(p.getNumero()==0)
			System.out.println("El registro indicado no existe.");
		else {
			p.mostrarDatos(0);
			do {
				System.out.println("Desea confirmar el borrado (s/n): ");
				confirmar=Character.toLowerCase(t.leerChar());
			}while(confirmar!='s' && confirmar!='n');
			if(confirmar =='s')	{	
				p= new Persona(0,"",0);
				fich.seek(numBus*p.tamano());
				p.grabarEnArchivo(fich);
				System.out.println("Registro borrado correctamente.......");
			}
			fich.close();
			System.out.println("\n Teclee <Intro> para continuar.");
			t.leerSalto();
		}
	}
	void modificaciones() throws IOException{
		Persona p= new Persona(0,"",0);
		Teclado t= new Teclado();
		int numBus=0,edadN=0,cm=0;
		String nombreN=null;
		char confirmar=' ';
		//do {
		RandomAccessFile fich= new RandomAccessFile(ruta+"mantpers.dat","rw");
		do {
			//estoy cerrando y abriendo el fichero en cada modificaci�n xd
			//el do puedes
			System.out.println("\n\tMODIFICACIONES\n\t==============");
			do {
				System.out.println("Teclee N�mero para modificar: ");
				numBus=t.leerInt();
			}while(numBus==Integer.MIN_VALUE);
			fich.seek(numBus*p.tamano());
			p.leerDeArchivo(fich);
			if(p.getNumero()==0)
				System.out.println("El registro indicado no existe.");
			else {
				nombreN=p.getNombre();
				edadN=p.getEdad();
				p.mostrarDatos(0);
				do {
					System.out.println("Teclee campo a modificar: ");
					cm=t.leerInt();
				}while(cm==Integer.MIN_VALUE);
				switch(cm) {
				case 1:
					do {
						System.out.println("Teclee el nuevo nombre: ");
						nombreN=t.leerString();
					}while(nombreN.length()>25);
					break;
				case 2:
					do {
						System.out.println("Teclee la nueva edad: ");
						edadN=t.leerInt();
					}while(edadN==Integer.MIN_VALUE);
					break;
				}
				do {
					System.out.println("Confirmar las modificaciones (s/n): ");
					confirmar=t.leerChar();
				}while(confirmar!='s' && confirmar!='n');
				if(confirmar=='s') {
					p=new Persona(numBus,nombreN,edadN);
					fich.seek(numBus*p.tamano());
					p.grabarEnArchivo(fich);
				}
				do {
					System.out.println("Desea modificar otro campo(s/n): ");
					confirmar=t.leerChar();
				}while (confirmar!='s'&& confirmar!='n');
			}
		}while (confirmar!='s'&& confirmar!='n');
		do {
			System.out.println("Desea modificar otro registro(s/n): ");
			confirmar=t.leerChar();
		}while (confirmar!='s'&& confirmar!='n');
		fich.close();	
	}
	void listado() throws IOException{
		Persona p = new Persona(0," ",0);
		Teclado t = new Teclado();
		boolean fin = false;
		final int LINEAS = 5;
		int cp =0,cl = LINEAS + 1,s = 0,edadP = 0;
		RandomAccessFile fich = new RandomAccessFile(ruta+"mantpers.dat","r");
		//		do {
		//		System.out.println("Teclee el n�mero de lineas: ");
		//		//final int lineas=t.leerInt();
		//		lineas=t.leerInt();
		//	}while(lineas<0);
		do {
			fin=p.leerDeArchivo(fich);
		}while(p.getNumero() == 0 && !fin);

		do{
			while(!fin && cl < LINEAS){
				if(p.getNumero() != 0){
					p.mostrarDatos(2);
					cl++;
					edadP += p.getEdad();
				}
				fin = p.leerDeArchivo(fich);
			}

			if (cl == LINEAS){
				System.out.println("\nMedia de edad en la p�gina: " + edadP/cl);
				//si van a ser 5 puedes dividir x 5
				s += edadP;
				edadP=0;
				System.out.println("\n\tPulse <Intro> para continuar....");
				t.leerSalto();				
				//syso ++cp+ si pide el cotnadore de las paginas en el pie Pag.: "+ ++cp +"\n\t\t=======\n")
			}

			if(!fin){
				System.out.println("\t\tLISTADO\t\tPag.: "+ ++cp +"\n\t\t=======\n");
				System.out.println("N�mero\tNombre\t\t\t\tEdad");
				System.out.println("--------------------------------------------");
				cl=0;
			}

		}while(!fin);
		s += edadP;
		fich.close();
		System.out.println("\nMedia de edades en la pagina: " + edadP/cl);
		System.out.println("Media de edades en total: " + s/(LINEAS*(cp-1)+cl));
		//con LINEAS*(cp-1)+cl obtienes todas las lineas que tiene el fichero
		//en vez de total pone esto: (LINEAS*(contPag-1)+contLinea)
		System.out.println("\n\n\t\tFIN DEL LISTADO");
		System.out.println("\n\tPulse <Intro> para continuar....");
		t.leerSalto();
	}	void fin() {
		System.out.println("\n\tFIN DEL PROGRAMA\n\t================");
	}
}

