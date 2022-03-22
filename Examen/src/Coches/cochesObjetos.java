package Coches;
import java.util.*;
import java.io.IOException;
public class cochesObjetos{
	static int menu(Scanner e) {
		int op=0;
		System.out.println("\n\tMEN� PRINCIPAL\n\t==========");
		System.out.println("1.Altas.");
		System.out.println("2.Modificaciones.");
		System.out.println("3.Consultas.");
		System.out.println("4.Listados.");
		System.out.println("5.Fin.");
		do {
			System.out.print("\n\tTeclee opci�n(1-5): ");
			op = e.nextInt();
		} while (op < 1 || op > 5);
		return op;
	}
	static void altas(Scanner e, Coches c[]) {
		System.out.println("ALTAS....");
		char gama=' ';
		for(int i=0;i<4;i++) {
			c[i]= new Coches();
			do {	
				System.out.println("�Matr�cula?");
				c[i].setMatricula(e.next());		
			}while(!c[i].comprobarLongitudMatricula());
			do {
				System.out.println("�Marca?");
				c[i].setMarca(e.next());	
			}while(!c[i].comprobarLongitudMarca());
			do {
				System.out.println("�Modelo?");
				c[i].setModelo(e.next());
			}while(!c[i].comprobarLongitudModelo());
			do {
				try {
					System.out.println("�Gama?");
					gama=Character.toUpperCase((char)System.in.read());
				}catch(IOException ioe) {}
			}while(gama!='a'&&gama!='m'&&gama!='b');
			c[i].literalGama(gama);
			do {
				try {
					System.out.println("�Precio?");
					c[i].setPrecio(e.nextDouble());
				}catch(InputMismatchException ime){
					c[i].setPrecio(Double.MAX_VALUE);
					e.nextLine();					
				}
			}while(c[i].getPrecio()==Double.MAX_VALUE);
			System.out.println("\n");
		}
	}
	static void modificaciones(Scanner e,Coches c[]) {
		e= new Scanner(System.in);
		char op=' ',gama=' ';
		int cm=0,i=0;
		char otro=' ',confirmar=' ';
		String matricula="",marca="",modelo="",litGama="";
		double precio=0;
		boolean falso=true;
		do {
			do {
				System.out.println("MODIFICACIONES....");	
				System.out.println("Matr�cula del coche a modificar�?");
				matricula=e.next();
			}while(matricula.length()!=7);
			for(i=0;i<c.length;i++) {
				try {
					if(matricula.equalsIgnoreCase(c[i].getMatricula())) {
						marca =c[i].getMarca();
						modelo=c[i].getModelo();
						c[i].literalGama(gama);
						precio=c[i].getPrecio();
						System.out.println("Marca............"+marca+"\nModelo............"+modelo+"\nGama............"+gama+"\nPrecio............"+precio);
						break;
					}
				}catch(NullPointerException npe){
					System.out.println("Array vac�o...\n");
					i=4;
				}
				if(i!=5) {
					if(i==4)
						System.out.println("\n El coche con la matr�cula "+matricula+" no existe.");
					else {
						do {
							do {
								System.out.println("Teclear campo a modificar(1-4)?");
								try {
									cm=e.nextInt();
								}catch(InputMismatchException ime) {
									cm=Integer.MAX_VALUE;
								}								
								e.nextLine();								
							}while(cm<1||cm>4||cm==Integer.MAX_VALUE);
							switch(cm) {
							case 1:
								do {
									System.out.println("Teclee la nueva marca.");
									marca=e.nextLine();
								}while(marca.length()>10);
								break;
							case 2:
								do {
									System.out.println("Teclee el nuevo modelo.");
									modelo=e.nextLine();
								}while(modelo.length()>15);								
								break;
							case 3:
								do {
									try {
										System.out.println("Teclee la nueva gama (A/M/B).");
										gama=Character.toUpperCase((char)System.in.read());
										while(System.in.read()!='\n');
									}catch(IOException npe){}
								}while(gama!='A' && gama!='M'&& gama!='B');
								break;
							default:
								do {		
									try {
										System.out.println("Teclee el nuevo precio.");
										precio=e.nextDouble();
									}catch(InputMismatchException ime){
										precio=Double.MAX_VALUE;
										e.nextLine();}
								}while(precio==Double.MAX_VALUE);								
								break;
							}
							do {
								try {
									System.out.println("Modificar otro campo�? (S/N)");
									otro=Character.toLowerCase((char)System.in.read());
									while(System.in.read()!='\n');
								}catch(IOException npe){}
							}while(otro !='s' && otro!='n');
							if (otro == 's') {
									c[i].setGama(gama);
								c[i] = new Coches(matricula,marca,modelo,litGama,precio);
							}
						}while(otro=='s');
						do {
							try {
								System.out.println("Modificar otro coche�? (S/N) ");
								otro=Character.toLowerCase((char)System.in.read());
								while(System.in.read()!='\n');
							}catch(IOException npe){}
						}while(otro !='s' && otro!='n');
					}

				}
			}
		}while(otro=='s'&& i!=5);
	}		
	static void consultas(Coches c[]) {
		Scanner e=new Scanner(System.in);
		String matricula="";
		char op=' ';
		do {
			System.out.println("CONSULTAS....");	
			System.out.println("Matr�cula del coche a consultar�?");


			for(int i=0;i<4;i++) {
				try {
					do {
						matricula=e.nextLine();				

						if(matricula.equalsIgnoreCase(c[i].getMatricula())){
							System.out.println("Marca............"+c[i].getMarca()+"\nModelo............"+c[i].getModelo()+"\nGama............"+c[i].getGama()+"\nPrecio............"+c[i].getPrecio());
						}
						else{
							i++;
						}
					}while(matricula.length()!=7);

				}catch(NullPointerException npe) {
					System.out.println("No hay coches dados de alta.");
				}	
				System.out.println("Desea hacer otra consulta(S/N)�?");
				op=e.next().charAt(0);
			}
			e.close();
		}while(op!='n');
	}
	static void listados(Coches c[]) {
		System.out.println("\t\tLISTADO\n\t\t=======\n");
		for (int i = 0; i<c.length;i++) 
			try {
				System.out.println(c[i].getMatricula()+"\t "+c[i].getMarca()+"\t\t"+c[i].getModelo()+"\t\t"+c[i].getGama()+"\t\t"+c[i].getPrecio()+"\t\t");
			}catch(NullPointerException npe) {
				System.out.println("No hay coches dados de alta.");
				break;
			}
	}
	static void fin() {
		System.out.println("FIN DEL PROGRAMA.");
	}
	public static void main(String []args) {
		Scanner e= new Scanner(System.in);
		Coches c[]= new Coches[4];
		int opcion=0;
		do {
			opcion=menu(e);
			switch(opcion) {
			case 1:
				altas(e,c);
				break;
			case 2:
				modificaciones(e,c);
				break;
			case 3:
				consultas(c);
				break;
			case 4:
				listados(c);
				break;
			case 5:
				fin();
				break;
			}
		}while(opcion!=5);
		e.close();
	}
}