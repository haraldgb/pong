package com.mygdx.game;

public class Score {
    int scores[];
    Pong game;

    public Score(Pong game) {
        this.game = game;
        scores = new int[]{0, 0};
    }

    public void p1Scored() {
        scores[0] += 1;
        if (scores[0] >= 21) {
            game.endGame();
        }
    }

    public void p2Scored() {
        scores[1] += 1;
        if (scores[1] >= 21) {
            game.endGame();
        }
    }

    public int getP1Score() {
        return scores[0];
    }

    public int getP2Score() {
        return scores[1];
    }
}
