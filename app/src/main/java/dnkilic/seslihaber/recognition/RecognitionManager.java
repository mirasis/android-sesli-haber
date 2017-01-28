package dnkilic.seslihaber.recognition;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import dnkilic.seslihaber.MainActivity;

//selamun aleykum :)
//a.// STOPSHIP: 28.01.2017  //naptın kardo anlat istejes
public class RecognitionManager implements  LanguageAvailabilityListener{

    private SpeechRecognizer mSpeechRecognizer;
    private boolean mIsFirstRequest = true;
    private Activity mActivity;

    public RecognitionManager(MainActivity act)
    {
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(act);
        mSpeechRecognizer.setRecognitionListener(act);
        mActivity = act;
    }

    public void start() {

        if(mIsFirstRequest)
        {
            Intent detailsIntent = new Intent(RecognizerIntent.ACTION_GET_LANGUAGE_DETAILS);
            mActivity.sendOrderedBroadcast(detailsIntent, null, new LanguageDetailsChecker(this), null, Activity.RESULT_OK, null, null);
        }
        else
        {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            mSpeechRecognizer.startListening(intent);
        }

        mIsFirstRequest = false;
    }

    public void destroy()
    {
        if(mSpeechRecognizer != null)
        {
            mSpeechRecognizer.destroy();
        }
    }

    @Override
    public void onLanguageAvailabilityCheck(boolean availability) {

        if(availability)
        {
            start();
        }
        else
        {
            //TODO
        }
    }
}
