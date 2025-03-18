package com.example.others.fequencyCount;

import java.util.HashMap;
import java.util.Map;

public class FrequencyCounter {
    public static void main(String[] args) {
        String input = "mississippi";
        Map<Character, Integer>  frequencyMap = new HashMap<>();
        for(char ch:input.toCharArray()){

            frequencyMap.put(ch,frequencyMap.getOrDefault(ch,0)+1);

        }


        for(Map.Entry<Character,Integer> entry : frequencyMap.entrySet()){


            System.out.println(" ' "+entry.getKey() +" ':"+entry.getValue());
        }
    }


}
