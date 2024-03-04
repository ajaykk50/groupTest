package com.example.groupsapp.groupDetails

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
import com.example.groupsapp.databinding.FragmentGroupDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GroupDetailsFragment : Fragment() {

    private lateinit var tvEdit: TextView
    private lateinit var toolbar: Toolbar
    private lateinit var _binding: FragmentGroupDetailsBinding
    private val mViewModel: GroupDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGroupDetailsBinding.inflate(inflater, container, false)

        toolbar = requireActivity().findViewById(R.id.toolbar)

        // Hide the back button
        val btnBack: ImageView = toolbar.findViewById(R.id.btnBack)
        btnBack.visibility = View.VISIBLE

        // Hide the search button
        val btnSearch: ImageView = toolbar.findViewById(R.id.btnSearch)
        btnSearch.visibility = View.GONE

//        val tvEdit: TextView = toolbar.findViewById(R.id.tvEdit)
//        tvEdit.visibility = View.GONE

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val groupId = arguments?.getString("groupId")

        groupId?.let { mViewModel.getGroupDetails(it) }
        setUpObservers()
    }

    private fun setUpObservers() {
        mViewModel.groupDetailsResponse.observe(viewLifecycleOwner) {
            if (it?.message == "OK") {

                val data = it.result

                if (it.result.user_status == "Admin") {
                    println("it.result.user_status" + it.result.user_status)
                    tvEdit = toolbar.findViewById(R.id.tvEdit)
                    tvEdit.visibility = View.VISIBLE
                }else{
                    tvEdit = toolbar.findViewById(R.id.tvEdit)
                    tvEdit.visibility = View.GONE
                }

                Glide.with(requireActivity()).load(it.result.group_photo)
                    .into(_binding.ivGroupImage)
                _binding.tvGroupName.text = it.result.name
                _binding.tvGroupBio.text = it.result.bio
                _binding.tvAbout.text = it.result.bio
                _binding.tvCount.text = it.result.participant_count.toString()


                tvEdit.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putSerializable("groupinfo", data)
                    findNavController().navigate(
                        R.id.action_groupDetailsFragment_to_groupEditFragment,
                        bundle
                    )
                }
            }
        }
    }
}