package dev.ashtonjones.reply.datalayer.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import dev.ashtonjones.reply.datamodels.MessageCard;
import dev.ashtonjones.reply.datamodels.User;

public class FirebaseRepository implements RepositoryInterface {

    private static final String LOG_TAG = FirebaseRepository.class.getSimpleName();

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private MutableLiveData<ArrayList<MessageCard>> personalMessagesLiveData;
    private MutableLiveData<ArrayList<MessageCard>> socialMessagesLiveData;
    private MutableLiveData<ArrayList<MessageCard>> businessMessagesLiveData;
    private MutableLiveData<ArrayList<MessageCard>> plus1MessagesLiveData;
    private MutableLiveData<ArrayList<MessageCard>> plus2MessagesLiveData;


    /**
     *
     * GET METHODS
     *
     * @return
     */
    @Override
    public MutableLiveData<ArrayList<MessageCard>> getPersonalMessagesLiveData() {


        // Intialize the LiveData variable
        personalMessagesLiveData = new MutableLiveData<>();

//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        String userID = firebaseUser.getUid();
//
//        Log.d(LOG_TAG, "UserID: " + userID);

        firebaseFirestore.collection("users").document(getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();

                    if(documentSnapshot.exists()) {

                        User user = documentSnapshot.toObject(User.class);

                        // List that will hold the messages
                        ArrayList<MessageCard> messages = new ArrayList<>();

                        messages = user.getPersonalMessages();

                        Log.d(LOG_TAG, "Personal Messages: " + messages.toString());

                        personalMessagesLiveData.postValue(messages);

                    }

                }

                else {

                    Log.d(LOG_TAG, "UNSUCCESSFUL");

                }
            }
        });



        return personalMessagesLiveData;
    }

    @Override
    public MutableLiveData<ArrayList<MessageCard>> getSocialMessagesLiveData() {


        // Intialize the LiveData variable
        socialMessagesLiveData = new MutableLiveData<>();

//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        String userID = firebaseUser.getUid();
//
//        Log.d(LOG_TAG, userID);

        firebaseFirestore.collection("users").document(getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();

                    if(documentSnapshot.exists()) {

                        User user = documentSnapshot.toObject(User.class);

                        // List that will hold the messages
                        ArrayList<MessageCard> messages = new ArrayList<>();

                        messages = user.getSocialMessages();

                        Log.d(LOG_TAG, "Social Messages: " + messages.toString());

                        socialMessagesLiveData.postValue(messages);

                    }

                }

                else {

                    Log.d(LOG_TAG, "UNSUCCESSFUL");

                }
            }
        });



        return socialMessagesLiveData;
    }

    @Override
    public MutableLiveData<ArrayList<MessageCard>> getBusinessMessagesLiveData() {


        // Intialize the LiveData variable
        businessMessagesLiveData = new MutableLiveData<>();

//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        String userID = firebaseUser.getUid();
//
//        Log.d(LOG_TAG, userID);

        firebaseFirestore.collection("users").document(getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();

                    if(documentSnapshot.exists()) {

                        User user = documentSnapshot.toObject(User.class);

                        // List that will hold the messages
                        ArrayList<MessageCard> messages = new ArrayList<>();

                        messages = user.getBusinessMessages();

                        Log.d(LOG_TAG, "Business Messages: " + messages.toString());

                        businessMessagesLiveData.postValue(messages);

                    }

                }

                else {

                    Log.d(LOG_TAG, "UNSUCCESSFUL");

                }
            }
        });



        return businessMessagesLiveData;
        }

    @Override
    public MutableLiveData<ArrayList<MessageCard>> getPlus1MessagesLiveData() {


        // Intialize the LiveData variable
        plus1MessagesLiveData = new MutableLiveData<>();

//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        String userID = firebaseUser.getUid();
//
//        Log.d(LOG_TAG, userID);

        firebaseFirestore.collection("users").document(getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();

                    if(documentSnapshot.exists()) {

                        User user = documentSnapshot.toObject(User.class);

                        // List that will hold the messages
                        ArrayList<MessageCard> messages = new ArrayList<>();

                        messages = user.getPlus1Messages();

                        Log.d(LOG_TAG, "Plus 1 Messages: " + messages.toString());

                        plus1MessagesLiveData.postValue(messages);

                    }

                }

                else {

                    Log.d(LOG_TAG, "UNSUCCESSFUL");

                }
            }
        });



        return plus1MessagesLiveData;
    }

    @Override
    public MutableLiveData<ArrayList<MessageCard>> getPlus2MessagesLiveData() {

        // Intialize the LiveData variable
        plus2MessagesLiveData = new MutableLiveData<>();

//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        String userID = firebaseUser.getUid();
//
//        Log.d(LOG_TAG, userID);

        firebaseFirestore.collection("users").document(getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();

                    if(documentSnapshot.exists()) {

                        User user = documentSnapshot.toObject(User.class);

                        // List that will hold the messages
                        ArrayList<MessageCard> messages = new ArrayList<>();

                        messages = user.getPlus2Messages();

                        Log.d(LOG_TAG, "Plus 2 Messages: " + messages.toString());

                        plus2MessagesLiveData.postValue(messages);

                    }

                }

                else {

                    Log.d(LOG_TAG, "UNSUCCESSFUL");

                }
            }
        });



        return plus2MessagesLiveData;

    }

    /**
     *
     * EDIT METHODS
     *
     * @param oldMessage
     * @param newMessage
     */

    @Override
    public void editPersonalMessage(MessageCard oldMessage, MessageCard newMessage) {

        // Use the index to delete the corresponding MessageCard in the ArrayList field in FB
        DocumentReference documentReference = getUserDocument(getUid());

        // Delete the old message
        documentReference.update("personalMessages", FieldValue.arrayRemove(oldMessage));

        // Add the new message
        documentReference.update("personalMessages", FieldValue.arrayUnion(newMessage));

    }

    @Override
    public void editSocialMessage(MessageCard oldMessage, MessageCard newMessage) {

        // Use the index to delete the corresponding MessageCard in the ArrayList field in FB
        DocumentReference documentReference = getUserDocument(getUid());

        // Delete the old message
        documentReference.update("socialMessages", FieldValue.arrayRemove(oldMessage));

        // Add the new message
        documentReference.update("socialMessages", FieldValue.arrayUnion(newMessage));

    }

    @Override
    public void editBusinessMessage(MessageCard oldMessage, MessageCard newMessage) {

        // Use the index to delete the corresponding MessageCard in the ArrayList field in FB
        DocumentReference documentReference = getUserDocument(getUid());

        // Delete the old message
        documentReference.update("businessMessages", FieldValue.arrayRemove(oldMessage));

        // Add the new message
        documentReference.update("businessMessages", FieldValue.arrayUnion(newMessage));

    }

    @Override
    public void editPlus1Message(MessageCard oldMessage, MessageCard newMessage) {

        // Use the index to delete the corresponding MessageCard in the ArrayList field in FB
        DocumentReference documentReference = getUserDocument(getUid());

        // Delete the old message
        documentReference.update("plus1Messages", FieldValue.arrayRemove(oldMessage));

        // Add the new message
        documentReference.update("plus1Messages", FieldValue.arrayUnion(newMessage));

    }

    @Override
    public void editPlus2Message(MessageCard oldMessage, MessageCard newMessage) {

        // Use the index to delete the corresponding MessageCard in the ArrayList field in FB
        DocumentReference documentReference = getUserDocument(getUid());

        // Delete the old message
        documentReference.update("plus2Messages", FieldValue.arrayRemove(oldMessage));

        // Add the new message
        documentReference.update("plus2Messages", FieldValue.arrayUnion(newMessage));

    }

    /**
     *
     * DELETE METHODS
     *
     * @param messageToDelete
     */

    @Override
    public void deletePersonalMessage(MessageCard messageToDelete) {

        DocumentReference documentReference = getUserDocument(getUid());

        documentReference.update("personalMessages", FieldValue.arrayRemove(messageToDelete));

    }

    @Override
    public void deleteSocialMessage(MessageCard messageToDelete) {

        DocumentReference documentReference = getUserDocument(getUid());

        documentReference.update("socialMessages", FieldValue.arrayRemove(messageToDelete));

    }

    @Override
    public void deleteBusinessMessage(MessageCard messageToDelete) {

        DocumentReference documentReference = getUserDocument(getUid());

        documentReference.update("businessMessages", FieldValue.arrayRemove(messageToDelete));

    }

    @Override
    public void deletePlus1Message(MessageCard messageToDelete) {

        DocumentReference documentReference = getUserDocument(getUid());

        documentReference.update("plus1Messages", FieldValue.arrayRemove(messageToDelete));

    }

    @Override
    public void deletePlus2Message(MessageCard messageToDelete) {

        DocumentReference documentReference = getUserDocument(getUid());

        documentReference.update("plus2Messages", FieldValue.arrayRemove(messageToDelete));

    }

    /**
     *
     * ADD METHODS
     *
     * @param messageToAdd
     */

    @Override
    public void addPersonalMessage(MessageCard messageToAdd) {

        DocumentReference documentReference = getUserDocument(getUid());

        documentReference.update("personalMessages", FieldValue.arrayUnion(messageToAdd));

    }


    @Override
    public void addSocialMessage(MessageCard messageToAdd) {

        DocumentReference documentReference = getUserDocument(getUid());

        documentReference.update("socialMessages", FieldValue.arrayUnion(messageToAdd));

    }

    @Override
    public void addBusinessMessage(MessageCard messageToAdd) {

        DocumentReference documentReference = getUserDocument(getUid());

        documentReference.update("businessMessages", FieldValue.arrayUnion(messageToAdd));

    }

    @Override
    public void addPlus1Message(MessageCard messageToAdd) {

        DocumentReference documentReference = getUserDocument(getUid());

        documentReference.update("plus1Messages", FieldValue.arrayUnion(messageToAdd));

    }

    @Override
    public void addPlus2Message(MessageCard messageToAdd) {

        DocumentReference documentReference = getUserDocument(getUid());

        documentReference.update("plus2Messages", FieldValue.arrayUnion(messageToAdd));

    }

    public String getUid() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userID = firebaseUser.getUid();

        return userID;

    }

    public DocumentReference getUserDocument(String uID) {

        DocumentReference userDocument = firebaseFirestore.collection("users").document(uID);

        return userDocument;

    }


}
