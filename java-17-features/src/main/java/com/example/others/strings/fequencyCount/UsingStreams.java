package com.example.others.fequencyCount;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UsingStreams {

    public static void main(String[] args) {
        String input = "mississipi";

        Map<Character, Long> frequencyMap = input.chars()
                .mapToObj(c -> (char) c).
                collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

frequencyMap.forEach((key,value)-> System.out.println("key: "+key+"..value: "+value));
    }
}
