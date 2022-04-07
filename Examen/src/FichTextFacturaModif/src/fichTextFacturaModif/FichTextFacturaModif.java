package fichTextFacturaModif;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;



public class FichTextFacturaModif {
	static  final String ruta = "C:\\Users\\Profesor\\JavaDAM_1\\";
	static int menu(Scanner e) {
		int op = 0;
		System.out.println("\n\tMENU\n\t====\n");
		System.out.println("1.- Altas.");
		System.out.println("2.- Factura.");
		System.out.println("3.- Modificaci�n Factura");
		System.out.println("4.- Fin.\n");
		do {
			try {
				System.out.print("\tTeclee opci�n (1-4)? ");
				op = e.nextInt();
			}catch(InputMismatchException ime) {
				op = 10;
				e.nextLine();
			}
		}while(op<1 || op>4);
		return op;
	}
	static void altas(Scanner e) {
		String codigo = null,denominacion = null,precio = null, unidVend = null;
		float prec = 0;
		int unVe = 0;
		char tipo = ' ';
		e.nextLine();
		try{
			BufferedWriter esc = new BufferedWriter(new FileWriter (ruta+"articulos.txt",true));
			System.out.println("\n\tALTAS\n\t=====\n");
			do {
				System.out.print("C�digo (m�ximo 6 caracteres) (\"*\" para fin)?.....: ");
				codigo = e.nextLine();
			}while(codigo.length()!=6 && !codigo.equals("*"));
			while(!codigo.equals("*")){
				do {
					System.out.print("Denominaci�n (m�ximo. 15 caracteres)? ");
					denominacion = e.nextLine();
				}while(denominacion.length()>15);
				do {
					System.out.print("Precio? ");
					precio = e.next();
					try {
						prec = Float.parseFloat(precio);
					}catch(NumberFormatException nfe) {
						prec = Float.MAX_VALUE;
					}
				}while(prec == Float.MAX_VALUE);
				try {
					do {
						System.out.print("Tipo (A/B/C)? ");
						tipo = Character.toUpperCase((char) System.in.read());
						while(System.in.read()!='\n');
					}while(tipo != 'A' && tipo != 'B' && tipo != 'C');
				}catch(IOException ioe) {}
				do {
					System.out.print("Unidades vendidas? ");
					unidVend = e.next();
					try {
						unVe = Integer.parseInt(unidVend);
					}catch(NumberFormatException nfe) {
						unVe = Integer.MAX_VALUE;
					}
				}while(unVe == Integer.MAX_VALUE);
				esc.write(codigo);
				esc.newLine();
				esc.write(denominacion);
				esc.newLine();
				esc.write(precio);
				esc.newLine();
				esc.write(tipo);
				esc.newLine();
				esc.write(unidVend);
				esc.newLine();
				e.nextLine();
				do {
					System.out.print("C�digo (m�ximo 6 caracteres) (\"*\" para fin)?.....: ");
					codigo = e.nextLine();
				}while(codigo.length()!=6 && !codigo.equals("*"));
			}
			esc.close();
		}catch(IOException ioe) {}
	}
	static float calculoIva(float p, char t) {
		float iva = 0;
		switch(t) {
		case 'A':
			iva = (float) 0.21;
			break;
		case 'B':
			iva = (float) 0.10;
			break;
			default:
				iva = (float) 0.04;
		}
		
		return p*iva;
	}
	static String tabular(String cad) {
		String t = "\t\t";
		if (cad.length()<8)
			t = "\t\t\t";
		return t;
	}
	static void crearFactura() {
		DecimalFormat numero = new DecimalFormat("######.##");
		String codigo = null, denominacion = null;
		float precio = 0,impIva = 0,precioB = 0;
		char tipo = ' ';
		int uniVend = 0;
		try {
			BufferedReader lec = new BufferedReader(new FileReader (ruta+"articulos.txt"));
			System.out.println("\n\t\t\t\t\tFactura\n\t\t\t\t\t-------\n");
			System.out.println("C�digo\tDenominaci�n\t\tPrec. Uni.\tUnid. Vend.\tTipo\tPrecio\t\tIVA\tPrecio + IVA");
			System.out.println("--------------------------------------------------------------------------------");
			codigo = lec.readLine();
			while (codigo != null) {
				denominacion = lec.readLine();
				precio =  Float.parseFloat(lec.readLine());
				tipo = lec.readLine().charAt(0);
				uniVend = Integer.parseInt(lec.readLine());
				precioB = precio*uniVend;
				impIva = calculoIva(precioB,tipo);
				System.out.print(codigo +"\t"+denominacion +tabular(denominacion));
				System.out.print(numero.format(precio)+"\t\t\t"+numero.format(uniVend)+"\t"+tipo+"\t");
				System.out.println(numero.format(precioB)+"\t\t"+numero.format(impIva)+"\t"+"\t"+numero.format(precioB+impIva));
				codigo = lec.readLine();
			}
			lec.close();
		}catch(IOException ioe) {}
	}
	static int contarRegistros() {
		int nr = 0;
		String codigo = null;
		try {
			BufferedReader lec = new BufferedReader(new FileReader (ruta+"articulos.txt"));
			codigo = lec.readLine();
			while(codigo != null) {
				lec.readLine();
				lec.readLine();
				lec.readLine();
				lec.readLine();
				nr++;
				codigo = lec.readLine();
			}
			lec.close();
		}catch(IOException ioe) {}
		return nr;
	}
	static void llenarArray(Articulo [] a) {
		int i = 0,uv=0;
		String c = null, d = null;
		float p = 0;
		char t = ' ';
		try {
			BufferedReader lec = new BufferedReader(new FileReader(ruta+"articulos.txt"));
			for(i=0;i<a.length;i++) {
//				c = lec.readLine();
//				d = lec.readLine();
//				p = Float.parseFloat(lec.readLine());
//				t = lec.readLine().charAt(0);
//				uv = Integer.parseInt(lec.readLine());
//				a[i] = new Articulo(c,d,p,t,uv);
	//			a[i] = new Articulo(lec.readLine(),lec.readLine(),Float.parseFloat(lec.readLine()),lec.readLine().charAt(0),Integer.parseInt(lec.readLine()));
				a[i] = new Articulo();
				a[i].setCodigo(lec.readLine());
				a[i].setDenominacion(lec.readLine());
				a[i].setPrecio(Float.parseFloat(lec.readLine()));
				a[i].setTipo(lec.readLine().charAt(0));
				a[i].setUniVendidas(Integer.parseInt(lec.readLine()));
			}
			lec.close();
		}catch(IOException ioe) {}
//		for(i=0;i<a.length;i++)
//			System.out.println(a[i].getCodigo()+"\t"+a[i].getDenominacion()+"\t"+a[i].getPrecio()+"\t"+a[i].getUniVendidas()+"\t"+a[i].getTipo());
	}
	static int buscarArticulo(String cb,Articulo a[]) {
		int i = 0;
		i=0;
		while(!cb.equalsIgnoreCase(a[i++].getCodigo()) && i < a.length);
		if(cb.equalsIgnoreCase(a[i-1].getCodigo()))
			i--;
		return i;
	}
	static void grabarFichero(Articulo a[]) {
		System.out.println();
		try {
			BufferedWriter grabar = new BufferedWriter(new FileWriter(ruta + "articulos.txt", false));
			for (int i = 0;i<a.length;i++) {
				grabar.write(a[i].getCodigo());
				grabar.newLine();
				grabar.write(a[i].getDenominacion());
				grabar.newLine();
				grabar.write(Float.toString(a[i].getPrecio()));
				grabar.newLine();
				grabar.write(a[i].getTipo());
				grabar.newLine();
				grabar.write(Integer.toString(a[i].getUniVendidas()));
				grabar.newLine();
			}
			grabar.close();
		}catch(IOException ioe) {}
	}
	static void modificarFactura(Scanner e) {
		int numReg = contarRegistros(), ia = 0, uniVendidasN = 0, cm = 0;
		String codigoBuscar = null, denominacionN = null;
		float precioN = 0;
		char tipoN = ' ', otro = ' ';
		Articulo varticulo[] = new Articulo[numReg];
		llenarArray(varticulo);
		System.out.print("\n\tMODIFICACIONES\n\t==============\n");
		do {
			do {
				System.out.print("C�digo de art�culo? ");
				codigoBuscar = e.next();
			}while(codigoBuscar.length() != 6);
			ia = buscarArticulo(codigoBuscar,varticulo);
//			System.out.println("ia = "+ia+"\tvarticulo.length = "+varticulo.length);
			if(ia == varticulo.length)
				System.out.println("El art�culo con codigo \""+codigoBuscar+"\" no existe.\n");
			else {
				denominacionN = varticulo[ia].getDenominacion();
				precioN = varticulo[ia].getPrecio();
				tipoN = varticulo[ia].getTipo();
				uniVendidasN = varticulo[ia].getUniVendidas();
				System.out.println("1.- Denominaci�n.......: "+denominacionN);
				System.out.println("2.- Precio.............: "+precioN);
				System.out.println("3.- Tipo...............: "+tipoN);
				System.out.println("4.- Unidades Vendidas..: "+uniVendidasN);			
				do {
					do {
						try {
							System.out.print("\tTeclee campo a modificar(1-4)? ");
							cm = e.nextInt();
							e.nextLine();
						}catch(InputMismatchException ime) {
							cm = 10;
							e.nextLine();
						}
						} while (cm < 1 || cm > 4);
					switch(cm) {
					case 1:
						do{
							System.out.print("\nTeclee nueva denominaci�n (m�x 15 caracteres)? ");
							denominacionN = e.nextLine();
						}while(denominacionN.length() > 15);
						break;
					case 2:
						do {
							try {
								System.out.print("Teclee nuevo precio? ");
								precioN = e.nextFloat();
							}catch(InputMismatchException ime) {
								precioN = Float.MAX_VALUE;
								}
							}while(precioN == Float.MAX_VALUE);
						break;
						case 3:
							try {
								do {
									System.out.print("Tipo nuevo (A/B/C)? ");
									tipoN = Character.toUpperCase((char) System.in.read());
									while(System.in.read()!='\n');
									}while("ABC".indexOf(tipoN)==-1);
								}catch(IOException ioe) {}
							break;
							default:
								System.out.print("Teclee nuevas unidades vendidas? ");
								uniVendidasN = e.nextInt();
								}
					do {
						System.out.print("Modificar otro campo (s/n)? ");
						otro = Character.toLowerCase(e.next().charAt(0));
						} while (otro != 's' && otro != 'n');
					}while(otro=='s');
				do {
					System.out.print("Confirmar modificaciones (s/n)? ");
					otro = Character.toLowerCase(e.next().charAt(0));
					} while (otro != 's' && otro != 'n');
				if(otro == 's') {
					varticulo[ia] = new Articulo(codigoBuscar,denominacionN,precioN, tipoN, uniVendidasN);
					}
				}
			do {
				System.out.print("Modificar otro registro (s/n)? ");
				otro = Character.toLowerCase(e.next().charAt(0));
				} while (otro != 's' && otro != 'n');	
			e.nextLine();
			}while(otro=='s');
		grabarFichero(varticulo);
	}
	static void fin() {
		System.out.println("\n\n\tFINAL DEL PROGRAMA\n\t==================\n");
	}
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		int opcion = 0;
		do {
			opcion = menu(entrada);
			switch(opcion) {
			case 1:
				altas(entrada);
				break;
			case 2:
				crearFactura();
				break;
			case 3:
				modificarFactura(entrada);
				break;
				default:
					fin();
			}
		}while(opcion != 4);
		entrada.close();
	}
}
