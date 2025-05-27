package com.example.others.dp.adp;

public class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter = new MediaAdapter();

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {

            System.out.println("playing  mp3: " + fileName);
        } else {
            mediaAdapter.play(audioType, fileName);
        }
    }
}
