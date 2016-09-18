package com.example.shekhchilli.miwok;

/**
 * Created by shekh chilli on 9/14/2016.
 */
public class Word {
    private String mDefaulttranstion;
    private String mMiwokktranslation;
    private int mSoundResourceid;
    private int image = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String mDefaulttranstion, String mMiwokktranslation , int soundResourceid) {
        this.mDefaulttranstion = mDefaulttranstion;
        this.mMiwokktranslation = mMiwokktranslation;
        this.mSoundResourceid = soundResourceid;
    }

    public Word(String Defaulttranstion, String Miwokktranslation, int image, int soundResourceid) {
        this.mDefaulttranstion = Defaulttranstion;
        this.mMiwokktranslation = Miwokktranslation;
        this.image = image;
        this.mSoundResourceid = soundResourceid;
    }

    public String getDefaulttranstion() {
        return mDefaulttranstion;
    }

    public String getMiwokktranslation() {
        return mMiwokktranslation;
    }

    public int getImage() {
        return image;
    }

    public int getSoundResourceid() {
        return mSoundResourceid;
    }

    public boolean hasImage(){
         return image!=NO_IMAGE_PROVIDED;
    }
}
