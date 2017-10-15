package VO;

/**
 * Created by ael on 2017. 10. 15..
 */

public class SimpleReportVO {
    private String reportId;
    private String reportType;
    private String place;
    private String time;
    private String details;
    private String petType;
    private String petRace;
    private String petName;
    private String petGender;

    public SimpleReportVO() {
    }

    public SimpleReportVO(String reportId, String reportType, String place, String time, String details, String petType, String petRace, String petName, String petGender) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.place = place;
        this.time = time;
        this.details = details;
        this.petType = petType;
        this.petRace = petRace;
        this.petName = petName;
        this.petGender = petGender;
    }

    public SimpleReportVO(String reportId, String reportType, String place, String time, String petType, String petRace, String petName, String petGender) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.place = place;
        this.time = time;
        this.petType = petType;
        this.petRace = petRace;
        this.petName = petName;
        this.petGender = petGender;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    @Override
    public String toString() {
        return "SimpleReportVO{" +
                "reportId='" + reportId + '\'' +
                ", reportType='" + reportType + '\'' +
                ", place='" + place + '\'' +
                ", time='" + time + '\'' +
                ", details='" + details + '\'' +
                ", petType='" + petType + '\'' +
                ", petRace='" + petRace + '\'' +
                ", petName='" + petName + '\'' +
                ", petGender='" + petGender + '\'' +
                '}';
    }
}
