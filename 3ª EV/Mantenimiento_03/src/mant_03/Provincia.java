package mant_03;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
public class Provincia{
	private int numero;
	private String nombre;
	private float superficie;
	private int poblacion;
	private char costa;
	final int LONGNOMBRE=22;
	Provincia(int numero,String nombre,float superficie, int poblacion,char costa){
		this.numero=numero;
		this.nombre=nombre;
		this.superficie=superficie;
		this.poblacion=poblacion;
		this.costa=costa;
	}
	Provincia(){}
	int tamano() { //20+2 este 2 es el control de los unicode igual que para el char
		return(4+22+2+4+4+2);
	}
	String construirNombre() { //no se pasan parámetros porque ya está en el objeto
		String aux;
		int relleno;
		nombre.trim();
		relleno = LONGNOMBRE -nombre.length();
		aux= nombre+ blancos(relleno);
		return aux;
	}
	String blancos(int numblancos) {
		char [] blancos= new char[numblancos];
		for(int i=0;i<numblancos;i++) 
			blancos[i]= ' ';
		String sblancos = new String (blancos);
		return sblancos;
	}
	void grabarEnArchivo(RandomAccessFile f) {
		String aux;
		try {
			f.writeInt(numero);
			aux=construirNombre();
			f.writeUTF(aux);
			f.writeFloat(superficie);
			f.writeInt(poblacion);
			f.writeChar(costa);
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	boolean leerDeArchivo(RandomAccessFile f) {
		boolean finArchivo=false;
		try {			
			numero=f.readInt();
			nombre=f.readUTF();
			superficie=f.readFloat();			
			poblacion=f.readInt();
			costa=f.readChar();
		}catch (EOFException eofe) {   //primero la EOF pq es subexception de IO
			finArchivo=true;
		}catch(IOException ioe) {
			System.out.println("Error: "+ioe);
		}
		return (finArchivo);
	}
	float densidad() {
		return poblacion/superficie;
	}
	void mostrarDatos(int t) {
		DecimalFormat num= new DecimalFormat("000,000.00");
		switch(t) {
		case 0:
			System.out.println(numero+"\t"+nombre+"\t"+superficie+"\t"+poblacion+"\t"+costa+"\t");
			break;
		case 1:
			System.out.println("1.-Nombre..........."+nombre);
			System.out.println("2.-Superficie..........."+superficie+" (Km\u00B2)");
			System.out.println("3.-Población..........."+poblacion);
			System.out.println("4.-Costa..........."+costa);
			break;
		case 2:
			System.out.println(numero+"\t"+nombre+"\t"+superficie+"\t"+poblacion+"\t"+costa+"\t"+num.format(densidad()));
			break;
		}
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getNumero() {
		return numero;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setSuperficie(float superficie) {
		this.superficie = superficie;
	}
	public float getSuperficie() {
		return superficie;
	}
	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}
	public int getPoblacion() {
		return poblacion;
	}
	public void setCosta(char costa) {
		this.costa = costa;
	}
	public char getCosta() {
		return costa;
	}
	}