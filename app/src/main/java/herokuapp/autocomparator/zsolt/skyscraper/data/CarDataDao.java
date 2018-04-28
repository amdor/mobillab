package herokuapp.autocomparator.zsolt.skyscraper.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import herokuapp.autocomparator.zsolt.skyscraper.model.CarDetailsEntity;

@Dao
public interface CarDataDao {
    @Query("SELECT * FROM car_data")
    public List<CarDetailsEntity> getAllCarData();

    @Insert
    void insertAll(CarDetailsEntity... carDetails);

    @Delete
    void delete(CarDetailsEntity carDetail);
}
