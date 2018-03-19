package dtmweb.com.instashop.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mdmunirhossain on 3/13/18.
 */

public class ProductObject implements Parcelable {
    protected ProductObject(Parcel in) {
    }

    public static final Creator<ProductObject> CREATOR = new Creator<ProductObject>() {
        @Override
        public ProductObject createFromParcel(Parcel in) {
            return new ProductObject(in);
        }

        @Override
        public ProductObject[] newArray(int size) {
            return new ProductObject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
