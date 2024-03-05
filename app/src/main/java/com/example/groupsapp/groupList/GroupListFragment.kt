package com.example.groupsapp.groupList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groupsapp.LoadingDialog
import com.example.groupsapp.R
import com.example.groupsapp.adapter.GroupListAdapter
import com.example.groupsapp.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GroupListFragment : Fragment() {

    private lateinit var _binding: FragmentListBinding
    private val mViewModel: GroupListViewModel by viewModels()
    private val loadingDialog = LoadingDialog.newInstance()
    private var isLoading = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        val toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)

        // Hide the back button
        val btnBack: ImageView = toolbar.findViewById(R.id.btnBack)
        btnBack.visibility = View.GONE

        // Hide the search button
        val btnSearch: ImageView = toolbar.findViewById(R.id.btnSearch)
        btnSearch.visibility = View.VISIBLE

        // Hide the edit TextView
        val tvEdit: TextView = toolbar.findViewById(R.id.tvEdit)
        tvEdit.visibility = View.GONE

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)

        return _binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getGroupDetails()
        setUpObservers()
    }

    private fun setUpObservers() {
        mViewModel.groupResponse.observe(viewLifecycleOwner) {

            val adapter = it?.let { it1 ->
                GroupListAdapter(
                    requireActivity(),
                    it1
                ) {
                    val bundle = Bundle()
                    bundle.putString("groupId", it.id.toString())
                    bundle.putString("status", it.user_status)
                    findNavController().navigate(
                        R.id.action_listFragment_to_groupDetailsFragment,
                        bundle
                    )
                }
            }
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            _binding.rvGroups.layoutManager = layoutManager
            _binding.rvGroups.adapter = adapter
        }

        mViewModel.loading.observe(viewLifecycleOwner) {
            if (it == true) {
                showLoading()
            } else {
                hideLoading()
            }
        }
    }

    fun hideLoading() {
        if (isLoading) {
            loadingDialog.dismiss()
            isLoading = false
        }
    }

    fun showLoading() {
        if (!isLoading) {
            loadingDialog.show(childFragmentManager, loadingDialog.tag)
            isLoading = true
        }
    }
}