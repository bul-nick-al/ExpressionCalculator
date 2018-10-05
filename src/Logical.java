/**
 * class, which represents logical. Inherited from Expression. Possible operators
 * between the members are and, or, xor and none if there's no operator
 */
public class Logical extends Expression {
    enum Opcode {and, or, xor, none}
    private Opcode operator;
    public Logical(Opcode operator, Expression left, Expression right) {
        super(left, right);
        this.operator = operator;
    }

    public Opcode getOperator() {
        return operator;
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
            case and:
                return left&right;
            case or:
                return left|right;
            case xor:
                return left^right;
            default:
                throw new Exception("calculation failed in class Logical with operator "
                        + operator.toString()+". The left member equals to "+left
                        +"and the right member equals to"+right);
        }
    }
}
