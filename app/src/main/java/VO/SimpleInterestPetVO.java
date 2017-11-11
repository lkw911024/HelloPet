package VO;

/**
 * Created by ael on 2017. 11. 11..
 */

public class SimpleInterestPetVO {
    private String interestPetId;
    private String interestPetType;
    private String interestPetGender;

    public SimpleInterestPetVO() {
    }

    public SimpleInterestPetVO(String interestPetId, String interestPetType, String interestPetGender) {
        this.interestPetId = interestPetId;
        this.interestPetType = interestPetType;
        this.interestPetGender = interestPetGender;
    }

    public String getInterestPetId() {
        return interestPetId;
    }

    public void setInterestPetId(String interestPetId) {
        this.interestPetId = interestPetId;
    }

    public String getInterestPetType() {
        return interestPetType;
    }

    public void setInterestPetType(String interestPetType) {
        this.interestPetType = interestPetType;
    }

    public String getInterestPetGender() {
        return interestPetGender;
    }

    public void setInterestPetGender(String interestPetGender) {
        this.interestPetGender = interestPetGender;
    }
}
