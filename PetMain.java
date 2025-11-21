import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class PetMain {
    private static String arqTutor="Tutor.dat";
    private static ArrayList<Tutor> tutores=new ArrayList<Tutor>();
    int nTutores=recuperaTutores();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("***** ESCOLHER UMA OPÇÃO *****");
                System.out.println("c: cadastrar tutor + pet(s)");
                System.out.println("i: imprimir cadastro");
                System.out.println("b: buscar pets por código tutor");
                System.out.println("e: excluir pets por código tutor");
                System.out.println("x: encerrar.");
                System.out.print("Opção escolhida: ");
                String opcao = sc.nextLine();

                switch (opcao) {
                    case "c":
                        cadastrarTutorPet();
                        gravaTutores();
                        recuperaTutores();
                        break;
                    case "i":
                        imprimeTutores();
                        break;
                    case "b":
                        buscarPorCodigoTutor();
                        break;
                    case "e":
                        excluirPetPorCodigoTutor();
                        gravaTutores();
                        recuperaTutores();
                        break;
                    case "x":
                        System.out.println("--- Programa de cadastro encerrado ---");
                        System.exit(1);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        }
    }

    // Gerador de Código para Tutor
    public static int geraCodTutor() {
        int ts = tutores.size();
        if (ts==0)
            return 1;
        else
            return tutores.get(ts-1).getCod()+1;
    }

    public static int recuperaTutores(){
        ObjectInputStream inputStream=null;
        int count = 0;
        try{
            inputStream=new ObjectInputStream
                            (new FileInputStream(arqTutor));
            Object obj = null;
            if(tutores.size()>0) tutores.clear();

            while ((obj=inputStream.readObject())!=null){
                if(obj instanceof Tutor)
                    tutores.add((Tutor)obj);
                count++;
            }
        }
        catch(EOFException ex) {
            System.out.println("Fim da leitura do arquivo "+arqTutor+".");
        }
        catch(FileNotFoundException ex){
            System.out.println("Arquivo de pessoas inexistente.");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{
                if(inputStream!=null)
                    inputStream.close();
            }catch(IOException ex){
                ex.getMessage();
            }
        }
        return count;
    }

    public static void gravaTutores(){
        ObjectOutputStream outputStream=null;
        try{
            outputStream=new ObjectOutputStream
                            (new FileOutputStream(arqTutor));
            for(Tutor t:tutores) outputStream.writeObject(t);
        }
        catch(EOFException ex) {
            System.out.println("Fim de escrita no arquivo de pessoas.");
        }
        catch(FileNotFoundException ex){
            System.out.println("Arquivo de tutor não encontrado.");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{
                if(outputStream!=null){
                    outputStream.flush();
                    outputStream.close();
                }
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    // Metodo para cadastrar tutor e pets
    public static void cadastrarTutorPet() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite nome do tutor (vazio encerra cadastro tutor): ");
        String nomeTutor = sc.nextLine();
        if (nomeTutor.isEmpty()) {
            sc.close();
            return;
        }

        System.out.print("Digite dia (dd), mês (mm) e ano (aaaa) de nascimento do tutor: ");
        int d = sc.nextInt();
        int m = sc.nextInt();
        int a = sc.nextInt();
        sc.nextLine();  // Consumir o newline leftover
        Tutor tutor = new Tutor(nomeTutor, d, m, a, geraCodTutor());

        while (true) {
            System.out.print("Digite nome do pet (vazio encerra cadastro pet): ");
            String nomePet = sc.nextLine();
            if (nomePet.isEmpty()) {
                break;
            }
            System.out.print("Digite tipo do pet: ");
            String tipoPet = sc.nextLine();
            System.out.print("Digite data dia (dd), mês (mm) e ano (aaaa) de nascimento do pet: ");
            int dp = sc.nextInt();
            int mp = sc.nextInt();
            int ap = sc.nextInt();
            tutor.incluiPet(nomePet, tipoPet, dp, mp, ap);
            System.out.println("--- Pet cadastrado ---");
        }

        tutores.add(tutor);
        System.out.println("--- Tutor cadastrado ---");
        sc.close();
    }

    // Metodo para imprimir o cadastro
    public static void imprimeTutores() {
        System.out.println("--- CADASTRO DE TUTORES E PETS ---");
        for (Tutor tutor : tutores) {
            System.out.println("Cod. do tutor: " + tutor.getCod());
            System.out.println("  Nome...........: " + tutor.getNome());
            System.out.println("  Data nascimento: " + tutor.getDataNasc());
            System.out.println("  Relação de Pets:");
            for (Pet pet : tutor.getPets()) {
                System.out.println("    - Nome do pet: " + pet.getNomePet() + "; Tipo: " + pet.getTipoPet() + "; Idade: " + pet.getIdade());
            }
            System.out.println();
        }
    }

    // Metodo para buscar pets por código do tutor
    public static void buscarPorCodigoTutor() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite código do tutor a ser localizado: ");
        int codTutor = sc.nextInt();
        sc.nextLine();  // Consumir o newline leftover

        boolean encontrado = false;
        for (Tutor tutor : tutores) {
            if (tutor.getCod() == codTutor) {
                System.out.println("--- Tutor localizado ---");
                System.out.println("Cod. do tutor: " + tutor.getCod());
                System.out.println("  Nome...........: " + tutor.getNome());
                System.out.println("  Data nascimento: " + tutor.getDataNasc());
                System.out.println("  Relação de Pets:");
                for (Pet pet : tutor.getPets()) {
                    System.out.println("    - Nome do pet: " + pet.getNomePet() + "; Tipo: " + pet.getTipoPet());
                }
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Tutor não encontrado.");
        }
        sc.close();
    }

    // Metodo para excluir pets por código do tutor
    public static void excluirPetPorCodigoTutor() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite código do tutor a ser excluído: ");
        int codTutor = sc.nextInt();
        sc.nextLine();  // Consumir o newline leftover

        boolean encontrado = false;
        for (Tutor tutor : tutores) {
            if (tutor.getCod() == codTutor) {
                tutores.remove(tutor);
                System.out.println("--- Tutor (+pets) com código " + codTutor + " excluído com sucesso! ---");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Tutor não encontrado.");
        }
        sc.close();
    }
}