package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
            try (FileInputStream input = new FileInputStream("data/even.txt")) {
                StringBuilder text = new StringBuilder();
                int read;
                while ((read = input.read()) != -1) {
                    text.append((char) read);
                }
                String[] lines = text.toString().split(System.lineSeparator());
                for (String line : lines) {
                        int number = Integer.parseInt(line);
                        if (number % 2 == 0) {
                            System.out.println(number + " число чётное");
                        } else {
                            System.out.println(number + " число нечетное");
                        }
                }
                System.out.println(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }