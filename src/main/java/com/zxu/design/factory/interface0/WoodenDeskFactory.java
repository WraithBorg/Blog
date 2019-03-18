package com.zxu.design.factory.interface0;

public class WoodenDeskFactory implements DeskFactory{
	@Override
	public IDesk createDesk() {
		return new IWoodenDesk();
	}
}
