package com.johnmorris;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by jmorris on 2/17/18
 */
public class FileAttributes {
    public static void main(String[] args) {
        try {
            Path filePath = FileSystems.getDefault().getPath("Examples", "file1.txt");
            long size = Files.size(filePath);
            System.out.println("Size= "+ size);
            System.out.println("Last Modified Time= " + Files.getLastModifiedTime(filePath));

            BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
            System.out.println("Size = " + attrs.size());
            System.out.println("LMT" + attrs.lastModifiedTime());
            System.out.println(attrs.fileKey());
//            Path fileToCreate = FileSystems.getDefault().getPath("Examples", "file2.txt");
//            Files.createFile(fileToCreate);
//            Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir4");
//            Files.createDirectory(dirToCreate);
//            Path dirsToCreate = FileSystems.getDefault().getPath("Examples", "Dir2/Dir3/Dir4/Dir5/Dir6");
//            Files.createDirectories(dirsToCreate);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
