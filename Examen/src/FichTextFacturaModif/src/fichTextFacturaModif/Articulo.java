package fichTextFacturaModif;

public class Articulo {
	private String codigo;
	private String denominacion;
	private float precio;
	private char tipo;
	private int uniVendidas;
	Articulo(String codigo, String denominacion,float precio,char tipo,int uniVendidas){
		this.codigo = codigo;
		this.denominacion = denominacion;
		this.precio = precio;
		this.tipo = tipo;
		this.uniVendidas = uniVendidas;
		
	}
	Articulo(){}
	String getCodigo() {
		return codigo;
	}
	void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	String getDenominacion() {
		return denominacion;
	}
	void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}	
	float getPrecio() {
		return precio;
	}	
	void setPrecio(float precio) {
		this.precio = precio;
	}
	char getTipo() {
		return tipo;
	}
	void setTipo(char tipo) {
		this.tipo = tipo;
	}

	int getUniVendidas() {
		return uniVendidas;
	}
	void setUniVendidas(int uniVendidas) {
		this.uniVendidas = uniVendidas;
	}

}
