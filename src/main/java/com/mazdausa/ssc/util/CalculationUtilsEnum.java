package com.mazdausa.ssc.util;

public enum CalculationUtilsEnum {
	
	WEEK_PER_YEAR(13), 
    DAYS_PER_WEEK(5.5),
    DFS_ACCT_POS_NO(3)
    
    //etc
    ;

    private double value;

    CalculationUtilsEnum(double value) {
        this.value = value;
    }

    public double getvalue() { 
        return value;
    }

}
