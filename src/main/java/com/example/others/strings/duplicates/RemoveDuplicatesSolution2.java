package com.example.others.duplicates;

public class RemoveDuplicatesSolution2 {

    public static void main(String[] args) {


        String input = "hello worldldldlllll";

        char[] chars = input.toCharArray();
        int length = chars.length;

        int index = 0;

        for(int i=0; i< length; i++){
            boolean isDuplicate = false;

            //check if the character alredy exist in the result array

            for(int j=0; j<i; j++){

                if(chars[i]==chars[j]){

                    isDuplicate = true;
                    /*break;*/

                }
            }
            //
            if(!isDuplicate){
                chars[index++] = chars[i];
            }


        }
        String finalResult = new String(chars,0,index);

        System.out.println(finalResult);

    }
}
