import java.util.Scanner;
public class ejerexam{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int []v1=new int[6];
        int v2[] = new int [6];
        int num=0;
        for(int i =0;i<6;i=i+2){
            System.out.print("Teclea un numero: ");
            num = sc.nextInt();
            v1[i]=num;
            if(i+1<6){
                v2[i+1]=num;
                if(i==4){
                    i=-1;
                }
            }
        }
        v2[0]=num;
        System.out.println("\n\nV1\tV2\n-------------");
        for(int i =0;i<6;i++){
            System.out.println(v1[i]+"\t"+v2[i]);
            sc.close();
        }
    }
}