package com.udacity.shoestore.screens

import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeViewModel

class ShoeListFragment : Fragment() {

    lateinit var binding: FragmentShoeListBinding
    lateinit var navController: NavController
    private val shoeViewModel: ShoeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(binding.root)

        binding.shoeListFab.setOnClickListener {
            navController.navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        shoeViewModel.shoeList.observe(viewLifecycleOwner, Observer {
            if (it != null){
                for (i in 0 until it.size){

                    val shoeItemView = layoutInflater.inflate(R.layout.shoe_item, binding.containerLayout, false)
                    val nameText = shoeItemView.findViewById<TextView>(R.id.shoe_item_name)
                    val companyText = shoeItemView.findViewById<TextView>(R.id.shoe_item_company)
                    val sizeText = shoeItemView.findViewById<TextView>(R.id.shoe_item_size)
                    val descriptionText = shoeItemView.findViewById<TextView>(R.id.shoe_item_description)

                    nameText.text = resources.getString(R.string.shoe_item_name, it[i].name)
                    companyText.text = resources.getString(R.string.shoe_item_company, it[i].company)
                    sizeText.text = resources.getString(R.string.shoe_item_size, it[i].size.toString())
                    descriptionText.text = resources.getString(R.string.shoe_item_description, it[i].description)

                    binding.containerLayout.addView(shoeItemView)
                }
            }
        })
    }
}