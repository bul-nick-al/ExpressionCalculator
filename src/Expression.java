/**
 * Thank you!
 */
public abstract class Expression {
    private Expression left;
    private Expression right;

    protected Expression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    protected Expression(){}

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public void print() {
        System.out.println(getName());
        if (left != null) {
            left.print("", false);
        }
        if (right != null) {
            right.print("", true);
        }
    }

    /**
     * this method recursively prints the tree
     * @param prefix
     * @param isTail
     */
    protected void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + getName());
        if (left != null) {
            left.print(prefix + (isTail ? "    " : "│   "), false);
        }
        if (right != null) {
            right.print(prefix + (isTail ?"    " : "│   "), true);
        }
    }

    /**
     * this method is overridden in the descendant classes, so that the content of the class is printed
     * @return
     */
    protected String getName(){
        return "";
    }

    public abstract long calculate() throws Exception;
}
