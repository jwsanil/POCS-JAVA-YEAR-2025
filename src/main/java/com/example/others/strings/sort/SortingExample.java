package com.example.others.sort;

import java.util.ArrayList;
import java.util.Optional;

public class SortingExample {

    public static void main(String[] args) {

        int arr[] = {10, 11, 12, 3, 4, 30, 31};

        ArrayList<Integer> arrayList = new ArrayList<>();

        int max = arr[0];
        int currentSumValue = arr[0];

        for (int i = 1; i < arr.length; i++) {


            if (arr[i] > arr[i - 1]) {  //11>10

                currentSumValue += arr[i];

            } else {
                currentSumValue = arr[i];// 11


            }

            arrayList.add(currentSumValue);
        }
        max = Math.max(max, currentSumValue);
        System.out.println("maximum value:" + max);
        System.out.println(arrayList);
        Optional<Integer> max1 = arrayList.stream().max((elemtent1, element2) -> {
            return Integer.compare(elemtent1, element2);
        });
        System.out.println("max valuees" + max1);
    }
}
