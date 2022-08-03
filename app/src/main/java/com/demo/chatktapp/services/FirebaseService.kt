package com.demo.chatktapp.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.demo.chatktapp.R
import com.demo.chatktapp.models.Message
import com.demo.chatktapp.models.Room
import com.demo.chatktapp.models.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

const val TAG = "FIREBASESERVICE"

class FirebaseService : Service() {
    companion object {
        fun <T> saveFireStore(context: Context?, fa: FragmentActivity, collection: String, data: T) {
            val firestore = FirebaseFirestore.getInstance()

            when (data) {
                is User -> {
                    firestore.collection(collection).document(data.deviceId).set(data)
                        .addOnSuccessListener {
                            Log.d(TAG, "DocumentSnapshot added with ID: ${data.deviceId}")
                            //Toast.makeText(context, "Welcome, ${data.username}", Toast.LENGTH_LONG).show()
                            val welcomeText = fa.getString(R.string.welcome_string) + ", " + data.username
                            Snackbar.make(fa.window.decorView.rootView,
                                welcomeText, Snackbar.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                }
                is Message -> {
                    firestore.collection(collection).document(data.id.toString()).set(data)
                        .addOnSuccessListener {
                            Log.d(TAG, "DocumentSnapshot added with ID: ${data.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                }
                is Room -> {
                    firestore.collection(collection).document(data.id.toString()).set(data)
                        .addOnSuccessListener {
                            Log.d(TAG, "DocumentSnapshot added with ID: ${data.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                }
            }
        }

        fun authenticate() {
            val auth = FirebaseAuth.getInstance()
            auth.signInAnonymously()
        }

        fun currentUser(): FirebaseUser? {
            val auth = FirebaseAuth.getInstance()
            return auth.currentUser
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.i(TAG, "Service onBind")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Service Created")
    }
}