package src.main;

import src.main.entity.NumContainer;
import src.main.entity.SmartNums;
import src.main.persistency.WriterAbstract;
import src.main.persistency.impl.FileWriterSingleton;
import src.main.persistency.impl.XmlWriterSingleton;

public class driver {
    public static void main(String[] args) {
        int limit = 220;
        if (args.length == 0)
            System.out.println("You need use enter integer as an argument. Using default value");
        else {
            try {
                limit = Integer.parseInt(args[0]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Bad program parameter, using default value.");
            }
        }

        System.out.println("Current limit is " + limit);
        printSquares(limit);
    }

    private static void printSquares(int limit) {
        int cnt = 0;
        int cur;
        NumContainer<SmartNums> numContainer = new NumContainer<>();

        // filling container
        while(cnt * cnt <= limit) {
            cur = cnt * cnt;
            System.out.print( cur + " ");
            numContainer.AddNum(new SmartNums(cur));
            cnt++;
        }

        WriterAbstract writer = XmlWriterSingleton.getInstance();

        // Пример полиморфизма когда незная какой выводчик работает, мы пишем наши данные
        writer.logSmartNums(numContainer);

    }
}
