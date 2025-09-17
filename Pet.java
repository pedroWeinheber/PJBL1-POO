//CLASSE PET
public class Pet {
    // Atributos
    private String nomePet;
    private String tipoPet;
    // Construtor
    public Pet(String nomePet, String tipoPet){
        if(nomePet == null){
            System.out.println("Cadastro do pet encerrado!");
            System.exit(1); 
        }
        this.nomePet=nomePet;
        this.tipoPet=tipoPet;
    }
    // Getters
    public String getNomePet(){
        return nomePet;
    }
    public String getTipoPet(){
        return tipoPet;
    }
}
