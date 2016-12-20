package com.asuper.zhihudailynews.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Super on 2016/8/4.
 */
public class DailyBean implements Parcelable {
    private int type;

    private int id;

    private String ga_prefix;

    private String title;

    private List<String> images;

    private boolean multipic;

    private boolean isRead = false;

    private String Date;


    public String getDate() {

        return Date;
    }

    public void setDate(String date) {

        Date = date;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getGa_prefix() {

        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {

        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public List<String> getImages() {

        return images;
    }

    public void setImages(List<String> images) {

        this.images = images;
    }

    public boolean isRead() {

        return isRead;
    }

    public void setRead(boolean read) {

        isRead = read;
    }

    public boolean isMultipic() {

        return multipic;
    }

    public void setMultipic(boolean multipic) {

        this.multipic = multipic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeInt(this.id);
        dest.writeString(this.ga_prefix);
        dest.writeString(this.title);
        dest.writeStringList(this.images);
        dest.writeByte(isRead ? (byte) 1 : (byte) 0);
    }

    protected DailyBean(Parcel in) {
        type = in.readInt();
        id = in.readInt();
        ga_prefix = in.readString();
        title = in.readString();
        images = in.createStringArrayList();
        multipic = in.readByte() != 0;
        isRead = in.readByte() != 0;
        Date = in.readString();
    }

    public static final Creator<DailyBean> CREATOR = new Creator<DailyBean>() {
        @Override
        public DailyBean createFromParcel(Parcel in) {
            return new DailyBean(in);
        }

        @Override
        public DailyBean[] newArray(int size) {
            return new DailyBean[size];
        }
    };
}
