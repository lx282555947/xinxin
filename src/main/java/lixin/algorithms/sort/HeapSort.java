package lixin.algorithms.sort;

public class HeapSort {
    public static void heapSort(int[] array){
        //初始化堆
        for (int i = array.length / 2; i >= 1; i--) {
            sink(array, i, array.length);
        }
        //将最大值，即array[1]的元素，与末尾元素互换，然后重新进行堆排序
        for (int len = array.length; len > 0;) {
            //元素互换
            int temp = array[0];
            array[0] = array[len-1];
            array[len-1] = temp;
            len--;
            //重新进行堆排序
            sink(array, 1, len);
        }
    }

    public static void sink(int[] array, int currentPoint, int len) {
        int length = len;
        int j = currentPoint * 2;
        while (j <= length) {
            //先判断子节点的大小,若左子节点小于右子节点，则与右子节点互换
            if (j < length && array[j-1] < array[j]) {
                j++;
            }
            //如果当前元素本来就比子节点大，则跳出循环
            if (array[currentPoint-1] >= array[j-1]) {
                break;
            }
            //交换元素
            int temp = array[currentPoint-1];
            array[currentPoint-1] = array[j-1];
            array[j-1] = temp;

            currentPoint = j;
            j = currentPoint * 2;
        }
    }

    public static void main(String[] args){
        int[] array = {5, 1, 9, 8, 5, 6, 2, 1, 6,7,4,6,5,5,6,1,2,3,7,5,6};
        heapSort(array);
        for (int c : array) {
            System.out.println(c);
        }
    }
}
