package com.example.groupsapp.groupList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groupsapp.netWorking.GroupRepository
import com.example.groupsapp.netWorking.ResourceState
import com.example.groupsapp.netWorking.SingleLiveEvent
import com.example.groupsapp.pojo.groupList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupListViewModel @Inject constructor(
    private val groupRepository: GroupRepository
) : ViewModel() {

    private val _groupResponse = SingleLiveEvent<groupList?>()
    val groupResponse: SingleLiveEvent<groupList?> = _groupResponse

    fun getGroupDetails() {
        viewModelScope.launch {
            try {
                groupRepository.getGroupList().collect { response: ResourceState<groupList> ->
                    when (response) {
                        is ResourceState.Success -> {
                            if (response.data.message == "OK") {
                                _groupResponse.value = response.data
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