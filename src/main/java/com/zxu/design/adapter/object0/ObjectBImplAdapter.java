package com.zxu.design.adapter.object0;

public class ObjectBImplAdapter implements ObjectB {
	private ObjectA objectA;

	public ObjectBImplAdapter(ObjectA objectA) {
		this.objectA = objectA;
	}

	@Override
	public void sayB() {
		objectA.sayA();
	}
}
