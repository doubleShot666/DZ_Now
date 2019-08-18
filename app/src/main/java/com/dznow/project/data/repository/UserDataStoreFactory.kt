package com.dznow.project.data.repository

import com.dznow.project.data.net.UserRemoteDataStore
import com.dznow.project.device.UserLocalDataStore

class UserDataStoreFactory() {

    fun create(isCached : Boolean): UserDataStore{
        return if(isCached)
            UserLocalDataStore()
        else
            UserRemoteDataStore()
    }

}