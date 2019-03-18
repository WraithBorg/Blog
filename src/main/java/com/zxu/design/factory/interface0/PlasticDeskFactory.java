package com.zxu.design.factory.interface0;

public class PlasticDeskFactory implements DeskFactory {
	@Override
	public IDesk createDesk() {
		return new IPlasticDesk();
	}
}
