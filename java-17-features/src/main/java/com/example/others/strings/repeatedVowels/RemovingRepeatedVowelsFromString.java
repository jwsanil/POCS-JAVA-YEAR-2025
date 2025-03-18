package com.example.others.repeatedVowels;

import java.nio.file.LinkPermission;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RemovingRepeatedVowelsFromString {

    public static void main(String[] args) {
        String vowels = "aeiouAEIOU";

        String input = "Beautiful queue cordinate";

       /* String s = IntStream.range(0, input.length()).mapToObj(i -> String.valueOf(input.charAt(i)))
                .reduce((prev, curr) -> (vowels.contains(prev) && vowels.contains(curr) ? prev
                        : prev + curr)).orElse("");
*/

        Set<Character> seenoVowels = new LinkedHashSet<>();
        String collect = input.chars().mapToObj(c -> (char) c).filter(c -> !(vowels.indexOf(c) != -1) || seenoVowels.add(c)).map(String::valueOf).collect(Collectors.joining());

        System.out.println(collect);



    }
}
