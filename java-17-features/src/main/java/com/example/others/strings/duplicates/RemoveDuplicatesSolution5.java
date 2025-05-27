package com.example.others.strings.duplicates;

public class RemoveDuplicatesSolution5 {


    public static void main(String[] args) {

       int checker = 0;

       StringBuilder result = new StringBuilder();

    String input = "helloworrld";
        for(int i = 0; i < input.length(); i++){

            int bit= input.charAt(i) -'a';//7 4 11 14 22 14 17 17 11 3
            System.out.println(bit);
            if((checker& (1 <<bit))==0){   //
                result.append(input.charAt(i));
                checker |= (1 << bit);
                System.out.println(checker);
            }
        }
        System.out.println(result);


    }

}
