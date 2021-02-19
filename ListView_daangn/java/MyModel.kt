package com.example.listview_daangn

import android.os.Parcel
import android.os.Parcelable
import android.util.Log

data class MyModel(var image:Int, var title:String?, var place:String?, var price : String?="무료나눔", var content:String? = "상품 좋아요:)"):Parcelable
{
    val TAG: String = "로그"


    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    // 기본 생성자
    init {
        image=this.image
        title=this.title
        place=this.place
        price=this.price
        content=this.content
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        image?.let { parcel.writeInt(it) }
        parcel.writeString(title)
        parcel.writeString(place)
        parcel.writeString(price)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyModel> {
        override fun createFromParcel(parcel: Parcel): MyModel {
            return MyModel(parcel)
        }

        override fun newArray(size: Int): Array<MyModel?> {
            return arrayOfNulls(size)
        }
    }


}
