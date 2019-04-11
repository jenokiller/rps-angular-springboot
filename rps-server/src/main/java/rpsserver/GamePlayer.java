package rpsserver;

import java.util.Objects;

public class GamePlayer {
    String name;
    String throwHand;

    public GamePlayer() {
    }

    public GamePlayer(String name, String throwHand) {
        this.name = name;
        this.throwHand = throwHand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThrowHand() {
        return throwHand;
    }

    public void setThrowHand(String throwHand) {
        this.throwHand = throwHand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePlayer that = (GamePlayer) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(throwHand, that.throwHand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, throwHand);
    }

    @Override
    public String toString() {
        return "GamePlayer{" +
                "name='" + name + '\'' +
                ", throwHand='" + throwHand + '\'' +
                '}';
    }
}
