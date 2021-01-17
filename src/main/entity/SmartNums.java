package src.main.entity;

public class SmartNums {
    private int val;
    // пример инкапсуляции, мы не даем прямой доступ к isEven
    private boolean isEven;

    public SmartNums(int val) {
        this.val = val;
        this.isEven = val % 2 == 0;
    }

    @Override
    public String toString() {
        return "{" +
                "val=" + val +
                ", isEven=" + isEven +
                '}';
    }

    public int getVal() {
        return val;
    }

    public boolean isEven() {
        return isEven;
    }
}
