package dev.ashtonjones.reply.datalayer.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datamodels.MessageCard;

public class Plus1MessagesViewModel extends ViewModel {

    FirebaseRepository firebaseRepository;

    private MutableLiveData<ArrayList<MessageCard>> plus1Messages;

    public Plus1MessagesViewModel() {
        firebaseRepository = new FirebaseRepository();

        if(plus1Messages == null) {

            plus1Messages = new MutableLiveData<>();

            loadPlus1Messages();

        }
    }

    public MutableLiveData<ArrayList<MessageCard>> getPlus1Messages() {
        return plus1Messages;
    }

    public void loadPlus1Messages() {

        // Get messages from Firebase
//        firebaseRepository.get
        ArrayList<MessageCard> firebasePersonalMessages = new ArrayList<>();


        // Set the MutableLiveData instance to the data received from Cloud Firestore
        plus1Messages.setValue(firebasePersonalMessages);

    }
}
