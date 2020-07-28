package br.com.egerton.entity;

public class NotaFiscal {

    private int id;
    private int numero;
    private String data;
    private Double valor;
    private Empresa tomadoda;
    private Empresa prestadora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Empresa getTomadoda() {
        return tomadoda;
    }

    public void setTomadoda(Empresa tomadoda) {
        this.tomadoda = tomadoda;
    }

    public Empresa getPrestadora() {
        return prestadora;
    }

    public void setPrestadora(Empresa prestadora) {
        this.prestadora = prestadora;
    }

}
