/**
* @author mzl
* @FileName Recursive.java
* Created on 2015年11月20日 上午9:54:50
* Copyright 2015
* 
*/
package com.myutil.algorithm;

import java.util.ArrayList;  
import java.util.Arrays;  
import java.util.List;  
  
 
/** 
 * 此类描述的是： 循环和递归两种方式实现未知维度集合的笛卡尔积 
 * @author: 毛占磊
 * @version: v1.0 
 * @dateTime：2015年11月20日 上午10:18:01
 */ 
public class Descartes {  
  
    /** 
     * 递归实现dimValue中的笛卡尔积，结果放在result中 
     * @param dimValue 原始数据 
     * @param result 结果数据 
     * @param layer dimValue的层数 
     * @param curList 每次笛卡尔积的结果 
     */  
    private static void recursive (List<List<String>> dimValue, List<List<String>> result, int layer, List<String> curList) {  
        if (layer < dimValue.size() - 1) {  
            if (dimValue.get(layer).size() == 0) {  
                recursive(dimValue, result, layer + 1, curList);  
            } else {  
                for (int i = 0; i < dimValue.get(layer).size(); i++) {  
                    List<String> list = new ArrayList<String>(curList);  
                    list.add(dimValue.get(layer).get(i));  
                    recursive(dimValue, result, layer + 1, list);  
                }  
            }  
        } else if (layer == dimValue.size() - 1) {  
            if (dimValue.get(layer).size() == 0) {  
                result.add(curList);  
            } else {  
                for (int i = 0; i < dimValue.get(layer).size(); i++) {  
                    List<String> list = new ArrayList<String>(curList);  
                    list.add(dimValue.get(layer).get(i));  
                    result.add(list);  
                }  
            }  
        }  
    }  
  
    /** 
     * 循环实现dimValue中的笛卡尔积，结果放在result中 
     * @param dimValue 原始数据 
     * @param result 结果数据 
     */  
    @SuppressWarnings("unused")
	private static void circulate (List<List<String>> dimValue, List<List<String>> result) {  
        int total = 1;  
        for (List<String> list : dimValue) {  
            total *= list.size();  
        }  
        String[] myResult = new String[total];  
  
        int itemLoopNum = 1;  
        int loopPerItem = 1;  
        int now = 1;  
        for (List<String> list : dimValue) {  
            now *= list.size();  
  
            int index = 0;  
            int currentSize = list.size();  
  
            itemLoopNum = total / now;  
            loopPerItem = total / (itemLoopNum * currentSize);  
            int myIndex = 0;  
  
            for (String string : list) {  
                for (int i = 0; i < loopPerItem; i++) {  
                    if (myIndex == list.size()) {  
                        myIndex = 0;  
                    }  
  
                    for (int j = 0; j < itemLoopNum; j++) {  
                        myResult[index] = (myResult[index] == null? "" : myResult[index] + ",") + list.get(myIndex);  
                        index++;  
                    }  
                    myIndex++;  
                }  
  
            }  
        }  
  
        List<String> stringResult = Arrays.asList(myResult);  
        for (String string : stringResult) {  
            String[] stringArray = string.split(",");  
            result.add(Arrays.asList(stringArray));  
        }  
    }  
  
    /** 
     * 程序入口 
     * @param args 
     */  
    public static void main (String[] args) {  
        List<String> list1 = new ArrayList<String>();  
        list1.add("1");  
        list1.add("2");  
  
        List<String> list2 = new ArrayList<String>();  
        list2.add("a");  
        list2.add("b");  
  
        List<String> list3 = new ArrayList<String>();  
        list3.add("3");  
        list3.add("4");  
        list3.add("5");  
   
  
        List<List<String>> dimValue = new ArrayList<List<String>>();  
        dimValue.add(list1);  
        dimValue.add(list2);  
        dimValue.add(list3);  
  
        List<List<String>> recursiveResult = new ArrayList<List<String>>();  
        // 递归实现笛卡尔积  
        recursive(dimValue, recursiveResult, 0, new ArrayList<String>());  
  
        System.out.println("递归实现笛卡尔乘积: 共 " + recursiveResult.size() + " 个结果");  
        for (List<String> list : recursiveResult) {  
            for (String string : list) {  
                System.out.print(string + " ");  
            }  
            System.out.println();  
        }  
  
        List<List<String>> circulateResult = new ArrayList<List<String>>();  
        circulate(dimValue, circulateResult);  
        System.out.println("循环实现笛卡尔乘积: 共 " + circulateResult.size() + " 个结果");  
        for (List<String> list : circulateResult) {  
            for (String string : list) {  
                System.out.print(string + " ");  
            }  
            System.out.println();  
        }  
    }  
}  
  