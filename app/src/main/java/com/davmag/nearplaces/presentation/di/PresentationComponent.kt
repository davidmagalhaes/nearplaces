package com.davmag.nearplaces.presentation.di

import br.com.davidmag.bingewatcher.presentation.di.PresentationScope
import com.davmag.nearplaces.infra.di.ApplicationComponent
import com.davmag.nearplaces.presentation.view.MainActivity
import dagger.Component

@PresentationScope
@Component(
    dependencies = [
        ApplicationComponent::class
    ],
    modules = [
        ViewModelModule::class
    ]
)
interface PresentationComponent {
    fun inject(mainActivity: MainActivity)
}