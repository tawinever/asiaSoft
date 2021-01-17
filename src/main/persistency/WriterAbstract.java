package src.main.persistency;

import src.main.entity.NumContainer;
import src.main.entity.SmartNums;

public abstract class WriterAbstract {

    // We didn't make it abstract void in case some of implementations haven't got this feature
    public void logSmartNums( NumContainer<SmartNums> numContainer){
        System.out.println("Log NumSmartNums not yet implemented in " + this.getClass()) ;
    };
}
