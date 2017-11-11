package VO;

import java.util.Arrays;

/**
 * Created by ael on 2017. 11. 11..
 */

public class SimplePetVO {

    private String petId;
    private String petName;
    private String petGender;

    public SimplePetVO() {
    }

    public SimplePetVO(String petId, String petName, String petGender) {
        this.petId = petId;
        this.petName = petName;
        this.petGender = petGender;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
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
        return "SimplePetVO{" +
                "petId='" + petId + '\'' +
                ", petName='" + petName + '\'' +
                ", petGender='" + petGender + '\'' +
                '}';
    }
}
