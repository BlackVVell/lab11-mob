package it_school.sumdu.edu.ua.lab11;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class DocRepository {
    private AppDatabase appDatabase;
    private DocDao mDocDao;
    private LiveData<Document> documentLiveData;

    public DocRepository(@NonNull Application application) {
        appDatabase = AppDatabase.getInstance(application);
        mDocDao = appDatabase.documentDao();
        documentLiveData = mDocDao.getDocument();
    }

    LiveData<Document> getDocument() {
        return documentLiveData;
    }

    public void updateDocument(Document document)  {
        new updateDocAsyncTask(mDocDao).execute(document);
    }

    private static class updateDocAsyncTask extends AsyncTask<Document, Void, Void> {
        private DocDao mAsyncTaskDao;

        updateDocAsyncTask(DocDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Document... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}
