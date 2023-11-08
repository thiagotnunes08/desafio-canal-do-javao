package desafio.dominio;

public class Estudante {
    private String nome;
    private String telefone;
    private String endereco;
    private String cpf;
    private String email;

    public Estudante(String nome, String telefone, String endereco, String cpf,String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
}
