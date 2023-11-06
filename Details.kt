package com.example.newrecipe.Model

import android.os.Parcel
import android.os.Parcelable

data class Details(
    val postId: String,
    val title: String,
    val imageUrl: String,
    val desc: String,       // Description of the recipe
    val likes: Int,         // Number of likes
    val likeCount: Int,     // Total like count
    val category: String    // Category of the recipe
) : Parcelable {

    constructor() : this("", "", "", "", 0, 0, "")

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(postId)
        parcel.writeString(title)
        parcel.writeString(imageUrl)
        parcel.writeString(desc)
        parcel.writeInt(likes)
        parcel.writeInt(likeCount)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe_model> {
        override fun createFromParcel(parcel: Parcel): Recipe_model {
            return Recipe_model(parcel)
        }

        override fun newArray(size: Int): Array<Recipe_model?> {
            return arrayOfNulls(size)
        }
    }
}
