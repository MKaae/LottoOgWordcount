package dk.setogmap.wordcount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCount {
    public static void main(String[] args){
        List<String> wordList = new ArrayList<>(){{
           add("a");
           add("A");
           add("Ukraine");
           add("Putin");
           add("Biden");
        }};
        try {
            System.out.println(wordCountFromWebpage(wordList));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static Map<String,Integer> wordCountFromWebpage(List<String> list) throws IOException {
        Map<String,Integer> updatedMap = new HashMap<>();
        URL connection = new URL("https://dr.dk/");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.openStream()));
        String inputLine = "";
        while(inputLine != null){
            wordCounterFrequency(inputLine, list, updatedMap);
            inputLine = reader.readLine();
        }
        reader.close();
        return updatedMap;
    }

    private static Map<String, Integer> wordCounterPerLine(String input, List<String> words, Map<String, Integer> tempMap) {
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
    private static Map<String,Integer> wordCounterFrequency(String input, List<String> words, Map<String,Integer> tempMap){
        String[] tempStr = words.toArray(new String[0]);
        int tempInt;
        for(String testStr : tempStr){
                tempInt = frequency(input, testStr);
                if(!tempMap.containsKey(testStr)){
                    tempMap.put(testStr,0);
                }
                else{
                    tempMap.put(testStr, tempMap.get(testStr) + tempInt);
                }
        }
        return tempMap;
    }
    public static int frequency(String input, String test){
        int amount = 0;
        int inputLength = input.length();
        int testLength = test.length();
        for(int index = 0; index < inputLength-testLength; index++){
            if(input.substring(index, index+testLength).equals(test)){
                amount++;
            }
        }
        return amount;
    }
}
