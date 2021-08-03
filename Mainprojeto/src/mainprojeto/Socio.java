package mainprojeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Socio {

    private int idt = 0;
    private String nome;
    private int nif;
    private int nascimento;
    private String mes;
    private int aulas;
    private EncEdu enc;
    private boolean dirigente;
    private int ide = 0;
    private int ids = 0;
    private int idM = 0;

    //ta a ser utilizado
    public Socio() {
    }

    public Socio(String nome, int nif, int nascimento) {
        this.nome = nome;
        this.nif = nif;
        this.nascimento = nascimento;
    }

    public Socio(int idt) {
        this.idt = idt;
    }

    public Socio(String nome, int nif, int nascimento, boolean dirigente, int idt, EncEdu enc) {
        this.nome = nome;
        this.nif = nif;
        this.nascimento = nascimento;
        this.dirigente = dirigente;
        this.idt = idt;
        this.enc = enc;
    }

    public Socio(int ids, String mes, int aulas) {
        this.mes = mes;
        this.aulas = aulas;
        this.ids = ids;
    }

    //construtor para o editar
    public Socio(int ids, String nome, int nif, int nascimento, boolean dirigente, int idt) {
        this.ids = ids;
        this.nome = nome;
        this.nif = nif;
        this.nascimento = nascimento;
        this.dirigente = dirigente;
        this.idt = idt;
    }

    public Socio(String nome, int nif, int nascimento, boolean dirigente, int idt, int ide) {
        this.nome = nome;
        this.nif = nif;
        this.nascimento = nascimento;
        this.dirigente = dirigente;
        this.idt = idt;
        this.ide = ide;
    }

    public void criarSocioMenor(Connection conn, Socio socio) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO socios (id_socio, nome, nif,  anoN,  dirigente, id_tipo, id_enc) VALUES (null,?,?,?,?,?,?)";

        int count;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, socio.getNome());
            ps.setInt(2, socio.getNif());
            ps.setInt(3, socio.getNascimento());
            ps.setBoolean(4, socio.isDirigente());
            ps.setInt(5, socio.getIdt());
            ps.setInt(6, socio.getIde());
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

    public void criarSocio(Connection conn, Socio socio) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO socios (id_socio, nome, nif,  anoN,  dirigente, id_tipo, id_enc) VALUES (null,?,?,?,?,?,?)";

        int count;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, socio.getNome());
            ps.setInt(2, socio.getNif());
            ps.setInt(3, socio.getNascimento());
            ps.setBoolean(4, socio.isDirigente());
            ps.setInt(5, socio.getIdt());
            ps.setInt(6, socio.getEnc().getId());
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

    public void idSocio(Connection conn, Socio socio) {
        Statement stmt2 = null;
        ResultSet rs2 = null;
        String sql2 = "SELECT MAX(id_socio) FROM socios;"; // tem que ser select max (id_enc) from tabela

        try {
            stmt2 = conn.createStatement();
            rs2 = stmt2.executeQuery(sql2);

            while (rs2.next()) {
                ids = (rs2.getInt("MAX(id_socio)"));

            }

        } catch (SQLException ex) {
            // Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
            ex.getMessage();
        } finally {
            try {
                rs2.close();
            } catch (SQLException ex) {
                System.out.println("Tentou encerrar um ResultSet nulo!");
            }
            try {
                stmt2.close();
            } catch (SQLException ex) {
                System.out.println("Tentou encerrar um Statement nulo!");
            }
        }
    }

    public void idSocioScan(Connection conn, Socio socio) {
        Statement stmt2 = null;
        ResultSet rs2 = null;
        String sql2 = "SELECT id_socio FROM socios WHERE id_socio = ?;";

        try {
            stmt2 = conn.createStatement();
            rs2 = stmt2.executeQuery(sql2);

            while (rs2.next()) {
                // System.out.println(rs.getInt("MAX(id_enc)"));
                ids = rs2.getInt("id_socio");
            }
        } catch (SQLException ex) {
            // Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
            ex.getMessage();
        } finally {
            try {
                rs2.close();
            } catch (SQLException ex) {
                System.out.println("Tentou encerrar um ResultSet nulo!");
            }
            try {
                stmt2.close();
            } catch (SQLException ex) {
                System.out.println("Tentou encerrar um Statement nulo!");
            }
        }
    }

    public void editSocios(Connection conn, Socio socio) {
        PreparedStatement ps = null;
        String sql = "UPDATE socios SET nome = ?, nif = ?, anoN = ?, dirigente = ?, id_tipo = ? WHERE id_socio = ?";
        //nome, nif, anoNasc, dirigente, idt, edu)
        int count;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, socio.getNome());
            ps.setInt(2, socio.getNif());
            ps.setInt(3, socio.getNascimento());
            ps.setBoolean(4, socio.isDirigente());
            ps.setInt(5, socio.getIdt());
            ps.setInt(6, socio.getIds());

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

    public void deleteMensalSocio(Connection conn, int idS) {
        PreparedStatement ps = null;
        int count = 0;
        String sql = "DELETE mensal, socios FROM mensal LEFT JOIN socios ON mensal.id_socio = socios.id_socio WHERE mensal.id_socio =?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idS);

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

    public void criarMensalSocio(Connection conn, Socio socio) {
        //MENSAL INSERT
        PreparedStatement ps = null;
        String sql = "INSERT INTO mensal (id_mensal, mes, nAulas, id_socio) VALUES (null,?, ?, ?);";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, socio.getMes());
            ps.setInt(2, socio.getAulas());
            ps.setInt(3, socio.getIds());

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

    //"DELETE mensal, socios FROM mensal LEFT JOIN socios ON mensal.id_socio = socios.id_socio WHERE mensal.id_socio =?; "
    public int getIdM() {
        return idM;
    }

    public void setIdM(int idM) {
        this.idM = idM;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getNascimento() {
        return nascimento;
    }

    public void setNascimento(int nascimento) {
        this.nascimento = nascimento;
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

    public EncEdu getEnc() {
        return enc;
    }

    public void setEnc(EncEdu enc) {
        this.enc = enc;
    }

    public boolean isDirigente() {
        return dirigente;
    }

    public void setDirigente(boolean dirigente) {
        this.dirigente = dirigente;
    }

    public int getIdt() {
        return idt;
    }

    public void setIdt(int Idt) {
        idt = Idt;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    @Override
    public String toString() {
        return "Socio{" + "nome=" + nome + ", nif=" + nif + ", nascimento=" + nascimento + '}';
    }

}
