package com.example.others.duplicates.streams;

import java.util.stream.Collectors;

public class RemoveDuplicateElementsUsingStreams {


    public static void main(String[] args) {
        String input = "programming";
        String result = input.chars().distinct().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
        System.out.println(result);
    }

}
