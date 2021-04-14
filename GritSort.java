/**
 * Project: Part 1
 *
 * TODO: implement gritSort.
 *
 * @sources TODO: list your sources here
 *
 * Use the algorithms written in sorting to implement this class.
 *
 */
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

public class GritSort<Item extends Comparable<Item>> {

    /**
     *
     * Default Constructor
     *
     */
    public GritSort() {
    }

    /**
     *
     * Implement Grit Sort as defined in the Handout.
     * for example:
     * list = {3,5,6,8,10,2,4,5,6,1,2,4,5}
     * return value = {1,2,2,3,4,4,5,5,5,6,6,8,10}
     *
     *
     * @param list
     * @return sorted list
     *
     */
    public ArrayList<Item> grit(ArrayList<Item> list) {
        /*
            TODO: part 1
            TODO: STEP 1: implement and call makeChunks on list to divide the list into the sorted chunks.
            TODO: STEP 1.5: make the required number of buckets(can differ from implementation to implementation) and
            TODO            put each chunk into a bucket of proper size.
            TODO: STEP 2: implement and call mergeChunk to merge the chunks in a bucket. do this for every bucket
            TODO: STEP 3: merge the buckets and return the elements of the merged buckets as a list
         */
        ArrayList<ArrayList<Item> > aList = new ArrayList<ArrayList<Item>>();
        aList=makeChunks(list);
        HashMap<Integer, ArrayList<ArrayList<Item>>> buckets = new HashMap<Integer, ArrayList<ArrayList<Item>>>();
        buckets=makeBuckets(aList);
        ArrayList<ArrayList<Item> > bList = new ArrayList<ArrayList<Item>>();
        Integer key[]=new Integer[buckets.size()];
        int k=0;
        for (Integer i : buckets.keySet()) {
            key[k]=i;
            k++;
        }
        for(int i=0;i<buckets.size();i++) {
            bList.add(mergeChunk(buckets.get(key[i])));
        }
        list=mergeChunk(bList);
        return list;
    }

    /**
     *
     * This function takes in a list and returns an ArrayList of ArrayList.
     * Each chunk found is stored in an array list and then all the chunks are in turn stored in an array list which is
     * then returned.
     *
     * for example
     * list = {3,5,6,8,10,2,4,5,6,1,2,4,5}
     *
     * return value = {
     *                     {3,5,6,8,10},
     *                     {2,4,5,6},
     *                     {1,2,4,5}
     *                }
     *
     * @param list
     * @return array list of chunks(sorted sub-lists in the original input list)
     *
     */
    public ArrayList<ArrayList<Item>> makeChunks(ArrayList<Item> list) {
        // TODO: part 1
        ArrayList<ArrayList<Item> > aList = new ArrayList<ArrayList<Item>>();
        ArrayList<Item> temp= new ArrayList<Item>();
        for(int i=0;i<list.size();i++) {
            if(temp.size()==0) {
                temp.add(list.get(i));
            }
            else {
                if(lessThan(list.get(i-1), list.get(i)) || equal(list.get(i-1), list.get(i))) {
                    temp.add(list.get(i));
                }
                else {
                    aList.add(new ArrayList<Item>(temp));
                    temp.clear();
                    i--;
                }
            }
        }
        aList.add(temp);
        return aList;
    }
    /**
    *
    * This function takes in an ArrayList of ArrayList and returns an HashMap<Integer, ArrayList<ArrayList<Item>>> 
    * Chunks of the same size are stored in the same bucket. 
    * Each chunk of the size say S is stored in a bucket which corresponds to that size S.
    * Thus, for the returned HashMap, the key is the chunk size, and the corresponding value is a list of chunks of that size.
    * for example
    * chunks = {
    *              {3,5,6,8,10},
    *              {2,4,5,6},
    *              {1,2,4,5}
    *          }
    *
    * return value = {
    * 					4: {{2,4,5,6},{1,2,4,5}},
    * 					5: {{3,5,6,8,10}}
    * 				  }
    * i.e., the bucket for chunks of size 4 will have {{2,4,5,6},{1,2,4,5}} and
    * bucket for chunks of size 5 will have {{3,5,6,8,10}}
    *
    * @param chunks
    * @return HashMap, where key is the chunk size and value is a list of chunks of that size
    * (sorted sub-lists in the original input list)
    *
    */
    public HashMap<Integer, ArrayList<ArrayList<Item>>> makeBuckets(ArrayList<ArrayList<Item>> chunks) {
    	HashMap<Integer, ArrayList<ArrayList<Item>>> buckets = new HashMap<Integer, ArrayList<ArrayList<Item>>>();
    	// make buckets is a helper function. 
    	// CHANGE THIS ACCORDING TO YOUR IMPLEMENTATION
    	ArrayList<ArrayList<Item> > aList = new ArrayList<ArrayList<Item>>();
        int size[]=new int[chunks.size()];
        int max=0;
        for(int i=0;i<chunks.size();i++) {
            size[i]=chunks.get(i).size();
            if(max<chunks.get(i).size())
                max=chunks.get(i).size();
        }
        for(int i=1;i<=max;i++) {
            for(int k=0;k<chunks.size();k++) {
                if(chunks.get(k).size()==i) {
                    aList.add(chunks.get(k));
                }
            }
            buckets.put(i, new ArrayList<ArrayList<Item>>(aList));
            aList.clear();
        }
	   	return buckets;
	}




    /**
     *
     * This function takes in a bucket(ArrayList of ArrayList) and returns an ArrayList which has the chunks in
     * the bucket merged.
     *
     * for example
     * bucket = {
     *          {2,4,5,6},
     *          {1,2,4,5}
     *         }
     *
     * return value = {1,2,2,4,4,5,5,6}
     *
     *
     * @param bucket
     * @return merged and sorted list
     *
     */
    public ArrayList<Item> mergeChunk(ArrayList<ArrayList<Item>> bucket) {
        // TODO: part 1
        ArrayList<Item>res=new ArrayList<Item>();
        if(bucket.size()>0)
            res.addAll(bucket.get(0));
        for(int i=1;i<bucket.size();i++) {
            for (int index1 = 0, index2 = 0; index2 < bucket.get(i).size(); index1++) {
                if (index1 == res.size() || greaterThan(res.get(index1), bucket.get(i).get(index2))) {
                    res.add(index1, bucket.get(i).get(index2++));
                }
            }
        }
        return res;
    }
    public boolean greaterThan(Item i, Item j) {
        return i.compareTo(j) > 0;
    }
    public boolean lessThan(Item i, Item j) {
        return i.compareTo(j) < 0;
    }
    public boolean equal(Item i, Item j) {
        return i.compareTo(j) == 0;
    }


    /**
     *
     * This is the main function to run the gritSort class to help with debugging.
     *
     * @param args
     */
    public static void main(String[] args) {
        Sorting<Integer> s = new Sorting<>();
        ArrayList<Integer> A = new ArrayList<Integer>();
        Integer[] K = {3,5,6,8,10,2,4,5,6,1,2,4,5};

        for (Integer k : K) {
            A.add(k);
        }
        s.print(A);

    }

}
