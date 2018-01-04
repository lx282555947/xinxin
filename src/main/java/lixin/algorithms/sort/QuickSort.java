package lixin.algorithms.sort;


public class QuickSort {

    public static void quickSort(int[] array, int low, int high) {
        //结束条件
        if (low >= high) {
            return;
        }
        //以这个基准进行排序
        int prior = partition(array, low, high);
        //对基准数以左的数组进行递归排序
        quickSort(array, low, prior - 1);
        quickSort(array, prior + 1, high);
    }

    public static int partition(int[] array, int low, int high) {
        int temp = array[low];
        //以low所在索引对应的元素为基准
        while (low < high) {
            while (low < high && array[high] >= temp) {
                if(low==high) break;
                high--;
            }

            if (low < high) {
                array[low] = array[high];
                low++;
            }

            while (low < high && array[low] <= temp) {
                if(low==high) break;
                low++;
            }
            if (low < high) {
                array[high] = array[low];
                high--;
            }
        }
        array[low] = temp;
        return low;
    }

    public static void quick3way(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int small = low;
        int large = high;
        int i = low;
        //设置基准点
        int base = array[low];
        while (i < large) {
            if (array[i] < base) {
                int temp = array[small];
                array[small] = array[i];
                array[i] = temp;
                small++;
                i++;
            } else if (array[i] > base) {
                int temp = array[large];
                array[large] = array[i];
                array[i] = temp;
                large--;
            }else {
                i++;
            }
        }
        quick3way(array, low, small - 1);
        quick3way(array, large + 1, high);
    }

    public static void main(String[] args){
        int[] array = {8, 9, 5, 2, 4, 5, 8, 9, 6, 2};
        quick3way(array, 0, array.length - 1);
        for (int a : array) {
            System.out.println(a);
        }
    }
}
