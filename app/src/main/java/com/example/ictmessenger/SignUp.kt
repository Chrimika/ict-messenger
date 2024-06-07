package com.example.ictmessenger


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey



class User : RealmObject {
    @PrimaryKey
    var id: Long = 0
    var uname: String? = null
    var email: String? = null
    var password: String? = null
}

class SignUp : AppCompatActivity() {

    private lateinit var realm: Realm

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtName: EditText
    private lateinit var btnSignUp: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize Realm
        val config = RealmConfiguration.Builder(schema = setOf(User::class))
            .name("ictmessenger.realm")
            .schemaVersion(1)
            .build()
        realm = Realm.open(config)

        // Initialize views
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        edtName = findViewById(R.id.edt_name)
        btnSignUp = findViewById(R.id.btnSignUp)

        // Set up the sign-up button click listener
        btnSignUp.setOnClickListener {
            saveUser()
        }
    }

    private fun saveUser() {
        val name = edtName.text.toString()
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()

        // Perform validation (optional but recommended)
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            // Show error message
            return
        }

        // Save the user to Realm
        realm.writeBlocking {
            copyToRealm(User().apply {
                uname = name
                this.email = email
                this.password = password
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

}