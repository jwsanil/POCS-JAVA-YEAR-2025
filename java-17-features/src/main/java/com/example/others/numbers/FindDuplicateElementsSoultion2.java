package com.example.others.numbers;

public class FindDuplicateElementsSoultion2 {

    static int array[] = {1, 8, 1, 8, 8, 6, 0, 1, 5, 8};


    static int max = findMax(array);


    public static void main(String[] args) {

        int[] countArray = new int[max + 1];

        for (int i = 0; i < array.length; i++) {
            countArray[array[i]]++; //  1  3  0  0  0  1  1  0  4

        }
        for (int i = 0; i < countArray.length; i++) {

            System.out.print(" " + countArray[i] + " ");
        }
        System.out.println("=================");

        for (int i = 0; i <= max; i++) {

            if (countArray[i] > 1) {
                System.out.println(i + " ");
            }
        }


    }

    private static int findMax(int[] array) {

        int max = array[0];
        for (int num : array) {

            if (num > max) {

                max = num;
            }

        }
        return max;
    }

    int[] countArray = new int[max + 1];
}
