package inv_man_sys_application;

import javafx.beans.property.*;

public class inHouse extends Part {

    private SimpleIntegerProperty machineID;

    public inHouse(){
        super();
        machineID = new SimpleIntegerProperty();
    }

    public int getMachineID(){
        return machineID.get();
    }

    public void setMachineID(int id){
        machineID.set(id);
    }
}
