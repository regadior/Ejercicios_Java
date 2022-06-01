package examen;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.InputMismatchException;
public class EjercicioExamen2{
	//static final String ruta="C:\\Datos\\";
	static final String ruta="C:\\Users\\DA-1.PUESTO222\\Desktop\\ejercicios programación\\textos\\articulo.txt\\";
	public static void main(String []args) {
		Scanner e= new Scanner(System.in);
		int op=0;
		op=menu(e);
		switch(op) {
		case 1:
			altas(e);
			break;
		case 2:
			factura();
			break;
		case 3:
			modificarFactura(e);
		default:
			fin();
		}
	}
	static void fin() {
		System.out.println("Fin del programa.");
	}
	static int menu(Scanner e) {
		int op=0;
		do {
			try {
				System.out.println("\n\tMENÚ\n\t====");
				System.out.println("1.Altas.");
				System.out.println("2.Factura.");
				System.out.println("3.Modificación factura.");
				System.out.println("4.Fin.");
				System.out.println("Teclee opción (1-4)");
				op=e.nextInt();
			}catch(InputMismatchException ime) {
				op=Integer.MAX_VALUE;
			}
		}while(op<1||op>4);
		return op;
	}
	static void altas(Scanner e) {
		String codigo=null,denominacion=null,uniVendidas=null,precio=null;
		char tipo=' ';
		boolean correcto=true;
		try {
			BufferedWriter escr=new BufferedWriter(new FileWriter(ruta+"articulo.txt"));	
			System.out.println("ALTAS");
			do {
				System.out.println("Teclee código (* para terminar)");
				codigo=e.nextLine();
			}while(codigo.length()!=6);
			while(!codigo.equals("*")){
				do {
					System.out.println("Teclee denominación");
					denominacion=e.nextLine();
				}while(denominacion.length()>15);
				do {
					System.out.print("Teclee precio: ");
					precio = e.nextLine();
					try {
						Float.parseFloat(precio);						
					}catch(NumberFormatException nfe) {
						correcto=false;
					}
				}while(!correcto);
				do {
					try {
						System.out.println("Teclee tipo");
						tipo=Character.toUpperCase((char)System.in.read());
					}catch(IOException ioe) {}
				}while(tipo!='A'&& tipo!='M' && tipo!='C');
				do {
					System.out.print("Teclee unidades: ");
					uniVendidas = e.nextLine();
					try {
						Integer.parseInt(uniVendidas);						
					}catch(NumberFormatException nfe) {
						correcto=false;
					}
				}while(!correcto);
				escr.write(codigo);
				escr.newLine();
				escr.write(denominacion);
				escr.newLine();
				escr.write(precio);
				escr.newLine();
				escr.write(tipo);
				escr.newLine();
				escr.write(uniVendidas);
				escr.newLine();
				e.nextLine();
				do {
					System.out.println("Teclee código (* para terminar)");
					codigo=e.nextLine();			
				}while(codigo.length()!=6);
			}
			escr.close();
		}catch(IOException ioe) {
			System.out.println("La ruta indicada no es válida.");
		}
	}
	static void factura() {
		String codigo=null,denominacion=null,uniVendidas=null,precio=null,tipo=null;
		try {
			BufferedReader lee=new BufferedReader(new FileReader(ruta+"articulo.txt"));
			System.out.println("Código\tDenominación\tPrec. Uni\tTipo\tPrecio\tIVA\tPrecio+IVA");
			codigo=lee.readLine();
			denominacion=lee.readLine();
			precio=lee.readLine();
			tipo=lee.readLine();
			uniVendidas=lee.readLine();
			lee.close();
			while(codigo!=null) {
				System.out.println(codigo+"\t"+denominacion+"\t"+precio+"\t"+uniVendidas+"\t"+(Float.parseFloat(precio)*Integer.parseInt(uniVendidas))+
						"\t"+tipo+"\t"+(Integer.parseInt(precio)*tipo.charAt(0))+"\t"+(precio+(Integer.parseInt(precio)*tipo.charAt(0))));
			}
		}catch(IOException ioe) {
			System.out.println("No existe fichero en la ruta indicada.");
		}
	}
	static int numeroRegistros() {
		String codigo=null;
		int nr=0;
		try {
			BufferedReader lee= new BufferedReader(new FileReader(ruta + "articulo.txt"));
			codigo=lee.readLine();
			while(codigo!=null) {
				lee.readLine();
				lee.readLine();
				lee.readLine();
				lee.readLine();
				nr++;
				codigo=lee.readLine();
			}
			lee.close();
		}catch(IOException ioe) {
			System.out.println("No existe fichero en la ruta indicada.");
		}
		return nr;
	}
	static void cargaVArticulos(Articulo ar[]) {
		String codigo=null,denominacion=null,precio=null,tipo=null,uniVendidas=null;
		float p=0.0f;
		char t=0;
		int u=0,i=0;
		try {
			BufferedReader lee= new BufferedReader(new FileReader(ruta+"articulo.txt"));
			codigo=lee.readLine();
			while(codigo!=null) {
				denominacion=lee.readLine();
				precio=lee.readLine();
				tipo=lee.readLine();
				uniVendidas=lee.readLine();
				p=Float.parseFloat(precio);
				t=tipo.charAt(0);		
				u=Integer.parseInt(uniVendidas);
				ar[i++]= new Articulo(codigo,denominacion,p,t,u);
				codigo=lee.readLine();
			}		
			lee.close();
		}catch(IOException ioe) {}
		for(i=0;i<ar.length;i++) {
			System.out.println(ar[i].getCodigo()+ar[i].getDenominacion()+ar[i].getPrecio()+ar[i].getTipo()+ar[i].getUniVendidas());
		}

	}
	static int buscarArticulo(String cb,Articulo ar[]) {
		int i=0;
		while(!cb.equalsIgnoreCase(ar[i++].getCodigo()) && i<ar.length);
		if(i==ar.length) i++;
		return --i;
	}
	static void modificarFactura(Scanner e) {
		String codigoBus=null,denomN=null;
		float precioN=0.0f;
		char tipoN=' ';		
		int uniVendidasN,ia=0,cm=0;
		char otro=' ';
		boolean correcto= true;
		Articulo ar[]= new Articulo[numeroRegistros()];
		cargaVArticulos(ar);
		System.out.println("MODIFICACIONES....");	
		do {
			System.out.println("¿Código del artículo a modificar?");
			codigoBus=e.nextLine();		
			ia=buscarArticulo(codigoBus, ar);
			if(ia==ar.length)
				System.out.println("El artículo /"+codigoBus+"\" no existe.");
			else {
				System.out.println("Código............"+ar[ia].getCodigo()+"\nDenominacion............"+
						ar[ia].getDenominacion()+"\nPrecio............"+ar[ia].getPrecio()+"\nTipo............"+
						ar[ia].getTipo()+"\nUnidades vendidas............"+ar[ia].getUniVendidas());
				denomN=ar[ia].getDenominacion();
				precioN=ar[ia].getPrecio();			
				tipoN=ar[ia].getTipo();
				uniVendidasN=ar[ia].getUniVendidas();			
				do {
					do {
						System.out.println("Teclear campo a modificar(1-4)?");
						cm=e.nextInt();		
						e.nextLine();								
					}while(cm<1||cm>4);
					switch(cm) {
					case 1:
						do {
							System.out.println("Teclee la nueva denominación.");
							denomN=e.nextLine();
						}while(denomN.length()>15);
						break;
					case 2:
						do {
							System.out.println("Teclee el nuevo precio");
							try {
								precioN=e.nextFloat();
							}catch(NumberFormatException nfe) {
								correcto= false;
							}
						}while(!correcto);	
						break;
					case 3:
						do {
							System.out.println("Teclee el nuevo tipo.");
							tipoN=Character.toUpperCase(e.next().charAt(0));
						}while(tipoN!='A' && tipoN!='B'&& tipoN!='C');
						break;				
					case 4:
						do {
							System.out.println("Teclee las nuevas unidades vendidas.");
							try {
								uniVendidasN=e.nextInt();
							}catch(NumberFormatException nfe) {
								correcto= false;
							}
						}while(!correcto);	
						break;				
					}					
					do {
						System.out.println("Confirmar modificaciones¿? (S/N) ");
						otro=Character.toUpperCase(e.next().charAt(0));
					}while(otro !='S' && otro!='N');	
					if(otro=='s') {
						ar[ia]= new Articulo(codigoBus,denomN,precioN,tipoN,uniVendidasN);
					}
					do {
						System.out.println("Modificar otro registro(S/N)");
						otro=Character.toUpperCase(e.next().charAt(0));
					}while(otro !='S' && otro!='N');
					grabarFichero(ar);
				}while (otro=='s');		
			}
		}while (otro=='s' && ia!=ar.length);	
	}

	static void grabarFichero(Articulo ar[]) {
		System.out.println();
		try {
			BufferedWriter grabar= new BufferedWriter(new FileWriter(ruta +"articulo.txt"));
			for(int i=0;i<ar.length;i++) {
				grabar.write(ar[i].getCodigo());
				grabar.newLine();
				grabar.write(ar[i].getDenominacion());
				grabar.newLine();
				grabar.write(Float.toString(ar[i].getPrecio()));
				grabar.newLine();
				grabar.write(ar[i].getTipo());
				grabar.newLine();
				grabar.write(Integer.toString(ar[i].getUniVendidas()));
				grabar.newLine();
			}
			grabar.close();
		}catch(IOException ioe) {}
	}
}