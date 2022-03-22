package Coches;
public class Coches{
	private String matricula;
	private String marca;
	private String modelo;
	private String gama;
	private double precio;
	Coches(String matricula, String marca, String modelo,String gama, double precio){
		this.matricula=matricula;
		this.marca=marca;
		this.modelo=modelo;
		this.gama=gama;
		this.precio=precio;
	}
	Coches (){
		
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMatricula() {
		return matricula;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getMarca() {
		return marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getModelo() {
		return modelo;
	}

	public void setGama(String gama) {
		this.gama = gama;
	}
	public String getGama() {
		return gama;
	}
	void literalGama(char gama) {
		String literal="Alta";
		if (gama=='m')
			literal="Media";
		if (gama=='b')
			literal="Baja";
		this.gama=literal;
	}
	boolean comprobarLongitudMatricula() {
		boolean correcta = false;
		if (matricula.length()==7)
			correcta=true;
		return correcta;
	}
	boolean comprobarLongitudMarca() {
		boolean correcta = false;
		if (marca.length()>0 && marca.length()<11)
			correcta=true;
		return correcta;
	}
	boolean comprobarLongitudModelo() {
		boolean correcta = false;
		if (modelo.length()>0 && modelo.length()<16)
			correcta=true;
		return correcta;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getPrecio() {
		return precio;
	}

}

