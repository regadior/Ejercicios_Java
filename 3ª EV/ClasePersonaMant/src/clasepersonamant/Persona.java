package clasepersonamant;
public class Persona{
	private String dni;
	private String nombre;
	private int edad;
	private int codPos;
	Persona (){}
	Persona (String dni, String nombre, int edad, int codPos){
		this.dni=dni;
		this.nombre=nombre;
		this.edad=edad;
		this.codPos=codPos;
	}
	void setDni(String dni) {
		this.dni = dni;
	}
	String getDni() {
		return dni;
	}
	void setNombre(String nombre) {
		this.nombre = nombre;
	}
	String getNombre() {
		return nombre;
	}
	void setEdad(int edad) {
		this.edad = edad;
	}
	int getEdad() {
		return edad;
	}
	void setCodPos(int codPos) {
		this.codPos = codPos;
	}
	int getCodPos() {
		return codPos;
	}
	boolean controlDNI(String dni) {
		boolean verdadero=false;
		if (dni.length()==9) 
			verdadero=true;		
		return verdadero;
	}
	boolean controlNombre() {
		boolean verdadero=false;
		if (nombre.length()<=15) 
			verdadero=true;		
		return verdadero;
	}
	//no se le manda el nombre porque ya está en el objeto a través de los SETS.
	boolean controlCodPos() {
		boolean verdadero=false;
		if (String.valueOf(codPos).length()==5) 
			verdadero=true;		
		return verdadero;
	} 
	// sacados de ControlLongitudCP
	boolean controlProvincia(String c) {
		boolean v=false;
		int p=Integer.parseInt(c.substring(0,2));
		if(p>=1 && p<=52)
			v=true;
		return v;
	}
	String controlLongitud() {
		String cp="0";
		if(String.valueOf(codPos).length()==4)
			cp+=String.valueOf(codPos);
		else
			cp=String.valueOf(codPos);
		return cp;
	}
}