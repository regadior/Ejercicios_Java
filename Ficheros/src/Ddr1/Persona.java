package Ddr1;

public class Persona {
    private String nombre;
    private int edad;
    private double estatura;
    private char sexo;

    public Persona(){
        this.nombre = "juan";
        this. edad = 25;
        this.estatura = 1.7;
        this.sexo =  'm';
    }
    //nombre de la variable de entrada
    //se pone igual por convenciones de JAVA
    public Persona(String nombre, int edad, double estatura, char sexo ){
        this.nombre=nombre;
        this.edad=edad;
        this.estatura=estatura;
        this.sexo=sexo;
    }

    //Accesadores publicos
    public String getNombre(){
        return nombre;
    }

}
