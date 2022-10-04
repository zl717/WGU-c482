package inv_man_sys_interface;

import inv_man_sys_application.*;
import javafx.fxml.*;

//import java.awt.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class AddPart_Controller implements Initializable {
    public RadioButton outsourced;
    public Label flexLabel;
    public TextField addPartNameField;
    public TextField addPartPriceField;
    public TextField addPartInvField;
    public TextField addPartMaxField;
    public TextField addPartMinField;
    public TextField addPartFlexField;
    public TextField addPartIDField;

    private int index;
    private boolean outB = false;

    inHouse in = new inHouse();
    Outsourced o = new Outsourced();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        index = Inventory.updateIndex();
        addPartIDField.setText(Integer.toString(index));
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

    public boolean changeToOutsourced(ActionEvent e){
        flexLabel.setText("Company");
        addPartFlexField.setPromptText("Company Name");
        outB = true;
        return true;
    }

    public void changeToInHouse(ActionEvent e){
        flexLabel.setText("Machine");
        addPartFlexField.setPromptText("Machine ID");
        outB = false;
    }

    public void saveAddPart(ActionEvent e) throws IOException {

        String id = addPartIDField.getText();
        String partName = addPartNameField.getText().toLowerCase();
        String inv = addPartInvField.getText();
        String price = addPartPriceField.getText();
        String max = addPartMaxField.getText();
        String min = addPartMinField.getText();
        String flex = addPartFlexField.getText();
        if(outB == false) {
            // in house
            //machine id/company name

            in.setID(Integer.parseInt(id));
            in.setName(partName);
            in.setInv(Integer.parseInt(inv));
            in.setPrice(Double.parseDouble(price));
            in.setMax(Integer.parseInt(max));
            in.setMin(Integer.parseInt(min));
            in.setMachineID(Integer.parseInt(flex));

            Inventory.addPart(in);
            // in house

            System.out.println(id + " " + partName + " " + inv + " " + price + " " + max + " " + min + " " + flex + " in house");
        }

        else {
            // outsourced
            //machine id/company name

            o.setID(Integer.parseInt(id));
            o.setName(partName);
            o.setInv(Integer.parseInt(inv));
            o.setPrice(Double.parseDouble(price));
            o.setMax(Integer.parseInt(max));
            o.setMin(Integer.parseInt(min));
            o.setCompanyName(flex);

            Inventory.addPart(o);
            // in house

            System.out.println(id + " " + partName + " " + inv + " " + price + " " + max + " " + min + " " + flex + " outsourced");
        }

        Parent addProductParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        Stage addProductStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        addProductStage.setScene(addProductScene);
        addProductStage.show();


    }

}

