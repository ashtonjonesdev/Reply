package dev.ashtonjones.reply.datalayer.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import dev.ashtonjones.reply.datamodels.MessageCard;

public interface RepositoryInterface {

    MutableLiveData<ArrayList<MessageCard>> getPersonalMessages();

    MutableLiveData<ArrayList<MessageCard>> getSocialMessages();

    MutableLiveData<ArrayList<MessageCard>> getBusinessMessages();

    MutableLiveData<ArrayList<MessageCard>> getPlus1Messages();

    MutableLiveData<ArrayList<MessageCard>> getPlus2Messages();

    void editMessage(MessageCard oldMessage, MessageCard newMessage);


}
