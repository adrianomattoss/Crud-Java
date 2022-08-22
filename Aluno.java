public class Aluno extends Pessoa{

    public Aluno(int codigo, String nome, String telefone, String dataNascimento, String dataCadastro, String dataAlteracao) {
        super(codigo, nome, telefone, dataNascimento, dataCadastro, dataAlteracao);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "Código:" + codigo +
                ", Nome: '" + nome + '\'' +
                ", Telefone: '" + telefone + '\'' +
                ", Data Nascimento: '" + dataNascimento + '\'' +
                ", Data Cadastro: '" + dataCadastro + '\'' +
                ", Data Alteracao: '" + dataAlteracao + '\'' +
                ", Nota Final: " + nota + '\'' +
                '}' + "\n";
    }

    private  float nota;

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {this.nota = nota;
    }
}
