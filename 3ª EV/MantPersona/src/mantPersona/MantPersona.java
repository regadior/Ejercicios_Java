package mantPersona;
import java.io.IOException;
import java.io.RandomAccessFile;
public class MantPersona {
	//	static final String ruta ="C:\\Users\\Emilio.PUESTO100\\FicherosDatos\\";
	static final String ruta = "D:\\Ejercicios_Java\\3ª EV\\textos\\";
	int menu()throws IOException{
		Teclado t = new Teclado();
		int op = 0;
		System.out.println("\n\tMENU\n\t====\n\n");
		System.out.println("1.- Altas.");
		System.out.println("2.- Bajas.");
		System.out.println("3.- Consultas.");
		System.out.println("4.- Modificaciones.");
		System.out.println("5.- Listados.");
		System.out.println("6.- Fin.");
		do{
			System.out.print("\n\n\tTeclee opci�n(1-6)? ");
			op = t.leerInt();
		}while(op<1 || op>6 || op == Integer.MIN_VALUE);
		return op;
	}
	void altas()throws IOException{
		Teclado t = new Teclado();
		Persona pv = new Persona(0," ",0);
		int numero=0, edad = 0;
		String nombre = "";
		char confirma = ' ';
		RandomAccessFile fich = new RandomAccessFile(ruta + "personas.dat","rw");
		System.out.println("\n\tALTAS\n\t-----\n");
		do{
			System.out.print("N�mero........: ");
			numero = t.leerInt();
		}while(numero == Integer.MIN_VALUE);
		
		while (numero!=0){
			fich.seek(numero * pv.tamano());
			pv.leerPersona(fich);
			if (pv.getNumero() != 0)
				System.out.println("\n\tEl registro ya existe.\n");
			else{
				do {
					System.out.print("Nombre........: ");
					nombre = t.leerString();
				}while(nombre.length()>20);
				do {
					System.out.print("Edad..........: ");
					edad = t.leerInt();
				}while(edad == Integer.MIN_VALUE);
				do{
					System.out.print("\n\tConfirmar alta (s/n)? ");
					confirma = Character.toLowerCase(t.leerChar());
				}while(confirma != 's' && confirma != 'n');
				if (confirma == 's'){
					Persona p = new Persona(numero, nombre, edad);
					if (numero * p.tamano()>fich.length())
						fich.seek(fich.length());
					while(numero * p.tamano()>fich.length())
						pv.grabarPersona(fich);
					fich.seek(numero * p.tamano());
					p.grabarPersona(fich);
				}
			}
			do{
				System.out.print("N�mero........: ");
				numero = t.leerInt();
			}while(numero == Integer.MIN_VALUE);
			pv = new Persona(0," ",0);
		}
		fich.close();
	}
	void bajas() throws IOException{
		Persona p = new Persona (0," ",0);
		Teclado t = new Teclado();
		int numBus = 0;
		char confirmar = ' ';
		System.out.println("\n\tBAJAS\n\t=====\n");
		RandomAccessFile fich = new RandomAccessFile(ruta + "personas.dat","rw");
		do{
			System.out.print("\nN�mero: ");
			numBus =   t.leerInt();
		}while(numBus==Integer.MIN_VALUE);
		fich.seek(numBus * p.tamano());
		p.leerPersona(fich);
		if(p.getNumero() == 0)
			System.out.println("\nLa persona con el n�mero: "+numBus+" no existe.\n");
		else{
			p.verDatosPersona(0);
			do{
				System.out.print("\n\tConfirmar borrado (s/n)? ");
				confirmar = Character.toLowerCase(t.leerChar());
			}while (confirmar != 's' && confirmar != 'n');
			if(confirmar == 's'){
				p = new Persona(0," ",0);
				fich.seek(numBus * p.tamano());
				p.grabarPersona(fich);
			}
		}
	}
	void consultas()throws IOException{
		Persona p = new Persona(0," ",0);
		Teclado t = new Teclado();
		int numBus;
		System.out.println("\n\tCONSULTAS\n\t=========\n");
		RandomAccessFile fich = new RandomAccessFile(ruta + "personas.dat","r");
		do{
			System.out.print("\nN�mero ...........: ");
			numBus = t.leerInt();
		}while(numBus == Integer.MIN_VALUE);
		fich.seek(numBus * p.tamano());
		p.leerPersona(fich);
		if(p.getNumero() != 0)
			p.verDatosPersona(0);
		else
			System.out.println("\nNo existe ninguna persona con el n�mero: "+numBus+" en el fichero.\n");
		fich.close();
		System.out.println("\n\tPulse <Intro> para continuar....");
		System.in.read();
		t.leerSalto();
	}
	void modificaciones() throws IOException{
		Persona p = new Persona(0," ",0);
		Teclado t = new Teclado();
		char otro;
		int numBus = 0,cm=0,edad=0,edadN=0;
		String nombreN;
		RandomAccessFile fich = new RandomAccessFile(ruta + "personas.dat","rw");
		do{
			System.out.println("\n\tMODIFICACIONES\n\t==============\n");
			do{
				System.out.print("N�mero................: ");
				numBus=t.leerInt();
			}while(numBus == Integer.MIN_VALUE);
			fich.seek(numBus * p.tamano());
			p.leerPersona(fich);
			if (p.getNumero()==0)
				System.out.println("\n\tLa persona con el n�mero: "+numBus+" no est� registrada.\n");
			else{
				nombreN = p.getNombre();
				edadN = p.getEdad();
				p.verDatosPersona(1);
				do{
					do{
						System.out.print("\n\tTeclee campo a modificar (1-2)?");
						cm=t.leerInt();
					}while(cm==Integer.MIN_VALUE || cm<1 || cm>2);
					switch(cm){
					case 1:
						do {
							System.out.print("Teclee nuevo nombre........: ");
							nombreN = t.leerString();
						}while(nombreN.length()>20);
						break;
					case 2:
						do{
							System.out.print("Teclee nueva edad .......: ");
							edadN = t.leerInt();
						}while (edadN == Integer.MIN_VALUE);
						break;
					}
					do{
						System.out.print("\n\tOtro campo a modificar (s/n)? ");
						otro = t.leerChar();
					}while (Character.toLowerCase(otro) != 's' && Character.toLowerCase(otro) != 'n');
				}while (Character.toLowerCase(otro)=='s');
				do{
					System.out.print("\n\tConfirmar las modificaciones (s/n)? ");
					otro = t.leerChar();
				}while (Character.toLowerCase(otro) != 's' && Character.toLowerCase(otro) != 'n');
				if (Character.toLowerCase(otro)=='s'){
					p = new Persona(numBus, nombreN, edadN);
					fich.seek(numBus*p.tamano());
					p.grabarPersona(fich);
				}
			}
			do{
				System.out.print("\n\tModificar otra persona (s/n).....: ");
				otro = t.leerChar();
			}while(Character.toLowerCase(otro)!='s' && Character.toLowerCase(otro)!='n');
		}while(Character.toLowerCase(otro)=='s');
		fich.close();
	}
	void listados()throws IOException{
		Persona p = new Persona(0," ",0);
		Teclado t = new Teclado();
		int sumaEdadP= 0,sumaEdadG =0;
		boolean fin = false;
		final int LINEAS = 5;
		int cp=0,cl=LINEAS+1;
		RandomAccessFile fich = new RandomAccessFile(ruta + "personas.dat","r");
		do{
			fin = p.leerPersona(fich);
		}while(p.getNumero()==0 && !fin);
		do{
			while(!fin && cl < LINEAS){
				if(p.getNumero()!=0){
					p.verDatosPersona(2);
					sumaEdadP+=p.getEdad();
					cl++;
				}
				fin = p.leerPersona(fich);
			}
			if (cl==LINEAS){
				System.out.println("\nMedia de edad p�gina...................:"+sumaEdadP/cl);
				sumaEdadG+=sumaEdadP;
				sumaEdadP = 0;
				System.out.println("\n\tPulse <Intro> para continuar....");
				t.leerSalto();
			}
			if(!fin){
				System.out.println("\n\n\tLISTADO PERSONAS\tPag.: "+ ++cp+"\n\t================\n");
				System.out.println("Numero\tNombre\t\t\tEdad");
				System.out.println("---------------------------------------");
				cl=0;
			}
		}while(!fin);
		sumaEdadG+=sumaEdadP;
		System.out.println("\nMedia de edad p�gina...................:"+sumaEdadP/cl);
		System.out.println("\nMedia de edad listado..................:"+sumaEdadG/(LINEAS*(cp-1)+cl));
		System.out.println("\n\n\t\tFIN DEL LISTADO \n");
		System.out.println("\n\tPulse <Intro> para continuar....");
		t.leerSalto();
	}
	void fin(){
		System.out.println("\n\n\n\n\tFIN DE PROGRAMA\n\t===============\n\n\n");
	}
	public static void main(String [] args)throws IOException{
		int op = 0;
		MantPersona mp = new MantPersona();
		RandomAccessFile fich = new RandomAccessFile(ruta + "personas.dat","rw");
		fich.close();
		do{
			op = mp.menu();
			switch(op){
			case 1:
				mp.altas();
				break;
			case 2:
				mp.bajas();
				break;
			case 3:
				mp.consultas();
				break;
			case 4:
				mp.modificaciones();
				break;
			case 5:
				mp.listados();
				break;
			default:
				mp.fin();
			}
		}while(op != 6);
	}
}
