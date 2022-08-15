package com.mazdausa.ssc.util;

public enum ConfigDataEnum {
	
	AVAIL_HOUR_PER_TECH_PER_WEEK("AHRTW"),
	PROFICIENCY_PCT("PROFC"),	
	FLAT_RATE_HOUR_PER_RO("FRHRO"),	
	RO_CIW_CNT_PER_SA_PER_DAY("ROSAD")
	;

private final String text;

/**
 * @param text
 */
ConfigDataEnum(final String text) {
    this.text = text;
}

@Override
public String toString() {
    return text;
}

}
