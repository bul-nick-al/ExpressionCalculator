/**
 * class, which represents a term. Inherited from Expression. Possible operators
 * between the members are + - addition, - - subtraction and none if there's no operator
 */
public class Term extends Expression {
    enum Opcode {addition, subtraction, none}
    private Opcode operator;

    public Term(Opcode operator, Expression left, Expression right) {
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
            case addition:
                return left+right;
            case subtraction:
                return left-right;
            default:
                throw new Exception("calculation failed in class Term with operator "
                        + operator.toString()+". The left member equals to "+left
                        +"and the right member equals to"+right);
        }
    }
}
