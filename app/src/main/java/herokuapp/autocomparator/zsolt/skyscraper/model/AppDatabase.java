package herokuapp.autocomparator.zsolt.skyscraper.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Grade.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GradeDAO gradeDao();
}
