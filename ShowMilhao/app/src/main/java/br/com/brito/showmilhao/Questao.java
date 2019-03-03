package br.com.brito.showmilhao;



public class Questao {
    String pergunta;
    String respA;
    String respB;
    String respC;
    int correta;


    public Questao(String pergunta, String respA, String respB, String respC, int correta) {
        this.pergunta = pergunta;
        this.respA = respA;
        this.respB = respB;
        this.respC = respC;
        this.correta = correta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getRespA() {
        return respA;
    }

    public void setRespA(String respA) {
        this.respA = respA;
    }

    public String getRespB() {
        return respB;
    }

    public void setRespB(String respB) {
        this.respB = respB;
    }

    public String getRespC() {
        return respC;
    }

    public void setRespC(String respC) {
        this.respC = respC;
    }

    public int getCorreta() {
        return correta;
    }

    public void setCorreta(int correta) {
        this.correta = correta;
    }
}
