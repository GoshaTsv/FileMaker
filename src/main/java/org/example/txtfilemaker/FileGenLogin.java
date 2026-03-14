package org.example.txtfilemaker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
}
