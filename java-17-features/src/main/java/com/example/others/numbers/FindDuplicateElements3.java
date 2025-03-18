package com.example.others.numbers;

import java.util.Arrays;

public class FindDuplicateElements3 {

    public static void main(String[] args) {


        int[] array = {1, 2, 3, 4, 2, 5, 6, 3, 7, 8, 1, 1, 3, 3, 4, 4,1,1,1,1,2,2,2,2};

        Arrays.sort(array);
        System.out.println(array);
        System.out.println("sorted array:\n ");
        for(int i =0; i < array.length;i++){

            System.out.print(" "+array[i]);
        }
        System.out.println();//1 1 1 1 1 1 1 2 2 2 2 2 2 3 3 3 3 4 4 4 5 6 7 8
        System.out.println("duplicated elements:\n ");
        for(int i =1; i < array.length; i++){
            if(array[i] == array[i-1]){
                if (i >= 2 && array[i] != array[i - 2] || i == 1) {
                    System.out.print(array[i]);
                }
            }
        }


    }
}
