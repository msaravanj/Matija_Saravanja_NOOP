package model;

import java.io.Serializable;

public class ChessPlayer implements Serializable {

    private static int cnt = 100;
    private int id;
    private String name;
    private String surname;
    private String gender;
    private int birthYear;
    private String country;
    private int eloRating;
    private int fideId;
    private ChessTitleEnum chessTitle;

    public ChessPlayer(int id, String name, String surname, String gender, int birthYear, String country, int eloRating, int fideId, ChessTitleEnum chessTitle) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthYear = birthYear;
        this.country = country;
        this.eloRating = eloRating;
        this.fideId = fideId;
        this.chessTitle = chessTitle;
    }

    public ChessPlayer(String name, String surname, String gender, int birthYear, String country, int eloRating, int fideId, ChessTitleEnum chessTitle) {
        this.id = cnt;
        cnt++;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthYear = birthYear;
        this.country = country;
        this.eloRating = eloRating;
        this.fideId = fideId;
        this.chessTitle = chessTitle;
    }

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

    public String getCountry() {
        return country;
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

    @java.lang.Override
    public java.lang.String toString() {
        return "ChessPlayer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthYear=" + birthYear +
                ", country='" + country + '\'' +
                ", eloRating=" + eloRating +
                ", fideId=" + fideId +
                ", chessTitle=" + chessTitle +
                '}';
    }

    public void chessPlayerInfo(){
        System.out.println(toString());
    }
}
