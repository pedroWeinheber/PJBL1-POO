// CLASSE CONTRIBUINTE
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Tutor {
    // Atributos
    private String nomeTutor;
    private LocalDate dataNasc;
    private int cod;
    private ArrayList<Pet> pet=new ArrayList<Pet>();
    // Construtor
    public Tutor(String Nome, int d, int m, int a, int Cod){
        cod=Cod;
        if(Nome == null){
            System.out.println("Cadastro do tutor encerrado!");
            System.exit(1);
        }
        nomeTutor=Nome;
        if(!validaData(d, m, a)){
            System.out.println("Data inválida: programa encerrado!");
            System.exit(1);
        }
        LocalDate dataTemp = LocalDate.of(a, m, d);
        if(dataTemp.isAfter(LocalDate.now())){
            System.out.println("Data inválida: programa encerrado!");
            System.exit(1);
        }
        dataNasc = dataTemp;
    }
    private boolean validaData(int d, int m, int a){
        if(m<1 || m>12){
            return false;
        }
        int quantDias=0;
        if(m==1||m==3||m==5||m==7||m==8||m==10||m==12){
            quantDias=31;
        }
        if(m==4||m==9||m==6||m==11){
            quantDias=30;
        }
        if(m==2){
            boolean bissexto = (a % 400 == 0) || (a % 4 == 0 && a % 100 != 0);
            if (bissexto) {
                quantDias = 29;
            } else {
                quantDias = 28;
            }
        }
        if(d < 1 || d > quantDias){
            return false;
        }
        return true;
    }

    public ArrayList<Pet> getPets(){
        return pet;
    }

    public int getCod(){
        return cod;
    }
    public String getNome(){
        return nomeTutor;
    }
    public String getDataNasc(){
        DateTimeFormatter fmt=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataNasc.format(fmt);
    }
    public void incluiPet(String nomePet, String tipoPet, int ap, int mp, int dp){
        Pet p = new Pet(nomePet,tipoPet, ap, mp, dp);
        pet.add(p);
    }

}