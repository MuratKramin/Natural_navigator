package com.nat_nav.natural_navigator.AlsModel;

import org.apache.commons.math3.linear.OpenMapRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SparseRealMatrix;

import java.util.Comparator;
import java.util.List;

public class ALSUtils {

    /**
     * Converts a list of {@link Rating} into a sparse ratings matrix ({@link SparseRealMatrix}).
     * @param ratings a {@link List} of {@link Rating}.
     * @return A sparse ratings matrix ({@link SparseRealMatrix}).
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
     * Returns the highest user id from a {@link List} of {@link Rating}.
     * @param ratings A {@link List} of {@link Rating}.
     * @return The highest user id as an integer.
     */
    public static int maxUser(List<Rating> ratings) {
        return ratings.stream().max(Comparator.comparing(Rating::getUser)).get().getUser();
    }

    /**
     * Returns the highest product id from a {@link List} of {@link Rating}.
     * @param ratings A {@link List} of {@link Rating}.
     * @return The highest product id as an integer.
     */
    public static int maxItem(List<Rating> ratings) {
        return ratings.stream().max(Comparator.comparing(Rating::getItem)).get().getItem();
    }

    public static RealMatrix approximate(LatentFactors factors) {
        return factors.getUsers().multiply(factors.getItems());
    }

}