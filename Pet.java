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
        if(nomePet == null){
            System.out.println("Cadastro do pet encerrado!");
            System.exit(1);
        }
        this.nomePet=nomePet;
        this.tipoPet=tipoPet;

        LocalDate dataTemp = LocalDate.of(dp, mp, ap);
        if(dataTemp.isAfter(LocalDate.now())){
            System.out.println("Data inválida: programa encerrado!");
            System.exit(1);
        }
        dataNasc = dataTemp;
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