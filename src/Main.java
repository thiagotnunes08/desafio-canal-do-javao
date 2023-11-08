

import desafio.dominio.Curso;
import desafio.dominio.Estudante;
import desafio.dominio.Turma;
import desafio.operacoes.CadastraEListaCursos;
import desafio.operacoes.CadastraEListaEstudantes;
import desafio.operacoes.CadastraEListaTurma;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        var leitor = new Scanner(System.in);
        var opcaoEscolhida = 0;

        while (opcaoEscolhida != 7) {
            exibirMenu();
            opcaoEscolhida = Integer.parseInt(leitor.nextLine());
            switch (opcaoEscolhida) {
                case 1 -> cadastraTurma(leitor);
                case 2 -> cadastradEstudante(leitor);
                case 3 -> cadastraCurso(leitor);
                case 4 -> listarTurmas();
                case 5 -> listaEstudante();
                case 6 -> listarCursos();
                case 7 -> System.out.println("Saindo....");
                default -> valorDefault();
            }
        }
    }
    private static void exibirMenu() {

        System.out.println("------------------------------");
        System.out.println("Escolha uma das opções abaixo:");
        System.out.println("1-Cadastrar turma");
        System.out.println("2-Cadastrar estudante");
        System.out.println("3-Cadastrar curso");
        System.out.println("4-Listar turmas");
        System.out.println("5-Listar estudantes");
        System.out.println("6-Listar cursos");
        System.out.println("7-Sair");
        System.out.println("------------------------------");
    }

    private static void cadastradEstudante(Scanner leitor) throws IOException {
        System.out.println("Digite qual o nome do estudante:");
        var nome = leitor.nextLine();
        System.out.println("Agora, digite o telefone do estudante:");
        var telefone = leitor.nextLine();
        System.out.println("Digite  o endereço do estudante:");
        var endereco = leitor.nextLine();
        System.out.println("Digite  o CPF do estudante:");
        var cpf = leitor.nextLine();
        System.out.println("Por fim, digite o email do estudante:");
        var email = leitor.nextLine();
        new CadastraEListaEstudantes("estudantes.csv").cadastra(new Estudante(nome, telefone, endereco, cpf, email));

    }

    private static void cadastraCurso(Scanner leitor) {
        System.out.println("Digite qual o codigo do curso:");
        var codigo = leitor.nextLine();
        System.out.println("Agora, digite o nome do curso:");
        var nome = leitor.nextLine();
        System.out.println("Digite a carga horaria do curso:");
        var cargaHoraria = leitor.nextLine();
        System.out.println("Digite qual o nivel do curso:");
        System.out.println("1=BASICO:");
        System.out.println("2=INTERMEDIARIO");
        System.out.println("3=AVANCADO");
        var nivel = leitor.nextLine();

        var curso = new Curso(codigo, nome, cargaHoraria, Integer.parseInt(nivel));

        new CadastraEListaCursos("cursos.csv").cadastra(curso);

    }


    private static void cadastraTurma(Scanner leitor) throws IOException {
        System.out.println("Digite qual o código de turma:");
        var codigo = leitor.nextLine();
        System.out.println("Agora, digite o codigo do curso:");
        var codigoCurso = leitor.nextLine();
        System.out.println("digite a data de inicio:");
        var dataInicio = leitor.nextLine();
        System.out.println("digite a data fim:");
        var dataFim = leitor.nextLine();
        System.out.println("Digite qual o periodo do curso:");
        System.out.println("1=matutino:");
        System.out.println("2=vespertino");
        System.out.println("3=noturno ");
        System.out.println("4=sábados ");
        var periodo = leitor.nextInt();
        new CadastraEListaTurma("turma.csv").cadastra(new Turma(codigo, codigoCurso, dataInicio, dataFim, periodo, "cursos.csv"));
    }

    private static void listarTurmas () throws IOException {

        new CadastraEListaTurma("turma.csv").lista();
    }

    private static void listaEstudante () {
        new CadastraEListaEstudantes("estudantes.csv").lista();
    }

    private static void valorDefault () {
        System.out.println("Opcção inválida! Escolhe alguma opção disponivel.");
    }

    private static void listarCursos () {
        new CadastraEListaCursos("cursos.csv").listaCursos();
    }
}
