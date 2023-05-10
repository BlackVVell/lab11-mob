package it_school.sumdu.edu.ua.lab11;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DocViewModel extends AndroidViewModel {
    private LiveData<Document> documentLiveData;
    private DocRepository mRepository;

    public DocViewModel(@NonNull Application application) {
        super(application);
        mRepository = new DocRepository(application);
        documentLiveData = mRepository.getDocument();
    }

    public LiveData<Document> getDocumentLiveData() {
        return documentLiveData;
    }

    public void updateDocument(Document document) {
        mRepository.updateDocument(document);
    }
}
