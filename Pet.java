public class Pet {
    private String nomePet;
    private String tipoPet;
    public Pet(String nomePet, String tipoPet){
        if(nomePet == null){
            System.out.println("Cadastro do pet encerrado!");
            System.exit(1); 
        }
        this.nomePet=nomePet;
        this.tipoPet=tipoPet;
    }
    public String getNomePet(){
        return nomePet;
    }
    public String getTipoPet(){
        return tipoPet;
    }
}
