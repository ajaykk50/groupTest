package com.example.groupsapp.groupDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groupsapp.netWorking.GroupRepository
import com.example.groupsapp.netWorking.ResourceState
import com.example.groupsapp.netWorking.SingleLiveEvent
import com.example.groupsapp.pojo.groupDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupDetailsViewModel @Inject constructor(
    private val groupRepository: GroupRepository
) : ViewModel() {

    private val _groupDetailsResponse = SingleLiveEvent<groupDetails?>()
    val groupDetailsResponse: SingleLiveEvent<groupDetails?> = _groupDetailsResponse

    fun getGroupDetails(id:String) {
        viewModelScope.launch {
            try {
                groupRepository.getGroupDetails(id).collect { response: ResourceState<groupDetails> ->
                    when (response) {
                        is ResourceState.Success -> {
                            if (response.data.message == "OK") {
                                _groupDetailsResponse.value = response.data
                            }
                        }

                        is ResourceState.Error -> {}

                        is ResourceState.Loading -> {}

                        else -> {}
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}