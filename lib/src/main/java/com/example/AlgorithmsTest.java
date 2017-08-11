package com.example;

public class AlgorithmsTest {
    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,6,7,8};
        System.out.println(rank(6,arr,0,arr.length - 1));
//        for (int z = 0; z < arr.length; z++) {
//            System.out.print(arr[z] + ",");
//        }
    }

    /**
     * 选择排序
     */
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

        for (int i = 0; i < N; i++) {
            System.out.print(a[i] + ",");
        }
    }

    /**
     * 插入排序：数组的初始顺序对插入排序的效率影响比较大，适合小规模数组或者部分有序数组
     */
    public static void InsertSort(int[] a,int lo,int hi) {
        if (hi <= lo) return;
        int N = hi - lo;
        for (int i = 0; i < N; i++) {
            for (int j = lo + i + 1; j > lo && a[j] < a[j - 1]; j--) {
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
    }

    /**
     * 希尔排序：是基于插入排序的高级排序算法，设置一个h值，将每间隔h的元素抽取出来组成一个新的部分数组并对其进行排序
     * 把所有的h数组都排序成为h有序数组，之后整个数组就变成部分有序了，对于已经左右元素相差不大的数组再使用插入排序，这样就大大提高了效率（希尔排序的难点在于递增序列的选择）
     */
    public static void ShellSort(int[] a) {
        int N = a.length;
        int h = 1;
        int temp = 0;
        while (h <= N / 3) {
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

    /**
     * 归并排序：复杂度为O(nlogn)
     * */
    public static void MergeSort(int[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        int[] aux = new int[hi + 1];
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];//将分成两半分别排序好的大数组复制一份（注意要两边都排序好再进行归并）
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];//如果右半边元素已经取完则从左半边数组取元素放回
            else if (j > hi) a[k] = aux[i++];//如果左半边元素已经取完则从右半边数组取元素放回
            else if (aux[j] < aux[i]) a[k] = aux[j++];//如果右半边的当前元素小于左半边的当前元素则取右半边的元素放回
            else a[k] = aux[i++];//最后就是右半边的当前元素大于等于左半边的当前元素取左半边的元素放回
        }
    }

    /**
     * 自顶向下的归并排序
     * 算法优化：1.当a[mid]<a[mid +1]这个的情况下，可以不用进行merge合并操作
     * 2.在数组被分割的比较小的时候可以使用插入排序来代替归并操作
     */
    public static void MergeUB(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        if (hi - lo <= 4) {//优化2
            InsertSort(a,lo,hi);
            return;
        }
        int mid = (hi - lo) / 2 + lo;
        MergeUB(a, lo, mid);
        MergeUB(a, mid + 1, hi);
        if (a[mid] > a[mid + 1])//优化1
            MergeSort(a, lo, mid, hi);
    }


    /**
     * 自底向上的归并排序,这个归并排序不需要现将数组排序为部分有序
     */
    public static void MergeBU(int[] a) {
        int N = a.length;

        for (int i = 0; i < N; i += 2) {//优化2.先将包含两个元素的每个小数组内部使用插入排序排好
            InsertSort(a, i, Math.min(i + 1, N - 1));
        }
        for (int sz = 2; sz < N; sz += sz) {
            //当小数组大小大于2的话就使用归并排序了
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                if (a[lo + sz - 1] > a[lo + sz])//优化1.lo+sz+1如果小于lo+sz可以不用归并
                    MergeSort(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }

        for (int z = 0; z < a.length; z++) {
            System.out.print(a[z] + ",");
        }
    }

    /**
     * 快速排序：速度快，额外占用内存很小
     * */
    public static void QuickSort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        /**
         * 这是《算法》上的方法，看起来不够简洁
         * int j = partition(a, lo, hi);
         * 下面我们使用另外一种方法
         * */
        int i = lo, j = hi,temp = a[i],t = 0;
        while (i != j) {
            while (a[j] >= temp && i < j) j--;
            while (a[i] <= temp && i < j) i++;
            if (i < j) {
                t = a[i];
                a[i] = a[j];
                a[j] = t;
            }

            //最后将基准数归位
            a[lo] = a[i];
            a[i] = temp;
        }

        QuickSort(a, lo, i - 1);//左半部分递归
        QuickSort(a, i + 1, hi);//右半部分递归
    }

    /**
     * 快速排序改进：三项切分的快速排序，适用于重复元素比较多的时候
     */
    public static void ThreeQuickSort(int[] a, int lo, int hi) {
        if(hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi, temp = a[lo],t = 0;
        while (i <= gt) {
            int cmp = a[i] - temp;
            if (cmp < 0) {
                t = a[lt];
                a[lt] = a[i];
                a[i] = t;
                lt++;
                i++;
            }
            else if (cmp > 0) {
                t = a[gt];
                a[gt] = a[i];
                a[i] = t;
                gt--;
                /**这里没有i++，因为我们已经知道lt右侧都是经过筛选的比切分元素小的元素，而与gt进行直接交换
                 * 并不能确定交换过来的元素都是比切分元素要小的，故不需要使i++,而是继续使a[i]与切分元素进行比较
                 * */
            } else i++;
        }

        //接下来利用递归重新选取切分元素进行小数组内部的排序
    ThreeQuickSort(a,lo, lt - 1);
        ThreeQuickSort(a, gt + 1, hi);
    }

    /**
     * 这里是快速排序的切分部分
     * */
    private static int partition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1,k = a[lo], temp = 0;
        while (true) {
            while (a[++i] > a[lo] && i < j) break;//从左向右查找
            while (a[--j] < a[lo] && j > i) break;
            if(i >= j) break;
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        temp = a[lo];
        a[lo] = a[j];
        a[j] = temp;
        return j;
    }

    /**
     * 二分查找：只需要查找几个条目就能够找到目标元素，所以效率比较高
     * 二分查找需要先将数组元素按照从小到大的顺序排好
     * */
    public static int rank(int key,int[] a,int lo,int hi) {
        if (hi < lo) return -1;
        int mid = lo + (hi - lo)/ 2;
        if(key > a[mid])
            return rank(key, a, mid + 1, hi);
        else if(key < a[mid])
            return rank(key, a, lo, mid - 1);
        else return mid;
    }
}
