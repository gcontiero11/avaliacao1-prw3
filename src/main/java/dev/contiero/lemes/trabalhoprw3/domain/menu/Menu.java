package dev.contiero.lemes.trabalhoprw3.domain.menu;

import dev.contiero.lemes.trabalhoprw3.domain.model.Aluno;
import dev.contiero.lemes.trabalhoprw3.persistence.FakeRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FakeRepository repository = new FakeRepository();

        while (true) {
            System.out.println("** CADASTRO DE ALUNOS **");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Excluir aluno");
            System.out.println("3 - Alterar aluno");
            System.out.println("4 - Buscar aluno pelo nome");
            System.out.println("5 - Listar alunos (com status aprovação)");
            System.out.println("6 - FIM");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            if (opcao == 6) break;

            switch (opcao) {
                case 1:
                    System.out.println("CADASTRO ALUNO:");
                    System.out.println("Digite o nome: ");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o RA: ");
                    String RA = scanner.nextLine();
                    System.out.println("Digite o email: ");
                    String email = scanner.nextLine();
                    System.out.println("Digite a nota1: ");
                    BigDecimal nota1 = scanner.nextBigDecimal();
                    System.out.println("Digite a nota2: ");
                    BigDecimal nota2 = scanner.nextBigDecimal();
                    System.out.println("Digite a nota3: ");
                    BigDecimal nota3 = scanner.nextBigDecimal();
                    Aluno aluno = new Aluno();
                    repository.cadastrar(aluno);
                    break;
                case 2:
                    System.out.println("EXCLUIR ALUNO:");
                    System.out.println("Digite o nome do aluno: ");
                    String nomeExcluir = scanner.nextLine();
                    repo.excluir(nomeExcluir);
                    break;
                case 3:
                    System.out.println("ID do aluno a alterar: ");
                    Long idAlterar = scanner.nextLong();
                    scanner.nextLine(); // Limpar buffer
                    Aluno alunoAlterar = repo.buscarPorNome(scanner.nextLine());
                    if (alunoAlterar != null) {
                        System.out.println("Novo nome do aluno: ");
                        alunoAlterar.setNome(scanner.nextLine());
                        System.out.println("Aprovado? (true/false): ");
                        alunoAlterar.setAprovado(scanner.nextBoolean());
                        repo.alterar(alunoAlterar);
                    } else {
                        System.out.println("Aluno não encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Nome do aluno: ");
                    String nomeBuscar = scanner.nextLine();
                    Aluno alunoEncontrado = repo.buscarPorNome(nomeBuscar);
                    if (alunoEncontrado != null) {
                        System.out.println("Aluno encontrado: " + alunoEncontrado.getNome());
                    } else {
                        System.out.println("Aluno não encontrado.");
                    }
                    break;
                case 5:
                    List<Aluno> alunos = repo.listar();
                    for (Aluno a : alunos) {
                        System.out.println("Nome: " + a.getNome() + ", Aprovado: " + a.isAprovado());
                    }
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}
