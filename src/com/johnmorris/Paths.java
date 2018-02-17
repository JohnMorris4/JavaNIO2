package com.johnmorris;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by jmorris on 2/17/18
 */
public class Paths{
    public static void main(String[] args) {
        try {


            Path fileToMove = FileSystems.getDefault().getPath("Examples", "file1.txt");
            Path destination = FileSystems.getDefault().getPath("Examples","file2.txt");
            Files.move(fileToMove, destination);
//            Path sourceFile = FileSystems.getDefault().getPath("Examples", "file1.txt");
//            Path copyFile = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
//            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
//
//            sourceFile = FileSystems.getDefault().getPath("Examples", "Dir1");
//            copyFile = FileSystems.getDefault().getPath("Examples", "Dir4");
//            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
