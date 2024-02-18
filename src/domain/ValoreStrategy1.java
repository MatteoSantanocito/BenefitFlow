package domain;

public class ValoreStrategy1 implements ValoreStrategyInterface{
    @Override
    public float calcolaValore(float valoreBase, int livello) {
        float valore;
        switch (livello){
            case 1:
                valore = (valoreBase + (float)(valoreBase * 0.4));
                break;
            case 2:
                valore = (valoreBase + (float)(valoreBase * 0.6));
                break;
            case 3:
                valore = (valoreBase + (float)(valoreBase * 0.8));
                break;
            default: valore = 0;
                break;
        }
        return valore;
    }
}
