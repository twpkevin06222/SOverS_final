package sovers.safeoversorry.utility;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import sovers.safeoversorry.R;
import sovers.safeoversorry.models.Message;
import sovers.safeoversorry.MainActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by User on 11/11/2017.
 */

public class MessageDialog extends DialogFragment {

    private static final String TAG = "MessageDialog";

    //create a new bundle and set the arguments to avoid a null pointer
    public MessageDialog(){
        super();
        setArguments(new Bundle());
    }

    //widgets
    EditText mMessage;

    //private String mUserId;
    private String mUserId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started.");

        //getid();
        mUserId = getArguments().getString(getString(R.string.intent_user_id));

        //safe the mUserId to a shared preference
        //save to a shared preference
        SharedPreferences preferences1 = getActivity().getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
        //SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        SharedPreferences.Editor editor = preferences1.edit();
        editor.putString("follower", mUserId);
        editor.apply();

        Log.i(TAG, "Follower selected" + mUserId);


    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_message, container, false);
        mMessage = (EditText) view.findViewById(R.id.message);

        Button send = (Button) view.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                getDialog().dismiss();




       /*         Log.d(TAG, "onClick: sending a new message");

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                if(!mMessage.getText().toString().equals("")){

                    //create the new message
                    Message message = new Message();
                    message.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    message.setMessage(mMessage.getText().toString());
                    message.setTimestamp(getTimestamp());

                    Log.i(TAG, "message_messanger: getUid " + FirebaseAuth.getInstance().getCurrentUser().getUid());
                    Log.i(TAG, "message_messanger: dbnode_messages " + getString(R.string.dbnode_messages));
                    Log.i(TAG, "message_messanger: mUserId: " + mUserId);
                    Log.i(TAG, "message_messanger: getkey " + reference.push().getKey());
                    Log.i(TAG, "message_messanger: message " + message);


                    //insert the new message
                    reference
                            .child(getString(R.string.dbnode_messages))
                            .child(mUserId)
                            .child(reference.push().getKey())
                            .setValue(message);
                    getDialog().dismiss();
                    Toast.makeText(getActivity(), "message sent", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "enter a message", Toast.LENGTH_SHORT).show();
                }
*/
            }
        });

        return view;
    }

    /**
     * Return the current timestamp in the form of a string
     * @return
     */
    private String getTimestamp(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("Canada/Pacific"));
        return sdf.format(new Date());
    }


}












