package dev.ashtonjones.reply.datalayer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datalayer.repository.RepositoryInterface;
import dev.ashtonjones.reply.datamodels.MessageCard;

public class SocialMessagesViewModel extends ViewModel {

    private RepositoryInterface firebaseRepository;

    private MutableLiveData<ArrayList<MessageCard>> socialMessagesLiveData;

    public SocialMessagesViewModel() {

        firebaseRepository = new FirebaseRepository();

        socialMessagesLiveData = new MutableLiveData<>();
    }

    public LiveData<ArrayList<MessageCard>> getSocialMessagesLiveData() {

        socialMessagesLiveData = firebaseRepository.getSocialMessages();

        return socialMessagesLiveData;
    }
}
