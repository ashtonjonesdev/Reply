package dev.ashtonjones.reply.datalayer.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import dev.ashtonjones.reply.datamodels.MessageCard;

public class FirebaseRepository implements RepositoryInterface {

    private static final String LOG_TAG = FirebaseRepository.class.getSimpleName();

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private MutableLiveData<ArrayList<MessageCard>> messagesLiveData;

    @Override
    public MutableLiveData<ArrayList<MessageCard>> getMessages() {

        // Intialize the LiveData variable
        messagesLiveData = new MutableLiveData<>();

        // List that will hold the messages
       ArrayList<MessageCard> messages = new ArrayList<>();

        firebaseFirestore.collection("messages").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                // Get all documents from the collection
                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();

                // Iterate through all the documents in the collection, and create a POJO using the MessageCard model class
                for (DocumentSnapshot documentSnapshot : documents) {

                    // Create a POJO for the document using the model class
                    MessageCard documentMessage = documentSnapshot.toObject(MessageCard.class);

                    Log.d(LOG_TAG, "Adding document from Firebase: " + documentMessage.getTitle() + "|" + documentMessage.getMessage());

                    // Add the POJO to the ArrayList
                    messages.add(documentMessage);

                    for(MessageCard messageCard: messages) {

                        Log.d(LOG_TAG, "Current List of Messages from Firebase: " + messageCard.getTitle() + " " + messageCard.getMessage());


                    }

                    // Set the value of the MutableLiveData object
                    messagesLiveData.postValue(messages);




                }

            }
        });

        return messagesLiveData;
    }
}
