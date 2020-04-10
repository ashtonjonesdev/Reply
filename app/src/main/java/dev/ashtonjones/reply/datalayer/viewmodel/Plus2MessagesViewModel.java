package dev.ashtonjones.reply.datalayer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datalayer.repository.RepositoryInterface;
import dev.ashtonjones.reply.datamodels.MessageCard;

public class Plus2MessagesViewModel extends ViewModel {

    private RepositoryInterface firebaseRepository;

    private MutableLiveData<ArrayList<MessageCard>> plus2MessagesLiveData;

    public Plus2MessagesViewModel() {

        firebaseRepository = new FirebaseRepository();

        plus2MessagesLiveData = new MutableLiveData<>();
    }

    public LiveData<ArrayList<MessageCard>> getPlus2MessagesLiveData() {

        plus2MessagesLiveData = firebaseRepository.getPlus2Messages();

        return plus2MessagesLiveData;
    }
}
