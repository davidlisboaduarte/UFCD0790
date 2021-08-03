package mainprojeto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SocioA {

    private boolean dirigenteA;
    private static int idA = 0;
    private String tipoA = "SAdulto-";

    public SocioA(boolean dirigenteA, int aulas, String nome, int nif, int nascimento) {
        this.dirigenteA = dirigenteA;
        idA++;
        tipoA = tipoA + idA;
    }

    public SocioA() {
    }

    public void displayAdulto(Connection conn) {
        String sql = "SELECT * FROM mensal RIGHT JOIN socios ON mensal.id_mensal = socios.id_socio WHERE id_tipo=2;";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("SAdulto-" + rs.getInt("id_socio"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("NIF: " + rs.getInt("nif"));
                System.out.println("Ano de nascimento: " + rs.getInt("anoN"));
                //if
                System.out.println("Nº de aulas: " + rs.getInt("nAulas"));
                int aulas = rs.getInt("nAulas");
                int valor = aulas * 25;
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
            }
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

    public boolean getDirigenteA() {
        return dirigenteA;
    }

    public void setDirigenteA(boolean dirigenteA) {
        this.dirigenteA = dirigenteA;
    }

}
