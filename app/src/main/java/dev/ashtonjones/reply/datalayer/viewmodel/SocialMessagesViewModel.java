package dev.ashtonjones.reply.datalayer.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datamodels.MessageCard;

public class SocialMessagesViewModel extends ViewModel {

    FirebaseRepository firebaseRepository;

    private MutableLiveData<ArrayList<MessageCard>> socialMessages;

    public SocialMessagesViewModel() {
        firebaseRepository = new FirebaseRepository();

        if(socialMessages == null) {

            socialMessages = new MutableLiveData<>();

            loadSocialMessages();

        }
    }

    public MutableLiveData<ArrayList<MessageCard>> getSocialMessages() {
        return socialMessages;
    }

    public void loadSocialMessages() {

        // Get messages from Firebase
//        firebaseRepository.get
        ArrayList<MessageCard> firebasePersonalMessages = new ArrayList<>();


        // Set the MutableLiveData instance to the data received from Cloud Firestore
        socialMessages.setValue(firebasePersonalMessages);

    }
}
