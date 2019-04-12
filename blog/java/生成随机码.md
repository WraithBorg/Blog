```java
/**
 * 生成随机码
 */
public static String getRamdon() {
	String retStr;
	String strTable = "123456789ABCDEFGHJKLMNPQRSTUVWXYZ";
	int len = strTable.length();//33 去掉 o0I
	retStr = "";
	StringBuilder retStrBuilder = new StringBuilder(retStr);
	for (int i = 0; i < 6; i++) {
		int intR = (int) Math.floor( Math.random() * len);
		char c = strTable.charAt(intR);
		retStrBuilder.append(strTable.charAt(intR));
	}
	retStr = retStrBuilder.toString();
	return retStr;
}
/**
 * 生成至少包含两位数字的随机码
 */
public static String generateRamdon() {
	String retStr;
	String strTable = "123456789ABCDEFGHJKLMNPQRSTUVWXYZ";
	int len = strTable.length();//33 去掉 o0I
	boolean bDone = true;
	do {
		retStr = "";
		int count = 0;
		StringBuilder retStrBuilder = new StringBuilder(retStr);
		for (int i = 0; i < 6; i++) {
			int intR = (int) Math.floor( Math.random() * len);
			char c = strTable.charAt(intR);
			if (('0' <= c) && (c <= '9')) {
				count++;
			}
			retStrBuilder.append(strTable.charAt(intR));
		}
		retStr = retStrBuilder.toString();
		if (count >= 2) bDone = false;//至少含有两个数字

	} while (bDone);

	return retStr;

}
```
