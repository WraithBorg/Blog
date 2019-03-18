package com.zxu.design.factory.simple;

public class WoodenDesk implements Desk{
	@Override
	public void getType() {
		System.out.println("Wooden");
	}
}
