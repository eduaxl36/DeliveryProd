package br.com.kantar.modelMessage;

public class ModelMessage {

private int CodigoRetorno;
private String Mensagem;

    public ModelMessage() {
    }

    public int getCodigoRetorno() {
        return CodigoRetorno;
    }

    public void setCodigoRetorno(int CodigoRetorno) {
        this.CodigoRetorno = CodigoRetorno;
    }

    public String getMensagem() {
        return Mensagem;
    }

    public void setMensagem(String Mensagem) {
        this.Mensagem = Mensagem;
    }

    public ModelMessage(int CodigoRetorno, String Mensagem) {
        this.CodigoRetorno = CodigoRetorno;
        this.Mensagem = Mensagem;
    }

   
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ModelException{");
        sb.append("CodigoRetorno=").append(CodigoRetorno);
        sb.append(", Mensagem=").append(Mensagem);
        sb.append('}');
        return sb.toString();
    }
	
	
	
}
