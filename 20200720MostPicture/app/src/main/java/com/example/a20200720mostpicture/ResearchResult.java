package com.example.a20200720mostpicture;

import android.os.Parcel;
import android.os.Parcelable;

public class ResearchResult implements Parcelable {
    private int count;
    private Integer imageFileid;
    private String name;

    public ResearchResult(String name) {
        this.name = name;
    }

    public ResearchResult(int count, Integer imageFileid, String name) {
        this.count = count;
        this.imageFileid = imageFileid;
        this.name = name;
    }

    protected ResearchResult(Parcel in) {
        count = in.readInt();
        if (in.readByte() == 0) {
            imageFileid = null;
        } else {
            imageFileid = in.readInt();
        }
        name = in.readString();
    }

    public static final Creator<ResearchResult> CREATOR = new Creator<ResearchResult>() {
        @Override
        public ResearchResult createFromParcel(Parcel in) {
            return new ResearchResult(in);
        }

        @Override
        public ResearchResult[] newArray(int size) {
            return new ResearchResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(count);
        if (imageFileid == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(imageFileid);
        }
        parcel.writeString(name);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getImageFileid() {
        return imageFileid;
    }

    public void setImageFileid(Integer imageFileid) {
        this.imageFileid = imageFileid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
