package com.company.Bookings;
import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {
    private int BookingID;
    private LocalDate BookingDate;
    private LocalTime EntryTime;
    private LocalTime ExitTime;
    private int Account;

    public Booking(int bookingID, LocalDate bookingDate, LocalTime entryTime, LocalTime exitTime, int account) {
        BookingID = bookingID;
        BookingDate = bookingDate;
        EntryTime = entryTime;
        ExitTime = exitTime;
        Account = account;
    }

    public int getBookingID() {
        return BookingID;
    }

    public void setBookingID(int bookingID) {
        BookingID = bookingID;
    }

    public LocalDate getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        BookingDate = bookingDate;
    }

    public LocalTime getEntryTime() {
        return EntryTime;
    }

    public void setEntryTime(LocalTime entryTime) {
        EntryTime = entryTime;
    }

    public LocalTime getExitTime() {
        return ExitTime;
    }

    public void setExitTime(LocalTime exitTime) {
        ExitTime = exitTime;
    }

    public int getAccount() {
        return Account;
    }

    public void setAccount(int account) {
        Account = account;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "BookingID=" + BookingID +
                ", BookingDate=" + BookingDate +
                ", EntryTime=" + EntryTime +
                ", ExitTime=" + ExitTime +
                ", Account=" + Account +
                '}';
    }
}
