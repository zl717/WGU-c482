package inv_man_sys_application;

import javafx.beans.property.*;

public class Outsourced extends Part {

    private SimpleStringProperty companyName;

    public Outsourced() {
        super();
        companyName = new SimpleStringProperty();
    }

    public String getCompanyName(){
        return companyName.get();
    }

    public void setCompanyName(String name){
        companyName.set(name);
    }
}
