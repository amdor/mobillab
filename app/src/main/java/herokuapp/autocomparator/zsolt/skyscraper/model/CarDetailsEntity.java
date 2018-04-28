package herokuapp.autocomparator.zsolt.skyscraper.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "car_data")
public class CarDetailsEntity {

    @PrimaryKey
    @NonNull
    public String uname;

    @Embedded
    public CarDetails carDetails;
}
