package com.example.others.numbers.minMax;

public class FindingMinMaxExample {

    public static void main(String[] args) {
        int arr[] = {3, 1, 7, 2, 9, 4, 0, 6};

        int min = findMin(arr);
        int max = findMax(arr);
        System.out.println("min:" + min + " and max:" + max);
    }

    private static int findMin(int[] array) {

        int min = array[0];
        for (int num : array) {

            if (num < min) {

                min = num;
            }
        }

        return min;
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


    private static int[] findMinMax(int[] array) {

        int min = array[0];

        int max = array[0];

        for (int num : array) {

            if (num < min) {
                min = num;
            }
            if (num > max) {

                max = num;
            }
        }
        return new int[]{min, max};
    }
}
