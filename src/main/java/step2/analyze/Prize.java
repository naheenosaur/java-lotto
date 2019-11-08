package step2.analyze;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static step2.analyze.WinningCount.MAX_MATCH_COUNT;

public enum Prize {
    KRW_0_000(0, false, 0),
    KRW_5_000(3, false, 5_000),
    KRW_50_000(4, false, 50_000),
    KRW_1_500_000(5, false, 1_5000_000),
    KRW_30_000_000(5, true, 30_000_000),
    KRW_2_000_000_000(6, false, 2_000_000_000);

    private final int matchCount;
    private final boolean bonus;
    private final int money;

    Prize(int matchCount, boolean bonus, int money) {
        this.matchCount = matchCount;
        this.bonus = bonus;
        this.money = money;
    }

    public static Prize of(int matchCount, boolean bonus) {
        return Arrays.stream(values())
                .filter(prize -> prize.matchCount == matchCount)
                .filter(prize -> prize.matchCount != (MAX_MATCH_COUNT - 1) || prize.bonus == bonus)
                .findFirst()
                .orElse(Optional.of(KRW_0_000).get());
    }

    public static Map<Prize, String> ofPrizesAndWinningCondition() {
        Map<Prize, String> prizes = new HashMap<>();
        Arrays.stream(values())
                .filter(prize -> prize.matchCount > 0)
                .forEach(prize -> {
                    String condition = prize.matchCount + "개 일치";
                    if (prize.bonus) {
                        condition += ", 보너스 볼 일치";
                    }
                    prizes.put(prize, condition);
                });
        return prizes;
    }

    public int calculateEarningMoney(int lottoGameCount) {
        return this.money * lottoGameCount;
    }

    public int getMoney() {
        return money;
    }

    public static int compareTo(Prize prize1, Prize prize2) {
        return Integer.compare(prize1.money, prize2.money);
    }
}
