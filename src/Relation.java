/**
 * class, which represents a relation. Inherited from Expression. Possible operators
 * between the members are < - less, <= - less or equal, > - greater, >=  - greater or equal
 * = – equal, /= – not equal and none if there's no operator
 */
public class Relation extends Expression {
    enum Opcode {less, less_eq, greater, greater_eq, equal, not_eq, none}
    private Opcode operator;

    public Relation(Opcode operator, Expression left, Expression right) {
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
            case less:
                return left < right ? 1:0;
            case less_eq:
                return left <= right ? 1:0;
            case greater:
                return left > right ? 1:0;
            case greater_eq:
                return left >= right ? 1:0;
            case equal:
                return left == right ? 1:0;
            case not_eq:
                return left != right ? 1:0;
            default:
                throw new Exception("calculation failed in class Relation with operator "
                        + operator.toString()+". The left member equals to "+left
                        +"and the right member equals to"+right);
        }
    }
}
