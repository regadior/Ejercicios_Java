package cochesObjetos;
import java.util.Scanner;
public class Clase_coches {
    static int menu(Scanner entrada){
        int opcion=0;
        System.out.println("1.-Altas");
        System.out.println("2.-Modificaciones");
        System.out.println("3.-Consultas");
        System.out.println("4.-Listados");
        System.out.println("5.-Fin");

        do {
            System.out.println("Introduzca opcion (1-5), 5 para acabar");
            opcion=entrada.nextInt();
            entrada.nextLine();//eliminar intro de  opcion
        }while(opcion>5|opcion<1);

        return opcion;
    }

    static void altas (Coches[] ArrCoches) {
        System.out.println("ALTAS");
        ArrCoches[0]=new Coches("1111aaa","Seat","Ibiza","Media",12034.5);
        ArrCoches[1]=new Coches("2222bbb","Ford","Fusion","Media",16895);
        ArrCoches[2]=new Coches("3333ccc","Ford","","Baja",9234.5);
        ArrCoches[3]=new Coches("4444ddd","Renault","Lajuna","Media",18434.5);
    }
    static void listado(Coches ArrCoches[]) {
        int i=0;
        System.out.println("\n=====Listado\n\t========\n");
        for(i=0; i<4 ; i++) {
            try {
                System.out.println(ArrCoches[i].getMatricula()+"\t"+ArrCoches[i].getMarca()+"\t"+ArrCoches[i].getModelo()+"\t"+ArrCoches[i].getGama()+ArrCoches[i].getPrecio());

            }catch(NullPointerException npe) {
                System.out.println("\nArray vacío...\n");
            break;//sino aparece mensaj array vacío según nº elementos array
            }

        }// fin for
    }

    static void consultas(Coches ArrCoches[],Scanner entrada) {
        String matricula="";
        String modelo="";
        String marca="";
        char gama=' ';
        double precio=0;
        int i=0;
        boolean existe=false;
    do {
        System.out.println("Introduza matricula para visualizar el coche que le corresponde");
        matricula=entrada.next();
    }while(matricula.length()<7);
        System.out.println("INFORMACIÓN RELATIVA a la matrícula introducida"+matricula);
    for(i=0 ; i<4 && !existe; i++) {
        try {

        if(ArrCoches[i].getMatricula().equalsIgnoreCase(matricula)) {
            existe=true;
            System.out.println(ArrCoches[i].getMatricula());
            System.out.println("modelo\t"+ArrCoches[i].getModelo()+"\nMarca"+ArrCoches[i].getMarca()+"\ngama"+ArrCoches[i].getGama()+"\nprecio"+ArrCoches[i].getPrecio());
        }

        }catch(NullPointerException npe) {
            System.out.println("Array Vacío");
            break;
        }
    }
        if(!existe) {
            System.out.println("No se ha encontrado el coche con la matrícula introducida.");
        }
    }                                                                                                                                                    

    public static void main(String[]args) {
        Scanner entrada=new Scanner(System.in);
        int opcion=0;
        Coches ArrCoches[]= new Coches[4];

        do { //faltan  altas (la que está es para comprobar que funciona el listado) y modificación
            opcion=menu(entrada);
            switch(opcion) {
                case 1:
                    altas(ArrCoches);
                break;
                case 3:
                    consultas(ArrCoches,entrada);
                break;

                case 4:
                    listado(ArrCoches);
                break;




            }//fin switch
        }while(opcion!=5);

        }

}
