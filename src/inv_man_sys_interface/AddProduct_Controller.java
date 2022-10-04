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

public class AddProduct_Controller implements Initializable {

    public TextField addProductIDField;
    public TextField addProductNameField;
    public TextField addProductInvField;
    public TextField addProductPriceField;
    public TextField addProductMaxField;
    public TextField addProductMinField;

    public Button searchButtonAdd;
    public TextField searchFieldAdd;
    public Button clearButtonAdd;

    private int index;

    public TableView<Part> allParts_AddProduct;
    public TableColumn<Part, Integer> allParts_ID;
    public TableColumn<Part, String> allParts_Name;
    public TableColumn<Part, Integer> allParts_Inv;
    public TableColumn<Part, Double> allParts_Price;

    public TableView<Part> associatedParts_AddProduct;
    public TableColumn<Part, Integer> assParts_ID;
    public TableColumn<Part, String> assParts_Name;
    public TableColumn<Part, Integer> assParts_Inv;
    public TableColumn<Part, Double> assParts_Price;


    Product p = new Product();
    public Part associated;
    public static ObservableList<Part> assParts = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        index = Inventory.updateIndexProduct();
        addProductIDField.setText(Integer.toString(index));

        allParts_ID.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        allParts_Name.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        allParts_Inv.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        allParts_Price.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        allParts_AddProduct.setItems(Inventory.getAllParts());

        assParts_ID.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        assParts_Name.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        assParts_Inv.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        assParts_Price.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
    }

    public void saveAddProduct(ActionEvent e) throws IOException {

        if(p.getAllAssociatedParts().size() <= 0){
            throw new IOException("Products must have 1 associated part minimum");
        }

        p.setID(Integer.parseInt(addProductIDField.getText()));
        p.setPropName(addProductNameField.getText());
        p.setPropInv(Integer.parseInt(addProductInvField.getText()));
        p.setPropPrice(Double.parseDouble(addProductPriceField.getText()));
        p.setPropMax(Integer.parseInt(addProductMaxField.getText()));
        p.setPropMin(Integer.parseInt(addProductMinField.getText()));

        Inventory.addProduct(p);

        Parent addProductParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        Stage addProductStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        addProductStage.setScene(addProductScene);
        addProductStage.show();


    }

    public void associate(ActionEvent e){
        associated = allParts_AddProduct.getSelectionModel().getSelectedItem();
        p.addAssociatedPart(associated);
        associatedParts_AddProduct.setItems(p.getAllAssociatedParts());
        assParts.add(associated);

    }

    public void deleteAssPart(ActionEvent e){

        associated = allParts_AddProduct.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Deleting Associated Part ID: " + associated.getPartID() + " - Name: " + associated.getName());
        alert.setContentText("Do you wish to proceed?");
        alert.showAndWait();

        p.deleteAssociatedPart(associated);
        assParts.remove(associated.getPartID());
        updateParts();
    }


    public void updateParts(){
        allParts_AddProduct.setItems(Inventory.getAllParts());
    }

    public void clearPartsSearch(ActionEvent e){
        searchFieldAdd.setText("");
        updateParts();
    }

    public void partsSearchButton(ActionEvent e) {

        ObservableList<Part> searchedResultList = FXCollections.observableArrayList();
        Part temp;
        String search = searchFieldAdd.getText();
        int searchIndex;

        if (search.matches("\\d+")) {
            searchIndex = Integer.parseInt(search);
            if (searchIndex <= Inventory.getAllParts().size() && searchIndex >= 0) {
                temp = Inventory.lookupPart(searchIndex);
                searchedResultList.add(temp);
                allParts_AddProduct.setItems(searchedResultList);
            }
        } else {
            allParts_AddProduct.setItems(Inventory.lookupPart(search));
        }
    }



    public void home(ActionEvent e) throws IOException {
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

