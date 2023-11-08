package desafio.dominio;

public class Curso {

    private String codigo;
    private String nome;

    private String cargaHoraria;

    private Nivel nivel;

    public Curso(String codigo, String nome, String cargaHoraria, Integer nivel) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.nivel = atribuiNivel(nivel);

    }


    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public Nivel getNivel() {
        return nivel;
    }

    private Nivel atribuiNivel(Integer nivel) {
        switch (nivel) {
            case 1 -> {
                return Nivel.BASICO;
            }
            case 2 -> {
                return Nivel.INTERMEDIARIO;
            }
            case 3 -> {
                return Nivel.AVANCADO;
            }
        }

        return Nivel.BASICO;
    }
}


