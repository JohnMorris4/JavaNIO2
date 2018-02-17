package com.johnmorris;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by jmorris on 2/17/18
 */
public class CopyFiles extends SimpleFileVisitor<Path> {

    private Path sourceRoot;
    private Path targetRoot;

    public CopyFiles(Path sourceRoot, Path targetRoot) {
        this.sourceRoot = sourceRoot;
        this.targetRoot = targetRoot;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Error Accessing Directory" + file.toAbsolutePath() + " " + exc.getMessage());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path relativitedPath = sourceRoot.relativize(dir);
        System.out.println("Relativized Path= " + relativitedPath);
        Path copyDir = targetRoot.resolve(relativitedPath);
        System.out.println("Resolved Path= " + copyDir);

        try {
            Files.copy(dir, copyDir);
        } catch (IOException e){
            System.out.println(e.getMessage());
            return FileVisitResult.SKIP_SUBTREE;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path relativitedPath = sourceRoot.relativize(file);
        System.out.println("Relativized Path= " + relativitedPath);
        Path copyDir = targetRoot.resolve(relativitedPath);
        System.out.println("Resolved Path= " + copyDir);

        try {
            Files.copy(file, copyDir);
        } catch (IOException e){
            System.out.println(e.getMessage());

        }
        return FileVisitResult.CONTINUE;
    }
}
