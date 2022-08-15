package com.mazdausa.ssc.util;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
@Getter
public final class Utils {

	LocalDate ROLL_TWL_MNTH_ENDDATE = LocalDate.now().minusMonths(1).with(lastDayOfMonth());
	LocalDate ROLL_TWL_MNTH_STARTDATE = ROLL_TWL_MNTH_ENDDATE.minusYears(1).withDayOfMonth(1);
}
