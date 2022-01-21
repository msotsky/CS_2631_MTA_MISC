//package Midterm;
//Maxime Sotsky
public class HashTable {
    private WordCountPair[] wcp; //the actual hashtable
    private int tableSize;//table size
    private int numPairs; //number of (key,value) pairs stored in the table

    //isPrime method
    public static boolean isPrime(int x){
        if(x <= 1)
            return false;
        else{
            for(int i = 2; i <= Math.sqrt(x);i++){
                if(x % i == 0){
                    return false;
                }
            }
        }
        return true;
    }
    //constructor
    public HashTable(int tableSize){
        if(isPrime(tableSize) == true && tableSize % 2 == 1){
            
            //HashTable ht = new HashTable(tableSize); //allocate new hashTable of this size ?
            wcp = new WordCountPair[tableSize];
            this.tableSize = tableSize;
        }
        if(isPrime(tableSize) == false || tableSize % 2 != 1){
            boolean f = false;
            while(f == false){
                tableSize++;
                if(isPrime(tableSize) == true && tableSize % 2 == 1)
                    f = true;
            }
            //HashTable ht = new HashTable(tableSize); //allocate new hashTable of this size ?
            wcp = new WordCountPair[tableSize];
            this.tableSize = tableSize;
        }
    }
    
    //accessor method getNumPairs
    public int getNumPairs(){
        return numPairs;
    }

    //get method
    public Integer get(Word key){
        if(key == null)
            throw new IllegalArgumentException("Key is null");
        else{
            int H = key.hashCode() % tableSize;

            if(wcp[H] == null)
                return null;

            for(int i = (H * 2) % tableSize; i != H; i = (i + H) % tableSize){
                if(wcp[i] == null)
                    return null;
                if (wcp[i].getWord().equals(key)){
                    return wcp[i].getCount();
                }
                    
            }
            return null;
        }
    }

    //put method
    //put method should visit locations just like get does -quadratic probing
    public void put(Word key, Integer value){
        if(key == null || value == null)
            throw new IllegalArgumentException("key and value cannot be null");

        int H = key.hashCode() % tableSize;
        
        WordCountPair nwcp = new WordCountPair(key,value);
        for(int i = (H * 2) % tableSize; i != H; i = (i + H) % tableSize){
            if (wcp[i] != null && wcp[i].getWord().equals(key)){
                wcp[i] = new WordCountPair(wcp[i].getWord(),value);
                wcp[i].incrementCount();

            }    
        }
        for(int i = (H * 2) % tableSize; i != H; i = (i + H) % tableSize){
            if(wcp[i] == null){
                wcp[i] = nwcp;
                numPairs++;
                if(numPairs > (tableSize/2))
                    enlargeAndRehash();
            }
        }
    }

    //enlargeAndRehash method
    public void enlargeAndRehash(){
        tableSize *= 2;
        boolean f = false;
        while(f == false){
            tableSize++;
            if(isPrime(tableSize) == true && tableSize % 2 == 1)
                f = true;
        }

        WordCountPair[] temp = wcp.clone();
        wcp = new WordCountPair[tableSize];

        for(WordCountPair p : temp){
            if(p != null){
                int H = p.getWord().hashCode() % tableSize;
                for(int i = (H * 2) % tableSize; i != H; i = (i + H) % tableSize){
                    if(wcp[i] == null){
                        wcp[i] = p;
                    }
                }
            }
        }
    }
}
