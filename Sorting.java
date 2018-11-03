import java.util.Random;
import java.util.Arrays;

public class Sorting {

    public static void main(String[] args) {
        int data[] = generateNumberArray(10);
        //int data[] = {10, 2, 5, 2, 5, 6, 7, 12, 3, 4};
        int[] tempArray;
        long startTime, endTime;
        double elapsedTime;

        System.out.println("Array generated: ");
        displayArray(data);

        //Testing Selection Sort
        System.out.println("\nSorting with Selection sort:");
        tempArray = Arrays.copyOf(data, data.length);
        startTime = System.nanoTime();
        tempArray = selectionSort(tempArray);
        endTime = System.nanoTime();
        elapsedTime = (endTime - startTime) / 1000000.0;
        displayArray(tempArray);
        System.out.println("The sort took: " + elapsedTime + "ms");


        //Testing Bubble Sort
        System.out.println("\nSorting with Bubble sort:");
        tempArray = Arrays.copyOf(data, data.length);
        startTime = System.nanoTime();
        tempArray = bubbleSort(tempArray);
        endTime = System.nanoTime();
        elapsedTime = (endTime - startTime) / 1000000.0;
        displayArray(tempArray);
        System.out.println("The sort took: " + elapsedTime + "ms");

        //Testing Insertion Sort
        System.out.println("\nSorting with Insertion sort:");
        tempArray = Arrays.copyOf(data, data.length);
        startTime = System.nanoTime();
        tempArray = insertionSort(tempArray);
        endTime = System.nanoTime();
        elapsedTime = (endTime - startTime) / 1000000.0;
        displayArray(tempArray);
        System.out.println("The sort took: " + elapsedTime + "ms");

        //Testing Merge Sort
        System.out.println("\nSorting with Merge sort:");
        tempArray = Arrays.copyOf(data, data.length);
        startTime = System.nanoTime();
        tempArray = mergeSort(tempArray, 0, tempArray.length - 1);
        endTime = System.nanoTime();
        elapsedTime = (endTime - startTime) / 1000000.0;
        displayArray(tempArray);
        System.out.println("The sort took: " + elapsedTime + "ms");

        //Testing Quick Sort
        System.out.println("\nSorting with Quick sort:");
        tempArray = Arrays.copyOf(data, data.length);
        startTime = System.nanoTime();
        tempArray = quickSort(tempArray, 0, tempArray.length - 1);
        endTime = System.nanoTime();
        elapsedTime = (endTime - startTime) / 1000000.0;
        displayArray(tempArray);
        System.out.println("The sort took: " + elapsedTime + "ms");

        //Testing Arrays.sort
        System.out.println("\nSorting with Arrays.sort sort:");
        tempArray = Arrays.copyOf(data, data.length);
        startTime = System.nanoTime();
        tempArray = javaBuiltInSort(tempArray);
        endTime = System.nanoTime();
        elapsedTime = (endTime - startTime) / 1000000.0;
        displayArray(tempArray);
        System.out.println("The sort took: " + elapsedTime + "ms");
    }

    private static int[] generateNumberArray(int numElements) {
        int[] generated = new int[numElements];

        //add unique numbers into array in order
        for (int i = 0 ; i< generated.length;i++)
            generated[i] = i;

        //shuffle the numbers randomly
        Random randomNumber = new Random();
        for (int i = 0 ; i < generated.length; i++) {
            //swap to random positions
            int temp;
            int first = randomNumber.nextInt(generated.length);
            int second = randomNumber.nextInt(generated.length);
            temp = generated[first];
            generated[first] = generated[second];
            generated[second] = temp;
        }
        return generated;
    }

    private static void displayArray(int[] numbers) {
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }

    private static int[] selectionSort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++)  {
            int index = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[index]) {
                    index = j;
                }
            }
            int temp = numbers[index];
            numbers[index] = numbers[i];
            numbers[i] = temp;
        }
        return numbers;
    }

    private static int[] bubbleSort(int[] numbers) {
        boolean swap = true;
        for (int i = 1; i < numbers.length && swap; i++) {
            swap = false;
            for (int j = 0; j < numbers.length - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = temp;
                    swap = true;
                }

            }
        }
        return numbers;
    }

    private static int[] insertionSort(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            int temp = numbers[i];
            int index = i - 1;
            while ((index > - 1) && (numbers[index] > temp)) {
                numbers[index + 1] = numbers[index];
                index--;
            }
            numbers[index + 1] = temp;
        }
        return numbers;
    }

    private static int[] mergeSort(int[] numbers, int start, int end) {
        if (end - start == 1) {
            if (numbers[start] > numbers[end]) {
                int temp = numbers[start];
                numbers[start] = numbers[end];
                numbers[end] = temp;
            }
        }
        else if (end - start > 1) {
            int mid = (start + end) / 2;
            mergeSort(numbers, start, mid);
            mergeSort(numbers, mid + 1, end);
            //Merge
            int[] tempArray = new int[end - start + 1];
            int left = start;
            int right = mid + 1;
            for (int i = 0; i < end - start + 1; i++) {
                if (left > mid) { //If all elements on the left side has been checked just add the right side
                    tempArray[i] = numbers[right];
                    right++;
                }
                else if (right > end) { //If all elements on the right side has been checked just add the left side
                    tempArray[i] = numbers[left];
                    left++;
                }
                else if (numbers[left] < numbers[right]) {
                    tempArray[i] = numbers[left];
                    left++;
                }
                else {
                    tempArray[i] = numbers[right];
                    right++;
                }
            }
            System.arraycopy(tempArray, 0, numbers, start, tempArray.length);
        }
        return numbers;
    }

    private static int[] quickSort(int[] numbers, int start, int end) {
        int mid = (start + end) / 2;
        int left = start;
        int right = end;
        while (left <= right) {
            while (numbers[left] < numbers[mid]) {
                left++;
            }
            while (numbers[right] > numbers[mid]) {
                right--;
            }
            if (left <= right) {
                int temp = numbers[left];
                numbers[left] = numbers[right];
                numbers[right] = temp;
                left++;
                right--;

            }
        }
        if (start < right) {
            quickSort(numbers, start, right);
        }
        if (left < end) {
            quickSort(numbers, left, end);
        }
        return numbers;
    }

    private static int[] javaBuiltInSort(int[] numbers) {
        Arrays.sort(numbers);
        return numbers;
    }
}