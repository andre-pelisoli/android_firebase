package br.com.pelisoli.android_firebase.utils;

import br.com.pelisoli.android_firebase.BuildConfig;

/**
 * Created by pelisoli on 31/05/16.
 */
public class Constants {

    public static final String FIREBASE_LOCATION_ACTIVE_LIST = "activeLists";

    public static final String FIREBASE_PROPERTY_LIST_NAME = "listName";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED = "timestampLastChanged";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_CREATED = "timestampCreated";

    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
    public static final String FIREBASE_URL_ACTIVE_LIST = FIREBASE_URL + FIREBASE_LOCATION_ACTIVE_LIST;

    public static final String KEY_LIST_ID = "list";

}
