package com.resetsa.homeworks;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ArrayList<?> listInt = new ArrayList<Integer>(Arrays.asList(1,1,2,3,4,4,5,6,7,8,9,9));
        System.out.println(Unique.GetUnique(listInt));
        ArrayList<?> listString = new ArrayList<String>(Arrays.asList("a","1","b","1","4","4"));
        System.out.println(Unique.GetUnique(listString));
    }
}