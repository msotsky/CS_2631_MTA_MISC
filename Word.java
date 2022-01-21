//package Midterm;
//Maxime Sotsky
import java.util.*;
public class Word {
    private String w;
    
    //constructor
    private Word(String w){
        this.w = w;
    }

    //makeword method
    //a good string = length is non zero and contains only letter of the english alphabet
    //if input is good, then construct a word object containing the lowercase version of that word
    //if input is not good, then null
    //return word
    public static Word makeWord(String word){
        //check if length is 0
        if(word.length() == 0)
            return null;
        //check if contains only letter in the english alphabet
        //checks each character in the array for non-letters
        char[] wordArr = word.toCharArray();
        for(char ch : wordArr){
            if(Character.isLetter(ch) == false)
                return null;
        }
        //word is good
        Word w = new Word(word.toLowerCase());
        return w;

    }

    //hashCode method (overriden)
    @Override
    public int hashCode(){
        //return the product of the ASCII values of the chracters in the string instance variable
        int x = 1;
        char[] charArr = w.toCharArray();
        for(int i = 0; i < charArr.length;i++){
            x *= charArr[i];
        }
        //If MIN_VALUE was negated it wouldn't change the value.
        //this is because the absolute value of this integer is greater than MAX_VALUE
        //By negating MIN_MAX it would be out of reach by 1 to the positive side and loop backwards to the negative
        //MAX_VALUE + 1 = MIN_MAX
        if(x == Integer.MIN_VALUE)
            x = 0;

        if(x < 0)
            x *= -1;

        return x;
    }

    //toString method (overriden)
    //instead of getWord() and getString accessors
    @Override
    public String toString(){
        return this.w;
    }

    //equals method (overriden)
    @Override
    public boolean equals(Object obj){

        if(!( obj instanceof Word))
            return false;
        if (this.toString().equals(obj.toString()))
            return true;
        else
            return false;        
    }

}
