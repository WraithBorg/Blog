package com.zxu.design.factory.interface0;
/**
 * 工厂模式
 * 满足开闭原则
 * MyBatis使用较多 例如事物模块和数据源模块
 */
public class TestComplexFactory {
	public static void main(String[] args) {
		DeskFactory deskFactory = new PlasticDeskFactory();
		IDesk desk = deskFactory.createDesk();
		desk.getType();
	}
}
