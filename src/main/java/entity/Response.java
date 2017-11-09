package entity;

import java.text.ParseException;

public class Response {

    double interestRate;
    String ssn;


    public Response(String commaSeparatedValue) {
        String[] values = commaSeparatedValue.split(",");
        try {
            this.ssn = values[0];
            this.interestRate = Double.parseDouble(values[1]);
        }catch (Exception ex){
            ex.getMessage();
        }
    }

    public Response(double interestRate, String ssn) {
        this.interestRate = interestRate;
        this.ssn = ssn;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public String toString() {
        return "Response{" +
                "interestRate=" + interestRate +
                ", ssn='" + ssn + '\'' +
                '}';
    }


}
