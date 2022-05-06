package com.demo.chatktapp.inscriptionFragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.TransitionInflater
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.demo.chatktapp.MainActivity
import com.demo.chatktapp.R
import com.demo.chatktapp.models.User
import com.demo.chatktapp.services.FirebaseService


/**
 * Fragment suivant la page de bienvenue qui prend le nom de d'utilisateur
 */
class UsernameFragment : Fragment() {
    private lateinit var usernameEditText: EditText
    private lateinit var signUpButton: Button
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)

        sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_username, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameEditText = view.findViewById(R.id.usernameEditText) as EditText
        usernameTypeChangeListener()
        closeKeyBoardOnPressEnter(view)
        registerUser(view)
    }

    private fun closeKeyBoardOnPressEnter(view: View) {
        usernameEditText.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                val imm: InputMethodManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                if (imm.isActive)
                    imm.hideSoftInputFromWindow(view.windowToken, 0)

                return@OnKeyListener true
            }
            false
        })
    }

    private fun usernameTypeChangeListener() {
        usernameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun registerUser(view: View) {
        signUpButton = view.findViewById(R.id.signUpButton) as Button

        signUpButton.setOnClickListener {
            val deviceID = sharedPreferences?.getString("deviceID", null) as String
            val username = usernameEditText.text.toString()
            val user = User(username, deviceID)

            FirebaseService.saveFireStore(context, requireActivity(), "users", user)
            sharedPreferences!!.edit()?.putString("username", username)?.apply()

            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment UsernameFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            UsernameFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}