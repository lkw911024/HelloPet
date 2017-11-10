package VO;

/**
 * Created by Huni on 2017-11-11.
 */

public class WitnessVO {

    private String witnessId;
    private String witnessType;
    private String witnessPlace;
    private String witnessTime;
    private String witnessWrittenDate;
    private String witnessDetails;
    private String witnessPetType;
    private String witnessRace;
    private String witnessGender;
    private String witnessHairColor;
    private String witnessAttribute;
    private String witnessPetDetails;
    private String witnessNeutralization;
    private String witnessReportCnt;
    private String memberId;
    private String memberNickName;
    private String memberPhone;

    public WitnessVO() {
    }

    public WitnessVO(String witnessId, String witnessType, String witnessPlace, String witnessTime, String witnessWrittenDate, String witnessDetails, String witnessPetType, String witnessRace, String witnessGender, String witnessHairColor, String witnessAttribute, String witnessPetDetails, String witnessNeutralization, String witnessReportCnt, String memberId, String memberNickName, String memberPhone) {

        this.witnessId = witnessId;
        this.witnessType = witnessType;
        this.witnessPlace = witnessPlace;
        this.witnessTime = witnessTime;
        this.witnessWrittenDate = witnessWrittenDate;
        this.witnessDetails = witnessDetails;
        this.witnessPetType = witnessPetType;
        this.witnessRace = witnessRace;
        this.witnessGender = witnessGender;
        this.witnessHairColor = witnessHairColor;
        this.witnessAttribute = witnessAttribute;
        this.witnessPetDetails = witnessPetDetails;
        this.witnessNeutralization = witnessNeutralization;
        this.witnessReportCnt = witnessReportCnt;
        this.memberId = memberId;
        this.memberNickName = memberNickName;
        this.memberPhone = memberPhone;
    }

    public String getWitnessId() {
        return witnessId;
    }

    public void setWitnessId(String witnessId) {
        this.witnessId = witnessId;
    }

    public String getWitnessType() {
        return witnessType;
    }

    public void setWitnessType(String witnessType) {
        this.witnessType = witnessType;
    }

    public String getWitnessPlace() {
        return witnessPlace;
    }

    public void setWitnessPlace(String witnessPlace) {
        this.witnessPlace = witnessPlace;
    }

    public String getWitnessTime() {
        return witnessTime;
    }

    public void setWitnessTime(String witnessTime) {
        this.witnessTime = witnessTime;
    }

    public String getWitnessWrittenDate() {
        return witnessWrittenDate;
    }

    public void setWitnessWrittenDate(String witnessWrittenDate) {
        this.witnessWrittenDate = witnessWrittenDate;
    }

    public String getWitnessDetails() {
        return witnessDetails;
    }

    public void setWitnessDetails(String witnessDetails) {
        this.witnessDetails = witnessDetails;
    }

    public String getWitnessPetType() {
        return witnessPetType;
    }

    public void setWitnessPetType(String witnessPetType) {
        this.witnessPetType = witnessPetType;
    }

    public String getWitnessRace() {
        return witnessRace;
    }

    public void setWitnessRace(String witnessRace) {
        this.witnessRace = witnessRace;
    }

    public String getWitnessGender() {
        return witnessGender;
    }

    public void setWitnessGender(String witnessGender) {
        this.witnessGender = witnessGender;
    }

    public String getWitnessHairColor() {
        return witnessHairColor;
    }

    public void setWitnessHairColor(String witnessHairColor) {
        this.witnessHairColor = witnessHairColor;
    }

    public String getWitnessAttribute() {
        return witnessAttribute;
    }

    public void setWitnessAttribute(String witnessAttribute) {
        this.witnessAttribute = witnessAttribute;
    }

    public String getWitnessPetDetails() {
        return witnessPetDetails;
    }

    public void setWitnessPetDetails(String witnessPetDetails) {
        this.witnessPetDetails = witnessPetDetails;
    }

    public String getWitnessNeutralization() {
        return witnessNeutralization;
    }

    public void setWitnessNeutralization(String witnessNeutralization) {
        this.witnessNeutralization = witnessNeutralization;
    }

    public String getWitnessReportCnt() {
        return witnessReportCnt;
    }

    public void setWitnessReportCnt(String witnessReportCnt) {
        this.witnessReportCnt = witnessReportCnt;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    @Override
    public String toString() {
        return "WitnessVO{" +
                "witnessId='" + witnessId + '\'' +
                ", witnessType='" + witnessType + '\'' +
                ", witnessPlace='" + witnessPlace + '\'' +
                ", witnessTime='" + witnessTime + '\'' +
                ", witnessWrittenDate='" + witnessWrittenDate + '\'' +
                ", witnessDetails='" + witnessDetails + '\'' +
                ", witnessPetType='" + witnessPetType + '\'' +
                ", witnessRace='" + witnessRace + '\'' +
                ", witnessGender='" + witnessGender + '\'' +
                ", witnessHairColor='" + witnessHairColor + '\'' +
                ", witnessAttribute='" + witnessAttribute + '\'' +
                ", witnessPetDetails='" + witnessPetDetails + '\'' +
                ", witnessNeutralization='" + witnessNeutralization + '\'' +
                ", witnessReportCnt='" + witnessReportCnt + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberNickName='" + memberNickName + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                '}';
    }
}
