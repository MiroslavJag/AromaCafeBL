package aromacafe.com.aromacafebl.list;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by m.jagodic on 9.3.2017..
 */

public class ListItems implements Parcelable {

    private Long id;
    private String title;
    private String thumbnail;
    private String url;
    private String content_post;

    //empty constructor
    public ListItems(){}


    public ListItems(Parcel input){
        id = input.readLong();
        title = input.readString();
        thumbnail = input.readString();
        url = input.readString();
        content_post = input.readString();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent_post() {
        return content_post;
    }

    public void setContent_post(String content_post) {
        this.content_post = content_post;
    }




    public static final Creator<ListItems> CREATOR = new Creator<ListItems>() {
        @Override
        public ListItems createFromParcel(Parcel in) {
            return new ListItems(in);
        }

        @Override
        public ListItems[] newArray(int size) {
            return new ListItems[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(thumbnail);
        dest.writeString(title);
        dest.writeString(content_post);
    }
}