package dev.ashtonjones.reply.datalayer.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import dev.ashtonjones.reply.datamodels.MessageCard;

public interface RepositoryInterface {

    // GET METHODS
    MutableLiveData<ArrayList<MessageCard>> getPersonalMessagesLiveData();

    MutableLiveData<ArrayList<MessageCard>> getSocialMessagesLiveData();

    MutableLiveData<ArrayList<MessageCard>> getBusinessMessagesLiveData();

    MutableLiveData<ArrayList<MessageCard>> getPlus1MessagesLiveData();

    MutableLiveData<ArrayList<MessageCard>> getPlus2MessagesLiveData();


    // DELETE METHODS
    void deletePersonalMessage(MessageCard messageToDelete);

    void deleteSocialMessage(MessageCard messageToDelete);

    void deleteBusinessMessage(MessageCard messageToDelete);

    void deletePlus1Message(MessageCard messageToDelete);

    void deletePlus2Message(MessageCard messageToDelete);


    // ADD METHODS
    void addPersonalMessage(MessageCard messageToAdd);

    void addSocialMessage(MessageCard messageToAdd);

    void addBusinessMessage(MessageCard messageToAdd);

    void addPlus1Message(MessageCard messageToAdd);

    void addPlus2Message(MessageCard messageToAdd);


    // EDIT METHODS
    void editPersonalMessage(MessageCard oldMessage, MessageCard newMessage);

    void editSocialMessage(MessageCard oldMessage, MessageCard newMessage);

    void editBusinessMessage(MessageCard oldMessage, MessageCard newMessage);

    void editPlus1Message(MessageCard oldMessage, MessageCard newMessage);

    void editPlus2Message(MessageCard oldMessage, MessageCard newMessage);

    // REPLY LATER METHODS
    void updateReplyLaterMessage(MessageCard selectedMessage);

    MutableLiveData<MessageCard> getReplyLaterMessage();



}
