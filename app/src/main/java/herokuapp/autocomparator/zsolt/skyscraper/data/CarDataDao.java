package herokuapp.autocomparator.zsolt.skyscraper.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import herokuapp.autocomparator.zsolt.skyscraper.model.CarDetail;

@Dao
public interface CarDataDao {
    @Query("SELECT * FROM car_data")
    public List<CarDetail> getAllGrades();

    @Insert
    void insertAll(CarDetail... carDetails);

    @Delete
    void delete(CarDetail carDetail);
}
