package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
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
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(binding.root)
        binding.viewmodel = shoeViewModel
        binding.shoe = Shoe("", 0.0, "", "")

        shoeViewModel.shoeAddedState.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    navController.popBackStack()
                    shoeViewModel.resetAddedState()
                }
            }
        })

        shoeViewModel.cancelState.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    navController.popBackStack()
                    shoeViewModel.resetCancelState()
                }
            }
        })

        shoeViewModel.addShoeError.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                shoeViewModel.resetAddShoeError()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.loginFragment).isVisible = false
    }
}