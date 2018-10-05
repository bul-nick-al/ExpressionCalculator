/**
 * class, which represents an expression within parentheses. Inherited from Primary. Has an expression inside
 */
public class Parenthesized extends Primary {
    private Expression expression;
    private boolean isPositive;

    public Parenthesized(Expression expression, boolean isPositive) {
        this.expression = expression;
        this.isPositive = isPositive;
    }

    @Override
    protected void print(String prefix, boolean isTail) {
        expression.print(prefix, isTail);
    }

    public boolean isPositive() {
        return isPositive;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public long calculate() throws Exception{
        return isPositive() ? expression.calculate() : - expression.calculate();
    }
}
