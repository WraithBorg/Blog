package com.zxu.design.factory.simple;
/**
 * 简单工厂模式
 * 目标类实现变多，必须修改工厂方法，违背了开闭原则
 * 开闭原则：对扩展开发，对修改关闭
 */
public class TestSimpleF {
	public static void main(String[] args) {
		Desk desk = DeskSimpleFactory.getDesk("Wooden");
		desk.getType();
	}
}
