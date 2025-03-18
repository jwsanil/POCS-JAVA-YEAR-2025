package com.example.others.duplicates;

public class RemoveDuplicatesFromString {


    public static void main(String[] args) {

        StringBuilder input= new StringBuilder("programming");
        //String builder to save the results



        StringBuilder stringBuilder = new StringBuilder();

        //loop through each character in the input
        for(int i=0; i<input.length();i++){

          char currentChar= input.charAt(i);

          //check if the current character already prsent
            System.out.println(stringBuilder.indexOf(String.valueOf(currentChar))==-1);
            if(stringBuilder.indexOf(String.valueOf(currentChar))==-1){



                stringBuilder.append(currentChar);// joing the string

            }


        }
        System.out.println(stringBuilder);


    }



}
