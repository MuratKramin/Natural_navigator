package com.nat_nav.natural_navigator.AlsModel;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SparseRealMatrix;

public class BatchALS extends AbstractALS {

    private final SparseRealMatrix ratings;
    private final double alpha;
    private final double beta;



    public BatchALS(SparseRealMatrix ratings,
                    int rank,
                    double alpha,
                    double beta) {
        super(rank);
        this.ratings = ratings;
        this.alpha = alpha;
        this.beta = beta;
    }

    public LatentFactors run(LatentFactors factors) {

        final int rows = this.ratings.getRowDimension();
        final int cols = this.ratings.getColumnDimension();

        final RealMatrix user_factors = factors.getUsers();
        final RealMatrix item_factors = factors.getItems();

        double squaredErrorSum = 0.0; // Сумма квадратичных ошибок

        for (int row = 0 ; row < rows ; row++) {
            for (int col = 0 ; col < cols ; col++) {
                final double rating = this.ratings.getEntry(row, col);
                if (rating > 0d) {
                    double error = rating - predict(factors, row, col);

                    squaredErrorSum += error * error; // Добавляем квадратичную ошибку

                    for (int k = 0; k < this.rank; k++) {
                        final double _uf = item_factors.getEntry(k, col) +
                                this.alpha * (2.0 * error * user_factors.getEntry(row, k) -
                                        this.beta * item_factors.getEntry(k, col));
                        item_factors.setEntry(k, col, _uf);

                        final double _if = user_factors.getEntry(row, k) +
                                this.alpha * (2.0 * error * item_factors.getEntry(k, col) -
                                        this.beta * user_factors.getEntry(row, k));
                        user_factors.setEntry(row, k, _if);
                    }
                }
            }
        }

        int nnzCount = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (ratings.getEntry(row, col) > 0) {
                    nnzCount++;
                }
            }
        }

        // Вычисляем MSE
        double mse=0;
        if (nnzCount > 0) {
            mse = squaredErrorSum / nnzCount;
        }

        if(mse<0.01)
        // Выводим MSE в консоль
        System.out.println("Mean Squared Error (MSE): " + mse);

        return new LatentFactors(user_factors, item_factors);

    }

}
