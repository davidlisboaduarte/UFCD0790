package mainprojeto;

public class SocioJ extends Socio {

    private static int idJ = 0;
    private int valorBasej = 100;
    private int aulas;

    public SocioJ(int aulas, String nome, int nif, int nascimento) {
        super(nome, nif, nascimento);
        this.aulas = aulas;
    }

    public static int getIdJ() {
        return idJ;
    }

    public static void setIdJ(int idJ) {
        SocioJ.idJ = idJ;
    }

    public int getValorBasej() {
        return valorBasej;
    }

    public void setValorBasej(int valorBasej) {
        this.valorBasej = valorBasej;
    }

    public int getAulas() {
        return aulas;
    }

    public void setAulas(int aulas) {
        this.aulas = aulas;
    }

    public double pagamento() {
        double pagamentoJovem;
        double valAulas;
        if (aulas <= 4) {
            pagamentoJovem = valorBasej;
        } else {
            valAulas = (aulas - 4) * 25;
            pagamentoJovem = valAulas + valorBasej;
        }
        return pagamentoJovem;
    }

    @Override
    public String toString() {
        return "SocioJ{" + "valorBase=" + valorBasej + ", aulas=" + aulas + '}';
    }

}
