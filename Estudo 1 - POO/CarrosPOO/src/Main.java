import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static int menu(Scanner sc, int opcao) {

        System.out.println("\nEscolha uma opção:");
        System.out.println("\n1 - Cadastrar carro");
        System.out.println("2 - Exibir carros cadastrados");
        System.out.println("3 - Remover Carro");
        System.out.println("4 - Editar Carro");
        System.out.println("5 - Salvar Carros em Aqruivo .txt");
        System.out.println("0 - Sair");
        System.out.print("\n-> ");
        opcao = sc.nextInt();
        sc.nextLine();

        return opcao;
    }

    public static Carro cadastrarCarro(Scanner sc) {

        System.out.print("\nMarca: ");
        String marca = sc.nextLine();

        System.out.print("Modelo: ");
        String modelo = sc.nextLine();

        System.out.print("Ano: ");
        int ano = sc.nextInt();
        sc.nextLine();

        System.out.print("Placa: ");
        String placa = sc.nextLine();

        System.out.print("Quilometragem: ");
        double km = sc.nextDouble();
        sc.nextLine();

        System.out.println("\nCarro cadastrado com sucesso!");

        return new Carro(marca, modelo, ano, placa, km);
    }

    public static void exibeLista(ArrayList<Carro> listaCarros) {

        if (listaCarros.isEmpty()) {
            System.out.println("\nNenhum carro cadastrado.");
        } else {
            System.out.println("\nCarros Cadastrados: ");
            for (Carro carro : listaCarros) {
                carro.exibeCarro();
            }
        }

    }

    public static void removerCarro(ArrayList<Carro> listaCarros, Scanner sc) {

        if (listaCarros.isEmpty()) {
            System.out.println("\nNenhum carro foi cadastrado.");
            return;
        }

        for (int i = 0; i < listaCarros.size(); i++) {
            Carro carro = listaCarros.get(i);
            System.out.println("\nCarro: " + carro.modelo + " | Índice: " + i);
        }

        System.out.print("\nInforme o Índice do carro que deseja remover: ");
        int indice = sc.nextInt();

        if (indice >= 0 && indice < listaCarros.size()) {
            listaCarros.remove(indice);
            System.out.println("\nCarro removido com sucesso!");
        } else {
            System.out.println("\nÍndice inválido. Nenhum carro removido.");
        }

    }

    public static void editarCarro(ArrayList<Carro> listaCarros, Scanner sc) {

        if (listaCarros.isEmpty()) {
            System.out.println("\nNenhum carro foi cadastrado.");
            return;
        }

        for (int i = 0; i < listaCarros.size(); i++) {
            Carro carro = listaCarros.get(i);
            System.out.println("\nCarro: " + carro.modelo + " | Índice: " + i);
        }

        System.out.print("Informe o índice do carro que deseja editar: ");
        int indice = sc.nextInt();

        sc.nextLine();

        if (indice >= 0 && indice <= listaCarros.size()) {

            System.out.println("\nDigite as novas informações: ");

            System.out.print("\nMarca: ");
            String marca = sc.nextLine();

            System.out.print("Modelo: ");
            String modelo = sc.nextLine();

            System.out.print("Ano: ");
            int ano = sc.nextInt();
            sc.nextLine();

            System.out.print("Placa: ");
            String placa = sc.nextLine();

            System.out.print("Quilometragem: ");
            double km = sc.nextDouble();
            sc.nextLine();

            Carro carroEditado = new Carro(marca, modelo, ano, placa, km);
            listaCarros.set(indice, carroEditado);
            System.out.println("\nCarro editado com sucesso!");

        }

        else
            System.out.println("\nNenhum carro foi encontrado.");

    }

    public static void salvarArquivo(ArrayList<Carro> listaCarros, String CarrosResumo) {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(CarrosResumo));

            for (Carro carro : listaCarros) {
                escritor.println(carro);
            }

            escritor.close();
            System.out.println("\nCarros salvos em: " + CarrosResumo);

        } catch (IOException e) {
            System.err.println("\nErro ao salvar os carros: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Carro> listaCarros = new ArrayList<>();
        int opcao = 0;

        try {
            BufferedReader leitor = new BufferedReader(new FileReader("carros.txt"));
            String linha;
            int contadorLinha = 1;

            while ((linha = leitor.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue; 
                    
                }

                try {
                    String marca = linha.split(":")[1].trim();

                    linha = leitor.readLine();
                    contadorLinha++;
                    String modelo = linha.split(":")[1].trim();

                    linha = leitor.readLine();
                    contadorLinha++;
                    int ano = Integer.parseInt(linha.split(":")[1].trim());

                    linha = leitor.readLine();
                    contadorLinha++;
                    String placa = linha.split(":")[1].trim();

                    linha = leitor.readLine();
                    contadorLinha++;
                    String KM = linha.split(":")[1].replace("Km", "").trim();
                    double quilometragem = Double.parseDouble(KM);

                    Carro carro = new Carro(marca, modelo, ano, placa, quilometragem);
                    listaCarros.add(carro);

                } catch (Exception e) {
                    System.out.println("Erro ao ler o carro na linha " + contadorLinha + ": " + e.getMessage());
                }
            }

            leitor.close();

        } catch (IOException e) {
            System.out.println("Arquivo 'carros.txt' não encontrado ou erro ao abrir: " + e.getMessage());
        }

        System.out.println("\nMenu de Gerenciamento de Carros");

        do {
            opcao = menu(sc, 0);
            switch (opcao) {
                case 1:
                    Carro novocarro = cadastrarCarro(sc);
                    listaCarros.add(novocarro);
                    break;
                case 2:
                    exibeLista(listaCarros);
                    break;
                case 3:
                    removerCarro(listaCarros, sc);
                    break;
                case 4:
                    editarCarro(listaCarros, sc);
                    break;
                case 5:
                    salvarArquivo(listaCarros, "carros.txt");
                    break;
                default:
                    break;
            }
        } while (opcao != 0);

        sc.close();
    }

}
