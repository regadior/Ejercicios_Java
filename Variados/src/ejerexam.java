import java.util.Scanner;
public class ejerexam{
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int [][]matriz=new int[6][6];
        int j=0;
        for(int i=0;i<matriz.length;i=i+2){
            System.out.print("Teclea un numero? ");
            matriz[i][j]=sc.nextInt();
            if(matriz[i][j]%2==0){
                System.out.println("par"+matriz);
            }
        }
        
        
    
    
    
    
    
    
    }
}