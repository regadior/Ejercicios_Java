package mant_02;
import java.io.IOException;
import java.io.RandomAccessFile;
public class MantArt_01{
	static final String ruta="G:\\Mi unidad\\PROGRAMACIÓN\\EJERCICIOS\\3ª EV\\textos\\";
	String menu() throws IOException {
		Teclado t = new Teclado();
		System.out.println("\n\tMENU\n\t====\n");
		System.out.println("A.- Altas.");
		System.out.println("B.- Bajas.");
		System.out.println("M.- Modificaciones.");
		System.out.println("C.- Consultas. ");
		System.out.println("L.- Listados.");
		System.out.println("F.- Fin.");
		System.out.print("\n\n\tTeclee opción:  ");
		String op = t.leerString();
		return op;
	}
	int menuListados()throws IOException {
		Teclado t = new Teclado();
		System.out.println("\n\tMENU\n\t====\n");
		System.out.println("1.- General.");
		System.out.println("2.- Entre límites.");
		System.out.println("3.- Pedidos.");
		System.out.println("4.- Volver al menú principal. ");
		System.out.print("\n\n\tTeclee opción:  ");
		int op = t.leerInt();
		return op;
	}
	public static void main(String []args) throws IOException{
		MantArt_01 ma= new MantArt_01(); //instancio la propia clase para moverme por los mï¿½todos de la propia clase ahorrï¿½ndome los static
		//evitando conflictos con los nombres de otras clases ya que especificas quï¿½ clase es.
		String op;int opl;
		boolean fin=false;
		RandomAccessFile fs=new RandomAccessFile (ruta+"articulos.dat","rw"); // si lo abres de rw y no existe cuando lo lees lo crea
		fs.close(); // asï¿½ te aseguras con certeza de que el fichero existe.-
		while (!fin) {
			op = ma.menu();
			if(op.equalsIgnoreCase("A")) ma.altas();
			if(op.equalsIgnoreCase("B")) ma.bajas();
			if(op.equalsIgnoreCase("M")) ma.modificaciones();
			if(op.equalsIgnoreCase("C")) ma.consultas();
			if(op.equalsIgnoreCase("L")) {
				do {
					opl =ma.menuListados();
					switch(opl) {
					case 1:
						ma.listadoGeneral();
						break;
					case 2:
						ma.listadoEntreLimites();
						break;
					case 3:
						ma.listadoPedidos();
						break;
					}				
				}while(opl!=4);
			}
			if(op.equalsIgnoreCase("F")) {
				ma.fin();
				fin=true; //solamente estï¿½ a verdadero cuando pulso f y salgo del while.
			}
		}
	}
	void altas()throws IOException{
		Articulo av = new Articulo (0," ",0,0,0,0,' '),a = new Articulo();
		Teclado t = new Teclado();
		int codigo=0;
		String denominacion=null;
		double stockAct= 0, stockMin= 0,stockMax = 0;
		float precio=0;
		char otro=' ';
		RandomAccessFile fich = new RandomAccessFile(ruta+"articulos.dat","rw");
		System.out.println("\n\tALTAS\n\t");
		do {
			System.out.print("Código (0 = Fin) ");
			codigo= t.leerInt();
		}while(codigo==Integer.MIN_VALUE);
		while(codigo!=0) { // si no quieres salir no tecleas cero.
			fich.seek(codigo * av.tamano());
			a.leerDeArchivo(fich);
			if(a.getCodigo()!=0)
				System.out.println("\n\tEl registro ya existe.....\n");
			else {
				do {
					System.out.print("Denominación.....");
					denominacion=t.leerString();
				}while(denominacion.length()>20);
				do {
					System.out.print("Stock actual.....");
					stockAct=t.leerDouble();
				}while(stockAct==Double.MIN_VALUE);
				do {
					do {
						System.out.print("Stock mínimo.....");
						stockAct=t.leerDouble();
					}while(stockMin==Double.MIN_VALUE);
					do {
						System.out.print("Stock máximo.....");
						stockMax=t.leerDouble();
					}while(stockMax==Double.MIN_VALUE);
				}while(stockMax<stockMin);
				do {
					System.out.print("Precio.....");
					precio=t.leerFloat();
				}while(precio<0);					
				do {
					System.out.println("Confirmar alta (s/n)");
					otro=Character.toLowerCase(t.leerChar());
				}while(otro!='s' && otro!='n');
				if(otro=='s') {
					a= new Articulo(codigo,denominacion,stockAct,stockMin,stockMax,precio,' ');
					if(codigo * a.tamano()>fich.length())
						fich.seek(fich.length()); // como queda mucho recorrido te pones en la posicion "final"
					while(codigo*a.tamano()>fich.length()) //mientras la direcciï¿½n sea mayor que la longitud
						av.grabarEnArchivo(fich); //como no puede haber espacios se crean registros en blanco (av es el archivo vacï¿½o)
					fich.seek(codigo * a.tamano()); // ya se dejï¿½ de cumplir el while por lo que ya estoy posicionado.
					a.grabarEnArchivo(fich);
				}
			}				
			do {
				System.out.print("Código (0 = Fin) ");
				codigo= t.leerInt();
			}while(codigo==Integer.MIN_VALUE);	
		}
		fich.close();		
	}
	void consultas() throws IOException{
		Articulo a = new Articulo (0," ",0,0,0,0,' ');
		Teclado t = new Teclado();
		int codBus=0;
		System.out.println("\n\tCONSULTAS\n\t");
		RandomAccessFile fich = new RandomAccessFile(ruta+"articulos.dat","r");	
		do {
			System.out.print("\nTeclee código del artículo ");
			codBus= t.leerInt();
		}while(codBus==Integer.MIN_VALUE);
		fich.seek(codBus * a.tamano());
		a.leerDeArchivo(fich);
		if(a.getCodigo()!=0)
			a.mostrarDatos(2);
		else
			System.out.println("\nNo existe ningún artículo con el número: "+codBus);
		fich.close();
		System.out.println("\n Teclee <Intro> para continuar.");
		t.leerSalto();
	}
	void bajas() throws IOException{
		Articulo a = new Articulo (0," ",0,0,0,0,' ');
		Teclado t = new Teclado();
		int codBus=0;
		char confirmar;
		RandomAccessFile fich = new RandomAccessFile(ruta+"articulos.dat","rw");
		System.out.println("\n\tBAJAS\n\t");
		do {
			System.out.print("\nTeclee código del artículo: ");
			codBus= t.leerInt();
		}while(codBus==Integer.MIN_VALUE);
		fich.seek(codBus *a.tamano());
		a.leerDeArchivo(fich);
		if(a.getCodigo()==0)
			System.out.println("\n\tEl artículo con el código "+codBus+" no existe.....\n");
		else {
			a.mostrarDatos(2);
			do {
				System.out.println("\n\tDesea borrar el registro (s/n)....\n");
				confirmar = Character.toLowerCase(t.leerChar());
			}while (confirmar!='s' || confirmar!='n');
			if (confirmar == 's') {
				a= new Articulo (0," ",0,0,0,0,' ');
				fich.seek(codBus *a.tamano());
				a.grabarEnArchivo(fich);
				System.out.println("\n\t Registro borrado correctamente.....");
			}
		}
		fich.close();
		System.out.println("\n Teclee <Intro> para continuar.");
		t.leerSalto();	
	}
	void modificaciones() throws IOException{
		Articulo a = new Articulo (0," ",0,0,0,0,' ');
		Teclado t = new Teclado();
		int codBus=0,cm=0;
		char otro;
		String denomN;
		double stockActN,stockMinN,stockMaxN;
		float precioN;
		RandomAccessFile fich = new RandomAccessFile(ruta+"articulos.dat","rw");
		do {
			System.out.println("\n\tMODIFICACIONES\n\t");
			do {
				System.out.print("\nTeclee número del artículo: ");
				codBus= t.leerInt();
			}while(codBus==Integer.MIN_VALUE);
			fich.seek(codBus * a.tamano());
			a.leerDeArchivo(fich);
			if(a.getCodigo()==0)
				System.out.println("\n\tEl artículo con el número "+codBus+" no existe.....\n");
			else {
				denomN=a.getDenominacion();
				stockActN=a.getStockAct();
				stockMinN=a.getStockMin();
				stockMaxN=a.getStockMax();
				precioN=a.getPrecio();
				a.mostrarDatos(1);
				do {
					do {
						System.out.println("Teclee campo a modificar (1-5)");
						cm=t.leerInt();
					}while(cm<1||cm>5);
					switch(cm){
					case 1:
						do {
							System.out.println("Teclee la nueva denominación....");
							denomN=t.leerString();
						}while (denomN.length()>20);
						break;
					case 2:
						do {
							System.out.print("Teclee el nuevo stock actual.....");
							stockActN=t.leerDouble();
						}while(stockActN==Double.MIN_VALUE);
						break;
					case 3:
						do {
							do {
								System.out.print("Teclee el nuevo stock mínimo.....");
								stockMinN=t.leerDouble();
							}while(stockMinN==Double.MIN_VALUE || stockMinN>a.getStockMax());
						}while(stockMinN>stockMaxN);
						break;
					case 4:
						do {
							do {
								System.out.print("Teclee el nuevo stock máximo.....");
								stockMaxN=t.leerDouble();
							}while(stockMaxN==Double.MIN_VALUE ||  stockMaxN<a.getStockMin());
							break;
						}while(stockMaxN<stockMinN);
						do {
							System.out.print("Teclee el nuevo precio.....");
							precioN=t.leerFloat();
						}while(precioN<0);
					}
					do {
						System.out.println("Otro campo a modificar (s/n)");
						otro=Character.toLowerCase(t.leerChar());
					}while(otro!='s' && otro!='n');
				}while(Character.toLowerCase(otro)=='s');
				do {
					System.out.println("Confirmar modificaciones (s/n)");
					otro=Character.toLowerCase(t.leerChar());
				}while(otro!='s' && otro!='n');
				if(otro=='s') {
					a= new Articulo(codBus,denomN,stockActN,stockMinN,stockMaxN,precioN,' ');
					fich.seek(codBus*a.tamano());
					a.grabarEnArchivo(fich);
				}
			}
			do {
				System.out.println("Modificar otro artículo (s/n)");
				otro=Character.toLowerCase(t.leerChar());
			}while(otro!='s' && otro!='n');
		}while(otro=='s');
		fich.close();
	}
	void listadoGeneral() throws IOException{
		Articulo a = new Articulo (0," ",0,0,0,0,' ');
		Teclado t = new Teclado();
		boolean fin =false;
		final int LINEAS=4;
		//valor 5 de nombre LINEAS por convenio es mayuscula.
		int cp=0,cl=LINEAS+1 ; //ca cont aprobados cp cont páginas cs cont suspensos y cl cont líneas vale 6 para saltar
		RandomAccessFile fich = new RandomAccessFile(ruta+"articulos.dat","r");
		System.out.println("Posición de puntero inicial..........:"+fich.getFilePointer());
		do {
			fin=a.leerDeArchivo(fich);
		}while(a.getCodigo()==0 &&!fin);
		do {
			while(!fin && cl < LINEAS) {
				if(a.getCodigo()!=0) {
					a.mostrarDatos(0);
					cl++;
				}
				fin=a.leerDeArchivo(fich);
				System.out.println("Posición de puntero inicial..........:"+fich.getFilePointer());
			}
			if(cl==LINEAS) {
				System.out.println("\n\t Pulse <Intro> para continuar");
				t.leerSalto();
			}
			if(!fin) {
				System.out.println("\t\tLISTADO\t\tPag.: "+ ++cp+"\n\t\t=======\n");
				System.out.println("Código\tDenominación\tStock actual\tStock mínimo.\tStock máximo\t\tApto");
				System.out.println("-----------------------------------------------------------------------");
				cl=0;
			}			
		}while(!fin);
		fich.close();
		System.out.println("\n\n\tFIN DEL LISTADO \n");
		System.out.println("\n\t Pulse <Intro> para continuar");	
		t.leerSalto();
	}
	void listadoEntreLimites() throws IOException {
		Articulo a = new Articulo (0," ",0,0,0,0,' ');
		Teclado t = new Teclado();
		boolean fin =false;
		final int LINEAS= 4;
		//valor 5 de nombre LINEAS por convenio es mayuscula.
		int cp=0,cl=LINEAS+1,li=0,ls=0; //ca cont aprobados cp cont páginas cs cont suspensos y cl cont líneas vale 6 para saltar
		do {
			do {
				System.out.println("Teclee límite inferior.....: ");
				li=t.leerInt();
			}while(li==Integer.MIN_VALUE);
			do {
				System.out.println("Teclee límite superior.....: ");
				ls=t.leerInt();
			}while(ls==Integer.MIN_VALUE);
		}while (li>ls);
		RandomAccessFile fich = new RandomAccessFile(ruta+"articulos.dat","r");
		fich.seek(li*a.tamano()); //me posiciono en li oporque es tonteria leer los atnerioers
		do {
			fin=a.leerDeArchivo(fich);
		}while(a.getCodigo()==0 &&!fin);
		do {
			while(!fin && cl < LINEAS) {
				a.mostrarDatos(0);
				cl++;
				do {
					fin=a.leerDeArchivo(fich);
				}while(a.getCodigo()==0 &&!fin); //sigue leyendo mientras el código no sea 0 o fin, se sale del bucle en el siguiente registro que exista
				if(a.getCodigo()>ls) {
					fin=true;
				}
			}
			if(cl==LINEAS) {
				System.out.println("\n\t Pulse <Intro> para continuar");
				t.leerSalto();
			}
			if(!fin) {
				System.out.println("\t\tLISTADO ENTRE LÍMITES\t\tPag.: "+ ++cp+"\n\t\t======================\n");
				System.out.println("Código\tDenominación\tStock actual\tStock mínimo.\tStock máximo\t\tApto");
				System.out.println("-----------------------------------------------------------------------");
				cl=0;
			}	
		}while(!fin);
		fich.close();
		System.out.println("\n\n\tFIN DEL LISTADO \n");
		System.out.println("\n\t Pulse <Intro> para continuar");	
		t.leerSalto();		
	}
	void listadoPedidos() throws IOException{
		Articulo a = new Articulo (0," ",0,0,0,0,' ');
		Teclado t = new Teclado();
		boolean fin =false;
		final int LINEAS= 4;
		int cp=0,cl=LINEAS+1;
		double udsPedidas= 0;
		float precioPedido=0,tp=0,sumsigue=0;
		RandomAccessFile fich= new RandomAccessFile(ruta+"articulos.dat","r");
		do {
			fin=a.leerDeArchivo(fich);
		}while(a.getCodigo()==0 &&!fin);
		do {

			while(!fin && cl < LINEAS) {
				if(a.getAviso()=='B') {
					udsPedidas=a.getStockMax()-a.getStockAct();
					precioPedido=(float) udsPedidas * a.getPrecio();
					lineasDetalle(a.getCodigo(),a.getDenominacion(),udsPedidas,a.getPrecio(),precioPedido);
					tp+=precioPedido;
					cl++;
				}
				do {
					fin=a.leerDeArchivo(fich);
				}while(a.getCodigo()==0 &&!fin);
				if(cl==LINEAS) {
					sumsigue+=tp;
					System.out.println("Total página..............: "+tp);
					System.out.println("Total final...............: "+sumsigue);
					tp=0;
					System.out.println("Teclee <Intro> para continuar....");
					t.leerSalto();
				}

				if(!fin) {
					System.out.println("\t\tLISTADO PEDIDOS\t\tPag.: "+ ++cp+"\n\t\t=============\n");
					System.out.println("Código\tDenominación\tUnidades Pedidas\tPrecio Unidad\tValor pedido");
					System.out.println("-------------------------------------------------------------------");
					cl=0;
				}	
			}
		}while(!fin);
		System.out.println("Total página..............: "+tp);
		System.out.println("Total final...............: "+sumsigue);
		fich.close();
		System.out.println("\n\n\tFIN DEL LISTADO \n");
		System.out.println("\n\t Pulse <Intro> para continuar");	
		t.leerSalto();	
	}
	void lineasDetalle(int codigo,String denominacion, double uds, float precio,float preciope) {
		System.out.println("\t"+codigo+"\t"+denominacion+"\t"+uds+"\t\t"+precio+"\t\t"+preciope);
	}
	void fin () {
		System.out.println("\nFIN DEL PROGRAMA\n=================");
	}
}