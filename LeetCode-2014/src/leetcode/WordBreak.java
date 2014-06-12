package leetcode;

import java.util.HashSet;
import java.util.Set;
/**
 * Date: June 11, 2014
 * 
 * Given a string s and a dictionary of words dict, 
 * determine if s can be segmented into a space
 * -separated sequence of one or more dictionary words.
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 * @author Lei
 *
 */
public class WordBreak {
	 public static boolean wordBreak(String s, Set<String> dict) {
		 if (s==null || dict == null) 
			 return false;
		 int len = s.length();
		 boolean[] dpArr = new boolean[len];
		 String curStr;
		 // scan the sentence and build up the dpArr
		 for(int i = 0; i < len; i++){
			 curStr = s.substring(0,i+1);
			 // if the curStr is in the dictionary,
			 // set the corresponding dpArr entry to true
			 if(dict.contains(curStr)){
				 dpArr[i]=true;
			 }else{
				 // if the whole curStr is not in the dictionary,
				 // try to decompose it by using the dictionary
				 int k = i-1;
				 // starting from i-1
				 while(k>=0){
					 // if the substring [0,k] is breakable and the substring
					 // [k+1, i] is also in the dictionary
					 if(dpArr[k] && dict.contains(s.substring(k+1, i+1))){
						 dpArr[i]=true;
						 break;
					 }else{
						 k--;
					 }
				 }
			 }
		 }
		 return dpArr[len-1];
	 }
	 
	 public static void main(String[] args){
		 Set<String> dict = new HashSet<String>();
		 dict.add("leet");
		 dict.add("code");
		 System.out.println(WordBreak.wordBreak("leetcodecodeleet", dict));
		 
	 }
}
