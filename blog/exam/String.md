# String
`https://juejin.im/post/59fffddc5188253d6816f9c1`
byte是字节，char是字符，bytes 字节流
如果不考虑线程安全，优先选择StringBuilder
String 字符串是常量，其值在实例创建后就不能被修改
hash值很多时候用来判断两个对象的值是否相等，所以需要尽可能保证唯一性
由于String对象是不可变的，所以在重载的时候会创建多个对象，而StringBuilder对象是可变的，可以直接使用append方法来进行拼接
equals方法 先判断两个对象的地址是否相等
StringBuffer跟StringBuilder差不多，只不过在所有的方法上面加了一个同步锁而已
String重新了Object的equas方法，所以只要两个String对象的值一样，那么就会返回true.
==:这个比较的是内存地址
String被final修饰，一旦被创建，无法更改
String类的所有方法都没有改变字符串本身的值，都是返回了一个新的对象
如果你需要一个可修改的字符串，应该使用StringBuilder或者 StringBuffer
如果你只需要创建一个字符串，你可以使用双引号的方式，如果你需要在堆中创建一个新的对象，你可以选择构造函数的方式
在使用StringBuilder时尽量指定大小这样会减少扩容的次数，有助于提升效率
### String 实现了Serializable Comparable CharSequence三个接口
Serializable 可以被序列化
Comparable 可以用于比较大小
CharSequence 表示一直有序字符序列，实现了通用的字符序列方法
### String 有三个成员变量 char value[] ,int hash , long serialVersionUID,ObjectStreamField[] serialPersistentFields
String 是一个字符串，由字符 char 所组成，因此实际上 String 内部其实就是一个字符数组，用 value[] 表示，注意这里的 value[] 是用 final 修饰的，表示该值是不允许修改的
String 是一个字符序列，内部数据结构其实是一个字符数组，所有的操作方法都是围绕这个字符数组的操作
String 中频繁使用到了 System 类的 arraycopy 方法，目的是拷贝字符数组
String 内部本质就是操作字符数组 value[]

String长度不可变而StringBuffer和SringBuilder长度可变
SringBuilder 线程不安全 和 StringBuffer线程安全
# 序列化
