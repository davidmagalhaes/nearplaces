package com.davmag.nearplaces.presentation.common

import android.os.Bundle
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    open fun initViewModel(args: Bundle?){}
}