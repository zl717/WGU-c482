package inv_man_sys_interface;

import inv_man_sys_application.*;
import javafx.fxml.*;

import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static inv_man_sys_application.Inventory.getAllParts;
import static inv_man_sys_application.Inventory.updatePart;
import static inv_man_sys_interface.MainScreen_Controller.getModifyID;


public class ModifyPart_Controller implements Initializable {
    public RadioButton outsourced;
    public Label flexLabel;
    public TextField modifyPartFlexField;
    public TextField modifyPartIDField;
    public TextField modifyPartNameField;
    public TextField modifyPartCostField;
    public TextField modifyPartMaxField;
    public TextField modifyPartMinField;
    public TextField modifyPartInvField;
    public RadioButton inHouseRadio;

    inHouse in = new inHouse();
    Outsourced o = new Outsourced();

    boolean outB, inH;
    int partID = getModifyID();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Part part = getAllParts().get(partID);
        partID = getAllParts().get(partID).getPartID();
        modifyPartIDField.setText(Integer.toString(partID));
        modifyPartNameField.setText(part.getName());
        modifyPartInvField.setText(Integer.toString(part.getInv()));
        modifyPartCostField.setText(Double.toString(part.getPrice()));
        modifyPartMinField.setText(Integer.toString(part.getMin()));
        modifyPartMaxField.setText(Integer.toString(part.getMax()));


        if(part instanceof inHouse){
            flexLabel.setText("Machine ID");
            modifyPartFlexField.setText(Integer.toString(((inHouse) part).getMachineID()));
            inHouseRadio.setSelected(true);
            //changeToInHouse();
        }

        else {
            flexLabel.setText("Company Name");
            modifyPartFlexField.setText(((Outsourced) getAllParts().get(getModifyID())).getCompanyName());
            outsourced.setSelected(true);
            //changeToOutsourced();
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

    public void changeToOutsourced(ActionEvent e){
        flexLabel.setText("Company");
        modifyPartFlexField.setPromptText("Company Name");
        outB = true;
    }

    public void changeToOutsourced(){
        flexLabel.setText("Company");
        modifyPartFlexField.setPromptText("Company Name");
        outB = true;
    }

    public void changeToInHouse(ActionEvent e){
        flexLabel.setText("Machine");
        modifyPartFlexField.setPromptText("Machine ID");
        inH = true;
    }

    public void changeToInHouse(){
        flexLabel.setText("Machine");
        modifyPartFlexField.setPromptText("Machine ID");
        inH = true;
    }


    public void saveModifyPart(ActionEvent e) throws IOException {

        String id = modifyPartIDField.getText();
        String partName = modifyPartNameField.getText().toLowerCase();
        String inv = modifyPartInvField.getText();
        String price = modifyPartCostField.getText();
        String max = modifyPartMaxField.getText();
        String min = modifyPartMinField.getText();
        String flex = modifyPartFlexField.getText();

        if(!outB) {
            // in house

            in.setID(Integer.parseInt(id));
            in.setName(partName);
            in.setInv(Integer.parseInt(inv));
            in.setPrice(Double.parseDouble(price));
            in.setMax(Integer.parseInt(max));
            in.setMin(Integer.parseInt(min));
            in.setMachineID(Integer.parseInt(flex));

            updatePart(Integer.parseInt(id), in);
            outsourced.setSelected(false);
            //changeToInHouse();

            // in house

            System.out.println(id + " " + partName + " " + inv + " " + price + " " + max + " " + min + " " + flex + " in house");
        }

        else if(!inH){
            // outsourced

            o.setID(Integer.parseInt(id));
            o.setName(partName);
            o.setInv(Integer.parseInt(inv));
            o.setPrice(Double.parseDouble(price));
            o.setMax(Integer.parseInt(max));
            o.setMin(Integer.parseInt(min));
            o.setCompanyName(flex);

            updatePart(o.getPartID(), o);
            inHouseRadio.setSelected(false);
           // changeToOutsourced();

            System.out.println(id + " " + partName + " " + inv + " " + price + " " + max + " " + min + " " + flex + " outsourced");
        }

        Parent addProductParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        Stage addProductStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        addProductStage.setScene(addProductScene);
        addProductStage.show();

    }

}