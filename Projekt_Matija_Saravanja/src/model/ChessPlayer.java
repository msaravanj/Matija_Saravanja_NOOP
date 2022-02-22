package model;

import java.io.Serializable;

public class ChessPlayer implements Serializable {

    private int id;
    private String name;
    private String surname;
    private String gender;
    private int birthYear;
    private int eloRating;
    private int fideId;
    private ChessTitleEnum chessTitle;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getEloRating() {
        return eloRating;
    }

    public int getFideId() {
        return fideId;
    }

    public ChessTitleEnum getChessTitle() {
        return chessTitle;
    }

    @Override
    public String toString() {
        return "ChessPlayer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthYear=" + birthYear +
                ", eloRating=" + eloRating +
                ", fideId=" + fideId +
                ", chessTitle='" + chessTitle + '\'' +
                '}';
    }

    public void chessPlayerInfo(){
        System.out.println(toString());
    }
}
