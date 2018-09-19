package br.com.ubook.desafioubook.domain

import android.os.Parcel
import android.os.Parcelable

data class Movie(val title: String?,
                 val poster_path: String?,
                 val overview: String?,
                 val vote_average: Float,
                 val original_language: String?,
                 val release_date: String?
                 ) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readFloat(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(poster_path)
        parcel.writeString(overview)
        parcel.writeFloat(vote_average)
        parcel.writeString(original_language)
        parcel.writeString(release_date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}