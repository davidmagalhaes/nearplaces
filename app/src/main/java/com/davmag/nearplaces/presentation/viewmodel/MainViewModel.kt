package com.davmag.nearplaces.presentation.viewmodel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.PagingData
import androidx.paging.map
import com.davmag.nearplaces.domain.repository.PlaceRepository
import com.davmag.nearplaces.presentation.common.BaseViewModel
import com.davmag.nearplaces.presentation.common.toLiveData
import com.davmag.nearplaces.presentation.mapper.PlacePresentationMapper
import com.davmag.nearplaces.presentation.model.PlacePresentation

class MainViewModel(
    private val placeRepository: PlaceRepository
) : BaseViewModel() {

    private var _places = MediatorLiveData<PagingData<PlacePresentation>>()
    val places : LiveData<PagingData<PlacePresentation>> = _places

    private var initialized = false

    override fun initViewModel(args : Bundle?){
        if(!initialized){
            placeRepository.get()
                .map { pagingData ->
                    pagingData.map {
                        PlacePresentationMapper.parse(it).first()
                    }
                }
                .toLiveData(_places)

            placeRepository.fetch()

            initialized = true
        }
    }

    fun reload(){
        placeRepository.fetch()
    }
}