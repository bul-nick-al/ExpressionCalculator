/**
 * class, which represents factor. Inherited from Expression. Possible operators
 * between the members are * - multiplication, / - division and none if there's no operator
 */
public class Factor extends Expression{
    enum Opcode {multiplication, division, none}
    private Opcode operator;

    public Factor(Opcode operator, Expression left, Expression right) {
        super(left, right);
        this.operator = operator;
    }

    @Override
    protected String getName() {
        return operator.toString();
    }

    @Override
    public long calculate() throws Exception{
        long left = getLeft().calculate();
        long right = getRight().calculate();
        switch (operator){
            case multiplication:
                return left*right;
            case division:
                return left/right;
            default:
                throw new Exception("calculation failed in class Factor with operator "
                        + operator.toString()+". The left member equals to "+left
                        +"and the right member equals to"+right);
        }
    }
}
