package com.example.groupsapp.groupEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groupsapp.netWorking.GroupRepository
import com.example.groupsapp.netWorking.SingleLiveEvent
import com.example.groupsapp.pojo.groupDetails
import com.example.groupsapp.room.entity.GroupInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupEditViewModel @Inject constructor(
    private val groupRepository: GroupRepository,
) : ViewModel() {

    private val _groupInfo = SingleLiveEvent<GroupInfo?>()
    val groupInfo: SingleLiveEvent<GroupInfo?> = _groupInfo

    fun addUpdateGroupDetails(groupInfo: GroupInfo) {
        viewModelScope.launch {
            try {
                val groupData = groupRepository.getGroupInfo(groupInfo.group_id)
                if (groupData != null) {
                    groupRepository.updateGroupInfo(
                        groupInfo.group_name,
                        groupInfo.group_about,
                        groupInfo.group_id
                    )
                } else {
                    groupRepository.editUpdateGroupInfo(groupInfo)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getGroupDetails(groupId: String) {
        viewModelScope.launch {
            try {
                val groupInfo = groupRepository.getGroupInfo(groupId)
                _groupInfo.value = groupInfo
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}