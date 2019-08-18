package com.dznow.project.data.entity


import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.reactivex.annotations.NonNull

@Entity(tableName = "users")
class UserEntity(
    @PrimaryKey @NonNull var id : String
)
