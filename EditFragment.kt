package com.example.newrecipe.Fragment
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newrecipe.databinding.FragmentEditBinding
import com.example.newrecipe.databinding.FragmentHomeBinding
import com.example.newrecipe.databinding.FragmentProfileBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
class EditFragment : Fragment() {
    private var binding: FragmentEditBinding? = null

    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser
    private lateinit var imageview4: Image

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentEditBinding.inflate(inflater, container, false)

        return binding!!.root



    }

}