package com.company.Memberships;

public class Accounts extends Users{
    private int AccountID;
    private int MembershipId;

    public Accounts(int userID, String firstName, String lastName, String email, String phone, String postcode, int houseNumber,
                    String street, int accountID, int MembershipID) {
        super(userID, firstName, lastName, email, phone, postcode, houseNumber, street);
        AccountID = accountID;
        MembershipId = MembershipID;


    }
    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int accountID) {
        AccountID = accountID;
    }

    public int getMembershipId() {
        return MembershipId;
    }

    public void setMembershipId(int membershipId) {
        MembershipId = membershipId;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "AccountID=" + AccountID +
                ", MembershipId=" + MembershipId +
                ", UserID=" + UserID +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Postcode='" + Postcode + '\'' +
                ", HouseNumber=" + HouseNumber +
                ", Street='" + Street + '\'' +
                '}';
    }
}
