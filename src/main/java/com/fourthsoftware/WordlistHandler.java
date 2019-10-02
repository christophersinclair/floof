package com.fourthsoftware;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class WordlistHandler {
    private String dir;
    private List<String> wordList;

    public WordlistHandler() {

    }

    public List<String> readWordlist(String filename) {


        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            List<String> words = new ArrayList<String>();
            String line = null;
            while ((line = br.readLine()) != null) {
                line.replace("\n", "");
                words.add(line);
            }
            wordList = words;
        } catch (IOException ioException) {
            System.out.println("Error occurred reading file: " + ioException.getMessage());
        }
        return wordList;
    }
}
