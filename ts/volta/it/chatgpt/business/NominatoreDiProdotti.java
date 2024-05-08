package ts.volta.it.chatgpt.business;

public class NominatoreDiProdotti {

    public String generaNome() {
        return String.format("Nome-%d", (int)(Math.random() * 10000));
    }

}
