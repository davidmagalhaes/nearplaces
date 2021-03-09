package com.davmag.nearplaces.presentation.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.davmag.nearplaces.infra.App
import com.davmag.nearplaces.presentation.common.PresentationMapper

private val presentationComponent : PresentationComponent by lazy {
    DaggerPresentationComponent
        .builder()
        .applicationComponent(App.applicationComponent)
        .build()
}

val AppCompatActivity.presentationComponent by lazy {
    presentationComponent
}

val Fragment.presentationComponent by lazy {
    presentationComponent
}

val <T, V> PresentationMapper<T, V>.presentationComponent by lazy {
    presentationComponent
}

