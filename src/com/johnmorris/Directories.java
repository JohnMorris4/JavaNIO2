package com.johnmorris;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Paths;

/**
 * Created by jmorris on 2/17/18
 */
public class Directories {
    public static void main(String[] args) {
        DirectoryStream.Filter<Path> filter = p -> Files.isRegularFile(p); //Lambda Expression
//        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
//            public boolean accept(Path path) throws IOException {
//                return (Files.isRegularFile(path));
//            }
//        };
        Path directory = FileSystems.getDefault().getPath("FileTree" + File.separator + "Dir2");
        try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory, filter)){
                for (Path file : contents) {
                    System.out.println(file.getFileName());
                }
        } catch (IOException | DirectoryIteratorException e) {
            System.out.println(e.getMessage());
        }
        String separator = File.separator;
        System.out.println(separator);
        separator = FileSystems.getDefault().getSeparator();
        System.out.println(separator);
        try {
        Path tempFile =  Files.createTempFile("myapp", ".appext");
            System.out.println("Temp File = " + tempFile.toAbsolutePath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for(FileStore store : stores) {
            System.out.println("Volume/Drive: " +store);
            System.out.println("File Store: " + store.name());
        }
        System.out.println("***********************************************");
        Iterable<Path> rootPaths = FileSystems.getDefault().getRootDirectories();
        for(Path path : rootPaths) {
            System.out.println("Path: = " + path);
        }
        System.out.println("======WALKING THE FILE TREE=====");
        Path dir2Path = FileSystems.getDefault().getPath("FileTree" + File.separator + "Dir2");
        try {
            Files.walkFileTree(dir2Path, new PrintNames());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("====== Copy Dir2 to Dir4--- ========");
        Path copyPath = FileSystems.getDefault().getPath("FileTree" + File.separator + "Dir4" + File.separator + "Dir2Copy");
        try {
            Files.walkFileTree(dir2Path, new CopyFiles(dir2Path, copyPath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        File file = new File("/Examples/file.txt");
        Path convertPath = file.toPath();
        System.out.println("Converted path = " + convertPath);

        File parent = new File("/Examples");
        File resolvedFile = new File(parent, "dir/file.txt");
        System.out.println(resolvedFile.toPath());

        resolvedFile = new File("/Examples", "dir/file.txt");
        System.out.println(resolvedFile.toPath());

        Path parentPath = Paths.get("Examples");
        Path childRelativePath = Paths.get("dir/file.txt");
        System.out.println(parentPath.resolve(childRelativePath));



        File workingDir = new File("").getAbsoluteFile();
        System.out.println("PWD = " + workingDir.getAbsolutePath());

        System.out.println("---------------------------------");
        File dir2file = new File(workingDir, "/FileTree/Dir2");
        String[] dir2contents = dir2file.list();
        for (int i=0; i<dir2contents.length; i++) {
            System.out.println(dir2contents[i]);
        }
        System.out.println("===========================================");
        File[] dir2Files = dir2file.listFiles();
        for (int i=0; i<dir2Files.length; i++) {
            System.out.println(dir2Files[i].getName());
        }
    }
}
