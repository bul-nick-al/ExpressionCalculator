public class Parser {
    private String input;
    private int position = 0;
    private char current;

    /**
     * constructor
     * @param input
     */
    public Parser(String input) {
        this.input = input;
        position = 0;
        current = input.charAt(0);
    }

    /**
     * parses an expression in the input string
     * @return the parsed expression
     */
    public Expression parse() throws Exception {
        Expression result = parseLogical();
        if (current == '\n' && result != null)
            return result;
        else
            throwException();
        return null;
    }

    /**
     * pareses logical expression
     * @return either an object of type Logical or lower in the hierarchy
     */
    private Expression parseLogical() throws Exception {
        Expression result = parseRelation();
        Logical.Opcode opcode = parseLogOperator();
        if (opcode != Logical.Opcode.none) {
            Expression right = parseLogical();
            result = new Logical(opcode, result, right);
        }
        return result;
    }

    private Logical.Opcode parseLogOperator(){
        try {
            if (input.substring(position, position+3).equals("and")){
                nextChar(); nextChar(); nextChar();
                return Logical.Opcode.and;
            }
            if (input.substring(position, position+2).equals("or")) {
                nextChar(); nextChar();
                return Logical.Opcode.or;
            }
            if (input.substring(position, position+3).equals("xor")) {
                nextChar(); nextChar(); nextChar();
                return Logical.Opcode.xor;
            }
            return Logical.Opcode.none;
        } catch (Exception ex){
            return Logical.Opcode.none;
        }
    }
    /**
     * pareses relation
     * @return either an object of type Relation or lower in the hierarchy
     */
    private Expression parseRelation() throws Exception {
        Expression result = parseTerm();
        Relation.Opcode opcode = parseRelOperator();
        if (opcode != Relation.Opcode.none){
            Expression right = parseRelation();
            result = new Relation(opcode, result, right);
        }
        return result;
    }

    private Relation.Opcode parseRelOperator(){
        try {
            if (input.substring(position, position+2).equals(">=")){
                nextChar(); nextChar();
                return Relation.Opcode.greater_eq;
            }
            if (input.substring(position, position+2).equals("<=")) {
                nextChar(); nextChar();
                return Relation.Opcode.less_eq;
            }
            if (input.substring(position, position+1).equals("=")) {
                nextChar();
                return Relation.Opcode.equal;
            }
            if (input.substring(position, position+2).equals("/=")) {
                nextChar(); nextChar();
                return Relation.Opcode.not_eq;
            }
            if (input.substring(position, position+1).equals(">")) {
                nextChar();
                return Relation.Opcode.greater;
            }
            if (input.substring(position, position+1).equals("<")) {
                nextChar();
                return Relation.Opcode.less;
            }
            return Relation.Opcode.none;
        } catch (Exception ex){
            return Relation.Opcode.none;
        }
    }
    /**
     * pareses term
     * @return either an object of type Term or lower in the hierarchy
     */
    public Expression parseTerm() throws Exception {
        Expression result = parseFactor();
        Term.Opcode opcode = parseTermOperator();
        if (opcode != Term.Opcode.none){
            Expression right = parseTerm();
            result = new Term(opcode, result, right);
        }
        return result;
    }

    private Term.Opcode parseTermOperator(){
        try {
            if (input.substring(position, position+1).equals("+")){
                nextChar();
                return Term.Opcode.addition;
            }
            if (input.substring(position, position+1).equals("-")) {
                nextChar();
                return Term.Opcode.subtraction;
            }
            return Term.Opcode.none;
        } catch (Exception ex){
            return Term.Opcode.none;
        }
    }
    /**
     * pareses factor
     * @return either an object of type Factor or lower in the hierarchy
     */
    public Expression parseFactor() throws Exception {
        Expression result = parsePrimary();
        Factor.Opcode opcode = parseFactorOperator();
        if (opcode != Factor.Opcode.none){
            Expression right = parseFactor();
            result = new Factor(opcode, result, right);
        }
        return result;
    }

    private Factor.Opcode parseFactorOperator(){
        try {
            if (input.substring(position, position+1).equals("*")){
                nextChar();
                return Factor.Opcode.multiplication;
            }
            if (input.substring(position, position+1).equals("/")&&!input.substring(position, position+2).equals("/=")) {
                nextChar();
                return Factor.Opcode.division;
            }
            return Factor.Opcode.none;
        } catch (Exception ex){
            return Factor.Opcode.none;
        }
    }
    /**
     * pareses primary
     * @return either an object of type MyInteger or Parenthesized
     */
    private Expression parsePrimary() throws Exception {
        Primary result = null;
        int initialPosition = position;
        if (current == '('){
            nextChar();
            result = new Parenthesized(parseLogical(), true);
            if (current != ')')
                throwException();
            nextChar();
        } else if(input.substring(initialPosition, initialPosition + 1).equals("-") &&
                input.substring(initialPosition, initialPosition + 2).equals("-(")){
            nextChar();nextChar();
            result = new Parenthesized(parseLogical(), false);
            if (current != ')')
                throwException();
            nextChar();
        } else if(input.substring(initialPosition, initialPosition + 1).equals("+") &&
                input.substring(initialPosition, initialPosition + 2).equals("+(")){
            nextChar();nextChar();
            result = new Parenthesized(parseLogical(), true);
            if (current != ')')
                throwException();
            nextChar();
        } else if (isNumber(current + "")||current == '+'||current == '-'){
            if (current == '+'||current == '-')
                nextChar();
            while (isNumber(current + "")){
                nextChar();
            }
            result = new MyInteger(Integer.parseInt(input.substring(initialPosition, position)));
        } else
            throwException();
        return result;
    }

    /**
     * check whether the given string is a number
     * @param string
     * @return true if a number, false if not
     */
    public static boolean isNumber (String string){
        try
        {
            Double.parseDouble(string);
        }
        catch (NumberFormatException e ){
            return false;
        }
        return true;
    }

    /**
     * shifts the pointer position to the next char and updates the value of the current variable
     */
    void nextChar() {
        current = (++position < input.length()) ? input.charAt(position) : '\n';
    }

    /**
     * in case of unsuccessful parsing throws an exception along with an approximate position of the fault
     * @throws Exception
     */
    void throwException() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < position; i++){
            stringBuilder.append("-");
        }
        stringBuilder.append("^");
        throw new Exception("Illegal expression, parsing failed probably at this symbol: "+ current
        +"\n"+input+"\n"+stringBuilder.toString());
    }
}
