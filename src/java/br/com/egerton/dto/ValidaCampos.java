package br.com.egerton.dto;

import java.util.ArrayList;

public class ValidaCampos {

    private boolean valido;
    private ArrayList<String> validacoes;

    public ValidaCampos() {
        validacoes = new ArrayList<>();
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public ArrayList<String> getValidacoes() {
        return validacoes;
    }

    public void setValidacoes(ArrayList<String> validacoes) {
        this.validacoes = validacoes;
    }

    public void addValidacoes(String validacao) {
        if (validacao != null && !validacao.equals("")) {
            this.validacoes.add(validacao);
        }
    }
}
