package com.example.others.duplicates;


public class RemovingDuplicateSoultion6 {


    static String removed = "";

    public static void main(String[] args) {

        String output = removeDuplicates("helloworld", 0, "");


        System.out.println(output);
        System.out.println(removed);

    }

    public static String removeDuplicates(String input, int index, String result) {

        if (index == input.length()) {

            return result;

        }

        char currentChar = input.charAt(index);
        //if the character is not alredy in result , appending


        if (result.indexOf(currentChar) == -1) {

            result += currentChar;
        } else {
            if (removed.indexOf(currentChar) == -1) {
                removed += currentChar;
            }
        }

        return removeDuplicates(input, index + 1, result);

    }


}
