import java.util.ArrayList;

public class PetShop {
    private static ArrayList<Tutor> cont=new ArrayList<Tutor>();
    public static void main(String[] args){

    }
    // Gerador de Código
    public static int geraCodTutor(){ // Gera c�digo p/ contribuinte.
      if (cont.size()==0)
         return 1;
      else // Incrementa o c�digo do contribuinte no final da lista.
         return cont.get(cont.size()-1).getCod()+1;
   }   

   //Geracao da string com os dados do tutor+pets
   public String toString(){

   }

   public static void imprimeCont(){
      System.out.println("*** CADASTRO DE TUTOR ***");  
      for (Tutor T:cont)
         System.out.println(T.toString());               
   }
}
