package com.example.others.repeatedVowels;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class RepeatedVowels {

    public static void main(String[] args) {
        String input = "beautiful";
        Set<Character> seenoVowels = new LinkedHashSet<>();
        if (input == null || input.isEmpty()) {

            System.out.println("");
        }

        StringBuilder stringBuilder = new StringBuilder();

        char prevChar = '\0';
        IntStream chars = input.chars();
        StringBuilder builder = new StringBuilder();
        boolean flag = true;
        for (char c : input.toCharArray()) {

            if ((isVowel(c)) && seenoVowels.add(c)) {
                builder.append(c);

                continue;
            }
            if (seenoVowels.add(c)) {
                builder.append(c);
            }
        }

        System.out.println(builder);

    }

    private static boolean isVowel(char c) {

        return "AEIOUaeiou".indexOf(c) != -1;
    }
}
