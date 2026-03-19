package org.example.txtfilemaker;

import java.io.*;
import java.util.ArrayList;

public class FileGenLogin {
    public static boolean genFile(File file, String fileCont) {
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                    System.err.println("Нихуя нету у тебя папки");
                return false;
            }
        }

        if (parentDir != null && parentDir.exists() && !parentDir.canWrite()) {
            System.err.println("У тебя нет прав");
            return false;
        }
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(fileCont);
            fw.close();
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка при создании файла: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<String> readFile(File file) throws IOException {
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                System.err.println("Нихуя нету у тебя папки");
                return null;
            }
        }

        if (parentDir != null && parentDir.exists() && !parentDir.canWrite()) {
            System.err.println("У тебя нет прав");
            return null;
        }
        String line;
        var br = new BufferedReader(new FileReader(file));
        var stringBuilder = new StringBuilder();
        while(br.ready() && (line = br.readLine()) != null){
            stringBuilder.append(line + "\n");
        }
        String filename = file.getName();
        br.close();
        ArrayList<String> finList = new ArrayList<>();
        finList.add(stringBuilder.toString());
        finList.add(filename.substring(0, filename.lastIndexOf(".")));
        finList.add(filename.substring(filename.lastIndexOf(".")));
        return finList;
    }
}
