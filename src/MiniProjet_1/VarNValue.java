package MiniProjet_1;

public class VarNValue {
    private int variable;
    private BooleanValue value;

    public VarNValue(int variable, boolean value){
        this.variable = variable;
        this.value = new BooleanValue(value);
    }

    public int getVariable() {
        return variable;
    }

    public boolean getValue() {
        return value.getValue();
    }
}
