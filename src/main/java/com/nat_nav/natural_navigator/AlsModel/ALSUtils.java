package com.nat_nav.natural_navigator.AlsModel;

import org.apache.commons.math3.linear.OpenMapRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SparseRealMatrix;

import java.util.Comparator;
import java.util.List;

public class ALSUtils {

    /**
     * Преобразует список {@link Rating} в разреженную матрицу оценок ({@link SparseRealMatrix}).
     * @param ratings {@link List} {@link Rating}.
     * @return Разреженная матрица оценок ({@link SparseRealMatrix}).
     */
    public static SparseRealMatrix toMatrix(List<Rating> ratings,int a,int b) {

        int max_user = maxUser(ratings);
        int max_item = maxItem(ratings);

        max_user=a;
        max_item=b;

        final SparseRealMatrix ratingsMatrix = new OpenMapRealMatrix(max_user, max_item);
        for (Rating rating : ratings) {
            ratingsMatrix.setEntry(rating.getUser() - 1, rating.getItem() - 1, rating.getRating());
        }
        return ratingsMatrix;
    }

    /**
     * Возвращает наивысший идентификатор пользователя из {@link List} {@link Rating}.
     * @param ratings {@link List} {@link Rating}.
     * @return Самый высокий идентификатор пользователя в виде целого числа.
     */
    public static int maxUser(List<Rating> ratings) {
        return ratings.stream().max(Comparator.comparing(Rating::getUser)).get().getUser();
    }

    /**
     * Возвращает наивысший идентификатор продукта из {@link List} {@link Rating}.
     * @param ratings {@link List} {@link Rating}.
     * @return Самый высокий идентификатор продукта в виде целого числа.
     */
    public static int maxItem(List<Rating> ratings) {
        return ratings.stream().max(Comparator.comparing(Rating::getItem)).get().getItem();
    }

    public static RealMatrix approximate(LatentFactors factors) {
        return factors.getUsers().multiply(factors.getItems());
    }

}
