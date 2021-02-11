package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

// Implemented as a Singleton
public class Score {
    int scores[];
    private static final Score INSTANCE = new Score();
    private Pong game;
    private List<ScoreObserver> observers;


    private Score() {
        scores = new int[]{0, 0};
        observers = new ArrayList();
    }

    public static Score getInstance() {
        return INSTANCE;
    }

    public void addObserver(ScoreObserver observer) {
        observers.add(observer);
    }

    public void p1Scored() {
        scores[0] += 1;
        // Wanted to implement this as a stream, but it requires higher minimum android API level
        for (ScoreObserver obs: observers){
            obs.goal(scores[0], scores[1]);
        }
    }

    public void p2Scored() {
        scores[1] += 1;
        // Wanted to implement this as a stream, but it requires higher minimum android API level
        for (ScoreObserver obs: observers){
            obs.goal(scores[0], scores[1]);
        }
    }

    public int getP1Score() {
        return scores[0];
    }

    public int getP2Score() {
        return scores[1];
    }
}
