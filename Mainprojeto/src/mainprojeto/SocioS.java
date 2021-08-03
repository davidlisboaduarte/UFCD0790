package mainprojeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SocioS extends Socio {

    private boolean dirigenteS = true;
    private static int idS = 0;
    private String tipoS = "SSénior-";
    private int aulas;
    private int valorBase = 100;

    public SocioS(boolean dirigenteS, int aulas, String nome, int nif, int nascimento) {
        super(nome, nif, nascimento);
        this.dirigenteS = dirigenteS;
        this.aulas = aulas;
        idS++;
        tipoS = tipoS + idS;
    }

    public SocioS() {
    }

    
     public void displaySenior(Connection conn) {
        String sql = "SELECT * FROM mensal RIGHT JOIN socios ON mensal.id_mensal = socios.id_socio WHERE id_tipo=3;";
        //String sql="SELECT * FROM socios LEFT JOIN mensal ON socios.id_socio = mensal.id_mensal WHERE id_tipo = 3;";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("SSenior- " + rs.getInt("id_socio"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("NIF: " + rs.getInt("nif"));
                System.out.println("Ano de nascimento: " + rs.getInt("anoN"));
                //if
                System.out.println("Nº de aulas: " + rs.getInt("nAulas"));
                System.out.println("dirigente: " + rs.getBoolean("dirigente"));
                int aulas = rs.getInt("nAulas");
                int valor = aulas * 25;
                double desconto = valor * 0.10;
                double total = valor - desconto;
                boolean dirigenteA = rs.getBoolean("dirigente");
                if (dirigenteA == true) {
                    double diri = 0.0;
                    System.out.println("dirigente: " + diri);
                } else {
                    System.out.println("dirigente: " + rs.getBoolean("dirigente"));
                    System.out.println("Valor a pagar: " + total + "€");

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

    public boolean getDirigenteS() {
        return dirigenteS;
    }

    public void setDirigenteS(boolean dirigenteS) {
        this.dirigenteS = dirigenteS;
    }

    public static int getIdS() {
        return idS;
    }

    public static void setIdS(int idS) {
        SocioS.idS = idS;
    }

    public String getTipoS() {
        return tipoS;
    }

    public void setTipoS(String tipoS) {
        this.tipoS = tipoS;
    }
    public void editSenior(Connection conn, Socio socio){
        PreparedStatement ps = null;
        String sql = "UPDATE socios SET nome = ?, nif = ?, anoN = ?, dirigente = ?, id_tipo = 3 WHERE id_socio = ?";
        //nome, nif, anoNasc, dirigente, idt, edu)
        int count;
        try{
           ps=conn.prepareStatement(sql);
           ps.setString(1,socio.getNome());
           ps.setInt(2,socio.getNif());
           ps.setInt(3,socio.getNascimento());
           ps.setBoolean(4,socio.isDirigente());
           ps.setInt(5,socio.getIdt());
           ps.setInt(6,socio.getIds());

           count = ps.executeUpdate();
           
           if(count>0){
               System.out.println("Editou com sucesso!");
           }else{
               System.out.println("Não conseguiu editar os dados!");
           }
           ps.close();
       }catch(SQLException ex){
           ex.getMessage();
       }
    }
    
    public double pagamento() {
        double pagamentoSenior;
        double valAulas;
        if (dirigenteS == true) {
            return 0.0;
        } else {
            if (aulas <= 4) {
                pagamentoSenior = valorBase;
            } else {
                valAulas = (aulas - 4) * 25;
                pagamentoSenior = valAulas + valorBase;
            }
            pagamentoSenior = pagamentoSenior - pagamentoSenior * 0.10;
        }
        return pagamentoSenior;
    }

    @Override
    public String toString() {
        return tipoS + "\nNome: " + super.getNome() + "\nNif: " + super.getNif() + "\nAno de nascimento: " + super.getNascimento() + "\nDirigente: " + dirigenteS + "\nNº Aulas: " + aulas + "\nTotal a pagar: " + pagamento();
    }

}
