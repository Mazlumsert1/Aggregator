package controller;

import org.apache.commons.lang3.math.NumberUtils;
public class Validator {

    private boolean isSsn(String ssn){
        return NumberUtils.isCreatable(ssn) && ssn.length() == 10;
    }

    private boolean isInterestRate(String interestRate){
        return NumberUtils.isCreatable(interestRate);
    }

    public boolean isValid(String request){
        String[] values = request.split( "," );
        boolean requestLength = values.length == 2;
        boolean validRequest = isSsn(values[0]) &&
                               isInterestRate(values[1]) ;
        return requestLength && validRequest;
    }



}
