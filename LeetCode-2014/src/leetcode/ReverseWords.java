package leetcode;
/**
 * Given an input string, reverse the string word by word.
 * For example,
 * Given s = "the sky is blue", return "blue is sky the".
 * click to show clarification.
 * Clarification:
 * What constitutes a word?
 * A sequence of non-space characters constitutes a word.
 * Could the input string contain leading or trailing spaces?
 * Yes. However, your reversed string should not contain leading or trailing spaces.
 * How about multiple spaces between two words?
 * Reduce them to a single space in the reversed string.
 * @author Lei
 *
 */
public class ReverseWords {
	/**
	 * Starting from the end of the string, find a word
	 * then append it to a StringBuilder.
	 * @param s
	 * @return
	 */
	public String reverseWords(String s) {
        if (s==null || s.length()==0)
               return s;
        int len = s.length();
        StringBuilder sb = new StringBuilder(); 
        int index = len-1;
        int startIndex, endIndex;
        while(index >= 0){
               // skip all the space
               while(index >=0 && s.charAt(index)== ' ')
                     index--;
               if(index<0)
                     break;
               // save the ending index for the current word
               endIndex = index;
               // starting to find the beginning for the current word
               while (index >=0 && s.charAt(index)!= ' '){
                     index--;
               }
               // no matter index < 0 or s.charAt(index)== ' '
               // the startIndex would be the current index plus 1
               startIndex = index+1;
               // if the current word is not the first word, then add a 
               // space
               if (sb.length() > 0)
                     sb.append(' ');
               // append the word
               sb.append(s.substring(startIndex, endIndex+1));
        }
        return sb.toString();
	}
	
	public static void  main(String[] args){
		ReverseWords tt = new ReverseWords();
		System.out.println("**"+tt.reverseWords("hulei")+"&&");
	}
}
