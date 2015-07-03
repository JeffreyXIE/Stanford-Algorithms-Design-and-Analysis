import java.util.Arrays;
import java.io.File;
import java.util.Scanner;
public class TestMergeSorting
{
    //input txt file 
    public static void main(String[] args){
        int [] integerArray=new int[100000];//create a Array for holding txt file data
        try{
            File inputFile= new File("integerArray.txt");
            Scanner input= new Scanner(inputFile);
            for (int i=0;  input.hasNextLine(); i++) { 
                integerArray[i] = input.nextInt();//write data into Array
            }
            input.close();
            System.out.println(Arrays.toString(integerArray));

        }
        catch(Exception e){
            System.out.println("Cannot read the word list file.");
        }
        MergeSort Test=new MergeSort(); 
        Test.mergeSort(integerArray);
        //System.out.println(Arrays.toString(integerArray)); //output the sorted array
        System.out.println("The total number of integer inversion from the input array is:"+Test.totalNum);
    }
}

public class MergeSort
{
    long totalNum=0;// the total number of inversion
    //main mergeSort method
    public void mergeSort(int[] inputArray){
        int size=inputArray.length;
        if(size>1){
            //divide the original array into half
            int []left=leftHalf(inputArray);
            int []right=rightHalf(inputArray);
            //recursively call mergeSort for left and right half
            mergeSort(left);
            mergeSort(right);
            //merging the left and right half
            merging(inputArray,left, right);
        }
    }
    //create the lefthalf of the input array
    public int[] leftHalf(int[]input){
        int size=input.length/2;
        int[] left= new int[size];
        for(int i=0;i<size;i++){
            left[i]=input[i];
        }
        return left;
    }
    //create the righthalf of the input array
    //if the two half is not equal length
    //right half always get one more element
    public int[] rightHalf(int[]input){
        if(input.length%2==0){
            int size=input.length/2;
            int[] right= new int[size];
            for(int i=0;i<size;i++){
                right[i]=input[size+i];
            }
            return right;
        }
        else{
            int size=input.length/2+1;
            int[] right= new int[size];
            for(int i=0;i<size;i++){
                right[i]=input[size-1+i];
            }
            return right;
        }
    }
    //merging method for the two half
    //the inversion is counted by adding the remaining number of elements in lefthalf once an element in righthalf is written in the final array 
    public void merging(int[] output,int[]l, int[]r){
        int i=0;
        int j=0;
        int n=output.length;
        for(int k=0; k<n;k++){
            if(j>=r.length||i<l.length && l[i]<r[j]){
                output[k]=l[i];
                i++;
            }
            else  {
                output[k]=r[j];
                j++;
                totalNum=totalNum+(l.length-i);
            }
        }
    }

}