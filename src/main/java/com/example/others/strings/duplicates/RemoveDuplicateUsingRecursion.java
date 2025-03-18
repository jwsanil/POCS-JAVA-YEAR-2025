package com.example.others.duplicates;


public class RemoveDuplicateUsingRecursion {


    public static void main(String[] args) {

    String output = removeDuplicates("helloworld",0,"");



        System.out.println(output);



    }

    public static String removeDuplicates(String input, int index, String result) {

        if (index == input.length()) {

            return result;

        }

        char currentChar = input.charAt(index);
        //if the character is not alredy in result , appending


        if (result.indexOf(currentChar) == -1) {

            result += currentChar;
        }

        return removeDuplicates(input, index + 1, result);

    }


}
