package desafio.operacoes;


import desafio.dominio.Curso;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

public class CadastraEListaCursos {

    private final String diretorio;

    public CadastraEListaCursos(String diretorio) {
        this.diretorio = diretorio;
    }

    public void cadastra(Curso curso) {

        try {
            if (!Files.exists(Path.of(this.diretorio))) {
                Files.createFile(Path.of(this.diretorio));
            }

            if (cursoJaExiste(curso.getCodigo())) {
                throw new IllegalArgumentException("Curso já existente no sistema.");
            }

            Files.writeString(Path.of(diretorio), curso.getCodigo() + "," + curso.getCargaHoraria() + "," + curso.getNome() + "," + curso.getNivel() + "\n", StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar Curso! "+e.getMessage());
        }
    }

    public void listaCursos() {

        try {

            Files.readAllLines(Path.of(diretorio)).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Erro ao listar cursos");
        }
    }

    private boolean cursoJaExiste(String codigoASerCadastrado) throws IOException {

        var mapCodigoCurso = new HashMap<>();

        var linhas = Files.readAllLines(Path.of(diretorio));

        linhas.forEach(linha -> {

            var coluna = linha.split(",");
            String codigoExistente = coluna[0];

            mapCodigoCurso.put("cod", codigoExistente);
        });

        return mapCodigoCurso.containsValue(codigoASerCadastrado);
    }
}
