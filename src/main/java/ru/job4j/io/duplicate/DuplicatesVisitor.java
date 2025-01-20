package ru.job4j.io.duplicate;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            FileProperty files = new FileProperty(attrs.size(), file.getFileName().toString());
            if (map.containsKey(files)) {
                map.get(files).add(file);
            }
            map.putIfAbsent(files, new ArrayList<Path>(Arrays.asList(file)));
        }
        return super.visitFile(file, attrs);
    }


    public void rsl() {
        for (FileProperty filePropertyp : map.keySet()) {
            if (map.get(filePropertyp).size() > 1) {
                for (Path patch : map.get(filePropertyp)) {
                    System.out.print(patch.toString());
                }
            }
        }
    }

}