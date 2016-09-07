package CS3213;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
/**
 * Created by junchao on 8/23/2014.
 */
public class Main {

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter movie titles (terminate input by entering empty line) ");

        List<String> inputs = new ArrayList<String>();
        String userInput = sc.nextLine();
        while (!userInput.isEmpty()) {
            inputs.add(userInput);
            userInput = sc.nextLine();
        }
        System.out.println("Enter require words (terminate input by entering empty line)");
        ArrayList<String> requireWords=new ArrayList<String>();
        String inputWordToRequire=sc.nextLine();
        while(!inputWordToRequire.isEmpty()){
        	requireWords.add(inputWordToRequire);
        	inputWordToRequire=sc.nextLine();
        }

        System.out.println("Enter words to ignore (terminate input by entering empty line) ");
        String inputWordToIgnore = sc.nextLine();
        WordsToIgnore wordsToIgnore = WordsToIgnore.getWordsToIgnore();
        while (!inputWordToIgnore.isEmpty()) {
            wordsToIgnore.addWordToIgnore(inputWordToIgnore);
            inputWordToIgnore = sc.nextLine();
        }

        Alphabetizer alphabetizer = new Alphabetizer();
        for (String str : inputs) {
            CircularShift shifter = new CircularShift(str);
            alphabetizer.addLines(shifter.getCircularShifts());
        }

        String[] result = alphabetizer.getSortedLines();
        ArrayList<String>output=new ArrayList<String>();
        Collections.sort(requireWords);
        for(int i=0;i<requireWords.size();i++){
        	String check=requireWords.get(i);
        	int length=check.length();
        	for(int j=0;j<result.length;j++){
        		if(check.equalsIgnoreCase(result[j].substring(0, length))){
        			output.add(result[j]);
        		}
        	}
        }
        StringBuilder builder = new StringBuilder();
        String separator = System.lineSeparator();
        for (String str : output) {
            builder.append(str).append(separator);
        }
        System.out.print(builder.toString());

        long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime) );
        System.exit(0);
    }
}
