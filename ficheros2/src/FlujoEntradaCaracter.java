
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class FlujoEntradaCaracter {

	public static void main(String[] args) {

		int car=' ';
		String texto= null;
		
		try {
			FileWriter esc=new FileWriter("E:\\CLASES\\PRG\\textoFicheros.txt",true);
			System.out.print("Teclear texto a grabar: ");
			while (car!='*') {
				esc.write(car);
				car=(char) System.in.read();
			}
			esc.close();
		} catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		System.out.println("Fin del proceso de escritura");
		
		texto="";
		try {
			FileReader in=new FileReader("E:\\CLASES\\PRG\\textoFicheros.txt");
			car=in.read();
			while(car!=-1) {
				texto+=(char)car;
				car=in.read();
			}
			System.out.println("Texto leido caracter a caracter es   :"+texto);
			in.close();
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

}
