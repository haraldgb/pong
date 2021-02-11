package com.mygdx.game;

// Implemented as a Singleton
public class Score {
    int scores[];
    private static final Score INSTANCE = new Score();
    private Pong game;


    private Score() {
        scores = new int[]{0, 0};
    }

    public static Score getInstance() {
        return INSTANCE;
    }

    public void addGame(Pong pong) {
        INSTANCE.game = pong;
    }

    public void p1Scored() {
        scores[0] += 1;
        game.goal(scores[0], scores[1]);
    }

    public void p2Scored() {
        scores[1] += 1;
        game.goal(scores[0], scores[1]);
    }

    public int getP1Score() {
        return scores[0];
    }

    public int getP2Score() {
        return scores[1];
    }
}
