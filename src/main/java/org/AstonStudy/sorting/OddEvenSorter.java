package org.AstonStudy.sorting;

import org.AstonStudy.collection.MyArrayList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class OddEvenSorter {

    public static <T> void sortByEven(MyArrayList<T> list,
                                      Function<T, Integer> fieldExtractor,
                                      SortingStrategy<T> strategy,
                                      Comparator<T> comparator) {
        List<Integer> evenIndices = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            Integer fieldValue = fieldExtractor.apply(element);
            if (fieldValue != null && fieldValue % 2 == 0) {
                evenIndices.add(i);
            }
        }
        List<T> evenElements = new ArrayList<>();
        for (int index : evenIndices) {
            evenElements.add(list.get(index));
        }
        strategy.sort(evenElements, comparator);
        for (int i = 0; i < evenIndices.size(); i++) {
            list.set(evenIndices.get(i), evenElements.get(i));
        }
    }
}