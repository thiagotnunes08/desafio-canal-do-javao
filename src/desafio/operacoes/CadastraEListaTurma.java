package desafio.operacoes;


import desafio.dominio.Turma;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class CadastraEListaTurma {


    private final String diretorio;

    public CadastraEListaTurma(String diretorio) {
        this.diretorio = diretorio;
    }

    public void cadastra(Turma turma) throws IOException {


        var diretorio = Path.of(this.diretorio);

        if (!Files.exists(diretorio)) {

            Files.createFile(diretorio);
        }

        if (turmaJaExiste(turma.getCodigo())) {
            throw new IllegalArgumentException("Turma já cadastrada no sistema");
        }

        Files.writeString(Path.of(this.diretorio),turma.getCodigo()+","+turma.getInicioEm()+","+turma.getTerminaEm()+","+turma.getCodigoCurso()+","+turma.getPeriodo());

    }

    public void lista() throws IOException {
        Files.readAllLines(Path.of(this.diretorio)).forEach(System.out::println);
    }

    private boolean turmaJaExiste(String codigo) throws IOException {

        var mapCodigo = new HashMap<>();

        Files.readAllLines(Path.of(this.diretorio)).forEach(linha-> {

            var colunas = linha.split(",");

            mapCodigo.put("cod",colunas[0]);

        });

        return mapCodigo.containsValue(codigo);
    }
}
