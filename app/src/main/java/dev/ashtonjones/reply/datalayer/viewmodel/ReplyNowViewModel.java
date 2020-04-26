package dev.ashtonjones.reply.datalayer.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datalayer.repository.RepositoryInterface;
import dev.ashtonjones.reply.datamodels.MessageCard;

public class ReplyNowViewModel extends ViewModel {

    private RepositoryInterface firebaseRepository;
    private MutableLiveData<MessageCard> replyLaterMessageLiveData;

    public ReplyNowViewModel() {

        firebaseRepository = new FirebaseRepository();

        replyLaterMessageLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<MessageCard> getReplyLaterMessageLiveData() {

        replyLaterMessageLiveData = firebaseRepository.getReplyLaterMessage();

        return replyLaterMessageLiveData;
    }
}
