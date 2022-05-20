package controlLongitudCP;
import java.util.Scanner;
public class ControlLongitudCP{
	static String control_1 (int c) {
		String cp="0";
		if(String.valueOf(c).length()==4) {
			cp+=String.valueOf(c);
		}
		else
			cp=String.valueOf(c);
		return cp;
	}
	static boolean control_2 (String c) {
		boolean v=false;
		int p=Integer.parseInt(c.substring(0,2));
//		System.out.println("Dos primeros (c.substring(0,2)+"   "+p);
		if(p>=1 && p<=52)
			v=true;
		return v;
	}
	public static void main (String []args) {
		Scanner e= new Scanner(System.in);
		int cp=0;
		String scp;
		System.out.println("Teclee código postal ");
		cp=e.nextInt();
		System.out.println("El código tecleado es: "+cp);
		scp=control_1(cp);
		System.out.println("La longitud del código postal es: "+scp+" "+scp.length());
		if(control_2(scp))
			System.out.println("Código correcto.");
		else
			System.out.println("Código incorrecto.");
	}
}