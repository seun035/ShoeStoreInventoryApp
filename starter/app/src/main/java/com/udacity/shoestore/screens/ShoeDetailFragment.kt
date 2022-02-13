package com.udacity.shoestore.screens

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeViewModel

class ShoeDetailFragment : Fragment() {

    lateinit var binding: FragmentShoeDetailBinding
    lateinit var navController: NavController
    private val shoeViewModel: ShoeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(binding.root)

        binding.saveBtn.setOnClickListener {
            val name = binding.shoeNameEdit.text.toString()
            val size =  binding.shoeSizeEdit.text.toString()
            val company = binding.shoeCompanyEdit.text.toString()
            val description = binding.shoeDescriptionEdit.text.toString()

            if (name.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Name is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(company.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Company is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (size.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Size is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (description.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Description is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            shoeViewModel.addShoe(Shoe(name,  size.toDouble(), company , description ))
            navController.popBackStack()
        }

        binding.cancelBtn.setOnClickListener {
            navController.popBackStack()
        }
    }
}