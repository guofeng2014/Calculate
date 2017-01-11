package com.calculate;

import java.util.Arrays;

/**
 * 作者: guofeng
 * 日期: 2017/1/10.
 */
public class CalculateCollections {

    public static void main(String[] args) {
        int array[] = {1, 3, 6, 2, 9, 5, 0, 13, 19, 23, 22, 10, 15, 30};
//        System.out.println("选择排序=" + Arrays.toString(selectArrange(array)));
//        System.out.println("冒泡排序=" + Arrays.toString(bubbleArrange(array)));
//        System.out.println("快速排序=" + Arrays.toString(fastArrange(array)));
//        System.out.println("归并排序=" + Arrays.toString(mergeArrange(array)));
//        System.out.println("插入排序=" + Arrays.toString(insertArrange(array)));
        System.out.println("希尔排序=" + Arrays.toString(shellArrange(array)));
    }

    /**
     * 选择排序,时间复杂度n平方
     *
     * @param array
     */
    private static int[] selectArrange(int array[]) {
        if (array == null) return null;
        if (array.length <= 0) return array;
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            int minValue = array[i];
            int minIndex = -1;
            //查找最小值
            for (int j = i + 1; j < length; j++) {
                if (array[j] < minValue) {
                    minValue = array[j];
                    minIndex = j;
                }
            }
            //替换位置
            if (minIndex != -1) {
                array[minIndex] = array[i];
                array[i] = minValue;
            }
        }
        return array;
    }

    /**
     * 冒泡排序, 时间复杂度n平方
     */
    private static int[] bubbleArrange(int array[]) {
        if (array == null) return null;
        if (array.length <= 1) return array;
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 快速排序,时间复杂度nlogn
     */
    private static int[] fastArrange(int array[]) {
        if (array == null) return null;
        if (array.length <= 1) return array;
        fastArrange(array, 0, array.length - 1);
        return array;
    }

    private static void fastArrange(int array[], int start, int end) {
        int middleIndex = fastArrangeMiddleIndex(array, start, end);
        if (start < end) {
            fastArrange(array, start, middleIndex - 1);
            fastArrange(array, middleIndex + 1, end);
        }
    }

    private static int fastArrangeMiddleIndex(int array[], int start, int end) {
        int temp = array[start];
        while (start < end) {
            while (start < end && array[end] >= temp) {
                end--;
            }
            array[start] = array[end];
            while (start < end && array[start] <= temp) {
                start++;
            }
            array[end] = array[start];
        }
        array[start] = temp;
        return start;
    }

    /**
     * 归并排序,时间复杂度nlogn
     */
    private static int[] mergeArrange(int array[]) {
        if (array == null) return null;
        if (array.length <= 1) return array;
        splitArrays(array, 0, array.length - 1, new int[array.length]);
        return array;
    }

    private static void splitArrays(int array[], int start, int end, int temp[]) {
        if (start < end) {
            int middle = (start + end) / 2;
            splitArrays(array, start, middle, temp);
            splitArrays(array, middle + 1, end, temp);
            arrangeArrays(array, start, end, middle, temp);
        }
    }

    private static void arrangeArrays(int array[], int start, int end, int middle, int temp[]) {
        int leftStart = start;
        int rightStart = middle + 1;
        int copyStart = start;
        while (leftStart <= middle && rightStart <= end) {
            if (array[leftStart] < array[rightStart]) {
                temp[start++] = array[leftStart++];
            } else {
                temp[start++] = array[rightStart++];
            }
        }

        while (leftStart <= middle) {
            temp[start++] = array[leftStart++];
        }

        while (rightStart <= end) {
            temp[start++] = array[rightStart++];
        }

        while (copyStart <= end) {
            array[copyStart] = temp[copyStart++];
        }
    }

    /**
     * 插入排序,时间复杂度n平方
     */
    private static int[] insertArrange(int array[]) {
        //5,8,30，49，10,6
        if (array == null) return null;
        if (array.length <= 1) return array;
        int length = array.length;
        int j;
        for (int i = 0; i < length; i++) {
            int temp = array[i];
            for (j = i; j > 0 && temp < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = temp;
        }
        return array;
    }

    /**
     * 希尔排序
     *
     * @param array
     */
    private static int[] shellArrange(int array[]) {
        if (array == null) return null;
        if (array.length <= 0) return array;
        double d1 = array.length;
        int temp = 0;
        while (true) {
            d1 = Math.ceil(d1 / 2);
            int d = (int) d1;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < array.length; i += d) {
                    int j = i - d;
                    temp = array[i];
                    for (; j >= 0 && temp < array[j]; j -= d) {
                        array[j + d] = array[j];
                    }
                    array[j + d] = temp;
                }
            }
            if (d == 1)
                break;
        }
        return array;
    }

}
