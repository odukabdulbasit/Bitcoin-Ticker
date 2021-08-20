package com.odukabdulbasit.bitcointicker.listsearch

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListSearchModel(val id : String?,
                           val symbol : String?,
                           val name : String?): Parcelable

