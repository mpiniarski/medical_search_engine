package com.joma.studies.article.importance;

import java.util.Collection;
import java.util.Map;

public class MathUtils {
    public double angle(Map<String, Integer> document1, Map<String, Integer> document2) {
        if (document1.isEmpty() || document2.isEmpty()) {
            throw new IllegalArgumentException();
        }

        double dotProduct = dotProduct(document1, document2);

        double document1Sqrt = vectorMagnitude(document1.values());
        double document2Sqrt = vectorMagnitude(document2.values());

        if (document1Sqrt == 0.0 || document2Sqrt == 0.0) {
            return 0.0;
        }

        return Math.acos(dotProduct / (document1Sqrt * document2Sqrt));
    }

    private double vectorMagnitude(Collection<Integer> vector) {
        Integer sum = vector
                .stream()
                .map(a -> a * a)
                .mapToInt(Integer::intValue)
                .sum();
        return Math.sqrt(sum);
    }

    private double dotProduct(Map<String, Integer> document1, Map<String, Integer> document2) {
        Map<String, Integer> documentShort;
        Map<String, Integer> documentLong;
        if (document1.size() <= document2.size()) {
            documentShort = document1;
            documentLong = document2;
        } else {
            documentShort = document2;
            documentLong = document1;
        }

        return documentShort.keySet()
                .stream()
                .map(word -> {
                    Integer documentShortValue = documentShort.get(word);
                    Integer documentLongValue = documentLong.getOrDefault(word, 0);
                    return documentShortValue * documentLongValue;
                })
                .mapToDouble(Integer::doubleValue)
                .sum();
    }
}
