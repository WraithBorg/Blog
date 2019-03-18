package com.zxu.design.factory.simple;

public class PlasticDesk implements Desk {
	@Override
	public void getType() {
		System.out.println("Plastic");
	}
}
