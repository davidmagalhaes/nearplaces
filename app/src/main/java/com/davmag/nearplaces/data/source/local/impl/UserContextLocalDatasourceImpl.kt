package com.davmag.nearplaces.data.source.local.impl

import com.davmag.nearplaces.data.source.local.contract.UserContextLocalDatasource
import com.davmag.nearplaces.domain.model.UserContext
import com.google.gson.Gson
import com.orhanobut.hawk.HawkFacade
import io.reactivex.Maybe

class UserContextLocalDatasourceImpl(
    private val gson : Gson,
    private val hawk: HawkFacade
): UserContextLocalDatasource {
    companion object {
        const val USER_CONTEXT_HAWK_KEY = "user_context"
    }

    override fun single(): Maybe<UserContext> {
        return Maybe.fromCallable {
            gson.fromJson(
                hawk.get(USER_CONTEXT_HAWK_KEY, "{}"),
                UserContext::class.java
            )
        }
    }

    override fun patch(userContext: UserContext) : Maybe<Any> {
        return Maybe.fromCallable {
            hawk.put(USER_CONTEXT_HAWK_KEY, gson.toJson(userContext))
        }
    }

}