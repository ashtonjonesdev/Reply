package dev.ashtonjones.reply.datalayer.repository;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dev.ashtonjones.reply.datamodels.MessageCard;
import dev.ashtonjones.reply.datamodels.User;

public class FirebaseRepository implements RepositoryInterface {

    private static final String LOG_TAG = FirebaseRepository.class.getSimpleName();

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private MutableLiveData<ArrayList<MessageCard>> messagesLiveData;

    @Override
    public MutableLiveData<ArrayList<MessageCard>> getPersonalMessages() {

//        // Intialize the LiveData variable
//        messagesLiveData = new MutableLiveData<>();



//        firebaseFirestore.collection("personal_messages").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
////            @Override
////            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
////
////
////                // Get all documents from the collection
////                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
////
////                // Iterate through all the documents in the collection, and create a POJO using the MessageCard model class
////                for (DocumentSnapshot documentSnapshot : documents) {
////
////                    // Create a POJO for the document using the model class
////                    MessageCard documentMessage = documentSnapshot.toObject(MessageCard.class);
////
////                    Log.d(LOG_TAG, "Adding document from Firebase: " + documentMessage.getTitle() + "|" + documentMessage.getMessage());
////
////                    // Add the POJO to the ArrayList
////                    messages.add(documentMessage);
////
////                    for(MessageCard messageCard: messages) {
////
////                        Log.d(LOG_TAG, "Current List of Messages from Firebase: " + messageCard.getTitle() + " " + messageCard.getMessage());
////
////
////                    }
////
////                    // Set the value of the MutableLiveData object
////                    messagesLiveData.postValue(messages);
////
////
////
////
////                }
////
////            }
////        });

        // Get the User

        // Intialize the LiveData variable
        messagesLiveData = new MutableLiveData<>();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userID = firebaseUser.getUid();

        Log.d(LOG_TAG, "UserID: " + userID);

        firebaseFirestore.collection("users").document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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

                        messagesLiveData.postValue(messages);

                    }

                }

                else {

                    Log.d(LOG_TAG, "UNSUCCESSFUL");

                }
            }
        });



        return messagesLiveData;
    }

    @Override
    public MutableLiveData<ArrayList<MessageCard>> getSocialMessages() {
//        // Intialize the LiveData variable
//        messagesLiveData = new MutableLiveData<>();
//
//        // List that will hold the messages
//        ArrayList<MessageCard> messages = new ArrayList<>();
//
//        firebaseFirestore.collection("social_messages").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//
//                // Get all documents from the collection
//                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
//
//                // Iterate through all the documents in the collection, and create a POJO using the MessageCard model class
//                for (DocumentSnapshot documentSnapshot : documents) {
//
//                    // Create a POJO for the document using the model class
//                    MessageCard documentMessage = documentSnapshot.toObject(MessageCard.class);
//
//                    Log.d(LOG_TAG, "Adding document from Firebase: " + documentMessage.getTitle() + "|" + documentMessage.getMessage());
//
//                    // Add the POJO to the ArrayList
//                    messages.add(documentMessage);
//
//                    for(MessageCard messageCard: messages) {
//
//                        Log.d(LOG_TAG, "Current List of Messages from Firebase: " + messageCard.getTitle() + " " + messageCard.getMessage());
//
//
//                    }
//
//                    // Set the value of the MutableLiveData object
//                    messagesLiveData.postValue(messages);
//
//
//
//
//                }
//
//            }
//        });
//
//        return messagesLiveData;

        // Intialize the LiveData variable
        messagesLiveData = new MutableLiveData<>();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userID = firebaseUser.getUid();

        Log.d(LOG_TAG, userID);

        firebaseFirestore.collection("users").document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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

                        messagesLiveData.postValue(messages);

                    }

                }

                else {

                    Log.d(LOG_TAG, "UNSUCCESSFUL");

                }
            }
        });



        return messagesLiveData;
    }

    @Override
    public MutableLiveData<ArrayList<MessageCard>> getBusinessMessages() {
//        // Intialize the LiveData variable
//        messagesLiveData = new MutableLiveData<>();
//
//        // List that will hold the messages
//        ArrayList<MessageCard> messages = new ArrayList<>();
//
//        firebaseFirestore.collection("business_messages").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//
//                // Get all documents from the collection
//                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
//
//                // Iterate through all the documents in the collection, and create a POJO using the MessageCard model class
//                for (DocumentSnapshot documentSnapshot : documents) {
//
//                    // Create a POJO for the document using the model class
//                    MessageCard documentMessage = documentSnapshot.toObject(MessageCard.class);
//
//                    Log.d(LOG_TAG, "Adding document from Firebase: " + documentMessage.getTitle() + "|" + documentMessage.getMessage());
//
//                    // Add the POJO to the ArrayList
//                    messages.add(documentMessage);
//
//                    for(MessageCard messageCard: messages) {
//
//                        Log.d(LOG_TAG, "Current List of Messages from Firebase: " + messageCard.getTitle() + " " + messageCard.getMessage());
//
//
//                    }
//
//                    // Set the value of the MutableLiveData object
//                    messagesLiveData.postValue(messages);
//
//
//
//
//                }
//
//            }
//        });
//
//        return messagesLiveData;

        // Intialize the LiveData variable
        messagesLiveData = new MutableLiveData<>();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userID = firebaseUser.getUid();

        Log.d(LOG_TAG, userID);

        firebaseFirestore.collection("users").document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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

                        messagesLiveData.postValue(messages);

                    }

                }

                else {

                    Log.d(LOG_TAG, "UNSUCCESSFUL");

                }
            }
        });



        return messagesLiveData;
        }

    @Override
    public MutableLiveData<ArrayList<MessageCard>> getPlus1Messages() {
//        // Intialize the LiveData variable
//        messagesLiveData = new MutableLiveData<>();
//
//        // List that will hold the messages
//        ArrayList<MessageCard> messages = new ArrayList<>();
//
//        firebaseFirestore.collection("plus_1_messages").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//
//                // Get all documents from the collection
//                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
//
//                // Iterate through all the documents in the collection, and create a POJO using the MessageCard model class
//                for (DocumentSnapshot documentSnapshot : documents) {
//
//                    // Create a POJO for the document using the model class
//                    MessageCard documentMessage = documentSnapshot.toObject(MessageCard.class);
//
//                    Log.d(LOG_TAG, "Adding document from Firebase: " + documentMessage.getTitle() + "|" + documentMessage.getMessage());
//
//                    // Add the POJO to the ArrayList
//                    messages.add(documentMessage);
//
//                    for(MessageCard messageCard: messages) {
//
//                        Log.d(LOG_TAG, "Current List of Messages from Firebase: " + messageCard.getTitle() + " " + messageCard.getMessage());
//
//
//                    }
//
//                    // Set the value of the MutableLiveData object
//                    messagesLiveData.postValue(messages);
//
//
//
//
//                }
//
//            }
//        });
//

        // Intialize the LiveData variable
        messagesLiveData = new MutableLiveData<>();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userID = firebaseUser.getUid();

        Log.d(LOG_TAG, userID);

        firebaseFirestore.collection("users").document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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

                        messagesLiveData.postValue(messages);

                    }

                }

                else {

                    Log.d(LOG_TAG, "UNSUCCESSFUL");

                }
            }
        });



        return messagesLiveData;
    }

    @Override
    public MutableLiveData<ArrayList<MessageCard>> getPlus2Messages() {
//        // Intialize the LiveData variable
//        messagesLiveData = new MutableLiveData<>();
//
//        // List that will hold the messages
//        ArrayList<MessageCard> messages = new ArrayList<>();
//
//        firebaseFirestore.collection("plus_2_messages").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//
//                // Get all documents from the collection
//                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
//
//                // Iterate through all the documents in the collection, and create a POJO using the MessageCard model class
//                for (DocumentSnapshot documentSnapshot : documents) {
//
//                    // Create a POJO for the document using the model class
//                    MessageCard documentMessage = documentSnapshot.toObject(MessageCard.class);
//
//                    Log.d(LOG_TAG, "Adding document from Firebase: " + documentMessage.getTitle() + "|" + documentMessage.getMessage());
//
//                    // Add the POJO to the ArrayList
//                    messages.add(documentMessage);
//
//                    for(MessageCard messageCard: messages) {
//
//                        Log.d(LOG_TAG, "Current List of Messages from Firebase: " + messageCard.getTitle() + " " + messageCard.getMessage());
//
//
//                    }
//
//                    // Set the value of the MutableLiveData object
//                    messagesLiveData.postValue(messages);
//
//
//
//
//                }
//
//            }
//        });

        // Intialize the LiveData variable
        messagesLiveData = new MutableLiveData<>();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userID = firebaseUser.getUid();

        Log.d(LOG_TAG, userID);

        firebaseFirestore.collection("users").document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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

                        messagesLiveData.postValue(messages);

                    }

                }

                else {

                    Log.d(LOG_TAG, "UNSUCCESSFUL");

                }
            }
        });



        return messagesLiveData;

    }

    @Override
    public void editMessage(MessageCard oldMessage, MessageCard newMessage) {

        // Use the oldMessage to get the element that shoud be edited
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userID = firebaseUser.getUid();

        // Use the index to delete the corresponding MessageCard in the ArrayList field in FB
        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);

        // Delete the old message
        documentReference.update("personalMessages", FieldValue.arrayRemove(oldMessage));

        // Add the new message
        documentReference.update("personalMessages", FieldValue.arrayUnion(newMessage));



    }


    public void addPersonalMessage(MessageCard messageCard) {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userID = firebaseUser.getUid();

        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);

        documentReference.update("personalMessages", FieldValue.arrayUnion(messageCard));

    }

    public void addSocialMessage(MessageCard messageCard) {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userID = firebaseUser.getUid();

        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);

        documentReference.update("socialMessages", FieldValue.arrayUnion(messageCard));

    }

    public void addBusinessMessage(MessageCard messageCard) {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userID = firebaseUser.getUid();

        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);

        documentReference.update("businessMessages", FieldValue.arrayUnion(messageCard));

    }

    public void addPlus1Message(MessageCard messageCard) {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userID = firebaseUser.getUid();

        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);

        documentReference.update("plus1Messages", FieldValue.arrayUnion(messageCard));

    }

    public void addPlus2Message(MessageCard messageCard) {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userID = firebaseUser.getUid();

        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);

        documentReference.update("plus2Messages", FieldValue.arrayUnion(messageCard));

    }

    public void deletePersonalMessage(MessageCard messageCardToDelete) {

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userID = firebaseUser.getUid();

        // Use the index to delete the corresponding MessageCard in the ArrayList field in FB
        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);

//        MessageCard messageCardToDelete = new MessageCard("hello", "my new message");

        documentReference.update("personalMessages", FieldValue.arrayRemove(messageCardToDelete));


    }
}
