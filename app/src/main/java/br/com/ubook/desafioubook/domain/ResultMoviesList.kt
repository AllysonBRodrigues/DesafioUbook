package br.com.ubook.desafioubook.domain

import android.os.Parcel
import android.os.Parcelable

class ResultMoviesList(val page: Int,
                       val total_result: Int,
                       val results: List<Movie>) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readInt(), parcel.createTypedArrayList(Movie.CREATOR)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(page)
        parcel.writeInt(total_result)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResultMoviesList> {
        override fun createFromParcel(parcel: Parcel): ResultMoviesList {
            return ResultMoviesList(parcel)
        }

        override fun newArray(size: Int): Array<ResultMoviesList?> {
            return arrayOfNulls(size)
        }
    }
}