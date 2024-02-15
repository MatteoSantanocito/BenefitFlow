package domain;

public class ValoreStrategy1 implements ValoreStrategyInterface{
    @Override
    public float calcolaValore(int livello) {
        float valore;
        switch (livello){
            case 1:
                valore = 100.00f;
                break;
            case 2:
                valore = 200.00f;
                break;
            case 3:
                valore = 300.00f;
                break;
            default: valore = 0;
                break;
        }
        return valore;
    }
}
