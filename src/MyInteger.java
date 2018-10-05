/**
 * class, which represents integer. Inherited from Primary.
 */
public class MyInteger extends Primary {
    private long value;

    public MyInteger(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    protected String getName() {
        return value + "";
    }

    @Override
    public long calculate() {
        return value;
    }
}

