package mantPersona;
import java.io.IOException;
import java.io.EOFException;
import java.io.RandomAccessFile;
public class Persona {
	private int numero;
	private String nombre;
	private int edad;
	final int LNOMBRE=20;
	Persona(int numero, String nombre,int edad){
		this.numero = numero;
		this.nombre = nombre;
		this.edad = edad;
	}
	int tamano(){
		return(4+20+2+4);
	}
	String blancos(int numblancos){
		char[] blancos = new char[numblancos];
		for(int i = 0;i<numblancos;i++)
			blancos[i]=' ';
		String sblancos = new String(blancos);
		return sblancos;
	}
	String construirNombre(){
		String aux;
		int relleno;
		nombre.trim();
		relleno = LNOMBRE - nombre.length();
		aux = nombre + blancos(relleno);
		return aux;
	}
	void grabarPersona(RandomAccessFile f){
		String aux;
		try{
			f.writeInt(numero);
			aux = construirNombre();
			f.writeUTF(aux);
			f.writeInt(edad);
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}
	boolean leerPersona(RandomAccessFile f){
		boolean finArchivo = false;
		try{
			numero = f.readInt();
			nombre = f.readUTF();
			edad =   f.readInt();
		}catch(EOFException eofe){
			finArchivo = true;
		}catch(IOException ioe){
			System.out.println("Error: "+ioe);
			}
		return  (finArchivo);
	}
	void setNumero(int numero) {
		this.numero = numero;
	}
	void setNombre(String nombre) {
		this.nombre = nombre;
	}
	void setEdad(int edad) {
		this.edad = edad;
	}
	int getNumero() {
		return this.numero;
	}
	String getNombre() {
		return this.nombre;
	}
	int getEdad() {
		return this.edad;
	}
	void verDatosPersona(int tv){
		switch(tv){
		case 0:
			System.out.println("Nombre............: "+nombre);
			System.out.println("Edad.................: "+edad);
			break;
		case 1:
			System.out.println("1.- Nombre............: "+nombre);
			System.out.println("2.- Edad.................: "+edad);
			break;
		case 2:
			System.out.println(numero+"\t"+nombre+"\t"+edad);
			break;
		}
	}
}
