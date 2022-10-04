package inv_man_sys_application;

import javafx.beans.property.*;
import javafx.collections.*;

public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int max, min;

    private SimpleIntegerProperty propID;
    private SimpleStringProperty propName;
    private SimpleDoubleProperty propPrice;
    private SimpleIntegerProperty propInv;
    private SimpleIntegerProperty propMax;
    private SimpleIntegerProperty propMin;

    public Product(){
        propID = new SimpleIntegerProperty();
        propName = new SimpleStringProperty();
        propPrice = new SimpleDoubleProperty();
        propInv = new SimpleIntegerProperty();
        propMax = new SimpleIntegerProperty();
        propMin = new SimpleIntegerProperty();
    }


    public Product(int propID, String propName, double propPrice, int stock, int propMin, int propMax){

        setID(propID);
        setPropName(propName);
        setPropPrice(propPrice);
        setPropInv(stock);
        setPropMax(propMax);
        setPropMin(propMin);
    }

    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part selectedAspart){
        associatedParts.remove(selectedAspart.getPartID());
        return true;
    }

    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }


    // observable
    public IntegerProperty productIDProperty() {
        return propID;
    }

    public StringProperty productNameProperty() {
        return propName;
    }

    public DoubleProperty productPriceProperty() {
        return propPrice;
    }

    public IntegerProperty productInvProperty() {
        return propInv;
    }

    public IntegerProperty productMaxProperty() {
        return propMax;
    }

    public IntegerProperty productMinProperty() {
        return propMin;
    }
    // observable

    // get
    public int getID() {
        return productIDProperty().get();
    }

    public String getPropName() {
        return productNameProperty().get();
    }

    public double getPropPrice() {
        return productPriceProperty().get();
    }

    public int getPropInv() {
        return productInvProperty().get();
    }

    public int getPropMin() {
        return productMinProperty().get();
    }

    public int getPropMax(){
        return productMaxProperty().get();
    }
    //get

    //set
    public void setID(int id) {
        productIDProperty().set(id);
    }

    public void setPropName(String propName) {
        productNameProperty().set(propName);
    }

    public void setPropPrice(Double propPrice) {
        productPriceProperty().set(propPrice);
    }

    public void setPropInv(int propInv) {
        productInvProperty().set(propInv);
    }

    public void setPropMin(int propMin) {
        productMinProperty().set(propMin);
    }

    public void setPropMax(int propMax) {
        productMaxProperty().set(propMax);
    }

    public void setPrice(int max){
        productPriceProperty().set(max);
    }
    //set


}
