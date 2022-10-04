package inv_man_sys_application;

import javafx.collections.*;


/*

Part J

1: ensuring that a product must always have at least one part

2: including a confirm dialogue for all “Delete” and “Cancel” buttons

 */


public class Inventory {

    private static ObservableList <Part> allParts = FXCollections.observableArrayList();
    private static ObservableList <Product> allProducts = FXCollections.observableArrayList();

    public static int index = 0;
    public static int indexProduct = 0;


    public static Part lookupPart(int id){

        int ind = 0;

        for(int i = 0; i < allParts.size(); i++){
            if(id == allParts.get(i).getPartID()){
                ind = i;
            }
        }
        return allParts.get(ind);
    }

    public static ObservableList<Part> lookupPart(String partName){

        ObservableList<Part> weasels = FXCollections.observableArrayList();

        for(int i = 0; i < allParts.size(); i++){
            if(allParts.get(i).getName().toLowerCase().contains(partName.toLowerCase())){
                weasels.add(allParts.get(i));
            }

        }
        return weasels;
    }

    public static Product lookupProduct(int id){

        int ind = 0;

        for(int i = 0; i < allProducts.size(); i++){
            if(id == allProducts.get(i).getID()){
                ind = i;
            }
        }
        return allProducts.get(ind);
    }

    public static ObservableList<Product> lookupProduct(String productName){

        ObservableList<Product> weasels = FXCollections.observableArrayList();

        for(int i = 0; i < allProducts.size(); i++){
            if(allProducts.get(i).getPropName().toLowerCase().contains(productName.toLowerCase())){
                weasels.add(allProducts.get(i));
            }

        }
        return weasels;
    }

    public static void deletePart(Part p){
        allParts.remove(p);
    }

    public static void deleteProduct(Product p){
        allProducts.remove(p);
    }

    public static void addPart(Part p){
        allParts.add(p);
    }

    public static void addProduct(Product p){
        allProducts.add(p);
    }

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }


    public static int updateIndex(){
        return index++;
    }

    public static int updateIndexProduct(){
        return indexProduct++;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }


}
