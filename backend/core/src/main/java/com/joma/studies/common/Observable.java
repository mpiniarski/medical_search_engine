package com.joma.studies.common;

public interface Observable<T> {
    void addObserver(Observer<T> observer);
    void deleteObserver(Observer<T> observer);

}
