package step2.View;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class InputView {
    private static final String AMOUNT = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String AMOUNT_EXCEPTION = "로또는 1000원단위로 구매할 수 있습니다.";
    private static final String NUMBER_COUNT_EXCEPTION = "입력된 로또번호가 6개가 아닙니다.";
    private static final String DUPLICATED_NUMBER_EXCEPTION = "로또번호는 중복된 숫자를 허용하지 않습니다.";
    private static final String NUMBER_RANGE_EXCEPTION = "로또번호는 1 ~ 45 사이의 숫자입니다.";
    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;


    private static Scanner scanner = new Scanner(System.in);

    public static int buyTickets() {
        System.out.println(AMOUNT);
        int amount = scanner.nextInt();
        verifyAmount(amount);
        return amount;
    }

    public static Set<Integer> getWinningNumbers() {
        scanner.nextLine();
        System.out.println(WINNING_NUMBER);
        String[] inputNumbers = scanner.nextLine().split(",");
        verifyLottoNumberCount(inputNumbers.length);

        Set<Integer> winningNumbers = Arrays.stream(inputNumbers)
                .map(num -> Integer.parseInt(num.trim()))
                .collect(toSet());

        verifyLottoNumber(winningNumbers);
        return winningNumbers;
    }

    private static void verifyAmount(int amount) {
        if (amount % LOTTO_PRICE != 0 || amount / LOTTO_PRICE < 1) {
            throw new IllegalArgumentException(AMOUNT_EXCEPTION);
        }
    }

    private static void verifyLottoNumberCount(int length) {
        if (length != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(NUMBER_COUNT_EXCEPTION);
        }
    }

    private static void verifyLottoNumber(Set<Integer> winningNumbers) {
        if (winningNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER_EXCEPTION);
        }

        if (winningNumbers.stream().anyMatch(num -> num < LOTTO_NUMBER_MIN || num > LOTTO_NUMBER_MAX)) {
            throw new IllegalArgumentException(NUMBER_RANGE_EXCEPTION);
        }
    }

}
