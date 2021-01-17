package src.main.persistency.impl;

import src.main.entity.NumContainer;
import src.main.entity.SmartNums;
import src.main.persistency.WriterAbstract;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileWriterSingleton extends WriterAbstract {
    private static FileWriterSingleton writer = null;
    public static final String fileDir = "logs";

    private FileWriterSingleton() throws Exception {
        File dir = new File(fileDir);
        if (dir.mkdir() || dir.exists()) {
            System.out.println("Directory created successfully");
        } else {
            System.out.println("Couldn't create a directory: " + fileDir);
            throw new Exception("Couldn't create a directory " + fileDir);
        }
    }

    @Override
    public void logSmartNums(NumContainer<SmartNums> numContainer) {
        try {
            long curTimestamp = new Date().getTime();
            //create file
            File file = new File(fileDir + "/smartnums_" + curTimestamp + ".txt");
            file.createNewFile();
            FileWriter fw =new FileWriter(file);

            numContainer.nums.forEach(x -> {
                try {
                    fw.write(x.toString() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            fw.close();
        } catch(Exception e) {
            //TODO maybe we need some how handle it?
            System.out.println(e);
        }
        System.out.println("Smart Nums logged successfully.");
    }

    public static FileWriterSingleton getInstance() {
        if (writer == null) {
            try {
                writer = new FileWriterSingleton();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return writer;
    }
}
