package dev.ashtonjones.reply.datalayer.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import dev.ashtonjones.reply.datamodels.MessageCard;

public interface RepositoryInterface {

    MutableLiveData<ArrayList<MessageCard>> getMessages();

}
