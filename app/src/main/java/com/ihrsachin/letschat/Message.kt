package com.ihrsachin.letschat

class Message {


    private var text: String? = null
    private var name: String? = "Anonymous"
    private var photoUri: String? = null
    private var userName: String? = "Anonymous"

    constructor(text: String?, name: String?, photoUri: String?, userName : String?) {
        this.text = text
        this.name = name
        this.photoUri = photoUri
        this.userName = userName
    }
    constructor()

    public fun getName() : String{
        return name!!
    }
    public fun getText() : String{
        return text!!
    }
    public fun getPhotoUri() : String{
        return photoUri!!
    }
    public fun getUserName() : String{
        return userName!!
    }

    public fun setName(name : String){
        this.name = name
    }
    public fun setText(text : String){
        this.text = text
    }
    public fun setPhotoUri(photoUri : String){
        this.photoUri = photoUri
    }
    public fun setUserName(userName: String){
        this.userName = userName
    }
}