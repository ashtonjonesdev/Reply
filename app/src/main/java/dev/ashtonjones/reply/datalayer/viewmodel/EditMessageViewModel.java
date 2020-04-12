package dev.ashtonjones.reply.datalayer.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;

import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datalayer.repository.RepositoryInterface;
import dev.ashtonjones.reply.datamodels.MessageCard;

public class EditMessageViewModel extends ViewModel {

    private RepositoryInterface firebaseRepository;

    public EditMessageViewModel() {

        firebaseRepository = new FirebaseRepository();

    }

    public void editPersonalMessage(MessageCard oldMessage, MessageCard newMessage) {

        firebaseRepository.editPersonalMessage(oldMessage, newMessage);

    }

    public void editSocialMessage(MessageCard oldMessage, MessageCard newMessage) {

        firebaseRepository.editSocialMessage(oldMessage, newMessage);

    }

    public void editBusinessMessage(MessageCard oldMessage, MessageCard newMessage) {

        firebaseRepository.editBusinessMessage(oldMessage, newMessage);

    }

    public void editPlus1Message(MessageCard oldMessage, MessageCard newMessage) {

        firebaseRepository.editPlus1Message(oldMessage, newMessage);

    }

    public void editPlus2Message(MessageCard oldMessage, MessageCard newMessage) {

        firebaseRepository.editPlus2Message(oldMessage, newMessage);

    }
}
