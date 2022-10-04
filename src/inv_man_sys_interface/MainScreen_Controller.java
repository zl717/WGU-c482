package inv_man_sys_interface;

import inv_man_sys_application.*;
import javafx.collections.*;
import javafx.fxml.*;

import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class MainScreen_Controller implements Initializable {

    public TableView<Part> parts_Table;
    public TableColumn<Part, Integer> parts_ID;
    public TableColumn<Part, String> parts_Name;
    public TableColumn<Part, Integer> parts_Inv;
    public TableColumn<Part, Double> parts_Cost;

    public static int modIndex, deleteIndex, modIndexProduct, deleteIndexProd;
    public Part modPart;
    public Product modProduct = new Product();

    public Button deleteButton;
    public TextField partsField;
    public Button partsSearchButton;
    public Button clearPartsSearch;
    //end parts

    //begin products

    public TableView<Product> products_Table;
    public TableColumn<Product, Integer> products_ID;
    public TableColumn<Product, String> products_Name;
    public TableColumn<Product, Integer> products_Inv;
    public TableColumn<Product, Double> products_Cost;

    public TextField productSearchField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        parts_ID.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        parts_Name.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        parts_Inv.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        parts_Cost.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        updateParts();

        products_ID.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        products_Name.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        products_Inv.setCellValueFactory(cellData -> cellData.getValue().productInvProperty().asObject());
        products_Cost.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
        updateProducts();

    }

    public void clearPartsSearch(ActionEvent e){
        partsField.setText("");
        updateParts();
    }

    public void partsSearchButton(ActionEvent e){

        ObservableList<Part> searchedResultList = FXCollections.observableArrayList();
        Part temp;
        String search = partsField.getText();
        int searchIndex;

        if (search.matches("\\d+")){
            searchIndex = Integer.parseInt(search);
            if (searchIndex <= Inventory.getAllParts().size() && searchIndex >= 0){
                temp = Inventory.lookupPart(searchIndex);
                searchedResultList.add(temp);
                parts_Table.setItems(searchedResultList);
            }
        }

        else{
            parts_Table.setItems(Inventory.lookupPart(search));
        }

    }


    public void clearProductSearch(ActionEvent e){
        productSearchField.setText("");
        updateProducts();
    }

    public void productSearchButton(ActionEvent e){

        ObservableList<Product> searchedResultList = FXCollections.observableArrayList();
        Product temp;
        String search = productSearchField.getText();
        int searchIndex;

        if (search.matches("\\d+")){
            searchIndex = Integer.parseInt(search);
            if (searchIndex <= Inventory.getAllProducts().size() && searchIndex >= 0){
                temp = Inventory.lookupProduct(searchIndex);
                searchedResultList.add(temp);
                products_Table.setItems(searchedResultList);
            }
        }

        else{
            products_Table.setItems(Inventory.lookupProduct(search));
        }

    }


    public void updateParts(){
        parts_Table.setItems(Inventory.getAllParts());
    }

    public void updateProducts(){
        products_Table.setItems(Inventory.getAllProducts());
    }

    public static int getModifyID(){
        return modIndex;
    }

    public static int getModifyIDProduct(){
        return modIndexProduct;
    }

    public void addPart_Display(ActionEvent e) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage addPartStage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    public void deletePart(ActionEvent e){

        modPart = parts_Table.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Deleting Part ID: " + modPart.getPartID() + " - Name: " + modPart.getName());
        alert.setContentText("Do you wish to proceed?");
        alert.showAndWait();

        deleteIndex = Inventory.getAllParts().indexOf(modPart);
        Inventory.getAllParts().remove(deleteIndex);
        System.out.println(deleteIndex);
        updateParts();

    }

    public void deleteProduct(ActionEvent e){

        modProduct = products_Table.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Deleting Product ID: " + modProduct.getID() + " - Name: " + modProduct.getPropName());
        alert.setContentText("Do you wish to proceed?");
        alert.showAndWait();

        deleteIndexProd = Inventory.getAllProducts().indexOf(modProduct);
        Inventory.getAllProducts().remove(deleteIndexProd);
        System.out.println(deleteIndexProd);
        updateProducts();

    }

    public void modifyPart_Display(ActionEvent e) throws IOException {
        modPart = parts_Table.getSelectionModel().getSelectedItem();
        modIndex = Inventory.getAllParts().indexOf(modPart);
        Parent addPartParent = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage addPartStage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    public void AddProduct_Display(ActionEvent e) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage addPartStage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    public void ModifyProduct_Display(ActionEvent e) throws IOException {
        modProduct = products_Table.getSelectionModel().getSelectedItem();
        modIndexProduct = Inventory.getAllProducts().indexOf(modProduct);
        Parent addPartParent = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage addPartStage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    public void exit(ActionEvent exit) {
        System.exit(0);
    }
}
