package Models;

public class Vote {


    private String mailAddress;
    private int value;

    public Vote(int value, String mailAddress) {
        this.value = value;
        this.mailAddress = mailAddress;
    }

    public Vote() {

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMail() {
        return mailAddress;
    }

    public void setMail(String mail) {
        this.mailAddress = mail;
    }
}

