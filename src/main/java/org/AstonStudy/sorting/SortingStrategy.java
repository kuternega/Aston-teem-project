package org.AstonStudy.sorting;

import java.util.List;
import java.util.Comparator;

public interface SortingStrategy<T> {
    void sort(List<T> list, Comparator<T> comparator);
}