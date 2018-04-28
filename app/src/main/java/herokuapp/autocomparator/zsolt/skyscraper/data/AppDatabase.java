package herokuapp.autocomparator.zsolt.skyscraper.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import herokuapp.autocomparator.zsolt.skyscraper.model.CarDetailsEntity;

@Database(entities = {CarDetailsEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CarDataDao gradeDao();
}