package ts.volta.it.produttoreconsumatore.bean;

public class Prodotto {
    private String nome;

    public Prodotto(String nome) {
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public String toString() {
        return "Prodotto " + this.nome;
    }

}
