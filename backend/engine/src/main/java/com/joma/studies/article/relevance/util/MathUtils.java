package com.joma.studies.article.relevance.util;

import com.joma.studies.measure.MeasureMap;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MathUtils {
    public static double cosineMeasure(MeasureMap document1, MeasureMap document2) {
        if (document1.isEmpty() || document2.isEmpty()) {
            throw new IllegalArgumentException("One or both documents are empty");
        }

        double dotProduct = dotProduct(document1, document2);

        double document1Sqrt = vectorMagnitude(document1.values());
        double document2Sqrt = vectorMagnitude(document2.values());

        double denominator = document1Sqrt * document2Sqrt;

        if (denominator == 0.0) {
            throw new IllegalArgumentException("One or both documents contain only zero values");
        }

        return (dotProduct / denominator);
    }

    public static double vectorMagnitude(Collection<Double> vector) {
        Double sum = vector
                .stream()
                .map(a -> a * a)
                .mapToDouble(Double::doubleValue)
                .sum();
        return Math.sqrt(sum);
    }

    public static double dotProduct(MeasureMap document1, MeasureMap document2) {
        MeasureMap documentShort;
        MeasureMap documentLong;
        if (document1.size() <= document2.size()) {
            documentShort = document1;
            documentLong = document2;
        } else {
            documentShort = document2;
            documentLong = document1;
        }

        return documentShort.keySet()
                .stream()
                .map(word -> documentShort.get(word) * documentLong.getOrDefault(word, 0.0))
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
