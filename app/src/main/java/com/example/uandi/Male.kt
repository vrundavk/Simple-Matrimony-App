package com.example.uandi

class Male {
    private var maleImage: String? = null
    private var maleName: String? = null
    private var  maleBio: String?=null
    private var maleDob: String?= null
    private var maleOccupation:String?=null
    private var  malePhone: String?=null
    private var maleAddress: String?=null
    private var maleEdu: String?=null

    constructor(){

    }

    constructor(
        mImage: String,
        mName: String,
        mBio:String,
        mDob:String,
        mOccupation:String,
        mPhone: String,
        mAddress:String,
        mEdu:String
        ){
        this.maleImage= mImage
        this.maleName=mName
        this.maleBio=mBio
        this.maleDob=mDob
        this.maleOccupation=mOccupation
        this.malePhone=mPhone
        this.maleAddress=mAddress
        this.maleEdu=mEdu

    }

    fun getImageUrl(): String? {
        return maleImage
    }

    fun setImageUrl(mImage: String) {
        maleImage = mImage
    }

    fun getName(): String? {
        return maleName
    }

    fun setName(mName: String) {
        maleName = mName
    }

    fun getBio(): String? {
        return maleBio
    }

    fun setBio(mBio: String) {
        maleBio = mBio
    }
    fun getDob(): String? {
        return maleDob
    }

    fun setDob(mDob: String) {
        maleDob = mDob
    }
    fun getOccupation(): String? {
        return maleOccupation
    }

    fun setOccupation(mOccupation: String) {
        maleOccupation = mOccupation
    }
    fun getPhone(): String? {
        return malePhone
    }

    fun setPhone(mPhone: String) {
        malePhone = mPhone
    }

    fun getAddress(): String? {
        return maleAddress
    }

    fun setAddress(mAddress: String) {
        maleAddress = mAddress
    }

    fun getEdu(): String? {
        return maleEdu
    }

    fun setEdu(mEdu: String) {
        maleEdu = mEdu
    }



}
