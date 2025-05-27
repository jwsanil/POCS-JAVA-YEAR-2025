package com.example.others.dp.adp;

public class MediaAdapter implements MediaPlayer {

    AdvanceMediaPlayer advanceMediaPlayer = new AdvanceMediaPlayer();

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {

            advanceMediaPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {

            advanceMediaPlayer.playMp4(fileName);
        }

    }
}

