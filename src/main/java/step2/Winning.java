package step2;

import java.util.Set;

public class Winning {
    Set<Integer>numbers;

    public Winning (Set<Integer> numbers) {
        this.numbers = numbers;
    }

    public boolean contains(int num) {
        return numbers.contains(num);
    }
}
