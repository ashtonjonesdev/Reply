package dev.ashtonjones.reply.datalayer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datalayer.repository.RepositoryInterface;
import dev.ashtonjones.reply.datamodels.MessageCard;

public class Plus1MessagesViewModel extends ViewModel {

    private RepositoryInterface firebaseRepository;

    private MutableLiveData<ArrayList<MessageCard>> plus1MessagesLiveData;

    public Plus1MessagesViewModel() {

        firebaseRepository = new FirebaseRepository();

        plus1MessagesLiveData = new MutableLiveData<>();
    }

    public LiveData<ArrayList<MessageCard>> getPlus1MessagesLiveData() {

        plus1MessagesLiveData = firebaseRepository.getPlus1MessagesLiveData();

        return plus1MessagesLiveData;
    }

    public void deletePlus1Message(MessageCard messageToDelete) {

        firebaseRepository.deletePlus1Message(messageToDelete);

    }

}
