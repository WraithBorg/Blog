package com.zxu.design.adapter.class0;

public class ClassBImplAdapter extends ClassAImpl implements ClassB {
	@Override
	public void sayClassB() {
		sayClassA();
	}
}
