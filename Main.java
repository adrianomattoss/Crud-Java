import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static ArrayList<Pessoa> cadastroPessoa = new ArrayList<>();
    static ArrayList<Aluno> cadastroAluno = new ArrayList<>();
    static Scanner src = new Scanner(System.in);

    private static int novoRegistro;

    private static String getDataHora() {

        DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dataAtual = new Date();
        return dataFormatada.format(dataAtual);

    }

    private static boolean encerrarCadastro() {

        boolean s, n, respostaValida, resultado = false;
        String res;

        System.out.println(" ");
        System.out.println("Cadastrar novo registro? [S/N]");

        do {
            res = src.next();
            System.out.println(" ");

            s = (res.equals("S") || res.equals("s"));
            n = (res.equals("N") || res.equals("n"));

            respostaValida = (s || n);

            if (respostaValida) {
                if (n)
                    resultado = true;
            } else {
                System.out.println(" ");
                System.out.println("Valor informado esta incorreto. Digitar S ou N.");
                System.out.println(" ");
            }

        } while (!respostaValida);

        return resultado;
    }

    private static void efetuarCadastro() {

        boolean cadastroFinalizado = false;

        while (!cadastroFinalizado) {

            novoRegistro += 1;

            System.out.print("> Nome: ");
            String nome = src.next();
            System.out.print("> Telefone: ");
            String telefone = src.next();
            System.out.print("> Data de Nascimento: ");
            String dataNascimento = src.next();
            System.out.println("> Nota final: ");
            String notaFinal = src.next();

            if (notaFinal.equals("0")) { //ARRUMAR PARA ENTRAR NULO

                Pessoa objPessoa = new Pessoa(novoRegistro, nome, telefone, dataNascimento,
                        getDataHora(), null);

                cadastroPessoa.add(objPessoa);

            } else {

                Aluno objAluno = new Aluno(novoRegistro, nome, telefone, dataNascimento,
                        getDataHora(),null);

                if (!notaFinal.equals(""))
                    objAluno.setNota(Float.parseFloat(notaFinal));

                cadastroAluno.add(objAluno);
            }

            cadastroFinalizado = encerrarCadastro();
        }
    }

    private static void efetuarExclusao() { //ADCIONAR PESSOA

        boolean continuarExclusao = true;

        while (continuarExclusao) {

            try {

                System.out.print("> Digite o código a ser excluído: ");

                int codigo = src.nextInt();

                boolean codigoEncontrado = false;

                for (int i = 0; i < cadastroPessoa.size(); i++) {
                    if (codigo == cadastroPessoa.get(i).getCodigo()) {

                        codigoEncontrado = true;
                        codigo = cadastroPessoa.get(i).getCodigo();

                        System.out.print("> Tem certeza que deseja excluir o registro [" + codigo + "]? (S/N)");
                        String selecao = src.next();

                        if (selecao.equals("S") || selecao.equals("s")) {

                            cadastroPessoa.remove(i);

                            System.out.println("Exclusão concluída!");

                        } else if (selecao.equals("N") || selecao.equals("n"))
                            System.out.println("Exclusão cancelada!");
                        else
                            System.out.println("Valor informado esta incorreto. Digite S ou N.");

                        break;
                    }
                }

                if (!codigoEncontrado)
                    System.out.println("Código não encontrado!");

                System.out.println("Deseja executar a exclusão novamente?");
                String selecao = src.next();

                if (selecao.equals("S") || selecao.equals("s"))
                    continuarExclusao = true;
                else if (selecao.equals("N") || selecao.equals("n"))
                    continuarExclusao = false;
                else
                    System.out.println("Valor informado esta incorreto. Digite S ou N.");

            } catch (InputMismatchException e) {
                System.out.println("Valor informado esta incorreto. Digite S ou N.");
            }
        }
    }


    private static void efetuarAlteracao() {

        int index = 0;
        boolean registroEncontrado = false;
        boolean isAluno = false;

        System.out.print("> Digite o código a ser alterado: ");

        int codigo = src.nextInt();

        for (int i = 0; i < cadastroPessoa.size(); i++) {
            if (codigo == cadastroPessoa.get(i).getCodigo()) {
                registroEncontrado = true;
                index = i;
            }
        }

        if (!registroEncontrado) {
            for (int i = 0; i < cadastroAluno.size(); i++) {
                if (codigo == cadastroAluno.get(i).getCodigo()) {
                    registroEncontrado = true;
                    isAluno  = true;
                    index = i;
                }
            }
        }

        if (!registroEncontrado)
            System.out.println("Registro não encontrado!");
        else {

            try {

                System.out.println("======================================");
                System.out.println("> Selecione qual campo deseja alterar:");
                System.out.println(" 1. Nome");
                System.out.println(" 2. Telefone");
                System.out.println(" 3. Data de Nascimento");

                if (isAluno)
                    System.out.println(" 4. Nota Final: ");

                System.out.println("--------------------------------------");

                int opcaoSelecionada = src.nextInt();

                String msgSelecao = "> Insira o novo valor para ";

                switch (opcaoSelecionada) {
                    case 1 -> msgSelecao += "[Nome]: ";
                    case 2 -> msgSelecao += "[Telefone]: ";
                    case 3 -> msgSelecao += "[Data de Nascimento]: ";
                    case 4 -> msgSelecao += "[Nota Final]: ";
                }

                System.out.println(msgSelecao);

                String novoValor = src.next();

                if (isAluno) {

                    switch (opcaoSelecionada) {
                        case 1 -> cadastroAluno.get(index).setNome(novoValor);
                        case 2 -> cadastroAluno.get(index).setTelefone(novoValor);
                        case 3 -> cadastroAluno.get(index).setDataNascimento(novoValor);
                        case 4 -> cadastroAluno.get(index).setNota(Float.parseFloat(novoValor));
                    }
                    cadastroAluno.get(index).setDataAlteracao(getDataHora());

                } else {

                    switch (opcaoSelecionada) {
                        case 1 -> cadastroPessoa.get(index).setNome(novoValor);
                        case 2 -> cadastroPessoa.get(index).setTelefone(novoValor);
                        case 3 -> cadastroPessoa.get(index).setDataNascimento(novoValor);
                    }
                    cadastroPessoa.get(index).setDataAlteracao(getDataHora());
                }

                System.out.println(">>> Registro atualizado com sucesso!");

            } catch (InputMismatchException e) {
                String str = (isAluno) ? "3" : "4";
                System.out.println("Opção invalida: Selecione um valor entre 1 e " + str + ".");
            }
        }
    }

    private static void exibirListaRegistros() {
        System.out.println(cadastroPessoa);
        System.out.println(" ");
        System.out.println(cadastroAluno);
    }

    public static void main(String[] args) {

        boolean sistemaFinalizado = false;

        while (!sistemaFinalizado) {

            System.out.println("======== SELECIONE UMA OPCAO ========");
            System.out.println(" 1. Cadastrar");
            System.out.println(" 2. Alterar");
            System.out.println(" 3. Excluir");
            System.out.println(" 4. Listar");
            System.out.println(" 5. Encerrar Programa");
            System.out.println("=====================================");

            try {

                byte opcaoSelecionada = src.nextByte();

                switch (opcaoSelecionada) {
                    case 1 -> System.out.println("=== CADASTRO DE PESSOAS E ALUNOS ====");
                    case 2 -> System.out.println("======= ALTERAÇÃO DE CADASTRO =======");
                    case 3 -> System.out.println("======= EXCLUSAO DE CADASTRO ========");
                    case 4 -> System.out.println("======= LISTA DE CADASTRADOS ========");
                    case 5 -> System.out.println("========= ENCERRAR PROGRAMA =========");
                    default -> System.out.println("Opção invalida: Selecione um valor entre 1 e 5");
                }

                if (opcaoSelecionada == 1) { //CADASTRO

                    try {
                        efetuarCadastro();
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro no cadastro, favor entrar em contato com desenvolvedor.");
                        break;
                    }

                } else if (opcaoSelecionada == 2) { //ALTERAÇÃO

                    try {
                        efetuarAlteracao();
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro na alteração do cadastro, favor entrar em contato com desenvolvedor.");
                        break;
                    }

                } else if (opcaoSelecionada == 3) { //EXCLUSÃO

                    try {
                        efetuarExclusao();
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro na exclusão dos registros, favor entrar em contato com desenvolvedor.");
                        break;
                    }

                } else if (opcaoSelecionada == 4) { //LISTAGEM

                    try {
                        exibirListaRegistros();
                    } catch (Exception e) {
                        System.out.println("Não foi possível exibir a lista de registros salvos.");
                        break;
                    }

                } else if (opcaoSelecionada == 5) { //ENCERRAMENTO

                    System.out.println("Tem certeza que deseja encerrar o programa? [S/N]");
                    String selecao = src.next();

                    if (selecao.equals("S") || selecao.equals("s")) {

                        sistemaFinalizado = true;
                        System.out.println(" Sistema finalizado!");
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Opção invalida: Selecione um valor entre 1 e 5");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado. Entre em contato com o desenvolvedor.");
            } finally {
                src.nextLine(); //estava dando erro no scanner quando um erro era gerado, essa foi a solução
            }
        }
    }
}


