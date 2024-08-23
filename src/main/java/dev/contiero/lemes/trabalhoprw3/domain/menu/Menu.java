package dev.contiero.lemes.trabalhoprw3.domain.menu;

import dev.contiero.lemes.trabalhoprw3.domain.model.Aluno;
import dev.contiero.lemes.trabalhoprw3.persistence.AlunoDao;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("banco");
        AlunoDao dao = new AlunoDao(factory);

        while (true) {
            System.out.println("** CADASTRO DE ALUNOS **");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Excluir aluno");
            System.out.println("3 - Alterar aluno");
            System.out.println("4 - Buscar aluno pelo nome");
            System.out.println("5 - Listar alunos (com status aprovação)");
            System.out.println("6 - FIM");

            System.out.println("Digite a opção:");
            int opcao = scanner.nextInt();
            scanner.nextLine();

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

                    Aluno novoAluno = new Aluno(nome, RA, email, nota1, nota2, nota3);

                    boolean sucessoCadastro = dao.save(novoAluno);
                    System.out.println(sucessoCadastro ? "Aluno cadastrado com sucesso!" : "Erro ao cadastrar o aluno.");
                    break;

                case 2:
                    System.out.println("EXCLUIR ALUNO:");
                    System.out.println("Digite o nome do aluno: ");
                    String nomeExcluir = scanner.nextLine();

                    Map<Aluno, Long> alunosParaExcluir = dao.getByName(nomeExcluir);
                    if (alunosParaExcluir.isEmpty()) {
                        System.out.println("Nenhum aluno encontrado com esse nome.");
                    } else {
                        Aluno alunoSelecionadoExcluir = selecionarAluno(scanner, alunosParaExcluir, "excluir");
                        if (alunoSelecionadoExcluir != null) {
                            boolean sucessoExclusao = dao.delete(alunoSelecionadoExcluir);
                            System.out.println(sucessoExclusao ? "Aluno excluído com sucesso!" : "Erro ao excluir o aluno.");
                        }
                    }
                    break;

                case 3:
                    System.out.println("ALTERAR ALUNO:");
                    System.out.println("Digite o nome do aluno: ");
                    String nomeAlterar = scanner.nextLine();

                    Map<Aluno, Long> alunosParaAlterar = dao.getByName(nomeAlterar);
                    if (alunosParaAlterar.isEmpty()) {
                        System.out.println("Nenhum aluno encontrado com esse nome.");
                    } else {
                        Aluno alunoParaAlterar = selecionarAluno(scanner, alunosParaAlterar, "alterar");
                        if (alunoParaAlterar != null) {
                            alteraAluno(alunoParaAlterar, scanner, dao);
                        }
                    }
                    break;

                case 4:
                    System.out.println("CONSULTAR ALUNO:");
                    System.out.println("Digite o nome: ");
                    String nomeParaBuscar = scanner.nextLine();

                    Map<Aluno, Long> alunosEncontrados = dao.getByName(nomeParaBuscar);
                    if (alunosEncontrados.isEmpty()) {
                        System.out.println("Nenhum aluno encontrado.");
                    } else {
                        for (Aluno aluno : alunosEncontrados.keySet()) {
                            System.out.println("Aluno encontrado: " + aluno.getNome() + ", RA: " + aluno.getRa());
                        }
                    }
                    break;

                case 5:
                    System.out.println("LISTA DE ALUNOS:");
                    Map<Aluno, Long> todosAlunos = dao.getAll();
                    if (todosAlunos.isEmpty()) {
                        System.out.println("Nenhum aluno cadastrado.");
                    } else {
                        printaAlunos(todosAlunos);
                    }
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }

    private static void printaAlunos(Map<Aluno, Long> todosAlunos) {
        for (Aluno aluno : todosAlunos.keySet()) {
            BigDecimal media = aluno.getNota1()
                    .add(aluno.getNota2())
                    .add(aluno.getNota3())
                    .divide(BigDecimal.valueOf(3), 2, BigDecimal.ROUND_HALF_UP);

            String situacao;
            if (media.compareTo(BigDecimal.valueOf(6)) >= 0) {
                situacao = "Aprovado";
            } else if (media.compareTo(BigDecimal.valueOf(4)) >= 0) {
                situacao = "Recuperação";
            } else {
                situacao = "Reprovado";
            }

            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Email: " + aluno.getEmail());
            System.out.println("RA: " + aluno.getRa());
            System.out.println("Notas: " + aluno.getNota1() + " - " + aluno.getNota2() + " - " + aluno.getNota3());
            System.out.println("Media: " + media);
            System.out.println("Situação: " + situacao);
            System.out.println();
        }
    }

    private static void alteraAluno(Aluno alunoSelecionadoAlterar, Scanner scanner, AlunoDao dao) {
        System.out.println("Dados atuais do aluno: " + alunoSelecionadoAlterar.getNome() + ", " + alunoSelecionadoAlterar.getEmail());

        System.out.println("Digite o novo nome (ou deixe em branco para manter o atual): ");
        String novoNome = scanner.nextLine();
        System.out.println("Digite o novo email (ou deixe em branco para manter o atual): ");
        String novoEmail = scanner.nextLine();
        System.out.println("Digite a nova nota1 (ou 0 para manter a atual): ");
        BigDecimal novaNota1 = scanner.nextBigDecimal();
        System.out.println("Digite a nova nota2 (ou 0 para manter a atual): ");
        BigDecimal novaNota2 = scanner.nextBigDecimal();
        System.out.println("Digite a nova nota3 (ou 0 para manter a atual): ");
        BigDecimal novaNota3 = scanner.nextBigDecimal();

        if (!novoNome.isEmpty()) alunoSelecionadoAlterar.setNome(novoNome);
        if (!novoEmail.isEmpty()) alunoSelecionadoAlterar.setEmail(novoEmail);
        if (novaNota1.compareTo(BigDecimal.ZERO) > 0) alunoSelecionadoAlterar.setNota1(novaNota1);
        if (novaNota2.compareTo(BigDecimal.ZERO) > 0) alunoSelecionadoAlterar.setNota2(novaNota2);
        if (novaNota3.compareTo(BigDecimal.ZERO) > 0) alunoSelecionadoAlterar.setNota3(novaNota3);

        boolean sucessoAtualizacao = dao.update(alunoSelecionadoAlterar);
        System.out.println(sucessoAtualizacao ? "Aluno alterado com sucesso!" : "Erro ao alterar o aluno.");
    }

    private static Aluno selecionarAluno(Scanner scanner, Map<Aluno, Long> alunos, String acao) {
        if (alunos.size() == 1) {
            return alunos.keySet().iterator().next();
        }

        System.out.println("Múltiplos alunos encontrados. Selecione um aluno para " + acao + ":");
        int i = 1;
        for (Aluno aluno : alunos.keySet()) {
            System.out.println(i + " - " + aluno.getNome() + ", RA: " + aluno.getRa());
            i++;
        }

        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha > 0 && escolha <= alunos.size()) {
            return (Aluno) alunos.keySet().toArray()[escolha - 1];
        } else {
            System.out.println("Seleção inválida.");
            return null;
        }
    }
}
