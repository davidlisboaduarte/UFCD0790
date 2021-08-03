package mainprojeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EncEdu {

    private int id;
    private int ide;
    private String encarregado;
    private int menoresAcargo;

    private List<EncEdu> listaEncarregadoEducacao = new ArrayList();

    public EncEdu(String encarregado) {
        this.encarregado = encarregado;
    }

    public EncEdu() {
    }

    public EncEdu(List<EncEdu> listaEncarregadoEducacao) {
        this.listaEncarregadoEducacao = listaEncarregadoEducacao;
    }

    //construtor para o editar
    public EncEdu(int ide, String nome) {
        this.ide = ide;
        this.encarregado = nome;
    }

    public EncEdu(int id) {
        this.id = id;
    }

    public void criarEnc(Connection conn, EncEdu edu) {
        //ENC INSERT
        PreparedStatement ps = null;
        String sql = "INSERT INTO enc (nome_enc) VALUES (?)";

        int count;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, edu.getEncarregado());

            count = ps.executeUpdate();

            if (count > 0) {
                System.out.println("Inseriu com sucesso!");
            } else {
                System.out.println("Não conseguiu inserir os dados!");
            }
        } catch (SQLException ex) {
            ex.getMessage();
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
    }

    public void idCriar(Connection conn, EncEdu edu) {
        String sql = "SELECT MAX(id_enc) FROM enc;"; // tem que ser select max (id_enc) from tabela
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // System.out.println(rs.getInt("MAX(id_enc)"));
                id = rs.getInt("MAX(id_enc)");
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

    public int editarEnc(Connection conn, int ide) {
        PreparedStatement ps = null;
        String sql = "UPDATE socios, enc INNER JOIN socios ON socios.id_enc = enc.id_enc SET socios.id_enc = enc.id_enc ;";
        UPDATE T1, T2
SET T1.c2 = T2.c2,
      T2.c3 = expr
WHERE T1.c1 = T2.c1
        //nome, nif, anoNasc, dirigente, idt, edu)
        int count;
        try {
            ps = conn.prepareStatement(sql);
            String encarregado = "";
            ps.setString(1, encarregado);
            ps.setInt(2, getIde());

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
        return ide;
    }

    public void encArray(Connection conn) {
        String sql = "SELECT * FROM enc;"; // tem que ser select max (id_enc) from tabela
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            EncEdu record;
            while (rs.next()) {
                record = new EncEdu();
                // System.out.println(rs.getInt("MAX(id_enc)"));
                int id = rs.getInt("id_enc");
                String encarregado = rs.getString("nome_enc");

                record.setId(id);
                record.setEncarregado(encarregado);

                listaEncarregadoEducacao.add(record);
            }
            System.out.println(listaEncarregadoEducacao);
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

    public static int buscarIdEnc(Connection conn, String enc) {
        int id = 0;
        Statement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT enc.nome_enc, enc.id_enc FROM enc WHERE nome_enc = '" + enc + "';";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                id = rs.getInt("id_enc");
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return id;
    }

    public int buscaridEncedit(Connection conn, int ids) {
        Statement stmt = null;
        ResultSet rs = null;
        int ide = 0;
        String sql = "SELECT socios.id_socio, socios.id_enc FROM socios WHERE id_socio = ";
        // SELECT enc.nome_enc, enc.id_enc FROM enc WHERE nome_enc = 
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ide = rs.getInt("id_enc");
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return ide;
    }

    public void deleteEnc(Connection conn, int id) {
        String sql = "DELETE FROM enc WHERE id_enc = ?";
        PreparedStatement ps = null;
        int count;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("Eliminou com sucesso!");
            } else {
                System.out.println("Não conseguiu eliminou sem sucesso!");
            }
            ps.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEncarregado() {
        return encarregado;
    }

    public void setEncarregado(String nome) {
        this.encarregado = nome;
    }

    public int getMenoresAcargo() {
        return menoresAcargo;
    }

    public void setMenoresAcargo(int menoresAcargo) {
        this.menoresAcargo = menoresAcargo;
    }

    public List<EncEdu> getListaEncarregadoEducacao() {
        return listaEncarregadoEducacao;
    }

    public void setListaEncarregadoEducacao(List<EncEdu> listaEncarregadoEducacao) {
        this.listaEncarregadoEducacao = listaEncarregadoEducacao;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    @Override
    public String toString() {
        return "\nId do encarregado: " + id + "\tNome: " + encarregado;
    }

    void setIdM(int idM) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
