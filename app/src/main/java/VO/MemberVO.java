package VO;

/**
 * Created by Huni on 2017-11-05.
 */

public class MemberVO {

    private String memId;
    private String memEmail;
    private String memPassword;
    private String memNickName;
    private String memName;
    private String memPhone;

    public MemberVO(){}

    public MemberVO(String memEmail, String memNickName, String memName, String memPhone)
    {
        this.memEmail = memEmail;
        this.memNickName = memNickName;
        this.memName = memName;
        this.memPhone = memPhone;
    }
    public MemberVO(String memId, String memEmail, String memPassword, String memNickName, String memName, String memPhone)
    {
        this.memId = memId;
        this.memEmail = memEmail;
        this.memPassword = memPassword;
        this.memNickName = memNickName;
        this.memName = memName;
        this.memPhone = memPhone;
    }
    public String getMemId(){return this.memId;}

    public void setMemId(){this.memId = memId;}

    public String getMemEmail(){return this.memEmail;}

    public void setMemEmail(String memEmail){this.memEmail = memEmail;}

    public String getMemPassword(){return this.memPassword;}

    public void setMemPassword(String memPassword){this.memPassword = memPassword;}

    public String getMemNickName() {return this.memNickName;}

    public void setMemNickName(String memNickName) {this.memNickName = memNickName;}


    public String getMemName() {return this.memName;}

    public void setMemName(String memName){this.memName = memName;}

    public String getMemPhone(){return this.memPhone;}

    public void setMemPhone(String memPhone){this.memPhone = memPhone;}

    @Override
    public String toString() {
        return "MemberVO{" +
                "memId='" + memId + '\'' +
                ", memEmail='" + memEmail + '\'' +
                ", memPassword='" + memPassword + '\'' +
                ", memNickName='" + memNickName + '\'' +
                ", memName='" + memName + '\'' +
                ", memPhone='" + memPhone + '\'' +
                '}';
    }
}
