package com.example.others.numbers.minMax;

public class FindSecondMinMax {

    public static void main(String[] args) {
        int[] array = {3,1,7,2,9,4,0,6};

        int[] result = findSecondMinMax(array);
        System.out.println("min: "+result[0]+", second min: "+result[1]);
        System.out.println("max: "+result[2]+", second max:"+result[3]);

    }

    private static int[] findSecondMinMax(int[] array) {

        if(array.length<2){return null;}

        int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;

        int max = Integer.MIN_VALUE,secondMax = Integer.MIN_VALUE;

        for(int num:array){
            //finding min and second min

            if(num<min){  //3,1,7,2,9,4,0,6
               secondMin=min ;
                min = num;                        //num<min num<secondMin
            }else if( num<min && num>secondMin){  //3 <10 &  3> 2   min = 10 ,  secondMin = 3
                secondMin = num;
            }
            //finding max and sencod max

            if (num> max){
                secondMax = max;
                max =num;


            }

            else if (num < max  && num > secondMax){

                secondMax = num;
            }


        }
        return new int[]{min,secondMin,max,secondMax};
    }
}
