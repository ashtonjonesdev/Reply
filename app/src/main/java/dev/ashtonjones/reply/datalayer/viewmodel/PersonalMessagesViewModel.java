package dev.ashtonjones.reply.datalayer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datamodels.MessageCard;

public class PersonalMessagesViewModel extends ViewModel {

    FirebaseRepository firebaseRepository;

    private MutableLiveData<ArrayList<MessageCard>> personalMessagesLiveData;

    public PersonalMessagesViewModel() {

        firebaseRepository = new FirebaseRepository();

        personalMessagesLiveData = new MutableLiveData<>();
    }

    public LiveData<ArrayList<MessageCard>> getPersonalMessagesLiveData() {

        if(personalMessagesLiveData.getValue() == null || personalMessagesLiveData.getValue().isEmpty()) {

            personalMessagesLiveData = firebaseRepository.getMessages();

        }

        return  personalMessagesLiveData;
    }

}
