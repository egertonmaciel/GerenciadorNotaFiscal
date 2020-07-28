package br.com.egerton.dto;

import java.util.ArrayList;

/**
 *
 * @author Egerton Maciel
 */
public class RetornoSQL {

    private boolean sucesso;
    private String mensagem;
    private ArrayList<String> rotulos;
    private ArrayList<ArrayList<String>> valores;

    public RetornoSQL() {
        rotulos = new ArrayList<>();
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ArrayList<String> getRotulos() {
        return rotulos;
    }

    public void addRotulo(String rotulo) {
        this.rotulos.add(rotulo);
    }

    public ArrayList<ArrayList<String>> getValores() {
        return valores;
    }

    public void setValores(ArrayList<ArrayList<String>> valores) {
        this.valores = valores;
    }
}
