package dk.setogmap.wordcount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class WordCount {
    public static void main(String[] args){
        ArrayList<String> wordList = new ArrayList<>(){{
           add("Ukraine");
           add("Covid");
           add("DF");
           add("Putin");
           add("Biden");
        }};
        try {
            System.out.println(wordCountFromWebpage(wordList));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static HashMap<String,Integer> wordCountFromWebpage(ArrayList<String> list) throws IOException {
        HashMap<String,Integer> updatedMap = new HashMap<>();
        URL connection = new URL("https://dr.dk/");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.openStream()));
        String inputLine = "";
        while(inputLine != null){
            wordCounter2(inputLine, list, updatedMap);
            inputLine = reader.readLine();
        }
        reader.close();
        return updatedMap;
    }

    private static HashMap<String, Integer> wordCounter(String input, ArrayList<String> words, HashMap<String, Integer> tempMap) {
        String[] tempStr = words.toArray(new String[0]);
        for(String testStr: tempStr){
            if(input.contains(testStr)){
                if(tempMap.containsKey(testStr)){
                    tempMap.put(testStr, tempMap.get(testStr)+1);
                }
                else{
                    tempMap.put(testStr,1);
                }
            }
        }
        return tempMap;
    }
    private static HashMap<String,Integer> wordCounter2(String input, ArrayList<String> words, HashMap<String,Integer> tempMap){
        String[] tempStr = words.toArray(new String[0]);
        String[] strToTest = input.split("[,.-0123456789;:<>=@()?´|/&%! ]");
        for(String listStr: tempStr){
            for(String inputStr: strToTest){
                if(inputStr.contains(listStr)){
                    if(tempMap.containsKey(listStr)){
                        tempMap.put(listStr, tempMap.get(listStr) +1);
                    }
                    else{
                        tempMap.put(listStr, 1);
                    }
                }
            }
        }
        return tempMap;
    }
}
