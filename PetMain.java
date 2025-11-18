import java.util.ArrayList;
import java.util.Scanner;

public class PetMain {
    private static ArrayList<Tutor> cont = new ArrayList<Tutor>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        popularCadastro();

        while (true) {
            System.out.println("***** ESCOLHER UMA OPÇÃO *****");
            System.out.println("c: cadastrar tutor + pet(s)");
            System.out.println("i: imprimir cadastro");
            System.out.println("b: buscar pets por código tutor");
            System.out.println("e: excluir pets por código tutor");
            System.out.println("x: encerrar.");
            System.out.print("Opção escolhida: ");
            String opcao = sc.nextLine();

            if (opcao.equals("c")) {
                cadastrarTutorPet();
            } else if (opcao.equals("i")) {
                imprimeCont();
            } else if (opcao.equals("b")) {
                buscarPorCodigoTutor();
            } else if (opcao.equals("e")) {
                excluirPetPorCodigoTutor();
            } else if (opcao.equals("x")) {
                System.out.println("--- Programa de cadastro encerrado ---");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    // Gerador de Código para Tutor
    public static int geraCodTutor() {
        if (cont.size() == 0) {
            return 1;
        } else {
            return cont.get(cont.size() - 1).getCod() + 1;
        }
    }

    // Método para popular o cadastro com dados iniciais
    public static void popularCadastro() {
        Tutor tutor1 = new Tutor("Zeca Silva", 11, 5, 2000, geraCodTutor());
        tutor1.incluiPet("Bilu", "Gato");
        tutor1.incluiPet("Wilson", "Canário");
        cont.add(tutor1);

        Tutor tutor2 = new Tutor("Maria Lopes", 22, 2, 1988, geraCodTutor());
        tutor2.incluiPet("Loro", "Papagaio");
        cont.add(tutor2);

        Tutor tutor3 = new Tutor("Mário Pacheco", 12, 4, 2001, geraCodTutor());
        tutor3.incluiPet("Rex", "Cão");
        tutor3.incluiPet("Miau", "Gato");
        cont.add(tutor3);
    }

    // Método para cadastrar tutor e pets
    public static void cadastrarTutorPet() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite nome do tutor (vazio encerra cadastro tutor): ");
        String nomeTutor = sc.nextLine();
        if (nomeTutor.isEmpty()) {
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
            tutor.incluiPet(nomePet, tipoPet);
            System.out.println("--- Pet cadastrado ---");
        }

        cont.add(tutor);
        System.out.println("--- Tutor cadastrado ---");
    }

    // Método para imprimir o cadastro
    public static void imprimeCont() {
        System.out.println("--- CADASTRO DE TUTORES E PETS ---");
        for (Tutor tutor : cont) {
            System.out.println("Cod. do tutor: " + tutor.getCod());
            System.out.println("  Nome...........: " + tutor.getNome());
            System.out.println("  Data nascimento: " + tutor.getDataNasc());
            System.out.println("  Relação de Pets:");
            for (Pet pet : tutor.getPets()) {
                System.out.println("    - Nome do pet: " + pet.getNomePet() + "; Tipo: " + pet.getTipoPet());
            }
            System.out.println();
        }
    }

    // Método para buscar pets por código do tutor
    public static void buscarPorCodigoTutor() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite código do tutor a ser localizado: ");
        int codTutor = sc.nextInt();
        sc.nextLine();  // Consumir o newline leftover

        boolean encontrado = false;
        for (Tutor tutor : cont) {
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
    }

    // Método para excluir pets por código do tutor
    public static void excluirPetPorCodigoTutor() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite código do tutor a ser excluído: ");
        int codTutor = sc.nextInt();
        sc.nextLine();  // Consumir o newline leftover

        boolean encontrado = false;
        for (Tutor tutor : cont) {
            if (tutor.getCod() == codTutor) {
                cont.remove(tutor);
                System.out.println("--- Tutor (+pets) com código " + codTutor + " excluído com sucesso! ---");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Tutor não encontrado.");
        }
    }
}