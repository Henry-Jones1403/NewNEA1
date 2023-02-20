package com.company.Memberships;

public class Accounts extends Users{
    private int AccountID;
    private int MembershipId;
    private String Username;
private boolean SpecialRequirement;
    public Accounts(int userID, String firstName, String lastName, String email, String phone, String postcode, int houseNumber,
                    String street, int accountID, int MembershipID, String username, boolean specialRequirement) {
        super(userID, firstName, lastName, email, phone, postcode, houseNumber, street);
        AccountID = accountID;
        MembershipId = MembershipID;
        Username = username;
        SpecialRequirement = specialRequirement;


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

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public boolean isSpecialRequirement() {
        return SpecialRequirement;
    }

    public void setSpecialRequirement(boolean specialRequirement) {
        SpecialRequirement = specialRequirement;
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
                ", Username='" + Username + '\'' +
                ", Special Requirement='" + SpecialRequirement + '\'' +
                '}';
    }
}
