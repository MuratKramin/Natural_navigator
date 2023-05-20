package com.nat_nav.natural_navigator.AlsModel;

public abstract class AbstractALS {

    protected final int rank;

    public AbstractALS(int rank) {
        this.rank = rank;
    }


    //Метод возвращает произведение скалярного произведения вектора пользователей с индексом i и вектора товаров с индексом j.
    // Другими словами, метод делает предсказание оценки пользователя i для товара j,
    // основываясь на данных векторов пользователей и товаров, полученных из объекта LatentFactors.
    public double predict(LatentFactors factors, int i, int j) {
        return factors.getUsers().getRowVector(i).dotProduct(factors.getItems().getColumnVector(j));
    }

}
