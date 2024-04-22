import java.io.*;
import java.util.*;


class Item {
    String jogo;
    String categoria;
    double avaliacao;

    public Item(String jogo, String categoria, double avaliacao) {
        this.jogo = jogo;
        this.categoria = categoria;
        this.avaliacao = avaliacao;
    }
}

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Item> jogos = new ArrayList<>(); 

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu(); 
            opcao = scanner.nextInt(); 
            switch (opcao) {
                case 1:
                    lerArquivo(); 
                    break;
                case 2:
                    ordenarPorCategoria(); 
                    break;
                case 3:
                    ordenarPorAvaliacao(); 
                    break;
                case 4:
                    System.out.println("Encerrando o programa..."); 
                    break;
                default:
                    System.out.println("Opção inválida!"); 
            }
        } while (opcao != 4); 
    }

    static void exibirMenu() {
        System.out.println("[1] Ler arquivo");
        System.out.println("[2] Ordenar por categoria");
        System.out.println("[3] Ordenar por avaliação");
        System.out.println("[4] Sair");
        System.out.print("Escolha uma opção: ");
    }

    static void lerArquivo() {
        try {
            
            BufferedReader br = new BufferedReader(new FileReader("JogosDesordenados.csv"));
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String jogo = parts[0];
                String categoria = parts[1];
                double avaliacao = Double.parseDouble(parts[2]);
                
                jogos.add(new Item(jogo, categoria, avaliacao));
            }
            System.out.println("Arquivo lido com sucesso!"); 
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage()); 
        }
    }

    static void ordenarPorCategoria() {
        Collections.sort(jogos, Comparator.comparing(item -> item.categoria)); 
        salvarOrdenado("JogosOrdenadosporCategoria.csv"); 
        System.out.println("Jogos ordenados por categoria e salvo no arquivo 'JogosOrdenadosporCategoria.csv'.");
    }

    static void ordenarPorAvaliacao() {
        Collections.sort(jogos, (a, b) -> Double.compare(b.avaliacao, a.avaliacao)); 
        salvarOrdenado("JogosOrdenadosporAvaliacao.csv"); 
        System.out.println("Jogos ordenados por avaliação e salvo no arquivo 'JogosOrdenadosporAvaliacao.csv'.");
    }

    static void salvarOrdenado(String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            for (Item item : jogos) {
                writer.println(item.jogo + "," + item.categoria + "," + item.avaliacao); 
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage()); 
        }
    }
}
