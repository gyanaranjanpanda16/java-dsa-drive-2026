package array;


import java.util.Arrays;

public class ReverseArray {
//    Input  : [10, 20, 30, 40, 50]
//    Output : [50, 40, 30, 20, 10]

    public static int[] revArr(int[] arr){
        if(arr == null || arr.length == 0) {
            return arr;
        }

        int left = 0;
        int right = arr.length - 1;

        while(left < right) {
            // Swap elements
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        return arr;
    }
    public static void main(String[] args) {
      int[] arr = {10, 20, 30, 40, 50};
      System.out.println("Reverse Array is :"+ Arrays.toString(revArr(arr)) +" ");

    }
}
