package br.com.pelisoli.android_firebase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firebase.client.ServerValue;

import java.util.HashMap;
import java.util.UUID;

import br.com.pelisoli.android_firebase.utils.Constants;

/**
 * Created by pelisoli on 15/05/16.
 */
public class ShoppingList {
    private UUID id;

    private String listName;

    private String owner;

    private HashMap<String, Object> timestamp;

    public ShoppingList(String listName, String owner) {
        this.id = UUID.randomUUID();
        this.listName = listName;
        this.owner = owner;
        this.timestamp = new HashMap<>();
        this.timestamp.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
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

    public HashMap<String, Object> getTimestamp() {
        return timestamp;
    }

    @JsonIgnore
    public long getDateLastChangedLong() {
        if (timestamp != null) {
            return (long) timestamp.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
        }

        timestamp = new HashMap<String, Object>();
        timestamp.put("date", ServerValue.TIMESTAMP);

        return (long) timestamp.get("date");
    }
}
