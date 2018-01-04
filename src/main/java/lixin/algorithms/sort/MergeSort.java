package lixin.algorithms.sort;


public class MergeSort {
    public static void main(String[] args){
        int[] array = {8, 9, 4, 5, 3, 0, 4, 56, 8, 9, 5};
        mergeSort(array, 0, array.length - 1);
        for (int c : array) {
            System.out.println(c);
        }
    }
    //[4,2]
    public static void mergeSort(int[] array, int start, int end) {

        //递归终止条件
        if (start == end) {
            return;
        }
        //临时存储数组
        int[] temp = new int[end - start + 1];
        //将数组拆分为两份
        int mid = (start + end) / 2;
        //数组被分为了两部分
        //前半部分为0到mid，后半部分为mid+1到end
        //对前半部分进行递归拆分
        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, end);
        //合并
        merge(array, temp, start, mid, end);
    }

    public static void merge(int[] source, int[] temp, int start, int mid, int end) {
        int i = 0, j = 0, k = 0;
        for (i = start, j = mid + 1; i <= mid && j <= end; k++) {
            //判断当前i，j所在索引的元素大小
            if (source[i] < source[j]) {
                temp[k] = source[i];
                i++;
            } else {
                temp[k] = source[j];
                j++;
            }
        }

        //将剩余的元素移动到temp末尾
        while (i <= mid) {
            temp[k] = source[i];
            i++;
            k++;
        }

        while (j <= end) {
            temp[k] = source[j];
            j++;
            k++;
        }

        int t=0;
        while (start <= end) {
            source[start++] = temp[t++];
        }
    }
}
