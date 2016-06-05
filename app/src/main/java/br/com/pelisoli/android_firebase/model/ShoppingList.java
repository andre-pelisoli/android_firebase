package br.com.pelisoli.android_firebase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firebase.client.ServerValue;

import java.util.HashMap;

import br.com.pelisoli.android_firebase.utils.Constants;

/**
 * Created by pelisoli on 15/05/16.
 */
public class ShoppingList {
    private String listName;

    private String owner;

    private HashMap<String, Object> timestampLastChanged;

    private HashMap<String, Object> timestampCreated;

    public ShoppingList(String listName, String owner) {
        this.listName = listName;
        this.owner = owner;
        this.timestampCreated = new HashMap<>();
        this.timestampLastChanged = new HashMap<>();
        this.timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP_CREATED, ServerValue.TIMESTAMP);
        this.timestampLastChanged.put(Constants.FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED, ServerValue.TIMESTAMP);
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

    public HashMap<String, Object> getTimestampLastChanged() {
        return timestampLastChanged;
    }

    public HashMap<String, Object> getTimestampCreated() {
        return timestampCreated;
    }

    @JsonIgnore
    public long getTimestampLastChangedLong() {

        return (long) timestampLastChanged.get(Constants.FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED);
    }

    @JsonIgnore
    public long getTimestampCreatedLong() {
        return (long) timestampLastChanged.get(Constants.FIREBASE_PROPERTY_TIMESTAMP_CREATED);
    }

}
