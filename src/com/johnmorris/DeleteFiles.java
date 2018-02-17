package com.johnmorris;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by jmorris on 2/17/18
 */
public class DeleteFiles {
    public static void main(String[] args) {
        try {
            Path fileToDelete = FileSystems.getDefault().getPath("Examples", "Dir1", "file2copy.txt");
            Files.deleteIfExists(fileToDelete);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
