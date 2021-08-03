package mainprojeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mensal {

    private int idM;
    private String mes;
    private int aulas;
    private int idS;

    public Mensal() {
    }

    public Mensal(int idS, String mes, int aulas) {
        this.idS = idS;
        this.mes = mes;
        this.aulas = aulas;
    }

    public Mensal(String mes, int aulas, int idM) {
        this.mes = mes;
        this.aulas = aulas;
        this.idM = idM;
    }

    public void criarMensal(Connection conn, Mensal mensal) {
        //MENSAL INSERT
        PreparedStatement ps = null;
        String sql = "INSERT INTO mensal (id_mensal, mes, nAulas, id_socio) VALUES (null,?, ?, ?);";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, mensal.getMes());
            ps.setInt(2, mensal.getAulas());
            ps.setInt(3, mensal.getIdS());

            int count = ps.executeUpdate();

            if (count > 0) {
                System.out.println("Inseriu com sucesso!");
            } else {
                System.out.println("Não conseguiu inserir os dados!");
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void editMes(Connection conn, Mensal mensal) {
        PreparedStatement ps = null;
        String sql = "UPDATE mensal SET mes = ?, nAulas =?  WHERE id_mensal = ?";
        //UPDATE socios SET nome = ?, nif = ?, anoN = ?, dirigente = ?, id_tipo = ? WHERE id_socio = ?

        int count;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, mensal.getMes());
            ps.setInt(2, mensal.getAulas());
            ps.setInt(3, mensal.getIdM());

            count = ps.executeUpdate();

            if (count > 0) {
                System.out.println("Editou com sucesso!");
            } else {
                System.out.println("Não conseguiu editar os dados!");
            }
            ps.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void menorMensal(Connection conn) {
        String sql = "SELECT * FROM mensal RIGHT JOIN socios ON mensal.id_mensal = socios.id_socio WHERE id_tipo=1;";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            double totalSoma = 0;
            while (rs.next()) {
                System.out.println("SMenor-" + rs.getInt("id_socio"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Nº de aulas: " + rs.getInt("nAulas"));
                int aulas = rs.getInt("nAulas");

                double valor = aulas * 25;
                double desconto = valor * 0.20;
                double total = valor - desconto;

                System.out.println("A pagar: " + total + "€");
                System.out.println();

                totalSoma += total;

            }
            System.out.println("Total a pagar de todos os sócios menores: " + totalSoma);

        } catch (SQLException ex) {
            ex.getMessage();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Tentou encerrar um ResultSet nulo!");
            }
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Tentou encerrar um Statement nulo!");
            }
        }
    }

    public void AdultoMensal(Connection conn) {
        String sql = "SELECT * FROM mensal RIGHT JOIN socios ON mensal.id_mensal = socios.id_socio WHERE id_tipo=2;";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            double totalSoma = 0;
            while (rs.next()) {
                System.out.println("SAdulto-" + rs.getInt("id_socio"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Nº de aulas: " + rs.getInt("nAulas"));
                int aulas = rs.getInt("nAulas");

                double valor = aulas * 25;
                boolean dirigenteA = rs.getBoolean("dirigente");
                if (dirigenteA == true) {
                    double diri = 0.0;
                    System.out.println("dirigente: " + diri);
                    System.out.println();
                } else {
                    System.out.println("dirigente: " + rs.getBoolean("dirigente"));
                    System.out.println("Valor a pagar: " + valor + "€");
                    System.out.println();
                }

                totalSoma += valor;

            }
            System.out.println("Total a pagar de todos os sócios Adultos: " + totalSoma);

        } catch (SQLException ex) {
            ex.getMessage();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Tentou encerrar um ResultSet nulo!");
            }
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Tentou encerrar um Statement nulo!");
            }
        }
    }

    public void SeniorMensal(Connection conn) {
        String sql = "SELECT * FROM mensal RIGHT JOIN socios ON mensal.id_mensal = socios.id_socio WHERE id_tipo=3;";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            double totalSoma = 0;
            while (rs.next()) {
                System.out.println("SSenior-" + rs.getInt("id_socio"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Nº de aulas: " + rs.getInt("nAulas"));
                int aulas = rs.getInt("nAulas");

                double valor = aulas * 25;
                double desconto = valor * 0.10;
                double total = valor - desconto;

                boolean dirigenteA = rs.getBoolean("dirigente");
                if (dirigenteA == true) {
                    double diri = 0.0;
                    System.out.println("dirigente: " + diri);
                    System.out.println();
                } else {
                    System.out.println("dirigente: " + rs.getBoolean("dirigente"));
                    System.out.println("Valor a pagar: " + total + "€");
                    System.out.println();
                    totalSoma += total;
                }
            }
            System.out.println("Total a pagar de todos os sócios Senior: " + totalSoma);

        } catch (SQLException ex) {
            ex.getMessage();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Tentou encerrar um ResultSet nulo!");
            }
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Tentou encerrar um Statement nulo!");
            }
        }
    }

    public int getIdM() {
        return idM;
    }

    public void setIdM(int idM) {
        this.idM = idM;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getAulas() {
        return aulas;
    }

    public void setAulas(int aulas) {
        this.aulas = aulas;
    }

    public int getIdS() {
        return idS;
    }

    public void setIdS(int idS) {
        this.idS = idS;
    }

}
