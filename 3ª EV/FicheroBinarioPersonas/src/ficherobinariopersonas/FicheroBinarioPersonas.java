package ficherobinariopersonas;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class FicheroBinarioPersonas{
	static final String ruta="C:\\Users\\DA-1.PUESTO222\\Desktop\\ejercicios programación\\textos\\";
	public static void main(String []args)  throws IOException{
		Scanner e= new Scanner(System.in);
		int op=0;
		do {
			op=menu(e,0);
			switch (op) {
			case 1:
				altas(e);	
				break;
			case 2:
				listados();
				break;
			case 3:
				op=menu(e,1);
				switch(op) {
				case 1:
					busquedas(0);
					break;
				case 2:
					busquedas(1);
					break;
				default:
					break;
				}
				break;
			case 4:
				fin();
				break;
			}
		}while(op!=4);
		e.close();	
	}
	static int menu(Scanner e,int tm) {
		int op = 0; 
		switch(tm) {
		case 0:
			System.out.println("\n\tMENÚ\n\t====");
			System.out.println("1.Altas personas.");
			System.out.println("2.Listados personas.");
			System.out.println("3.Búsqueda personas.");
			System.out.println("4.Fin.");
			do {
				try {
					System.out.print("\n\tTeclee opción(1-4): ");
					op = e.nextInt();
				}catch(InputMismatchException ime) {
					op=Integer.MAX_VALUE;					
				}
				e.nextLine();
			}while (op < 1 || op > 4);	
			break;
		case 1:
			System.out.println("\n\tMENÚ BÚSQUEDAS\n\t==============");
			System.out.println("1.Por nombre.");
			System.out.println("2.Por sexo.");
			System.out.println("3.Volver al menú principal.");
			do {
				try {
					System.out.print("\n\tTeclee opción(1-3): ");
					op = e.nextInt();
				}catch(InputMismatchException ime) {
					op=Integer.MAX_VALUE;
				}
			}while (op < 1 || op > 3);	
			e.nextLine();//vaciar buffer entrada
			break;
		}
		return op;
	}
	static void altas(Scanner e){
		String nombre = null;
		char sexo = ' ';
		int edad=0;
		try {
			System.out.println("\n\tALTAS\n\t=====\n");
			DataOutputStream ficheroSal = new DataOutputStream(new FileOutputStream(ruta + "persona2.dat",true));			
			System.out.println("Teclea nombre ");
			nombre = e.nextLine();
			while(!nombre.equals("**")){
				// de esta manera solamente se comprueba una vez
				System.out.println("Teclea nombre (** para finalizar)");
				nombre = e.nextLine();
				if(!nombre.equals("**")) { 
					do {
						try {
							System.out.println("Edad? ");
							edad = e.nextInt();
						}catch(InputMismatchException ime) {
							edad = Integer.MAX_VALUE;
						}
					}while(edad == Integer.MAX_VALUE);
					do {
						System.out.println("Sexo (V = Varón/M = mujer)? ");
						sexo = Character.toUpperCase((char) System.in.read());
						while(System.in.read() != '\n');
					}while(sexo != 'V' && sexo != 'M');
					e.nextLine(); //NO TE LO OLVIDES PORQUE SINO SE QUEDA EL NOMBRE CON EL INTRO.

					//if(nombre.equals("**")) { si pones aquí el if sigue con el alta pero no sería grabada 
					ficheroSal.writeUTF(nombre);
					ficheroSal.writeInt(edad);
					ficheroSal.writeChar(sexo);	
				}
				System.out.println("Teclea nombre (** para finalizar)");
				nombre = e.nextLine();
			}
			ficheroSal.close();	

		}catch(IOException ioe) {}
	}
	static String tabular(String n)	{
		String t="\t\t\t";
		if(n.length()>=8)
			t="\t\t";
		return t;
	}
	static void listados(){
		System.out.println("\tLISTADO\n\t=======");
		int edad=0;
		String nombre="";
		char sexo=' ';
		try {
			DataInputStream ficheroEnt= new DataInputStream(new FileInputStream(ruta + "persona2.dat"));
			nombre = ficheroEnt.readUTF();
			while(nombre!=null) {
				edad = ficheroEnt.readInt();
				sexo = ficheroEnt.readChar();
				System.out.println(nombre+tabular(nombre)+edad+"\t\t"+sexo);
				nombre = ficheroEnt.readUTF();				
			}
			ficheroEnt.close();
		}catch(IOException ioe) {}
	}
	static void busquedas(int tb) throws IOException {
		Scanner e=new Scanner(System.in);
		String nombreBus=null,nombre=null;
		char sexoBus=' ',sexo=' ';
		int edad=0;
		boolean encontrado = false;
		if (tb==0) {
			System.out.println("Teclee nombre¿?");
			nombreBus=e.nextLine();
			try {
				DataInputStream ficheroEnt = new DataInputStream(new FileInputStream(ruta + "persona2.dat"));
				nombre = ficheroEnt.readUTF();
				while(!nombre.equals(null)) {
					edad = ficheroEnt.readInt();
					sexo = ficheroEnt.readChar();
					if (nombreBus.equalsIgnoreCase(nombre)) {
						encontrado = true;
					}
					nombre = ficheroEnt.readUTF();
				}
				ficheroEnt.close();
			}catch(IOException ioe) {}
			if(!encontrado) {
				System.out.println("\nNo existe nadie llamado "+nombreBus+" en el fichero");
			}
			System.out.println(edad+"\t\t"+sexo);

		}
		if(tb==1) {		
			do {
				try {
					System.out.println("Teclee sexo¿?Sexo (V = Varón/M = mujer)? ");
					sexoBus=Character.toUpperCase((char)System.in.read());
					while(System.in.read() != '\n');
				}catch(IOException ioe) {}
			}while(sexoBus != 'V' && sexoBus != 'M');		
			try {
				DataInputStream ficheroEnt = new DataInputStream(new FileInputStream(ruta + "persona2.dat"));
				ficheroEnt.readUTF();
				ficheroEnt.readInt();
				sexo = ficheroEnt.readChar();
				while(sexo!=' ') {
					nombre = ficheroEnt.readUTF();
					edad = ficheroEnt.readInt();						
					if (sexoBus==sexo) 
						encontrado = true;

				}
				ficheroEnt.close();
			}catch(IOException ioe) {}
			if(!encontrado) {
				System.out.println("\nNo existe nadie de sexo "+sexoBus+" en el fichero");
			}
			System.out.println(edad+"\t\t"+sexo);
		}
		//EOFException eofe se puede usar pero como data lee datos y no objetos, por lo que aquí usamos el null que vale igualmente.
	}
	static void fin() {
		System.out.println("\n\tFIN DEL PROGRAMA\n\t================");
	}
}