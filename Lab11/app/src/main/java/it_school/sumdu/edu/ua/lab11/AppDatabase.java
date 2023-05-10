package it_school.sumdu.edu.ua.lab11;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Document.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DocDao documentDao();
    private static final String DATABASE_NAME = "app_database";
    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new DbAsync(instance).execute();
                }
            };

    private static class DbAsync extends AsyncTask<Void, Void, Void> {

        private final DocDao mDao;

        DbAsync(AppDatabase db) {
            mDao = db.documentDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            if (mDao.getAnyDocument().length < 1 ) {
                for (int i = 0; i < 1; i++) {
                    Document document = new Document("");
                    mDao.insert(document);
                }
            }
            return null;
        }
    }
}
