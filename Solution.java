package com.codegym.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Threads and bytes

*/

public class Solution {

    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String name;
        while (rd.ready()) {
            name = rd.readLine();
            if(!name.equals("exit")) {
                ReadThread thread = new ReadThread(name);
                thread.start();
            } else {
                break;
            }
        }
        rd.close();
    }


    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;

        }

        @Override
        public void run() {
            try {
                resultMap.put(fileName, findMostFrequent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Implement file reading here
        public int findMostFrequent() throws IOException {
            FileInputStream inputStream = new FileInputStream(fileName);
            HashMap<Integer, Integer> bytesRepetitions = new HashMap<>();
            while (inputStream.available() > 0) {
                int data = inputStream.read();
                if (!bytesRepetitions.containsKey(data)) {
                    bytesRepetitions.put(data, 1);
                } else {
                    bytesRepetitions.put(data, bytesRepetitions.get(data) + 1);
                }
            }
            inputStream.close();

            int mostFrequentByte = 0;
            int max = 0;
            for (Map.Entry<Integer, Integer> entry : bytesRepetitions.entrySet()) {
                if (entry.getValue() > max) {
                    mostFrequentByte = entry.getKey();
                    max = entry.getValue();
                }
            }
            return mostFrequentByte;
        }
    }
}
