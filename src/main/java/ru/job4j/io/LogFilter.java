package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader("data/log.txt"))) {
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                    String[] splitLine = line.split(" ");
                    if ("404".equals(splitLine[splitLine.length - 2])) {
                        rsl.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return rsl;
        }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}
