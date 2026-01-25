package array;

import java.util.Arrays;

public class MoveZerosToEnd {
    public static void moveZeros(int[]  arr){
        int j = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != 0){
                arr[j++] = arr[i];
            }
        }
        while(j < arr.length){
            arr[j++] = 0;
        }
    }
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 3,0, 12,0};
        moveZeros(arr);
        System.out.println(Arrays.toString(arr));
    }
}
