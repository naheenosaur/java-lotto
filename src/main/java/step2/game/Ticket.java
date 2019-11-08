package step2.game;

import step2.analyze.Prize;
import step2.analyze.WinningCount;

import java.util.*;

public class Ticket {
    private static final String AMOUNT_EXCEPTION = "로또는 1000원단위로 구매할 수 있습니다.";
    public static final int LOTTO_PRICE = 1000;

    private List<LottoGame> lottoGames;

    public Ticket(int amount) {
        lottoGames = new ArrayList<>();
        int lottoCount = buyLottoGames(amount);
        for (int count = 0; count < lottoCount; count++) {
            lottoGames.add(new LottoGame());
        }
    }

    public List<LottoGame> getLottoGames() {
        return Collections.unmodifiableList(lottoGames);
    }

    public int countGames() {
        return lottoGames.size();
    }

    private int buyLottoGames(int amount) {
        if (amount % LOTTO_PRICE != 0 || amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(AMOUNT_EXCEPTION);
        }
        return amount / LOTTO_PRICE;
    }

    public WinningCount checkWinningCount(WinningLotto winningLotto) {
        Map<Prize, Integer> winningCount = new HashMap<>();
        lottoGames.forEach(lottoGame -> {
            Prize prize = Prize.ofMatchCount(lottoGame.matchWinningNumberCount(winningLotto));
            winningCount.put(prize, winningCount.getOrDefault(prize, 0) + 1);
        });
        return new WinningCount(winningCount);

    }
}
