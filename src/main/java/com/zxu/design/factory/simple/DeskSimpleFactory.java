package com.zxu.design.factory.simple;

public class DeskSimpleFactory {
	static Desk getDesk(String type) {
		Desk desk;
		if (type.equals("Wooden")) {
			desk = new WoodenDesk();
		} else if (type.equals("Plastic")) {
			desk = new PlasticDesk();
		} else {
			desk = null;
		}
		return desk;
	}
}
