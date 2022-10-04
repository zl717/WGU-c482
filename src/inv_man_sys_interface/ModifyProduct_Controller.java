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

import static inv_man_sys_application.Inventory.*;
import static inv_man_sys_interface.MainScreen_Controller.getModifyIDProduct;
import static inv_man_sys_interface.MainScreen_Controller.modIndexProduct;

public class ModifyProduct_Controller implements Initializable {

    //text fields
    public TextField modifyProductIDField;
    public TextField modifyProductNameField;
    public TextField modifyProductInvField;
    public TextField modifyProductPriceField;
    public TextField modifyProductMaxField;
    public TextField modifyProductMinField;

    //top table all parts
    public TableView<Part> allParts_ModifyProduct;
    public TableColumn<Part, Integer> allParts_ID;
    public TableColumn<Part, String> allParts_Name;
    public TableColumn<Part, Integer> allParts_Inv;
    public TableColumn<Part, Double> allParts_Price;

    //bottom table associated parts
    public TableView<Part> assParts_ModifyProduct;
    public TableColumn<Part, Integer> assParts_ID;
    public TableColumn<Part, String> assParts_Name;
    public TableColumn<Part, Integer> assParts_Inv;
    public TableColumn<Part, Double> assParts_Price;
    
    public TextField searchPartsModify;
    public Button modifyProductSearchButton;

    private int index;

    public ObservableList<Part> assPartsReset = FXCollections.observableArrayList();


    public int prodID = getModifyIDProduct();
    public Product p = getAllProducts().get(prodID);

    //public Part associated;
    public Product product = new Product();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        modifyProductIDField.setText(Integer.toString(prodID));

        //set text fields
        modifyProductNameField.setText(p.getPropName().toLowerCase());
        modifyProductInvField.setText(Integer.toString(p.getPropInv()));
        modifyProductPriceField.setText(Double.toString(p.getPropPrice()));
        modifyProductMinField.setText(Integer.toString(p.getPropMin()));
        modifyProductMaxField.setText(Integer.toString(p.getPropMax()));

        //tables
        allParts_ID.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        allParts_Name.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        allParts_Inv.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        allParts_Price.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        allParts_ModifyProduct.setItems(getAllParts());

        assParts_ID.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        assParts_Name.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        assParts_Inv.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        assParts_Price.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        assParts_ModifyProduct.setItems(p.getAllAssociatedParts());
    }



    //save button
    public void saveModifyProduct(ActionEvent e) throws IOException {

        if(p.getAllAssociatedParts().size() <= 0){
            throw new IOException("Products must have 1 associated part minimum");
        }

        p.setPropName(modifyProductNameField.getText());
        p.setPropInv(Integer.parseInt(modifyProductInvField.getText()));
        p.setPropPrice(Double.parseDouble(modifyProductPriceField.getText()));
        p.setPropMin(Integer.parseInt(modifyProductMinField.getText()));
        p.setPropMax(Integer.parseInt(modifyProductMaxField.getText()));
        Inventory.updateProduct(modIndexProduct, p);

        Parent addProductParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        Stage addProductStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        addProductStage.setScene(addProductScene);
        addProductStage.show();

    }

    //add button in modify
    public void associate(ActionEvent e){
        Part part = allParts_ModifyProduct.getSelectionModel().getSelectedItem();
        p.addAssociatedPart(part);
        assParts_ModifyProduct.setItems(p.getAllAssociatedParts());
    }

    public void updateParts(){
        allParts_ModifyProduct.setItems(Inventory.getAllParts());
    }

    public void clearPartsSearch(ActionEvent e){
        searchPartsModify.setText("");
        updateParts();
    }

    public void partsSearchButton(ActionEvent e) {

        ObservableList<Part> searchedResultList = FXCollections.observableArrayList();
        Part temp;
        String search = searchPartsModify.getText();
        int searchIndex;

        if (search.matches("\\d+")) {
            searchIndex = Integer.parseInt(search);
            if (searchIndex <= Inventory.getAllParts().size() && searchIndex >= 0) {
                temp = Inventory.lookupPart(searchIndex);
                searchedResultList.add(temp);
                allParts_ModifyProduct.setItems(searchedResultList);
            }
        } else {
            allParts_ModifyProduct.setItems(Inventory.lookupPart(search));
        }
    }

    public void deleteAssPart(ActionEvent e){

        Part associated = assParts_ModifyProduct.getSelectionModel().getSelectedItem();
        ObservableList<Part> temp = FXCollections.observableArrayList();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Deleting Associated Part ID: " + associated.getPartID() + " - Name: " + associated.getName());
        alert.setContentText("Do you wish to proceed?");
        alert.showAndWait();

        temp = p.getAllAssociatedParts();
        temp.remove(associated);
        assParts_ModifyProduct.setItems(temp);
    }

    public void home(ActionEvent e) throws IOException {

        if(p.getAllAssociatedParts().size() <= 0){
            throw new IOException("Products must have 1 associated part minimum");
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Return Home, unsaved data will be lost");
        alert.setContentText("Do you wish to proceed?");
        alert.showAndWait();

        Parent addProductParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        Stage addProductStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        addProductStage.setScene(addProductScene);
        addProductStage.show();
    }

}

