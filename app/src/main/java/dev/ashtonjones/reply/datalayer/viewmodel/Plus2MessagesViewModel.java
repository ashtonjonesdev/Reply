package dev.ashtonjones.reply.datalayer.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datamodels.MessageCard;

public class Plus2MessagesViewModel extends ViewModel {

    FirebaseRepository firebaseRepository;

    private MutableLiveData<ArrayList<MessageCard>> plus2Messages;

    public Plus2MessagesViewModel() {
        firebaseRepository = new FirebaseRepository();

        if(plus2Messages == null) {

            plus2Messages = new MutableLiveData<>();

            loadPlus2Messages();

        }
    }

    public MutableLiveData<ArrayList<MessageCard>> getPlus2Messages() {
        return plus2Messages;
    }

    public void loadPlus2Messages() {

        // Get messages from Firebase
//        firebaseRepository.get
        ArrayList<MessageCard> firebasePersonalMessages = new ArrayList<>();


        // Set the MutableLiveData instance to the data received from Cloud Firestore
        plus2Messages.setValue(firebasePersonalMessages);

    }
}
