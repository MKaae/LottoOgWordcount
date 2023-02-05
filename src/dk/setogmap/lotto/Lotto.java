package dk.setogmap.lotto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Lotto {
    static HashSet<Integer> lotteryTicket = new HashSet<>();
    static HashMap<Integer,Integer> lotteryNumbers = new HashMap<>();
    static Random random = new Random();
    public static void main(String[] args){
        generateLottoryNumbers();
        generateLotteryTicket();
        checkLotteryTicket();
    }
    public static void generateLotteryTicket(){
        boolean success = false;
        while(!success){
            int ticketNumber = random.nextInt(70)+1;
            lotteryTicket.add(ticketNumber);
            if(lotteryTicket.size() == 7){
                success = true;
            }
        }
    }
    public static void generateLottoryNumbers(){
        for(int mapKey = 1; mapKey < 10; mapKey++){
            int lotteryNumber = random.nextInt(70)+1;
            if(!lotteryNumbers.containsValue(lotteryNumber)) {
                lotteryNumbers.put(mapKey, lotteryNumber);
            }
            else{
                mapKey--;
            }
        }
    }
    public static void checkLotteryTicket(){
        int winningNumberCount = 0;
        int bonusNumberCount = 0;
        for(int winningNumbers = 1; winningNumbers <= lotteryNumbers.size()-2; winningNumbers++){
            if(lotteryTicket.contains(lotteryNumbers.get(winningNumbers))){
                winningNumberCount++;
            }
        }
        for(int bonusNumbers = lotteryNumbers.size()-2; bonusNumbers <= lotteryNumbers.size(); bonusNumbers++){
            if(lotteryTicket.contains(lotteryNumbers.get(bonusNumbers))){
                bonusNumberCount++;
            }
        }
        System.out.println("Amount of winning numbers: " + winningNumberCount);
        System.out.println("Amount of bonus numbers: " + bonusNumberCount);
    }
}
