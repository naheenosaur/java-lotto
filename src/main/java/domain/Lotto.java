package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Lotto {
    private static final Integer limitCount = 6;

    private List<LottoNumber> lottoNumbers = new ArrayList<>();

    public Lotto() {
        validateAndAdd(Stream.generate(LottoNumber::getInstance));
    }

    public Lotto(Integer ...numbers){
        validateAndAdd(Arrays.stream(numbers)
            .map(LottoNumber::getInstance));
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    private void validateAndAdd(Stream<LottoNumber> lottoNumberStream){
        lottoNumberStream
            .distinct()
            .limit(limitCount)
            .sorted(Comparator.comparing(LottoNumber::getNumber))
            .forEach(lottoNumbers::add);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public Integer matchCount(Lotto lotto) {
        return (int) lotto.getLottoNumbers().stream()
            .filter(this::contains)
            .count();
    }
}