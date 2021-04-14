/**
 * Project: Part 1
 *
 * TODO: implement the sorting methods.
 *
 * @sources TODO: list your sources here
 *
 * DO NOT COPY PASTE THE ALGORITHMS FOR SORTING METHODS FROM THE INTERNET. WE WILL KNOW!
 *
 */

import java.util.ArrayList;

public class Sorting<Item extends Comparable<Item>> {


    /**
     *
     * Default Constructor
     *
     */
    public Sorting() {
    }

    /**
     *
     * returns true if the Item at index @param i in @ param list is greater than Item at index @param j
     * else return false
     *
     * @param list
     * @param i
     * @param j
     * @return
     */
    private ArrayList<Item> inputArray;
    public boolean greaterThan(ArrayList<Item> list, int i, int j) {
        return list.get(i).compareTo(list.get(j)) > 0;
    }

    /**
     *
     * returns true if the Item @param i is greater than Item @param j else return false
     *
     * @param i
     * @param j
     * @return
     */
    public boolean greaterThan(Item i, Item j) {
        return i.compareTo(j) > 0;
    }


    /**
     *
     * returns true if the Item at index @param i in @ param list is lesser than Item at index @param j
     * else return false
     *
     * @param list
     * @param i
     * @param j
     * @return
     */
    public boolean lessThan(ArrayList<Item> list, int i, int j) {
        return list.get(i).compareTo(list.get(j)) < 0;
    }

    /**
     *
     * returns true if the Item @param i is less than Item @param j else return false
     *
     * @param i
     * @param j
     * @return
     */
    public boolean lessThan(Item i, Item j) {
        return i.compareTo(j) < 0;
    }


    /**
     *
     * returns true if the Item @param i is equal to Item @param j else return false
     *
     * @param i
     * @param j
     * @return
     */
    public boolean equal(Item i, Item j) {
        return i.compareTo(j) == 0;
    }


    /**
     *
     * returns true if the Item at index @param i in @ param list is equal to the Item at index @param j
     * else return false
     *
     * @param list
     * @param i
     * @param j
     * @return
     */
    public boolean equal(ArrayList<Item> list, int i, int j) {
        return list.get(i).compareTo(list.get(j)) == 0;
    }


    /**
     * Sorts the list in ascending order using Insertion Sort.
     *
     *
     * @param list
     */
    public ArrayList<Item> insertionSort(ArrayList<Item> list) {
        // TODO: part 1
            for (int j = 1; j < list.size(); j++) {
                Item current = list.get(j);
                int i = j-1;
                while ((i > -1) && ((list.get(i).compareTo(current)) == 1)) {
                    list.set(i+1, list.get(i));
                    i--;
                }
                list.set(i+1, current);
            }
        return list;
    }


    /**
     *
     * Sorts the list in ascending order using Merge Sort.
     *
     * @param list
     */
    public ArrayList<Item> mergeSort(ArrayList<Item> list) {
        this.inputArray = list;
        sortGivenArray();
        return inputArray;
    }
    public void sortGivenArray(){       
        divide(0, this.inputArray.size()-1);
    }
    
    public void divide(int startIndex,int endIndex) {
        if(startIndex<endIndex && (endIndex-startIndex)>=1){
            int mid = (endIndex + startIndex)/2;
            divide(startIndex, mid);
            divide(mid+1, endIndex);        
            merger(startIndex,mid,endIndex);            
        }       
    }   
    public void merger(int startIndex,int midIndex,int endIndex) {
        ArrayList<Item> mergedSortedArray = new ArrayList<Item>();
        int leftIndex = startIndex;
        int rightIndex = midIndex+1;
        
        while(leftIndex<=midIndex && rightIndex<=endIndex){
            if(lessThan(inputArray.get(leftIndex),inputArray.get(rightIndex))) {
                mergedSortedArray.add(inputArray.get(leftIndex));
                leftIndex++;
            }
            else {
                mergedSortedArray.add(inputArray.get(rightIndex));
                rightIndex++;
            }
        }       
        while(leftIndex<=midIndex) {
            mergedSortedArray.add(inputArray.get(leftIndex));
            leftIndex++;
        }
        while(rightIndex<=endIndex) {
            mergedSortedArray.add(inputArray.get(rightIndex));
            rightIndex++;
        }
        int i = 0;
        int j = startIndex;
        while(i<mergedSortedArray.size()){
            inputArray.set(j, mergedSortedArray.get(i++));
            j++;
        }
    }
    /**
     *
     * prints the Items in the @param list.
     *
     * @param list
     */
    public void print(ArrayList<Item> list) {
        int n = list.size();

        StringBuffer bf = new StringBuffer();
        bf.append(list.get(0).toString());

        for (int i = 1;i < n; i += 1) {
            bf.append(" " + list.get(i).toString());
        }

        System.out.println(bf.toString());
    }


    /**
     *
     * Swaps the element at index @param i with element at index @param j in the @param list.
     *
     * @param list
     * @param i
     * @param j
     */
    private void swap(ArrayList<Item> list, int i, int j) {
        Item temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     *
     * This is the main function to run the Sorting class to help with debugging.
     *
     * @param args
     */
    public static void main(String[] args) {

        Sorting<Integer> s = new Sorting<>();
        ArrayList<Integer> A = new ArrayList<Integer>();
        Integer[] K = {4,4,3,1,3,9,8,2,5,6};

        for (Integer k : K) {
            A.add(k);
        }
        
        s.print(A);

    }

}
