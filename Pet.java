import java.time.LocalDate;
import java.time.Period;

//CLASSE PET
public class Pet {
    // Atributos
    private String nomePet;
    private String tipoPet;
    private LocalDate dataNasc;
    // Construtor
    public Pet(String nomePet, String tipoPet, int ap, int mp, int dp){
        
        try{
            if(nomePet==null){
                throw new NullPointerException("Nome nulo!");

            }
            this.nomePet=nomePet;
            this.tipoPet=tipoPet;
        }catch(NullPointerException e){
            System.out.println("cadastro encerrado");
            System.exit(1);
        }

        try{
            LocalDate dataTemp = LocalDate.of(dp, mp, ap);
            if(dataTemp.isAfter(LocalDate.now())){
                throw new IllegalArgumentException("Data futura!");
            }
            dataNasc = dataTemp;
        }catch(IllegalArgumentException e){
            System.out.println("cadastro encerrado!");
            System.exit(1);
        }    
    }
    // Getters
    public String getNomePet(){
        return nomePet;
    }
    public String getTipoPet(){
        return tipoPet;
    }
    public LocalDate getDataNasc(){ return dataNasc; }
    public int getIdade(){
        LocalDate hoje=LocalDate.now();
        Period tempo=Period.between(dataNasc,hoje);
        return tempo.getYears();
    }
}