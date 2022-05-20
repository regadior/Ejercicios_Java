package mantenimiento;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Alumno{
	private int numero;
	private String nombre;
	private float nota1;
	private float nota2;
	private char apto;
	final int LONGNOMBRE = 25;
	Alumno(int numero,String nombre, float nota1, float nota2, char apto){
		this.numero=numero;
		this.nombre=nombre;
		this.numero=numero;
		this.numero=numero;
		this.numero=numero;
	}
	Alumno(){}

	int tamano() {
		return(4+25+2+4+4+2);
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
			f.writeFloat(nota1);
			f.writeFloat(nota2);
			conocerApto();
			f.writeChar(apto);
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	void conocerApto() {
		if(nota1<4.5 || nota2<4.5) 
		{
			apto='N';
		}else {
			apto='S';
		}
	}
	boolean leerDeArchivo(RandomAccessFile f) {
		boolean finArchivo=false;
		try {
			numero=f.readInt();
			nombre=f.readUTF();
			nota1=f.readFloat();
			nota2=f.readFloat();
			apto=f.readChar();
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
			System.out.println(numero+"\t"+nombre+"\t"+nota1+"\t"+nota2+"\t"+apto);
			break;
		case 1:
			System.out.println("1.Nombre..............."+nombre);
			System.out.println("2.Nota 1..............."+nota1);
			System.out.println("3.Nota 2..............."+nota2);
			System.out.println("Apto..............."+nombre);
		case 2:
			System.out.println("Nombre..............."+nombre);
			System.out.println("Nota 1..............."+nota1);
			System.out.println("Nota 2..............."+nota2);
			System.out.println("Apto..............."+nombre);
			break;
		case 3:
			System.out.println(numero+"\t"+nombre+"\t"+nota1+"\t"+nota2+"\t"+apto+"\t"+(nota1+nota2)/2);
			break;

		}
	}
	void setNumero(int numero) {
		this.numero = numero;
	}
	public int getNumero() {
		return numero;
	}
	void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getNota1() {
		return nota1;
	}
	public void setNota1(float nota1) {
		this.nota1 = nota1;
	}
	public float getNota2() {
		return nota2;
	}
	public void setNota2(float nota2) {
		this.nota2 = nota2;
	}
	public String getNombre() {
		return nombre;
	}
	void setApto(char apto) {
		this.apto = apto;
	}
	public char getApto() {
		return apto;
	}
}