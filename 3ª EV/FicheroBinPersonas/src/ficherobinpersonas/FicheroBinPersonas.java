package ficherobinpersonas;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class FicheroBinPersonas{
	static final String ruta="C:\\Users\\DA-1.PUESTO222\\Desktop\\ejercicios programación\\textos";
	static int menu(Scanner e) {
		int op = 0; 
		System.out.println("\n\tMENÚ\n\t====");
		System.out.println("1.Altas.");
		System.out.println("2.Consultas.");
		System.out.println("3.Listados.");
		System.out.println("4.Fin.");
		do {
			try {
				System.out.print("\n\tTeclee opción(1-4): ");
				op = e.nextInt();
			}catch(InputMismatchException ime) {
				op=Integer.MAX_VALUE;
				e.nextLine();
			}
		} while (op < 1 || op > 4||op==Integer.MAX_VALUE);	
		e.nextLine();//vaciar buffer entrada
		return op;
	}	
	static boolean comprobarDNI(String dni) {
		boolean correcto = false;
		String letras="TRWAGMYFPDXBNJZSQVHLCKE";
		if(!dni.equals("999")&&Character.toUpperCase(dni.charAt(8))==letras.charAt(Integer.parseInt(dni.substring(0,8))%23))
			correcto=true;
		return correcto;
	}
	static String convertirLetraMayuscula(String dni) {
		String n=dni.substring(0,8); //coges el número
		n+=Character.toUpperCase(dni.charAt(8)); //lo concatenas con la letra en mayúscula.
		return n;

	}
	static void altas(Scanner e) throws IOException{
		String dni = null, nombre = null;
		char sexo = ' ';
		int edad=0;
		System.out.println("\n\tALTAS\n\t=====\n");
		try {
			//			ObjectOutputStream ficheroSal = new ObjectOutputStream(new FileOutputStream(ruta + "persona.dat"));
			DataOutputStream ficheroSal = new DataOutputStream(new FileOutputStream(ruta + "persona.dat"));
			do {
				System.out.println("DNI (999 para finalizar)? ");
				dni = e.nextLine();
			}while(!comprobarDNI(dni) && !dni.equalsIgnoreCase("999"));
			while(!dni.equalsIgnoreCase("999")){
				do {
					System.out.println("Nombre (máximo 20 caracteres)? ");
					nombre = e.nextLine();
				}while(nombre.length()>20);
				do {
					try {
						System.out.println("Edad? ");
						edad = e.nextInt();
					}catch(InputMismatchException ime) {
						edad = Integer.MAX_VALUE;
					}
					e.nextLine();
				}while(edad == Integer.MAX_VALUE);
				do {
					System.out.println("Sexo (V = Varon/M = mujer)? ");
					sexo = Character.toUpperCase((char) System.in.read());
					while(System.in.read() != '\n');
				}while(sexo != 'V' && sexo != 'M');
				dni = convertirLetraMayuscula(dni);
				ficheroSal.writeUTF(dni);
				ficheroSal.writeUTF(nombre);
				ficheroSal.writeInt(edad);
				ficheroSal.writeChar(sexo);
				do {
					System.out.println("DNI (999) para finalizar)? ");
					dni = e.nextLine();
				}while(!comprobarDNI(dni) && !dni.equalsIgnoreCase("999"));
			}
			ficheroSal.close();
		}catch(IOException ioe) {}
	}
	static String tabular(String cad) {
		String tab="\t";
		int l=0;
		l=cad.length();
		if (l==0)
			tab="\t\t\t";
		else	
			if (l<=16)
				tab="\t\t\t\t";
		return tab;
	}
	static void consultas(Scanner e) {
		String dniBus = null;
		String dni = null, nombre = null;
		int edad = 0;
		char sexo = ' ';
		char otro = ' ';
		boolean fin = false, encontrado = false;
		System.out.println("\n\tCONSULTAS\n\t=========\n");
		do {
			do {
				System.out.println("Teclea nif a buscar? ");
				dniBus = e.nextLine();
			}while(!comprobarDNI(dniBus));
			try {
				DataInputStream ficheroEnt = new DataInputStream (new FileInputStream(ruta + "persona.dat"));
				while(!fin) {
					try {
						dni = ficheroEnt.readUTF();
						nombre = ficheroEnt.readUTF();
						edad = ficheroEnt.readInt();
						sexo = ficheroEnt.readChar();
						if (dniBus.equalsIgnoreCase(dni)) {
							encontrado = true;
							ficheroEnt.close();
						}
					}catch(EOFException eofe) {
						ficheroEnt.close();
						fin = true;
					}
				}
			}catch (IOException ioe) {}
			if(encontrado) {
				System.out.println("Nombre => "+nombre);
				System.out.println("Edad ===> "+edad);
				System.out.println("Sexo ====> "+sexo+"\n");
			}
			else
				System.out.println("\nNo existe nadie con el dni: "+dniBus+" en el fichero");
			try {
				do {
					System.out.println("\tRealizar otra búsqueda (s/n)? ");
					otro = (char) System.in.read();
					while(System.in.read() != '\n');
				}while(otro != 's' && otro != 'n');
			}catch(IOException ioe) {}
			encontrado = false;
			fin = false;
		}while(otro=='s');
	}
	static char menuListados(Scanner e) {
		char op=' ';
		System.out.println("\n\tMENÚ LISTADOS.\n\t");
		System.out.println("G. General.");
		System.out.println("V. Hombres.");
		System.out.println("M. Mujeres.");
		System.out.println("E. Entre edades.");
		System.out.println("F. Volver al menú principal.");
		do {
			System.out.println("\t\t Teclee opción(G/V/M/E/F)¿?");
			op=e.next().charAt(0);
		}while("GVMEF".indexOf(op)==-1); /*si esta letra está te indica la posición y si no está te devuelve -1.*/
		switch(op) {
		case 'g':
			listadoGeneral();
			break;
		case 'v':
			listadoSexo('v');
			break;
		case 'm':
			listadoSexo('m');
			break;
		case 'e':
			listadoEdad();
			break;
		case 'f':
			menu(e);
			break;
		}
		return op;
	}
	static int mediaEdadSexo(char sexo) {
		Scanner e= new Scanner(System.in);
		String nombre="",dni="";
		double media=0;
		int c=0,edad=0;
		boolean fin=false;
		if(sexo=='V') {
			try {
				DataInputStream ficheroEnt= new DataInputStream(new FileInputStream(ruta + "gente.dat"));
				System.out.println("\tMEDIA EDAD\n\t=========\n");	
				while(!fin && sexo=='V') {
					try {
						dni = ficheroEnt.readUTF();
						nombre = ficheroEnt.readUTF();
						edad = ficheroEnt.readInt();
						sexo = ficheroEnt.readChar();
						try {	
							media+=edad;	
						}catch(NumberFormatException nfe) {
							media+=0;
							c--;
						}
						c++;						
					}catch(EOFException eofe) {
						ficheroEnt.close();
						fin = true;
					}
				}
			}catch(IOException ioe) {}		
			System.out.println("Número de hombres: "+c+"  \nMedia de su edad: "+(media/c));
		}
		if(sexo=='M') {
			try {
				DataInputStream ficheroEnt= new DataInputStream(new FileInputStream(ruta + "gente.dat"));
				System.out.println("\tMEDIA EDAD\n\t=========\n");	
				while(!fin && sexo=='V') {
					try {
						dni = ficheroEnt.readUTF();
						nombre = ficheroEnt.readUTF();
						edad = ficheroEnt.readInt();
						sexo = ficheroEnt.readChar();
						try {	
							media+=edad;	
						}catch(NumberFormatException nfe) {
							media+=0;
							c--;
						}
						c++;						
					}catch(EOFException eofe) {
						ficheroEnt.close();
						fin = true;
					}
				}
			}catch(IOException ioe) {}		
			System.out.println("Número de hombres: "+c+"  \nMedia de su edad: "+(media/c));
			if (sexo==' ')
				try {
					DataInputStream ficheroEnt= new DataInputStream(new FileInputStream(ruta + "gente.dat"));
					System.out.println("\tMEDIA EDAD\n\t=========\n");	
					while(!fin) {
						try {
							dni = ficheroEnt.readUTF();
							nombre = ficheroEnt.readUTF();
							edad = ficheroEnt.readInt();
							sexo = ficheroEnt.readChar();
							try {	
								media+=edad;	
							}catch(NumberFormatException nfe) {
								media+=0;
								c--;
							}
							c++;						
						}catch(EOFException eofe) {
							ficheroEnt.close();
							fin = true;
						}
					}
				}catch(IOException ioe) {}		
			System.out.println("Número de personas: "+c+"  \nMedia de su edad: "+(media/c));
		}	
		return c;
	}
	static void mayorMenor(char t) {
		int edad=0,mayor=0,menor=Integer.MAX_VALUE;
		boolean fin=false;
		String nombre="",dni="";
		char sexo=' ';
		switch (t) {
		case 'v':
			try {
				DataInputStream ficheroEnt= new DataInputStream(new FileInputStream(ruta + "gente.dat"));
				while(!fin && sexo=='V') {
					try {
						dni = ficheroEnt.readUTF();
						nombre = ficheroEnt.readUTF();
						edad = ficheroEnt.readInt();
						sexo = ficheroEnt.readChar();
						try {			
							if(edad>mayor) {					
								mayor=edad;
							}
							if(edad<menor) {
								menor=edad;
							}				
						}catch(NumberFormatException nfe) {}

					}catch(EOFException eofe) {
						ficheroEnt.close();
						fin = true;
					}
				}
			}catch(IOException ioe) {}
			System.out.println("El hombre más joven tiene:" +menor);
			System.out.println("El hombre más mayor tiene:" +mayor);
			break;
		case 'g':
			try {
				DataInputStream ficheroEnt= new DataInputStream(new FileInputStream(ruta + "gente.dat"));
				while(!fin && sexo=='M') {
					try {
						dni = ficheroEnt.readUTF();
						nombre = ficheroEnt.readUTF();
						edad = ficheroEnt.readInt();
						sexo = ficheroEnt.readChar();
						try {			
							if(edad>mayor) {					
								mayor=edad;
							}
							if(edad<menor) {
								menor=edad;
							}				
						}catch(NumberFormatException nfe) {}

					}catch(EOFException eofe) {
						ficheroEnt.close();
						fin = true;
					}
				}
			}catch(IOException ioe) {}

			System.out.println("La mujer más joven tiene:" +menor);
			System.out.println("La mujer más mayor tiene:" +mayor);
			break;
		case 'm':
			try {
				DataInputStream ficheroEnt= new DataInputStream(new FileInputStream(ruta + "gente.dat"));
				while(!fin) {
					try {
						dni = ficheroEnt.readUTF();
						nombre = ficheroEnt.readUTF();
						edad = ficheroEnt.readInt();
						sexo = ficheroEnt.readChar();
						try {			
							if(edad>mayor) {					
								mayor=edad;
							}
							if(edad<menor) {
								menor=edad;
							}				
						}catch(NumberFormatException nfe) {}

					}catch(EOFException eofe) {
						ficheroEnt.close();
						fin = true;
					}
				}
			}catch(IOException ioe) {}

			System.out.println("La persona más joven tiene:" +menor);
			System.out.println("La persona más mayor tiene:" +mayor);
			break;
		}
	}
	static void listadoGeneral(){
		System.out.println("\t\tListado general\n==============");
		double media=0;
		int edad=0,contV=0,contM=0,totEdV=0,totEdM=0;
		boolean fin=false;
		String nombre="",dni="";
		char sexo=' ';
		media=mediaEdadSexo(' ');
		try {
			DataInputStream ficheroEnt= new DataInputStream(new FileInputStream(ruta + "gente.dat"));
			while(!fin) {
				try {
					dni = ficheroEnt.readUTF();
					nombre = ficheroEnt.readUTF();
					edad = ficheroEnt.readInt();
					sexo = ficheroEnt.readChar();
					System.out.println(dni+"\t"+nombre+tabular(nombre)+edad+"\t\t"+sexo);
					if(sexo == 'V') {
						contV++;
						totEdV+=edad;
					}
					else {
						contM++;
						totEdM+=edad;
					}
				}catch(EOFException eofe) {
					ficheroEnt.close();
					fin = true;
				}
			}
		}catch(IOException ioe) {}
System.out.println("");
System.out.println("");
System.out.println("");
System.out.println("");
	}
	static void listadoSexo(char t) {
		int edad=0;
		boolean fin=false;
		double mediah=0;
		double mediam=0;
		String nombre="",dni="";
		char sexo=' ';
		mediah=mediaEdadSexo('v');
		mediam=mediaEdadSexo('m');
		if(t=='v') {			
			try {
				DataInputStream ficheroEnt= new DataInputStream(new FileInputStream(ruta + "gente.dat"));
				while(!fin && sexo=='v') {
					try {
						dni = ficheroEnt.readUTF();
						nombre = ficheroEnt.readUTF();
						edad = ficheroEnt.readInt();
						sexo = ficheroEnt.readChar();
						System.out.println(dni+"\t"+nombre+tabular(nombre)+edad+"\t\t"+sexo);
					}catch(EOFException eofe) {
						ficheroEnt.close();
						fin = true;
					}
				}
			}catch(IOException ioe) {}
			mediaEdadSexo('v');	
		}		
		else 
			if(t=='m') {
				try {
					DataInputStream ficheroEnt= new DataInputStream(new FileInputStream(ruta + "gente.dat"));
					while(!fin && sexo=='m') {
						try {
							dni = ficheroEnt.readUTF();
							nombre = ficheroEnt.readUTF();
							edad = ficheroEnt.readInt();
							sexo = ficheroEnt.readChar();
							System.out.println(dni+"\t"+nombre+tabular(nombre)+edad+"\t\t"+sexo);
						}catch(EOFException eofe) {
							ficheroEnt.close();
							fin = true;
						}
					}
				}catch(IOException ioe) {}
				System.out.println("La edad media de las mujeres del fichero es: "+mediam);
				mediaEdadSexo('m');
			}		
	}
	static void listadoEdad()  {
		Scanner e= new Scanner(System.in);
		int edadMin=0;
		int edadMax=0;
		int edad=0;
		boolean fin=false;
		String nombre="",dni="";
		char sexo=' ';
		do {
			System.out.println("Teclee una edad mínima");
			edadMin=e.nextInt();
			System.out.println("Teclee una edad máxima");
			edadMax=e.nextInt();
		}while(edadMin>edadMax);
		try {
			DataInputStream ficheroEnt= new DataInputStream(new FileInputStream(ruta + "gente.dat"));
			while(!fin) {
				try {
					dni = ficheroEnt.readUTF();
					nombre = ficheroEnt.readUTF();
					edad = ficheroEnt.readInt();
					sexo = ficheroEnt.readChar();
					if (edad>= edadMin && edad<=edadMax) {
						System.out.println(dni+"\t"+nombre+tabular(nombre)+"\t"+edad+sexo);
					}
				}catch(EOFException eofe) {
					ficheroEnt.close();
					fin = true;
				}
			}
		}catch(IOException ioe) {}
	}
	static void fin() {
		System.out.println("FIN DEL PROGRAMA\n ================");
	}
	public static void main(String []args) throws IOException {
		Scanner e = new Scanner (System.in);
		int op=0;
		op=menu(e);
		do {
			switch(op) {
			case 1:
				altas(e);
				break;
			case 2:
				consultas(e);
				break;
			case 3:
				menuListados(e);
				break;
			default:
				fin();
				break;
			}
		}while(op!=4);	
	}
}