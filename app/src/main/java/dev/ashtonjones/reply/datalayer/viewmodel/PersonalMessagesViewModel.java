package dev.ashtonjones.reply.datalayer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;

import java.util.ArrayList;

import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datalayer.repository.RepositoryInterface;
import dev.ashtonjones.reply.datamodels.MessageCard;

public class PersonalMessagesViewModel extends ViewModel {

    private RepositoryInterface firebaseRepository;

    private MutableLiveData<ArrayList<MessageCard>> personalMessagesLiveData;

    public PersonalMessagesViewModel() {

        firebaseRepository = new FirebaseRepository();

        personalMessagesLiveData = new MutableLiveData<>();
    }

    public LiveData<ArrayList<MessageCard>> getPersonalMessagesLiveData() {

        personalMessagesLiveData = firebaseRepository.getPersonalMessagesLiveData();

        return personalMessagesLiveData;
    }

    public void deletePersonalMessage(MessageCard messageToDelete) {

        firebaseRepository.deletePersonalMessage(messageToDelete);

    }

}
