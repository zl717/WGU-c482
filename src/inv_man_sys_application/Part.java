package inv_man_sys_application;

import javafx.beans.property.*;

public abstract class Part {

    private SimpleIntegerProperty id; //= new SimpleIntegerProperty();
    private SimpleStringProperty name; //= new SimpleStringProperty();
    private SimpleDoubleProperty price; //= new SimpleDoubleProperty();
    private SimpleIntegerProperty inv; //= new SimpleIntegerProperty();
    private SimpleIntegerProperty max; //= new SimpleIntegerProperty();
    private SimpleIntegerProperty min; //= new SimpleIntegerProperty();

    public Part(){

        id = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        inv = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();

    }

    public Part(int id, String name, double price, int inv, int min, int max){

        setID(id);
        setName(name);
        setPrice(price);
        setInv(inv);
        setMin(min);
        setMax(max);

    }

    // observable
    public IntegerProperty partIDProperty() {
        return id;
    }

    public StringProperty partNameProperty() {
        return name;
    }

    public DoubleProperty partPriceProperty() {
        return price;
    }

    public IntegerProperty partInvProperty() {
        return inv;
    }

    public IntegerProperty partMaxProperty() {
        return max;
    }

    public IntegerProperty partMinProperty() {
        return min;
    }
    // observable

    // get
    public int getPartID() {
        return partIDProperty().get();
    }

    public String getName() {
        return partNameProperty().get();
    }

    public double getPrice() {
        return partPriceProperty().get();
    }

    public int getInv() {
        return partInvProperty().get();
    }

    public int getMin() {
        return partMinProperty().get();
    }

    public int getMax(){
        return partMaxProperty().get();
    }
    //get

    //set
    public void setID(int id) {
        partIDProperty().set(id);
    }

    public void setName(String name) {
        partNameProperty().set(name);
    }

    public void setPrice(Double price) {
        partPriceProperty().set(price);
    }

    public void setInv(int inv) {
        partInvProperty().set(inv);
    }

    public void setMin(int min) {
        partMinProperty().set(min);
    }

    public void setMax(int max) {
        partMaxProperty().set(max);
    }
    //set

}