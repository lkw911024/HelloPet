package VO;

/**
 * Created by Huni on 2017-11-11.
 */

public class DisappearVO {

    private String disappearId;
    private String disappearType;
    private String disappearPlace;
    private String disappearTime;
    private String disappearWrittenDate;
    private String disappearDetails;
    private String disappearReportCnt;
    private String petId;
    private String petType;
    private String petRace;
    private String petName;
    private String petAge;
    private String petGender;
    private String petHairColor;
    private String petWeight;
    private String petNeutralization;
    private String petAttribute;
    private String petDetails;
    private String memberId;
    private String memberPhone;
    private String memberNickName;

    public DisappearVO() {
    }

    public DisappearVO(String disappearId, String disappearType, String disappearPlace, String disappearTime, String disappearWrittenDate, String disappearDetails, String disappearReportCnt, String petId, String petType, String petRace, String petName, String petAge, String petGender, String petHairColor, String petWeight, String petNeutralization, String petAttribute, String petDetails, String memberId, String memberPhone, String memberNickName) {

        this.disappearId = disappearId;
        this.disappearType = disappearType;
        this.disappearPlace = disappearPlace;
        this.disappearTime = disappearTime;
        this.disappearWrittenDate = disappearWrittenDate;
        this.disappearDetails = disappearDetails;
        this.disappearReportCnt = disappearReportCnt;
        this.petId = petId;
        this.petType = petType;
        this.petRace = petRace;
        this.petName = petName;
        this.petAge = petAge;
        this.petGender = petGender;
        this.petHairColor = petHairColor;
        this.petWeight = petWeight;
        this.petNeutralization = petNeutralization;
        this.petAttribute = petAttribute;
        this.petDetails = petDetails;
        this.memberId = memberId;
        this.memberPhone = memberPhone;
        this.memberNickName = memberNickName;
    }

    public String getDisappearId() {
        return disappearId;
    }

    public void setDisappearId(String disappearId) {
        this.disappearId = disappearId;
    }

    public String getDisappearType() {
        return disappearType;
    }

    public void setDisappearType(String disappearType) {
        this.disappearType = disappearType;
    }

    public String getDisappearPlace() {
        return disappearPlace;
    }

    public void setDisappearPlace(String disappearPlace) {
        this.disappearPlace = disappearPlace;
    }

    public String getDisappearTime() {
        return disappearTime;
    }

    public void setDisappearTime(String disappearTime) {
        this.disappearTime = disappearTime;
    }

    public String getDisappearWrittenDate() {
        return disappearWrittenDate;
    }

    public void setDisappearWrittenDate(String disappearWrittenDate) {
        this.disappearWrittenDate = disappearWrittenDate;
    }

    public String getDisappearDetails() {
        return disappearDetails;
    }

    public void setDisappearDetails(String disappearDetails) {
        this.disappearDetails = disappearDetails;
    }

    public String getDisappearReportCnt() {
        return disappearReportCnt;
    }

    public void setDisappearReportCnt(String disappearReportCnt) {
        this.disappearReportCnt = disappearReportCnt;
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

    public String getPetAttribute() {
        return petAttribute;
    }

    public void setPetAttribute(String petAttribute) {
        this.petAttribute = petAttribute;
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

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    @Override
    public String toString() {
        return "DisappearVO{" +
                "disappearId='" + disappearId + '\'' +
                ", disappearType='" + disappearType + '\'' +
                ", disappearPlace='" + disappearPlace + '\'' +
                ", disappearTime='" + disappearTime + '\'' +
                ", disappearWrittenDate='" + disappearWrittenDate + '\'' +
                ", disappearDetails='" + disappearDetails + '\'' +
                ", disappearReportCnt='" + disappearReportCnt + '\'' +
                ", petId='" + petId + '\'' +
                ", petType='" + petType + '\'' +
                ", petRace='" + petRace + '\'' +
                ", petName='" + petName + '\'' +
                ", petAge='" + petAge + '\'' +
                ", petGender='" + petGender + '\'' +
                ", petHairColor='" + petHairColor + '\'' +
                ", petWeight='" + petWeight + '\'' +
                ", petNeutralization='" + petNeutralization + '\'' +
                ", petAttribute='" + petAttribute + '\'' +
                ", petDetails='" + petDetails + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", memberNickName='" + memberNickName + '\'' +
                '}';
    }
}
