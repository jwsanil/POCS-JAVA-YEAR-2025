package com.example.others.numbers;



public class FindDuplicateElements {
    public static void main(String[] args) {
        int[] array = {2, 3, 2, 1, 2, 5, 3, 2, 2, 4, 4, 4, 6, 4, 4, 2, 2};
        int[] isDuplicateFlags = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (isDuplicateFlags[i] == 0) {
                boolean isDuplicate = false;
                for (int j = i + 1; j < array.length; j++) {
                    if (array[i] == array[j]) {

                        isDuplicate = true;
                        isDuplicateFlags[j] = 1;
                    }

                }
                if (isDuplicate) {
                    System.out.println(array[i]);

                }
            }

        }
    }
}

