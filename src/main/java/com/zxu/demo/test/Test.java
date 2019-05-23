package com.zxu.demo.test;

import org.hibernate.validator.internal.util.stereotypes.ThreadSafe;

import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		String copyCmd = "abc12345";
		String[] split = copyCmd.split(",");
		for (String s : split) {
			System.out.println(1);
			System.out.println(s);
		}

	}
}
