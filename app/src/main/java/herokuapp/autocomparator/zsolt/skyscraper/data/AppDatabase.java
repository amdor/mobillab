package herokuapp.autocomparator.zsolt.skyscraper.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import herokuapp.autocomparator.zsolt.skyscraper.model.CarDetail;

@Database(entities = {CarDetail.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CarDataDao gradeDao();
}