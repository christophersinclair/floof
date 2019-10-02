package com.fourthsoftware;

import com.fourthsoftware.Fuzzer;
import com.fourthsoftware.WordlistHandler;
import java.util.List;
import java.util.Map;

public class Floof {

    public static void main(String[] args) {
        String filename = args[0];
        String url = args[1];


        // Pass filename to the word list handler
        WordlistHandler wordlistHandler = new WordlistHandler();
        List<String> wordList = wordlistHandler.readWordlist(filename);

        // Pass the word list to the fuzzer
        Fuzzer fuzzer = new Fuzzer(url, wordList);
        Map<String, Integer> results = fuzzer.startFuzzing();

        StringBuilder mapAsString = new StringBuilder("{");
        for (String key : results.keySet()) {
            mapAsString.append(key + ": " + results.get(key) + "\n");
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
        System.out.println(mapAsString.toString());

    }
}
