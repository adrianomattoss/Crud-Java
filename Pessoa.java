public class Pessoa {
    protected int codigo;
    protected String nome;
    protected String telefone;
    protected String dataNascimento;
    protected String dataCadastro;
    protected String dataAlteracao;

    public Pessoa(int codigo, String nome, String telefone, String dataNascimento,
                  String dataCadastro, String dataAlteracao) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.dataAlteracao = dataAlteracao;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "Código:" + codigo +
                ", Nome: '" + nome + '\'' +
                ", Telefone: '" + telefone + '\'' +
                ", Data Nascimento: '" + dataNascimento + '\'' +
                ", Data Cadastro: '" + dataCadastro + '\'' +
                ", Data Alteracao: '" + dataAlteracao + '\'' +
                '}' + "\n";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(String dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

}

