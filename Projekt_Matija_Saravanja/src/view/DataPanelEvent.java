package view;

import model.ChessTitleEnum;

import java.util.EventObject;

public class DataPanelEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */

    private String name;
    private String surname;
    private String gender;
    private String country;
    private int birthYear;
    private int eloRating;
    private int fideId;
    private ChessTitleEnum chessTitle;

    public DataPanelEvent(Object source) {
        super(source);
    }

    public DataPanelEvent(Object source, String name, String surname, String gender, String country, int birthYear, int eloRating, int fideId, ChessTitleEnum chessTitle) {
        super(source);
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.country = country;
        this.birthYear = birthYear;
        this.eloRating = eloRating;
        this.fideId = fideId;
        this.chessTitle = chessTitle;
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

    public String getCountry() {
        return country;
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
        return "DataPanelEvent{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                ", birthYear=" + birthYear +
                ", eloRating=" + eloRating +
                ", fideId=" + fideId +
                ", chessTitle=" + chessTitle +
                '}';
    }
}
