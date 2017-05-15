package com.joma.studies.common;

public interface Observer<T> {
    void accept(Observable<T> observable, T item);
}
