package mant_04;
import java.io.*;
public class Persona{
	private int numero;
	private String nombre;
	private int edad;
	final int LONGNOMBRE=25;
	Persona(int numero,String nombre,int edad){
		this.numero=numero;
		this.nombre=nombre;
		this.edad=edad;
	}
	Persona(){}
	int tamano() {
		return(4+25+2+4);
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
			f.writeInt(edad);
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	boolean leerDeArchivo(RandomAccessFile f) {
		boolean finArchivo=false;
		try {			
			numero=f.readInt();
			nombre=f.readUTF();			
			edad=f.readInt();
		}catch (EOFException eofe) {   //primero la EOF pq es subexception de IO
			finArchivo=true;
		}catch(IOException ioe) {
			System.out.println("Error: "+ioe);
		}
		return (finArchivo);
	}
	void mostrarDatos(int t) {
		switch(t) {
		case 0:
			System.out.println("Nombre......."+nombre);
			System.out.println("Edad......."+edad);			
			break;
		case 1:
			System.out.println("1.Nombre......."+nombre);
			System.out.println("2.Edad......."+edad);
			break;
			case 2:
				System.out.println(numero+"\t"+nombre+"\t"+edad);
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
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getEdad() {
		return edad;
	}
	
}