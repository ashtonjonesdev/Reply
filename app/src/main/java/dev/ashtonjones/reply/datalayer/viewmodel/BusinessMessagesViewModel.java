package dev.ashtonjones.reply.datalayer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datalayer.repository.RepositoryInterface;
import dev.ashtonjones.reply.datamodels.MessageCard;

public class BusinessMessagesViewModel extends ViewModel {

    private RepositoryInterface firebaseRepository;

    private MutableLiveData<ArrayList<MessageCard>> businessMessagesLiveData;

    public BusinessMessagesViewModel() {

        firebaseRepository = new FirebaseRepository();

        businessMessagesLiveData = new MutableLiveData<>();
    }

    public LiveData<ArrayList<MessageCard>> getBusinessMessagesLiveData() {

        businessMessagesLiveData = firebaseRepository.getBusinessMessages();

        return businessMessagesLiveData;
    }
}
