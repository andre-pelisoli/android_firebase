package br.com.pelisoli.android_firebase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firebase.client.ServerValue;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by pelisoli on 15/05/16.
 */
public class ShoppingList {
    private UUID id;

    private String listName;

    private String owner;

    private HashMap<String, Object> dateLastChanged;

    public ShoppingList(String listName, String owner) {
        this.id = UUID.randomUUID();
        this.listName = listName;
        this.owner = owner;
        this.dateLastChanged = new HashMap<>();
        this.dateLastChanged.put("date", ServerValue.TIMESTAMP);
    }

    public ShoppingList() {
    }

    public UUID getId() {
        return id;
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

    public HashMap<String, Object> getDateLastChanged() {
        return dateLastChanged;
    }

    @JsonIgnore
    public long getDateLastChangedLong() {
        if (dateLastChanged != null) {
            return (long)dateLastChanged.get("date");
        }

        dateLastChanged = new HashMap<String, Object>();
        dateLastChanged.put("date", ServerValue.TIMESTAMP);

        return (long)dateLastChanged.get("date");
    }
}
