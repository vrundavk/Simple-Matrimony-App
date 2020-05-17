package com.example.uandi

class Female {
    private var femaleImage: String? = null
    private var femaleName: String? = null
    private var  femaleBio: String?=null
    private var femaleDob: String?= null
    private var femaleOccupation:String?=null
    private var  femalePhone: String?=null
    private var femaleAddress: String?=null
    private var femaleEdu: String?=null

    constructor(){

    }

    constructor(
        fImage: String,
        fName: String,
        fBio:String,
        fDob:String,
        fOccupation:String,
        fPhone: String,
        fAddress:String,
        fEdu:String
    ){
        this.femaleImage= fImage
        this.femaleName=fName
        this.femaleBio=fBio
        this.femaleDob=fDob
        this.femaleOccupation=fOccupation
        this.femalePhone=fPhone
        this.femaleAddress=fAddress
        this.femaleEdu=fEdu

    }

    fun getImageUrlF(): String? {
        return femaleImage
    }

    fun setImageUrlF(fImage: String) {
        femaleImage = fImage
    }

    fun getNameF(): String? {
        return femaleName
    }

    fun setNameF(fName: String) {
        femaleName = fName
    }

    fun getBioF(): String? {
        return femaleBio
    }

    fun setBioF(fBio: String) {
        femaleBio = fBio
    }
    fun getDobF(): String? {
        return femaleDob
    }

    fun setDobF(fDob: String) {
        femaleDob = fDob
    }
    fun getOccupationF(): String? {
        return femaleOccupation
    }

    fun setOccupationF(fOccupation: String) {
        femaleOccupation = fOccupation
    }
    fun getPhoneF(): String? {
        return femalePhone
    }

    fun setPhoneF(fPhone: String) {
        femalePhone = fPhone
    }

    fun getAddressF(): String? {
        return femaleAddress
    }

    fun setAddressF(fAddress: String) {
        femaleAddress = fAddress
    }

    fun getEduF(): String? {
        return femaleEdu
    }

    fun setEduF(fEdu: String) {
        femaleEdu = fEdu
    }



}