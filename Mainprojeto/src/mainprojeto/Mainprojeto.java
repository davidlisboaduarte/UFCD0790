package mainprojeto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Mainprojeto {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        DbUtil db = new DbUtil();
        Connection conn = null;

        Socio soc = new Socio();
        EncEdu edu1 = new EncEdu();

        List<EncEdu> listaEncarregadosEducacao = new ArrayList();
        listaEncarregadosEducacao.add(edu1);
        int menu;
        do {
            System.out.println(" _________________________________________________________________");
            System.out.println("|                                                                 |");
            System.out.println("|\t Criar novo sócio (carrega 1):                            |\t");//bruno/david
            System.out.println("|\t Editar sócio (carrega 2):                                |\t");//bruno/david
            System.out.println("|\t Consultar todos os Adultos (carrega 3):                  |\t");//bruno
            System.out.println("|\t Consultar todos os Menores (carrega 4):                  |\t");//david
            System.out.println("|\t Consultar todos os Séniores (carrega 5):                 |\t");//bruno
            System.out.println("|\t Ver mensalidades (carrega 6):                            |\t");//david
            System.out.println("|\t Detalhes dos encarregados de educação (carrega 7):       |\t");//david
            System.out.println("|\t Apagar... (carrega 8):                                   |\t");//bruno
            System.out.println("|\t Sair (carrega 0):                                        |");
            System.out.println("|_________________________________________________________________|");
            System.out.println();

            menu = sc.nextInt();
            switch (menu) {
                case 0:
                    System.out.println("");
                    break;
                case 1:
                    System.out.println("Ano de nascimento: ");
                    int anoNasc = sc.nextInt();
                    LocalDate data = LocalDate.now();
                    int ano = data.getYear();
                    int idade = ano - anoNasc;

                    if (idade < 18) {
                        sc.nextLine();

                        System.out.println("Nome: ");
                        String nome = "";
                        nome = Valida.validar3(nome);

                        System.out.println("NIF: ");
                        int nif = 0;
                        nif = Valida.validar1(nif);

                        System.out.println("Mês a registar (Insira as três primeiras letras do mês): ");
                        String mes = "";
                        mes = Valida.validar3(mes);

                        System.out.println("Nº de aulas nesse mês: ");
                        int aulas = 0;
                        aulas = Valida.validar5(aulas);

                        //ver se dá para validar!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        System.out.println("Encarregado de educação: ");
                        String enc = sc.nextLine();

                        boolean dirigente = false;

                        conn = db.getConnection();

                        int idt = 1;
                        EncEdu edu = new EncEdu();
                        EncEdu encarregado = new EncEdu();

                        int idEnc = encarregado.buscarIdEnc(conn, enc);
                        if (idEnc == 0) {
                            edu.criarEnc(conn, new EncEdu(enc));
                            edu.idCriar(conn, new EncEdu());
                            soc.criarSocio(conn, new Socio(nome, nif, anoNasc, dirigente, idt, edu));
                            soc.idSocio(conn, new Socio());
                            soc.criarMensalSocio(conn, new Socio(soc.getIds(), mes, aulas));
                        } else {
                            soc.criarSocioMenor(conn, new Socio(nome, nif, anoNasc, dirigente, idt, idEnc));
                            soc.idSocio(conn, new Socio());
                            soc.criarMensalSocio(conn, new Socio(soc.getIds(), mes, aulas));
                        }

                        conn.close();

                    } else if (idade > 17 && idade < 65) {
                        sc.nextLine();

                        System.out.println("Nome: ");
                        String nome = "";
                        nome = Valida.validar3(nome);

                        System.out.println("NIF: ");
                        int nif = 0;
                        nif = Valida.validar1(nif);

                        System.out.println("Mês a registar: (Insira as três primeiras letras do mês) ");
                        String mes = "";
                        mes = Valida.validar3(mes);

                        System.out.println("Nº de aulas nesse mês: ");
                        int aulas = 0;
                        aulas = Valida.validar5(aulas);

                        System.out.println("Este sócio é dirigente? (se sim escreva ´true', se não escreva 'false') ");
                        //boolean dirigente = sc.nextBoolean();
                        boolean dirigente = false;
                        dirigente = Valida.validar4(dirigente);

                        
                        String enc = null;

                        conn = db.getConnection();

                        int idt = 2;
                        EncEdu edu = new EncEdu();

                        edu.criarEnc(conn, new EncEdu(enc));
                        edu.idCriar(conn, new EncEdu());
                        soc.criarSocio(conn, new Socio(nome, nif, anoNasc, dirigente, idt, edu));
                        soc.idSocio(conn, new Socio());
                        soc.criarMensalSocio(conn, new Socio(soc.getIds(), mes, aulas));

                        conn.close();
                    } else {
                        sc.nextLine();

                        System.out.println("Nome: ");
                        String nome = "";
                        nome = Valida.validar3(nome);

                        System.out.println("NIF: ");
                        int nif = 0;
                        nif = Valida.validar1(nif);

                        System.out.println("Mês a registar (Insira as três primeiras letras do mês): ");
                        String mes = "";
                        mes = Valida.validar3(mes);

                        System.out.println("Nº de aulas nesse mês: ");
                        int aulas = 0;
                        aulas = Valida.validar5(aulas);

                        System.out.println("Este sócio é dirigente? (se sim escreva ´true', se não escreva 'false') ");
                        //boolean dirigente = sc.nextBoolean();
                        boolean dirigente = false;
                        dirigente = Valida.validar4(dirigente);

                        /*//ver se dá para validar!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        System.out.println("Encarregado de educação: ");
                        String enc = sc.nextLine();*/
                        String enc = null;

                        conn = db.getConnection();

                        int idt = 3;
                        EncEdu edu = new EncEdu();

                        edu.criarEnc(conn, new EncEdu(enc));
                        edu.idCriar(conn, new EncEdu());
                        soc.criarSocio(conn, new Socio(nome, nif, anoNasc, dirigente, idt, edu));
                        soc.idSocio(conn, new Socio());
                        soc.criarMensalSocio(conn, new Socio(soc.getIds(), mes, aulas));

                        conn.close();
                    }

                    System.out.println("Quer voltar ao menu? (carregue de 1 a 5)");
                    System.out.println("Se quiser sair (carregue em 0)");
                    menu = sc.nextInt();
                    break;
                case 2:
                    //é necessário estrutura condicional para definir id_tipo dada possível
                    //alteração de anoNascimento

                    System.out.println("Insira o ID do sócio a alterar: ");
                    int ids = sc.nextInt();
                    sc.nextLine();

                    boolean dirigente = false;
                    int anoNasc2 = 0;
                    String encarregado = "";

                    System.out.println("Nome: ");
                    String nome = "";
                    nome = Valida.validar3(nome);

                    System.out.println("NIF: ");
                    int nif = 0;
                    nif = Valida.validar1(nif);

                    System.out.println("Ano de nascimento: ");
                    anoNasc2 = Valida.validar1(anoNasc2);
                    LocalDate data2 = LocalDate.now();
                    int ano2 = data2.getYear();
                    int idade2 = ano2 - anoNasc2;

                    if (idade2 < 18) {

                        System.out.println("Encarregado de educação: ");
                        encarregado = Valida.validar3(encarregado);
                        dirigente = false;
                        int idt = 1;
                        conn = db.getConnection();
                        soc.editSocios(conn, new Socio(ids, nome, nif, anoNasc2, dirigente, 1));
                        EncEdu edu2 = new EncEdu();
                        edu2.buscaridEncedit(conn, ids);
                        edu2.editarEnc(conn, new EncEdu(edu2.getId(), encarregado));
                        //conn, new Socio(soc.getIds()
                        conn.close();
                    } else if (idade2 > 17 && idade2 < 65) {

                        System.out.println("Este sócio é dirigente? (se sim escreva ´true', se não escreva 'false') ");
                        dirigente = Valida.validar4(dirigente);
                        encarregado = null;
                        int idt = 2;
                        conn = db.getConnection();
                        soc.editSocios(conn, new Socio(ids, nome, nif, anoNasc2, dirigente, 2));
                        // EncEdu edu2 = new EncEdu(id, encarregado);
                        // edu2.editEnc(conn, edu2);
                        conn.close();
                    } else {

                        System.out.println("Este sócio é dirigente? (se sim escreva ´true', se não escreva 'false') ");
                        dirigente = Valida.validar4(dirigente);
                        encarregado = null;
                        int idt = 3;
                        conn = db.getConnection();
                        Socio socE = new Socio(ids, nome, nif, anoNasc2, dirigente, 3);
                        socE.editSocios(conn, socE);
                        //  EncEdu edu2 = new EncEdu(id, encarregado);
                        //   edu2.editEnc(conn, edu2);
                        conn.close();
                    }

                 
                    System.out.println("Quer voltar ao menu? (carregue de 1 a 5)");
                    System.out.println("Se quiser sair (carregue em 0)");
                    menu = sc.nextInt();
                    break;
                case 3:
                    //adulto
                    conn = db.getConnection();
                    SocioA adulto = new SocioA();
                    adulto.displayAdulto(conn);
                    System.out.println("Quer voltar ao menu? (carregue de 1 a 5)");
                    System.out.println("Se quiser sair (carregue em 0)");
                    menu = sc.nextInt();

                    break;
                case 4:
                    //menor
                    conn = db.getConnection();
                    SocioM menor = new SocioM();
                    menor.displayMenor(conn);
                    System.out.println("Quer voltar ao menu? (carregue de 1 a 5)");
                    System.out.println("Se quiser sair (carregue em 0)");
                    menu = sc.nextInt();

                    break;
                case 5:
                    //senior
                    SocioS senior = new SocioS();
                    conn = db.getConnection();
                    senior.displaySenior(conn);
                    System.out.println("Quer voltar ao menu? (carregue de 1 a 5)");
                    System.out.println("Se quiser sair (carregue em 0)");

                    menu = sc.nextInt();

                    break;

                case 6:
                    int mensalidade1;
                    System.out.println("Inserir mês: (carregue 1)");
                    System.out.println("Editar mês: (carregue 2)");
                    System.out.println("Ver mensalidade dos socios: (carregue 3)");
                    mensalidade1 = sc.nextInt();

                    switch (mensalidade1) {

                        case 1:
                            conn = db.getConnection();
                            int idS = 0;
                            System.out.println("Insira o id do socio: ");
                            idS = Valida.validar1(idS);
                            String mes = "";
                            System.out.println("Insira o mês: ");
                            mes = Valida.validar3(mes);
                            int aulas = 0;
                            System.out.println("Insira o numero de aulas: ");
                            aulas = Valida.validar1(aulas);
                            Mensal mensal = new Mensal();
                            mensal.criarMensal(conn, new Mensal(idS, mes, aulas));
                            conn.close();

                            System.out.println();
                            System.out.println("Quer voltar ao menu? (carregue de 1 a 5)");
                            System.out.println("Se quiser sair (carregue em 0)");

                            menu = sc.nextInt();
                            break;
                        case 2:
                            conn = db.getConnection();
                            int idM = 0;
                            System.out.println("Insira o id do mês que pretende mudar: ");
                            idM = Valida.validar1(idM);
                            System.out.println("Insira o mês: ");
                            String mesE = sc.next();
                            System.out.println("Insira o numero de aulas: ");
                            int aulasE = sc.nextInt();
                            Mensal mensal2 = new Mensal();
                            mensal2.editMes(conn, new Mensal(mesE, aulasE, idM));
                            conn.close();

                            System.out.println();
                            System.out.println("Quer voltar ao menu? (carregue de 1 a 5)");
                            System.out.println("Se quiser sair (carregue em 0)");

                            menu = sc.nextInt();
                            break;
                        case 3:
                            conn = db.getConnection();
                            Mensal mensal3 = new Mensal();

                            System.out.println("MENORES:");
                            mensal3.menorMensal(conn);
                            System.out.println();

                            System.out.println("ADULTOS:");
                            mensal3.AdultoMensal(conn);
                            System.out.println();

                            System.out.println("SENIOR:");
                            mensal3.SeniorMensal(conn);
                            System.out.println();

                            conn.close();

                            System.out.println();
                            System.out.println("Quer voltar ao menu? (carregue de 1 a 5)");
                            System.out.println("Se quiser sair (carregue em 0)");

                            menu = sc.nextInt();
                            break;
                        default:
                            System.out.println("Não existe esse mês!");
                            break;
                    }

                    break;

                case 7:
                    int EncMenu;
                    System.out.println("Editar Encarregado: (Carregue 1)");
                    System.out.println("Ver Encarregados: (Carregue 2)");
                    EncMenu = sc.nextInt();
                    switch (EncMenu) {

                        case 1:
                            conn = db.getConnection();
                            int ide = 0;

                            System.out.println("Insira o id do Encarregado: ");
                            ide = Valida.validar1(ide);
                            System.out.println("Insira o nome do encarregado");
                            String nomeEnc = sc.next();
                            EncEdu editEnc = new EncEdu();
                            editEnc.editarEnc(conn, new EncEdu(ide, nomeEnc));
                            conn.close();

                            System.out.println();
                            System.out.println("Quer voltar ao menu? (carregue de 1 a 5)");
                            System.out.println("Se quiser sair (carregue em 0)");
                            menu = sc.nextInt();
                            break;
                        case 2:
                            EncEdu listaenc = new EncEdu();
                            conn = db.getConnection();
                            listaenc.encArray(conn);
                            listaenc.getListaEncarregadoEducacao();
                            conn.close();

                            System.out.println();
                            System.out.println("Quer voltar ao menu? (carregue de 1 a 5)");
                            System.out.println("Se quiser sair (carregue em 0)");
                            menu = sc.nextInt();
                            break;
                    }
                    break;

                case 8:

                    int delete;
                    System.out.println("Apagar Sócio: (Carregue 1)");
                    System.out.println("Apagar Encarregado: (Carregue 2) (Necessita de apagar o sócio correspondente primeiro!)");
                    delete = sc.nextInt();

                    switch (delete) {
                        case 1:
                            conn = db.getConnection();
                            System.out.println("Insira o id do socio associado: ");
                            int idS = sc.nextInt();

                            Socio soc2 = new Socio();
                            soc2.deleteMensalSocio(conn, idS);
                            System.out.println("Quer voltar ao menu? (carregue de 1 a 5)");
                            System.out.println("Se quiser sair (carregue em 0)");
                            conn.close();
                            menu = sc.nextInt();
                            break;
                        case 2:
                            conn = db.getConnection();
                            System.out.println("Insira o id do encarregado do socio associado: ");
                            int ide = sc.nextInt();
                            EncEdu edu = new EncEdu();
                            edu.deleteEnc(conn, ide);

                            System.out.println("Quer voltar ao menu? (carregue de 1 a 5)");
                            System.out.println("Se quiser sair (carregue em 0)");
                            conn.close();
                            menu = sc.nextInt();
                            break;
                    }
                    break;

                default:
                    System.out.println("Essa opção não é valida!");
                    break;
            }
        } while (menu != 0);
    }

}
