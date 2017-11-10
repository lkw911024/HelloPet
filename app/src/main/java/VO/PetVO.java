package VO;

/**
 * Created by Huni on 2017-11-11.
 */

public class PetVO {

    private String petId;
    private String petType;
    private String petRace;
    private String petName;
    private String petAge;
    private String petGender;
    private String petHairColor;
    private String petWeight;
    private String petNeutralization;
    private String petDetails;
    private String memberId;

    public PetVO() {
    }

    public PetVO(String petId, String petType, String petRace, String petName, String petAge, String petGender, String petHairColor, String petWeight, String petNeutralization, String petDetails, String memberId) {
        this.petId = petId;
        this.petType = petType;
        this.petRace = petRace;
        this.petName = petName;
        this.petAge = petAge;
        this.petGender = petGender;
        this.petHairColor = petHairColor;
        this.petWeight = petWeight;
        this.petNeutralization = petNeutralization;
        this.petDetails = petDetails;
        this.memberId = memberId;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetRace() {
        return petRace;
    }

    public void setPetRace(String petRace) {
        this.petRace = petRace;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public String getPetHairColor() {
        return petHairColor;
    }

    public void setPetHairColor(String petHairColor) {
        this.petHairColor = petHairColor;
    }

    public String getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(String petWeight) {
        this.petWeight = petWeight;
    }

    public String getPetNeutralization() {
        return petNeutralization;
    }

    public void setPetNeutralization(String petNeutralization) {
        this.petNeutralization = petNeutralization;
    }

    public String getPetDetails() {
        return petDetails;
    }

    public void setPetDetails(String petDetails) {
        this.petDetails = petDetails;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "PetVO{" +
                "petId='" + petId + '\'' +
                ", petType='" + petType + '\'' +
                ", petRace='" + petRace + '\'' +
                ", petName='" + petName + '\'' +
                ", petAge='" + petAge + '\'' +
                ", petGender='" + petGender + '\'' +
                ", petHairColor='" + petHairColor + '\'' +
                ", petWeight='" + petWeight + '\'' +
                ", petNeutralization='" + petNeutralization + '\'' +
                ", petDetails='" + petDetails + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
