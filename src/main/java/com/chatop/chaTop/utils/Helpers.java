package com.chatop.chaTop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Helpers {
	public String formatDate(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		return formater.format(date);
	}
}
