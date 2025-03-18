package com.example.others.duplicates;

import java.util.Arrays;

public class RemoveDuplicatesSolution4 {


    public static void main(String[] args) {
        String input = "hppppayydmppda";
        //convert string to char array
        char chars[] = input.toCharArray();


        Arrays.sort(chars);

        System.out.println("sorted array:" + new String(chars));
//addhmppppppyy
        for (int i = 1; i < chars.length; i++) {
            //   System.out.println(chars[i]+"----"+chars[i-1]+"(i="+i);
            if (chars[i] == chars[i - 1]) {
                //found a duplicate print only once for a sequence of duplicates

                if ((i >= 2 && chars[i] != chars[i - 2]) || i == 1) {

                    System.out.println(chars[i] + "");
                }
            }

        }


    }
}
