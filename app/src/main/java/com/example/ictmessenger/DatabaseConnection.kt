package com.example.ictmessenger

import android.app.Application
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration


class DatabaseConnection: Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Realm with the schema
        val config = RealmConfiguration.Builder(schema = setOf(User::class))
            .name("ictmessenger.realm")
            .schemaVersion(1)
            .build()
        Realm.open(config)
    }
}