package desafio.operacoes;


import desafio.dominio.Estudante;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

public class CadastraEListaEstudantes {

    private final String diretorio;

    public CadastraEListaEstudantes(String diretorio) {
        this.diretorio = diretorio;
    }

    public void cadastra(Estudante estudante) throws IOException {

        if (!Files.exists(Path.of(this.diretorio))) {
            Files.createFile(Path.of(this.diretorio));
        }

        if (existirComCpfOuEmail(estudante.getEmail(), estudante.getCpf())) {
            throw new IllegalArgumentException("cpf ou email já cadastrado no sistema");
        }

        Files.writeString(Path.of(this.diretorio), estudante.getNome() +","+estudante.getTelefone()+","+ estudante.getEndereco() + ","+ estudante.getCpf()+","+estudante.getEmail()+"\n", StandardOpenOption.APPEND);

    }

    public void lista() {

        try {

            Files.readAllLines(Path.of(this.diretorio)).forEach(System.out::println);
        }

        catch (Exception e){
            System.out.println("Erro ao listar arquivo de estudantes");
        }
    }

    private boolean existirComCpfOuEmail(String email, String cpf) throws IOException {

        var mapaCnpjEEmail = new HashMap<>();

        var estudantes = Files.readAllLines(Path.of(this.diretorio));

        estudantes.forEach(linha -> {

            var colunas = linha.split(",");
            var cpfConsultado = colunas[3];
            var emailConsultado = colunas[4];

            mapaCnpjEEmail.put("cpf", cpfConsultado);
            mapaCnpjEEmail.put("email", emailConsultado);

        });

        return mapaCnpjEEmail.containsValue(email) || mapaCnpjEEmail.containsValue(cpf);
    }
}
