package com.example;

public class AlgorithmsTest {
    public static void main(String[] args) {
        int[] arr = {3,5,7,8,1,2,4,6};
        MergeSort(arr, 0, arr.length/2 - 1, arr.length - 1);
    }

    //选择排序
    public static void SelectSort(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i; j < N; j++) {
                if (a[min] > a[j]) {
                    min = j;
                }
            }
            int temp = 0;
            temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }

        for (int i = 0;i<N;i++) {
            System.out.print(a[i]+",");
        }
    }

    //插入排序：数组的初始顺序对插入排序的效率影响比较大，适合小规模数组或者部分有序数组
    public static void InsertSort(int[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
        for (int i = 0; i < N; i++) {
            System.out.print(a[i] + ",");
        }
    }

    //希尔排序：是基于插入排序的高级排序算法，设置一个h值，将每间隔h的元素抽取出来组成一个新的部分数组并对其进行排序
    //把所有的h数组都排序成为h有序数组，之后整个数组就变成部分有序了，对于已经左右元素相差不大的数组再使用插入排序，这样就大大提高了效率（希尔排序的难点在于递增序列的选择）
    public static void ShellSort(int[] a) {
        int N = a.length;
        int h = 1;
        int temp = 0;
        while (h <= N/3) {
            h = 3 * h + 1;//该递增序列最为常用
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && a[j] < a[j - h]; j -= h) {//对每个h数组内部进行大小排序
                    temp = a[j];
                    a[j] = a[j - h];
                    a[j - h] = temp;

                    for (int z = 0; z < N; z++) {
                        System.out.print(a[z] + ",");
                    }
                    System.out.println();
                }
            }
            h = h / 3;
        }
    }

    //归并排序
    public static void MergeSort(int[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        int[] aux = new int[hi + 1];
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];//将分成两半分别排序好的大数组复制一份
        }

        for (int k = lo; k <= hi; k++) {
            if(i > mid) a[k] = aux[j++];//如果右半边元素已经取完则从左半边数组取元素放回
            else if(j > hi) a[k] = aux[i++];//如果左半边元素已经取完则从右半边数组取元素放回
            else if(aux[j] < aux[i]) a[k] = aux[j++];//如果右半边的当前元素小于左半边的当前元素则取右半边的元素放回
            else a[k] = aux[i++];//最后就是右半边的当前元素大于等于左半边的当前元素取左半边的元素放回
        }

        for (int z = lo; z <= hi; z++) {
            System.out.print(a[z] + ",");
        }
    }
}
