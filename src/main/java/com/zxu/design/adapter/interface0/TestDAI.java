package com.zxu.design.adapter.interface0;
/**
 * 接口 InterfaceA
 * 适配器 InterfaceBAdapter
 * 实现类 InterfaceBImpl
 * 为避免实现A的全部方法，利用适配器进行抽象，这样实现类只需要实现需要的方法
 */
public class TestDAI {
	public static void main(String[] args) {
		InterfaceA interfaceA = new InterfaceBImpl();
		interfaceA.sayC1();
	}
}
