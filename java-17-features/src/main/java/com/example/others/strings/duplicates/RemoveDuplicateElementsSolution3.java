package com.example.others.duplicates;

public class RemoveDuplicateElementsSolution3 {

    public static void main(String[] args) {

        String input = "hello world";
        char[] chars = input.toCharArray();
        boolean[] seen = new boolean[256];
        System.out.println(input+".."+chars.length);
        int index=0;
        for(int i=0; i<chars.length;i++){
            System.out.println(chars[i]+".."+seen[i]+".."+seen[chars[i]]+".."+index); //seen[h,e,l,l

            if(!seen[chars[i]]){ //not seen h, not seen e, not not seen l, not l
                seen[chars[i]]=true;// h=true, e=true,l=true,!l=true

                chars[index++]=chars[i];
            }

        }


        String result = new String(chars, 0, index);
        System.out.println(result);
    }
}
