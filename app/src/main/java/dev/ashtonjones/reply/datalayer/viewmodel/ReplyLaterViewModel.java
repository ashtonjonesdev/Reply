package dev.ashtonjones.reply.datalayer.viewmodel;

import androidx.lifecycle.ViewModel;

import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datalayer.repository.RepositoryInterface;
import dev.ashtonjones.reply.datamodels.MessageCard;

public class ReplyLaterViewModel extends ViewModel {

    private RepositoryInterface firebaseRepository;


    public ReplyLaterViewModel() {

        firebaseRepository = new FirebaseRepository();

    }

    public void updateReplyLaterMessage(MessageCard selectedMessage) {

        firebaseRepository.updateReplyLaterMessage(selectedMessage);

    }
}
