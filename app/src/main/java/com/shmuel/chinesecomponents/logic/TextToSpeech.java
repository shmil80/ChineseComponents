package com.shmuel.chinesecomponents.logic;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * Created by shmuel on 20/01/2018.
 */

public class TextToSpeech
{
    private MediaPlayer mp = null;
    private static final String kVoiceRssServer = "http://api.voicerss.org";
    private static final String kVoiceRSSAppKey = "7c4189fa5f9744329240f277a9b2ed5c";

    private final MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener()
    {
        @Override
        public void onPrepared(MediaPlayer mp)
        {
            mp.setVolume(1, 1);
            mp.start();
        }
    };

    private final MediaPlayer.OnErrorListener onErrorListener = new MediaPlayer.OnErrorListener()
    {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra)
        {
            return false;
        }
    };

    private final MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion(MediaPlayer mp)
        {
            mp.release();
            mp = null;
        }
    };

    public TextToSpeech(Context context)
    {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
    }


    public void speak(final String text)
    {
        try
        {
            String url = buildSpeechUrl(text, "zh-cn");


            if (mp != null)
                mp.release();

            mp = new MediaPlayer();
            mp.setDataSource(url);
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setOnErrorListener(onErrorListener);
            mp.setOnCompletionListener(onCompletionListener);
            mp.setOnPreparedListener(onPreparedListener);
            mp.prepareAsync();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Build speech URL.
     */
    private String buildSpeechUrl(String words, String language)
    {
        String url = "";

        url = kVoiceRssServer + "/?key=" + kVoiceRSSAppKey + "&t=text&hl=" + language + "&src=" + words;

        return url;
    }

}
