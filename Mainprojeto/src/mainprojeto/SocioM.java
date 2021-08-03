package mainprojeto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SocioM {

    private EncEdu ee;
    private String tipoM = "SMenor-";
    private static int idM = 0;
    private int idE = 0;
    private List<SocioM> listaMenores = new ArrayList();

    public SocioM(EncEdu ee, int aulas, String nome, int nif, int nascimento) {
        this.ee = ee;
        this.idE = idE;
        tipoM = tipoM + idM;

    }

    public SocioM() {
    }

    public void displayMenor(Connection conn) {
        String sql = "SELECT * FROM mensal RIGHT JOIN socios ON mensal.id_mensal = socios.id_socio WHERE id_tipo=1;";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("SMenor-" + rs.getInt("id_socio"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("NIF: " + rs.getInt("nif"));
                System.out.println("Ano de nascimento: " + rs.getInt("anoN"));
                System.out.println("Nº de aulas: " + rs.getInt("nAulas"));
                int aulas = rs.getInt("nAulas");
                double valor = aulas * 25;
                double desconto = valor * 0.20;
                double total = valor - desconto;
                System.out.println("Valor: " + total + "€");
                System.out.println();
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

    public void MenoresArray(Connection conn) {
        String sql = "SELECT id_enc FROM socios;"; // tem que ser select max (id_enc) from tabela
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            SocioM record;
            while (rs.next()) {
                record = new SocioM();
                // System.out.println(rs.getInt("MAX(id_enc)"));
                int idE = rs.getInt("id_enc");

                record.setIdM(idE);

                listaMenores.add(record);
            }
            System.out.println(listaMenores);
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

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public List<SocioM> getListaMenores() {
        return listaMenores;
    }

    public void setListaMenores(List<SocioM> listaMenores) {
        this.listaMenores = listaMenores;
    }

    public EncEdu getEe() {
        return ee;
    }

    public void setEe(EncEdu ee) {
        this.ee = ee;
    }

    public String getTipoM() {
        return tipoM;
    }

    public void setTipoM(String tipoM) {
        this.tipoM = tipoM;
    }

    public static int getIdM() {
        return idM;
    }

    public static void setIdM(int idM) {
        SocioM.idM = idM;
    }

    /*public double pagamento() {
    double pagamentoMenor = super.pagamento() - super.pagamento() * 0.20;
    
    return pagamentoMenor;
    }*/
}
