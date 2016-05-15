package br.com.pelisoli.android_firebase.model;

/**
 * Created by pelisoli on 15/05/16.
 */
public class ShoppingList {

    private String listName;

    private String owner;

    public ShoppingList(String listName, String owner) {
        this.listName = listName;
        this.owner = owner;
    }

    public ShoppingList() {
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
