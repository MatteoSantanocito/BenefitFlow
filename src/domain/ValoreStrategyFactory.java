package domain;

public class ValoreStrategyFactory {
    private static ValoreStrategyFactory t;
    private ValoreStrategyFactory(){};

    public static ValoreStrategyFactory getInstance(){
        if(t == null){
            t = new ValoreStrategyFactory();
        }
        return t;
    }

    public ValoreStrategyInterface getValoreStrategy(){
        return new ValoreStrategy1();
    }
}
