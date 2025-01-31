package ru.job4j.io;
import java.util.HashMap;
import java.util.Map;
public class ArgsName {
    private final Map<String, String> values = new HashMap<>();
    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }
    private void parse(String[] args) {
        for (String pars : args) {
            if (!pars.startsWith("-")) {
                throw new IllegalArgumentException("Error: This argument '" + pars + "' does not start with a '-' character");
            }
            if (!pars.contains("=")) {
                throw new IllegalArgumentException("Error: This argument '" + pars + "' does not contain an equal sign");
            }
            if (pars.split("=", 2)[0].length() < 2) {
                throw new IllegalArgumentException("Error: This argument '" + pars + "' does not contain a key");
            }
            if (pars.split("=", 2)[1].isEmpty()) {
                throw new IllegalArgumentException("Error: This argument '" + pars + "' does not contain a value");
            }
            values.put(pars.split("=", 2)[0].split("-")[1], pars.split("=", 2)[1]);
        }
    }
    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}