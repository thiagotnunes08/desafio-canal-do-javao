package desafio.dominio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

public class Turma {

    private String codigo;
    private Curso curso;

    private LocalDate inicioEm;
    private LocalDate terminaEm;

    private Periodo periodo;

    public Turma(String codigo, String codigoCurso, String inicioEm, String terminaEm, Integer indicePeriodo,String diretorioCursos) throws IOException {
        this.codigo = codigo;
        this.curso = toCurso(codigoCurso,diretorioCursos);
        this.inicioEm = LocalDate.parse(inicioEm);
        this.terminaEm = LocalDate.parse(terminaEm);
        this.periodo = atribuiPeriodo(indicePeriodo);
    }
    public String getCodigo() {
        return codigo;
    }

    public Curso getCurso() {
        return curso;
    }

    public LocalDate getInicioEm() {
        return inicioEm;
    }

    public LocalDate getTerminaEm() {
        return terminaEm;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    private Periodo atribuiPeriodo(Integer indicePeriodo) {
        if (indicePeriodo.equals(1)) {
            return Periodo.MATUTINO;
        }
        if (indicePeriodo.equals(2)) {
            return Periodo.VESPERTINO;
        }
        if (indicePeriodo.equals(3)) {
            return Periodo.NOTURNO;
        }
        if (indicePeriodo.equals(4)) {
            return Periodo.SABADO;
        }

        return Periodo.INDEFINIDO;
    }
    private Curso toCurso(String codigoCurso,String diretorio) throws IOException {

        var curso = new ArrayList<Curso>();

        var linhasArquivoCurso = Files.readAllLines(Path.of(diretorio));

        linhasArquivoCurso.forEach(linha-> {

            var colunasArquivoCurso = linha.split(",");

            var codigo = colunasArquivoCurso[0];

            if (codigo.equals(codigoCurso)){
                curso.add(new Curso(codigo,colunasArquivoCurso[1],colunasArquivoCurso[2],getNivelIndex(colunasArquivoCurso[3])));
            }

        });

        return curso
                .stream()
                .findAny()
                .orElseThrow(()->
                        new IllegalArgumentException("Curso não cadastrado."));
    }
    private Integer getNivelIndex(String nivel) {

        if (nivel.equals("BASICO")) return 1;
        if (nivel.equals("INTERMEDIARIO")) return 2;
        if (nivel.equals("AVANCADO")) return 3;
        return 1;
    }

    public String getCodigoCurso() {
        return curso.getCodigo();
    }
}
