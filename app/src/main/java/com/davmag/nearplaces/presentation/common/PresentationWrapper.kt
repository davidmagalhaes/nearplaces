package com.davmag.nearplaces.presentation.common

class PresentationWrapper<T>(
    override val viewType: Int,
    val value : T? = null
) : PresentationObject