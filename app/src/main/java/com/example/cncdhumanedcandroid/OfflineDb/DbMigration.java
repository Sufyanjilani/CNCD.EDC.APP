package com.example.cncdhumanedcandroid.OfflineDb;

import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class DbMigration implements RealmMigration {

    private final int version;

    @Override
    public boolean equals(Object o) {
        return this.version == ((DbMigration) o).version;
    }

    public DbMigration(int version) {
        this.version = version;
    }

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        // Example: Add a new field to an existing object

        Log.d("dbVersion",String.valueOf(oldVersion));

        if (oldVersion == 3) {
            RealmObjectSchema entitiesModel= schema.get("EntitiesModel");
            entitiesModel.addField("personal_muzzle", String.class);
            entitiesModel.addField("renalCellForm", String.class);
            entitiesModel.addField("bloodCancer_form", String.class);
            entitiesModel.addField("acne_rosacea_form", String.class);
            entitiesModel.addField("alopecia_form", String.class);
        }

        // Handle other schema changes for different versions
        // ...

        // Increment the schema version if needed
        // schema.create("NewObject");
    }
}