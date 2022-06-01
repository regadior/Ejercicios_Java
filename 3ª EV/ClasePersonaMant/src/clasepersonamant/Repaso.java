package clasepersonamant;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DecimalFormat;
public class Repaso{
	static final String ruta="C:\\Users\\DA-1.PUESTO222\\Desktop\\ejercicios programación\\textos\\";
	static int menu(Scanner e) {
		int op=0;
		System.out.println("\n\tMENÚ\n\t====");
		System.out.println("1.Altas.");
		System.out.println("2.Listado.");
		System.out.println("3.Modificaciones.");
		System.out.println("4.Fin.");
		do {
			try {
				System.out.println("\n\tTeclee opción (1-4): ");
				op=e.nextInt();
			}catch(InputMismatchException ime) {
				op=Integer.MAX_VALUE;
			}
		}while(op<1||op>4);
		e.nextLine();
		return op;
	}
	static void altas(Scanner e)  {
		Persona p;
		System.out.println("\n\tALTAS\n\t=====");
		try {
			BufferedWriter esc= new BufferedWriter(new FileWriter(ruta +"personas2.txt",true));
			// el true es para añadir nuevas personas.
			p= new Persona();
			do {
				System.out.println("Teclee un DNI (fin para terminar)");
				p.setDni(e.nextLine());
			}while(!p.controlDNI(p.getDni()) && !p.getDni().equalsIgnoreCase("fin"));

			while(!p.getDni().equalsIgnoreCase("fin")) {
				do {
					System.out.println("Teclee un nombre ");
					p.setNombre(e.nextLine());
				}while(!p.controlNombre());

				do {
					System.out.println("Teclee una edad ");
					p.setEdad(e.nextInt());
				}while(e.nextInt()<0);

				do {
					System.out.println("Teclee un código postal");
					p.setCodPos(e.nextInt());
				}while(p.controlLongitud().length()!=5 || !p.controlProvincia(p.controlLongitud()));

				esc.write(p.getDni());
				esc.newLine();
				esc.write(p.getNombre());
				esc.newLine();
				esc.write(String.valueOf(p.getEdad()));
				esc.newLine();
				esc.write(String.valueOf(p.getCodPos()));
				esc.newLine();
				e.nextLine(); //saltar línea para vaciar buffer.

				p= new Persona();
				do {
					System.out.println("Teclee un DNI (fin para terminar)");
					p.setDni(e.nextLine());
				}while(!p.controlDNI(p.getDni()) && !p.getDni().equalsIgnoreCase("fin"));

			}
			esc.close();
		}catch(IOException ioe) {}
	}

	static String tabular(String n)	{
		String t="\t\t";
		if(n.length()<8)
			t="\t\t\t";
		return t;
	}
	static void listado() {
		Persona p;
		DecimalFormat df = new DecimalFormat("00000");
		System.out.println("\t\tLISTADO\n\t\t=======\n");
		System.out.println("DNI\tNombre\tEdad\tCód.Postal");
		try {
			BufferedReader lee= new BufferedReader(new FileReader(ruta +"personas2.txt"));
			p= new Persona();
			p.setDni(lee.readLine());
			while (p.getDni()!=null) {
				p.setNombre(lee.readLine());
				p.setEdad(Integer.parseInt(lee.readLine()));
				p.setCodPos(Integer.parseInt(lee.readLine()));
				System.out.println(p.getDni()+"\t"+p.getNombre()+tabular(p.getNombre())+p.getEdad()+"\t"+df.format(p.getCodPos()));
				p.setDni(lee.readLine());
			}
			lee.close();
		}catch(IOException ioe) {}
	}

	static int contarRegistros() {
		int nr = 0;
		String dni = null;
		try {
			BufferedReader lec = new BufferedReader(new FileReader (ruta+"personas2.txt"));
			dni = lec.readLine();
			while(dni != null) {
				lec.readLine();
				lec.readLine();
				lec.readLine();
				lec.readLine();
				nr++;
				dni = lec.readLine();
			}
			lec.close();
		}catch(IOException ioe) {}
		return nr;
	}
	static void llenarArray(Persona pe[]) {
		int i = 0;
		try {
			BufferedReader lec = new BufferedReader(new FileReader(ruta+"personas2.txt"));
			for(i=0;i<pe.length;i++) {
				pe[i] = new Persona();
				pe[i].setDni(lec.readLine());
				pe[i].setNombre(lec.readLine());
				pe[i].setEdad(Integer.parseInt(lec.readLine()));
				pe[i].setCodPos(Integer.parseInt(lec.readLine()));
			}
			lec.close();
		}catch(IOException ioe) {}
	}
	static int buscarPersona(String cb,Persona p[]) {
		int i = 0;
		i=0;
		while(!cb.equalsIgnoreCase(p[i++].getDni()) && i < p.length);
		if(cb.equalsIgnoreCase(p[i-1].getDni()))
			i--;
		return i;
	}
	static void grabarPersona(Persona p[]) {
		System.out.println();
		try {
			BufferedWriter grabar = new BufferedWriter(new FileWriter(ruta + "personas2.txt", false));
			for (int i = 0;i<p.length;i++) {
				grabar.write(p[i].getDni());
				grabar.newLine();
				grabar.write(p[i].getNombre());
				grabar.newLine();
				grabar.write(Integer.toString(p[i].getEdad()));
				grabar.newLine();
				grabar.write(Integer.toString(p[i].getCodPos()));
				grabar.newLine();
			}
			grabar.close();
		}catch(IOException ioe) {}
	}
	static void modificaciones(Scanner e) {
		String dniBus=null;
		int ip=0,cm=0;
		char otro=' ';
		Persona vp[]= new Persona[contarRegistros()];
		llenarArray(vp);
		Persona pN= new Persona();
		do {
			System.out.println("MODIFICACIONES....");
			System.out.println("¿DNI de la persona a modificar?");
			dniBus=e.nextLine();		
			ip=buscarPersona(dniBus, vp);
			if(ip==vp.length)
				System.out.println("La persona con DNI /"+dniBus+"\" no existe.");
			else {
				System.out.println("Nombre............"+vp[ip].getNombre()+"\nEdad............"+vp[ip].getEdad()+"\nCod Postal............"+vp[ip].getCodPos());
				pN.setNombre(vp[ip].getNombre());
				pN.setEdad(vp[ip].getEdad());
				pN.setCodPos(vp[ip].getCodPos());	
				do {
					do {
						System.out.println("Teclear campo a modificar(1-3)?");
						cm=e.nextInt();		
						e.nextLine();								
					}while(cm<1||cm>3);
					switch(cm) {
					case 1:
						do {
							System.out.println("Teclee el nuevo nombre.");
							pN.setNombre(e.nextLine());
						}while(pN.getNombre().length()>15);
						break;
					case 2:
						do {
							System.out.println("Teclee la nueva edad");
							try {
								pN.setEdad(e.nextInt());
							}catch(InputMismatchException ime) {
								pN.setEdad(Integer.MAX_VALUE);
							}
						}while(pN.getEdad()==Integer.MAX_VALUE);	
						break;
					default:
						do {
							System.out.println("Teclee el nuevo código postal");
							try {
								pN.setCodPos(e.nextInt());
							}catch(NumberFormatException nfe) {
								pN.setCodPos(Integer.MAX_VALUE);
							}
						}while(pN.getCodPos()==Integer.MAX_VALUE);	
						break;
					}
					do {
						System.out.println("Modificar otro campo¿? (S/N)");
						otro=Character.toUpperCase(e.next().charAt(0));
					}while(otro !='S' && otro!='N');
				}while(otro=='s');
				do {
					System.out.println("Confirmar modificaciones¿? (S/N) ");
					otro=Character.toUpperCase(e.next().charAt(0));
				}while(otro !='S' && otro!='N');	
				if(otro=='s') {
					vp[ip]= new Persona(dniBus,pN.getNombre(),pN.getEdad(),pN.getCodPos());
//					vp[ip]= pN;
				}
				do {
					System.out.println("Modificar otro registro(S/N)");
					otro=Character.toUpperCase(e.next().charAt(0));
				}while(otro !='S' && otro!='N');
			}
			grabarPersona(vp);
		}while (otro=='s');
	}
	static void fin() {
		System.out.println("Fin del programa.");
	}
	public static void main (String []args) {
		Scanner e= new Scanner(System.in);
		int op=0;
		do {
			op=menu(e);
			switch(op) {
			case 1:
				altas(e);
				break;
			case 2:
				listado();
				break;
			case 3:
				modificaciones(e);
				break;
			default:
				fin();
			}
		}while(op!=4);
	}
}