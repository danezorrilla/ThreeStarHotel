package com.bb.threestarhotel.model;

public class Guest {

    private String guestName;
    private String guestRoomNumber;
    private int guestId;
    private int guestPicture;

    public Guest(String guestName, String guestRoomNumber, int guestId, int guestPicture) {
        this.guestName = guestName;
        this.guestRoomNumber = guestRoomNumber;
        this.guestId = guestId;
        this.guestPicture = guestPicture;
    }

    public Guest(String guestName, String guestRoomNumber, int guestPicture) {
        this.guestName = guestName;
        this.guestRoomNumber = guestRoomNumber;
        this.guestPicture = guestPicture;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestRoomNumber() {
        return guestRoomNumber;
    }

    public void setGuestRoomNumber(String guestRoomNumber) {
        this.guestRoomNumber = guestRoomNumber;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getGuestPicture() {
        return guestPicture;
    }

    public void setGuestPicture(int guestPicture) {
        this.guestPicture = guestPicture;
    }
}
