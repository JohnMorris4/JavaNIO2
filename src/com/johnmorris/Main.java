package com.johnmorris;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("WorkingDirectoryFile.txt");
        printFile(path);
        Path filePath = Paths.get(".","files", "SubDirectoryFile.txt");
        printFile(filePath);
//        filePath = Paths.get("../PathFile.txt");
//        printFile(filePath);
//        filePath = Paths.get("/home/jmorris/IdeaProjects", "PathFile.txt");
//        printFile(filePath);
        filePath = Paths.get(".");
        System.out.println(filePath.toAbsolutePath());
        Path path2 = FileSystems.getDefault().getPath(".", "files", "..", "files","SubDirectoryFile.txt");
        System.out.println(path2.normalize().toAbsolutePath());
        printFile(path2.normalize());

    }
    private  static void printFile(Path path){
        try (BufferedReader fileReader = Files.newBufferedReader(path)){
            String line;
            while((line = fileReader.readLine()) != null) {
                System.out.println(line);

            }
            System.out.println("\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
