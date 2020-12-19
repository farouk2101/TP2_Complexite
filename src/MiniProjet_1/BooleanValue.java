package MiniProjet_1;

public class BooleanValue {
    boolean value;
    
    public BooleanValue(boolean value) {
        this.value = value;
    }

    public static BooleanValue Et(BooleanValue a, BooleanValue b){
        return new BooleanValue(a.getValue() && b.getValue());
    }

    public static BooleanValue Ou(BooleanValue a, BooleanValue b){
        return new BooleanValue(a.getValue() || b.getValue());
    }

    public static BooleanValue Non(BooleanValue booleanValue){
        return new BooleanValue(!booleanValue.getValue());
    }

    public boolean getValue(){
        return this.value;
    }
}
