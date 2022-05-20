package examen;
public class Articulo{
	private String codigo;
	private String denominacion;
	private float precio;
	private char tipo;
	private int uniVendidas;
	Articulo(){}
	Articulo(String codigo,String denominacion,float precio,char tipo, int uniVendidas){
		this.codigo=codigo;
		this.denominacion=denominacion;
		this.precio=precio;
		this.tipo=tipo;
		this.uniVendidas=uniVendidas;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public float getPrecio() {
		return precio;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	public char getTipo() {
		return tipo;
	}
	public void setUniVendidas(int uniVendidas) {
		this.uniVendidas = uniVendidas;
	}
	public int getUniVendidas() {
		return uniVendidas;
	}
	static float tipoIva(float precio,char tipo) {
		float iva=0.0f;
		if(tipo=='A') 
			iva=0.21f;
		if(tipo=='B') 
			iva=0.10f;
		if(tipo=='C')
			iva=0.04f;
			return precio*iva;
	}
}