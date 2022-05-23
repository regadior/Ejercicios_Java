/**
 * Ejercicios
 * Guarda distintos metodos de el ejercicios de repaso de el tema 6
 * @author regadior
 * @version 2.0
 * @see main
 */
public class Ejercicios {
    /**
     * parimpar
     * Es un metodo que se le da una cadana e indica si ese numero es par o impar
     * 
     * @param nume, numero entero
     * @return cad, String que indica si el numero es par o impar
     * @since 1.0
     */

    public static string parImpar(int nume){
        string cad="";
        if(nume%2 == 0)
            cad="Par";
        else
            cad="Impar";
        return cad;
    }

    /**
     * Visualizar metodo
     * Metodo que se le pasa dos numeros y hace la media de ellos
     * @param x, numero que le pasa el usuario
     * @param y, otro numero que le pasa el usuario
     * @since 1.2
     * @see parimpar
     *
     */
    public void visualizarMedia(float x, float y){
        float resultado=0;
        if (x<0 || y>0)
            System.out.println("X e Y deben de ser positivos");
        else {
            resultado=(x+y)/2;
            System.out.println ("La media es: "+resultado);
        }
    }
    /**
     * Contar letra
     * Metodo que devuelve el numero de veces que aparece una letra en el arrray
     * @param cadena, array de caracteres
     * @param letra, letra a buscar
     * @return n, numero de veces que aparece lo devuelve
     */
        public int contar_letra(char[] cadena, char letra){
            int contador, n, lon;
            n=0; contador=0;
            lon=cadena.length;
            if (lon>0){
                do{
                    if (cadena[contador]==letra)
                        n++;
                        contador++;
                        lon--;
                }while(lon>0);
            }
            return n;
        }

    public static void main(String[] args) {
        
    }
}
