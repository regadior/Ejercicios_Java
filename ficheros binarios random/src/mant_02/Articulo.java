package mant_02;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
public class Articulo{
	private int codigo;
	private String denominacion;
	private double stockMin;
	private double stockMax;
	private double stockAct;
	private float precio;
	private char aviso;	

	final int LONGDENOM = 20;
	Articulo() {}
	Articulo (int codigo, String denominacion, double stockAct,double stockMin, double stockMax,float precio, char aviso ){
		this.codigo=codigo;
		this.denominacion=denominacion;	
		this.stockAct=stockAct;
		this.stockMin=stockMin;
		this.stockMax=stockMax;
		this.precio=precio;
		this.aviso=aviso;
	}
	int tamano() { //20+2 este 2 es el control de los unicode igual que para el char
		return(4+20+2+8+8+8+4+2);
	}
	String construirDenom() { //no se pasan par�metros porque ya est� en el objeto
		String aux;
		int relleno;
		denominacion.trim();
		relleno = LONGDENOM -denominacion.length();
		aux= denominacion+ blancos(relleno);
		return aux;
	}
	String blancos(int numblancos) {
		char [] blancos= new char[numblancos];
		for(int i=0;i<numblancos;i++) 
			blancos[i]= ' ';
		String sblancos = new String (blancos);
		return sblancos;
	}
	void conocerAviso() {		
			if(stockAct<=stockMin)
				aviso='B';
			else if(stockAct>=stockMin)
				aviso='A';			
			else
				aviso='N';
	}
	void grabarEnArchivo(RandomAccessFile f) {
		String aux;
		try {
			f.writeInt(codigo);
			aux=construirDenom();
			f.writeUTF(aux);
			f.writeDouble(stockAct);
			f.writeDouble(stockMin);
			f.writeDouble(stockMax);
			f.writeFloat(precio);
			conocerAviso();
			f.writeChar(aviso);
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	boolean leerDeArchivo(RandomAccessFile f) {
		boolean finArchivo=false;
		try {			
			codigo=f.readInt();
			denominacion=f.readUTF();
			stockAct=f.readDouble();
			stockMin=f.readDouble();
			stockMax=f.readDouble();
			precio=f.readFloat();			
			aviso=f.readChar();
		}catch (EOFException eofe) { 
			finArchivo=true;
		}catch(IOException ioe) {
			System.out.println("Error: "+ioe);
		}
		return (finArchivo);
	}
	void mostrarDatos(int t) {
		switch(t) {
		case 0:
			System.out.println(codigo+"\t"+denominacion+"\t"+stockAct+"\t"+stockMin+"\t"+stockMax+"\t"+precio+"\t"+aviso);
			break;
		case 1:
			System.out.println("1.Nombre..............."+denominacion);
			System.out.println("2.Stock actual..............."+stockAct);
			System.out.println("3.Stock m�nimo..............."+stockMin);
			System.out.println("4.Stock m�ximo..............."+stockMax);
			System.out.println("5.Precio..............."+precio);
			System.out.println("6.Aviso..............."+aviso);
		case 2:
			System.out.println("Nombre..............."+denominacion);
			System.out.println("Stock actual..............."+stockAct);
			System.out.println("Stock m�nimo..............."+stockMin);
			System.out.println("Stock m�ximo..............."+stockMax);
			System.out.println("Precio..............."+precio);
			System.out.println("Aviso..............."+aviso);
			break;
		case 3:
			System.out.println(codigo+"\t"+denominacion+"\t"+stockAct+"\t"+stockMin+"\t"+stockMax+"\t"+precio+"\t"+aviso);
			break;
		}
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setStockMin(double stockMin) {
		this.stockMin = stockMin;
	}
	public double getStockMin() {
		return stockMin;
	}
	public void setStockMax(double stockMax) {
		this.stockMax = stockMax;
	}
	public double getStockMax() {
		return stockMax;
	}
	public void setStockAct(double stockAct) {
		this.stockAct = stockAct;
	}
	public double getStockAct() {
		return stockAct;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public float getPrecio() {
		return precio;
	}
	public void setAviso(char aviso) {
		this.aviso = aviso;
	}
	public char getAviso() {
		return aviso;
	}
}