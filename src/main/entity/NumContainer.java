package src.main.entity;

import java.util.ArrayList;

public class NumContainer<T> {
    // могдо быть пример инкапсуляции если за итерацию отвечали бы в этом классе, но так как хотелось написать через
    // stream api, сделал его паблик
    public ArrayList<T> nums;
    public NumContainer() {
        nums = new ArrayList<>();
    }
    public void AddNum(T numObject) {
        this.nums.add(numObject);
    }
}
