package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sort{


    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(4);
        list.add(6);
        list.add(1);
        list.add(11);
        list.add(1);
        list.add(12);
        list.add(2);

        System.out.println(list);

list.sort(Comparator.reverseOrder());
System.out.println(list);
    }

}