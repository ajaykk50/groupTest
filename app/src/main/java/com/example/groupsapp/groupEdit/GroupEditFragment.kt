package com.example.groupsapp.groupEdit

import android.os.Bundle
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
import com.bumptech.glide.Glide
import com.example.groupsapp.R
import com.example.groupsapp.databinding.FragmentGroupEditBinding
import com.example.groupsapp.pojo.ResultX
import com.example.groupsapp.room.entity.GroupInfo
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GroupEditFragment : Fragment() {

    private lateinit var groupInfo: ResultX
    private lateinit var _binding: FragmentGroupEditBinding
    private val mViewModel: GroupEditViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGroupEditBinding.inflate(inflater, container, false)

        val toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)

        // Hide the back button
        val btnBack: ImageView = toolbar.findViewById(R.id.btnBack)
        btnBack.visibility = View.VISIBLE

        // Hide the search button
        val btnSearch: ImageView = toolbar.findViewById(R.id.btnSearch)
        btnSearch.visibility = View.GONE

        // Hide the edit TextView
        val tvEdit: TextView = toolbar.findViewById(R.id.tvEdit)
        tvEdit.visibility = View.GONE


        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)


        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.getSerializable("groupinfo") != null) {
            groupInfo = arguments?.getSerializable("groupinfo") as ResultX
            Glide.with(requireActivity()).load(groupInfo.group_photo).into(_binding.ivGroupImage)

            mViewModel.getGroupDetails(groupInfo.id.toString())
        }

        _binding.btnSubmit.setOnClickListener {

            val groupInfo = GroupInfo(
                group_id = groupInfo.id.toString(),
                group_name = _binding.etName.text.toString(),
                group_about = _binding.etAbout.text.toString()
            )
            mViewModel.addUpdateGroupDetails(groupInfo)
        }

        _binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        setUpObservers()
    }

    private fun setUpObservers() {
        mViewModel.groupInfo.observe(viewLifecycleOwner) {
            if (it != null) {
                _binding.etName.setText(it.group_name)
                _binding.etAbout.setText(it.group_about)
            } else {
                _binding.etName.setText(groupInfo.name)
                _binding.etAbout.setText(groupInfo.bio)
            }
        }
    }


}