package com.ruigoncalo.romeo.providers;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ruigoncalo on 20/02/16.
 */
public class AudioProvider {

    private static final String FILE_NAME = "message.m4a";
    private static AudioProvider ourInstance = new AudioProvider();
    private AudioCallback callback;

    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;

    public static AudioProvider getInstance() {
        return ourInstance;
    }

    private AudioProvider() {
    }

    public void registerCallback(AudioCallback callback) {
        this.callback = callback;
    }

    public void unregisterCallback() {
        this.callback = null;
    }

    public void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void initMediaRecorder(Context context) {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        File file = new File(context.getFilesDir(), FILE_NAME);
        mediaRecorder.setOutputFile(file.getAbsolutePath());
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.HE_AAC);
    }

    public void playLocal(Context context, String fileName) {
        if (mediaPlayer == null) {
            initMediaPlayer();
        }

        try {
            AssetFileDescriptor assetFileDescriptor = context.getAssets().openFd(fileName);
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                    assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            assetFileDescriptor.close();
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });

        } catch (IOException exception){
            if (callback != null) {
                callback.onError("Error on play local file", exception);
            }
        }
    }

    public void play(Context context) {
        if (mediaPlayer == null) {
            initMediaPlayer();
        }

        try {
            File file = new File(context.getFilesDir(), FILE_NAME);
            mediaPlayer.setDataSource(file.getAbsolutePath());
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
        } catch (IllegalArgumentException | IOException exception) {
            if (callback != null) {
                callback.onError("Error on play file", exception);
            }
        }
    }

    public void play(Context context, String url) {
        if (mediaPlayer == null) {
            initMediaPlayer();
        }

        try {
            Uri uri = Uri.parse(url);
            mediaPlayer.setDataSource(context, uri);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
        } catch (IllegalArgumentException | IOException exception) {
            if (callback != null) {
                callback.onError("Error on play url", exception);
            }
        }
    }

    public void stopPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            cleanMediaPlayer();
        }
    }

    public void cleanMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void record(Context context) {
        if (mediaRecorder == null) {
            initMediaRecorder(context);
        }

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IllegalStateException | IOException exception) {
            if (callback != null) {
                callback.onError("Error on recording", exception);
            }
        }
    }

    public void stopRecording() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            cleanMediaRecorder();
        }
    }

    public void cleanMediaRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    public File getAudioFile(Context context) {
        return new File(context.getFilesDir(), FILE_NAME);
    }


    public File getLocalAudioFile(Context context, String name) {
        File result = new File(context.getFilesDir(), "audio.mp3");

        try {
            AssetFileDescriptor descriptor = context.getAssets().openFd(name);
            writeBytesToFile(descriptor.createInputStream(), result);
        } catch (IOException e) {
            if(callback != null){
                callback.onError("Error reading local file", e);
            }
        }

        return result;
    }

    public void writeBytesToFile(InputStream is, File file) throws IOException {
        FileOutputStream fos = null;
        try {
            byte[] data = new byte[2048];
            int nbread;
            fos = new FileOutputStream(file);
            while ((nbread = is.read(data)) > -1) {
                fos.write(data, 0, nbread);
            }
        } catch (Exception ex) {
            if(callback != null){
                callback.onError("Error passing bytes to file", ex);
            }
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    public String calculateDuration(Context context) {
        File file = new File(context.getFilesDir(), FILE_NAME);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
        String duration = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        mediaMetadataRetriever.release();
        return duration;
    }

    public interface AudioCallback {
        void onError(String type, Exception exception);
    }
}
