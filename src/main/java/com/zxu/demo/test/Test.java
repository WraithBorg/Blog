package com.zxu.demo.test;

import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		String copyCmd = "cmd.exe /c xcopy \"C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Data\\newdb\" \"c:\\test\" /y";
		try {
			Process process = Runtime.getRuntime().exec(copyCmd);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
