package com.example.sparta_team_searchyoutubedata.videoDetail

import android.os.Parcel
import android.os.Parcelable

data class VideoDetailItem (
    val thumbnail: String,
    val title: String,
    val description: String,
    val isLiked: Boolean
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(thumbnail)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeByte(if (isLiked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VideoDetailItem> {
        override fun createFromParcel(parcel: Parcel): VideoDetailItem {
            return VideoDetailItem(parcel)
        }

        override fun newArray(size: Int): Array<VideoDetailItem?> {
            return arrayOfNulls(size)
        }
    }
}