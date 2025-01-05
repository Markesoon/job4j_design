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

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)
                ))) {
            data.forEach(output::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/logout.txt");
    }
}
