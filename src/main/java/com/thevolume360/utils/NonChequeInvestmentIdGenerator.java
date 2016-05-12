package com.thevolume360.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.thevolume360.domain.enums.ClientType;
import com.thevolume360.domain.enums.InvestmentType;

public class NonChequeInvestmentIdGenerator {
	public static final int BASE_YEAR = 2015;
	private static final Random random = new Random();

	private static final String[] symbolPool = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "A",
			"B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
			"Y", "Z" };
	/* The last millisecond used by genFormIdSeq */
	private static int lastMillis = 0;
	private static int lastSecond = 0;

	public static String generate(String clientName, ClientType clientType, Date investmentDate) {
		String clientTypeShortCode = clientType.getShortCode();
		String randomShortCode = getThreeRandomCharString(clientName);
		String seq = nextSequence(investmentDate); // the sequence
		String main = clientTypeShortCode + "-" + InvestmentType.CASH + "-" + randomShortCode + "-" + seq;
		return main + calculateCheckDigit(main);
	}

	private static String nextSequence(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		int year = calender.get(Calendar.YEAR) - BASE_YEAR;
		int month = calender.get(Calendar.MONTH) + 1; // In java January is 0
		int day = calender.get(Calendar.DAY_OF_MONTH); // starts with 1
		int second = calender.get(Calendar.HOUR_OF_DAY) * 3600 + calender.get(Calendar.MINUTE) * 60
				+ calender.get(Calendar.SECOND);
		int timeStamp = calender.get(Calendar.MILLISECOND);
		int random = NonChequeInvestmentIdGenerator.random.nextInt(5); // Generate
																		// 5
		// digits at max
		if (second <= lastSecond) {
			if (second < lastSecond) {
				second = lastSecond;
			}
			if (timeStamp <= lastMillis) {
				timeStamp = lastMillis + 1;
				if (timeStamp >= 1024) {
					// There are 86400 seconds/day. The seconds value is
					// converted to base 32 and truncated to 4 digits.
					// This gives a max of about 1 million. It's unlikely that
					// our "s" will wrap around.
					second++;
					timeStamp = 0;
				}
			}
		}
		lastSecond = second;
		lastMillis = timeStamp;

		String y32 = base32(year, 0);
		String m32 = base32(month, 0);
		String d32 = base32(day, 0);
		String s32 = base32(second, 4);
		String millis32 = base32(timeStamp, 2);
		String random5Digit = base32(random, 0); // max 5 digits
		return y32 + m32 + d32 + s32 + millis32 + random5Digit;
	}

	/**
	 * Convert a base 10 integer to base32 string representation.
	 *
	 * @param n
	 *            The number to convert.
	 * @param width
	 *            Truncate to this width. 0 means no truncation.
	 * @return The base32 number.
	 */
	private static String base32(int n, int width) {
		StringBuilder val = new StringBuilder(10);
		int r;
		while (n > 0) {
			r = n % 32;
			n = n / 32;
			val.append(symbolPool[r]);
		}
		val.reverse();
		if (width == 0)
			return val.toString();
		StringBuilder v2 = new StringBuilder(10);
		for (int len = val.length(); len < width; len++) {
			v2.append(symbolPool[0]);
		}
		v2.append(val);
		return v2.toString();
	}

	private static String calculateCheckDigit(String formId) {
		char[] chars = formId.toCharArray();
		char hyphen = '-';
		int total = 0;
		int pos = 1;
		for (char c : chars) {
			if (c == hyphen)
				continue;
			total += (pos * Character.getNumericValue(c));
			pos++;
		}
		int check = total % 32;
		return base32(check, 0);
	}

	private static String getThreeRandomCharString(String clientName) {
		String randomShortCode = "";
		for (int i = 0; i < 3; i++) {
			randomShortCode += clientName.toUpperCase().charAt(random.nextInt(clientName.length()));
		}
		return randomShortCode;
	}
}
