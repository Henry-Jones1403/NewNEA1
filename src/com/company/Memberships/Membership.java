package com.company.Memberships;

public class Membership {
    private int MembershipID;
    private boolean Gym;
    private boolean Swim;
    private boolean Court;
    private boolean Premium;

    public Membership(int membershipID, boolean gym, boolean swim, boolean court, boolean premium) {
        MembershipID = membershipID;
        Gym = gym;
        Swim = swim;
        Court = court;
        Premium = premium;
    }

    public int getMembershipID() {
        return MembershipID;
    }

    public void setMembershipID(int membershipID) {
        MembershipID = membershipID;
    }

    public boolean isGym() {
        return Gym;
    }

    public void setGym(boolean gym) {
        Gym = gym;
    }

    public boolean isSwim() {
        return Swim;
    }

    public void setSwim(boolean swim) {
        Swim = swim;
    }

    public boolean isCourt() {
        return Court;
    }

    public void setCourt(boolean court) {
        Court = court;
    }

    public boolean isPremium() {
        return Premium;
    }

    public void setPremium(boolean premium) {
        Premium = premium;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "MembershipID=" + MembershipID +
                ", Gym=" + Gym +
                ", Swim=" + Swim +
                ", Court=" + Court +
                ", Premium=" + Premium +
                '}';
    }
}
