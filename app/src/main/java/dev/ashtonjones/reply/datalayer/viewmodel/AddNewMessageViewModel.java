package dev.ashtonjones.reply.datalayer.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;

import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datalayer.repository.RepositoryInterface;
import dev.ashtonjones.reply.datamodels.MessageCard;

public class AddNewMessageViewModel extends ViewModel {

    private RepositoryInterface firebaseRepository;

    public AddNewMessageViewModel() {

        firebaseRepository = new FirebaseRepository();

    }

    public void addPersonalMessage(MessageCard messageToAdd) {

        firebaseRepository.addPersonalMessage(messageToAdd);

    }


    public void addSocialMessage(MessageCard messageToAdd) {

        firebaseRepository.addSocialMessage(messageToAdd);

    }

    public void addBusinessMessage(MessageCard messageToAdd) {

        firebaseRepository.addBusinessMessage(messageToAdd);

    }

    public void addPlus1Message(MessageCard messageToAdd) {

        firebaseRepository.addPlus1Message(messageToAdd);

    }

    public void addPlus2Message(MessageCard messageToAdd) {

        firebaseRepository.addPlus2Message(messageToAdd);

    }
}
