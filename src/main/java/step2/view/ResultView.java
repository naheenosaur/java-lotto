package step2.view;

import step2.analyze.Prize;
import step2.analyze.WinningCount;
import step2.game.LottoGame;
import step2.game.Ticket;
import step2.game.WinningLotto;

import java.util.List;
import java.util.Map;

public class ResultView {
    private static final String AMOUNT_CONFIRM_POSTFIX = "개를 구매했습니다.";
    private static final String RESULT = "당첨 통계\n---------";
    private static final String WINNING_MONEY_PREFIX = "(";
    private static final String WINNING_MONEY_POSTFIX = "원) - ";
    private static final String WINNING_GAME_POSTFIX = "개";
    private static final String WINNING_RATE_PREFIX = "총 수익률은 ";
    private static final String WINNING_REPORT_PREFIX = "입니다.(기준이 1이기 때문에 결과적으로 ";
    private static final String WINNING_REPORT_POSTFIX = "(이)라는 의미임)";
    private static final String GAIN = "이득";
    private static final String LOSS = "손해";

    public static void printTickets(Ticket ticket) {
        List<LottoGame> lottoGames = ticket.getLottoGames();
        System.out.println(lottoGames.size() + AMOUNT_CONFIRM_POSTFIX);
        lottoGames.forEach(lottoGame -> System.out.println("[" + lottoGame.toString() + "]"));
    }

    public static void printAnalysis(Ticket ticket, WinningLotto winningLottoNumber) {
        printNewLine();
        System.out.println(RESULT);
        WinningCount winningCount = ticket.checkWinningCount(winningLottoNumber);
        printWinningCount(winningCount);
        printWinningRate(winningCount);
    }

    private static void printWinningCount(WinningCount winningCount) {
        Map<Prize, String> prizesAndWinningCondition = Prize.ofPrizesAndWinningCondition();
        prizesAndWinningCondition.keySet()
                .stream().sorted()
                .forEach(prize -> {
                    System.out.println(prizesAndWinningCondition.get(prize)
                            + WINNING_MONEY_PREFIX + prize.getMoney() + WINNING_MONEY_POSTFIX
                            + winningCount.countOfPrize(prize) + WINNING_GAME_POSTFIX);
                });
    }

    private static void printWinningRate(WinningCount winningCount) {
        float rate = winningCount.calculateWinningRate();
        System.out.println(WINNING_RATE_PREFIX + rate + WINNING_REPORT_PREFIX +
                (rate > 1 ? GAIN : LOSS) + WINNING_REPORT_POSTFIX);
    }

    private static void printNewLine() {
        System.out.println();
    }
}
