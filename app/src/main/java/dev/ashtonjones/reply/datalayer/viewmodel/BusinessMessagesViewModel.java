package dev.ashtonjones.reply.datalayer.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datamodels.MessageCard;

public class BusinessMessagesViewModel extends ViewModel {

    FirebaseRepository firebaseRepository;

    private MutableLiveData<ArrayList<MessageCard>> businessMessages;

    public BusinessMessagesViewModel() {
        firebaseRepository = new FirebaseRepository();

        if(businessMessages == null) {

            businessMessages = new MutableLiveData<>();

            loadBusinessMessages();

        }
    }

    public MutableLiveData<ArrayList<MessageCard>> getBusinessMessages() {
        return businessMessages;
    }

    public void loadBusinessMessages() {

        // Get messages from Firebase
//        firebaseRepository.get
        ArrayList<MessageCard> firebasePersonalMessages = new ArrayList<>();


        // Set the MutableLiveData instance to the data received from Cloud Firestore
        businessMessages.setValue(firebasePersonalMessages);

    }
}
