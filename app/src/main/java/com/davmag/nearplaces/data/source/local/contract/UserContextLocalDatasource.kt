package com.davmag.nearplaces.data.source.local.contract

import com.davmag.nearplaces.domain.model.UserContext
import io.reactivex.Maybe

interface UserContextLocalDatasource {
    fun single() : Maybe<UserContext>
    fun patch(userContext: UserContext) : Maybe<Any>
}