package com.zxu.design.adapter.object0;
/**
 * 接口 ObjectA
 * 接口 ObjectB
 * 实现类 ObjectAImpl
 * 适配器 ObjectBImplAdapter 将接口B转换为接口A
 */
public class TestDAO {
	public static void main(String[] args) {
		ObjectB objectB = new ObjectBImplAdapter(new ObjectAImpl());
		objectB.sayB();
		//OutPut: O A
	}
}
