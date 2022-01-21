//package Midterm;
//Maxime Sotsky
public class WordCountPair {
    private Word w;
    private Integer count;

    //constructor
    public WordCountPair(Word w, Integer count){
        if(w == null || count == null)
            throw new IllegalArgumentException("Arguments cannot be null");
        else{
            this.w = w;
            this.count = count;
        }
    }
    //Accessor method for word
    public Word getWord(){
        return w;
    }
    //Accessor method for count
    public Integer getCount(){
        return count;
    }

    //mutator-ish method incrementing count
    public void incrementCount(){
        this.count++;
        /*
        int x = this.count.intValue();
        x++;
        Integer c = Integer.valueOf(x);
        this.count = c;
        */
        
        
        
        
    }
}
