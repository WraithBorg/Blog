package com.zxu.design.adapter.class0;
/**
 * 接口ClassA
 * 接口ClassB
 * 实现类 ClassAImpl
 * 适配器ClassBImplAdapter 将B接口转换为A接口
 */
public class DACTest {
	public static void main(String[] args) {
		ClassB classB = new ClassBImplAdapter();
		classB.sayClassB();
		// OutPut: ClassA
	}
}
