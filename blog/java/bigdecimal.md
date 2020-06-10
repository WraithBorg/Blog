```
odd 奇数，even偶数
ROUND_HALF_EVEN ：如果舍弃的数字 ，左边的数是奇数 则等同于ROUND_HALF_DOWN，反之则等同于 则等同于ROUND_HALF_UP。四舍六入，五看前一位是偶数舍，奇数进位

ROUND_HALF_DOWN：五舍六入   如果被舍弃的数字大于0.5，则等同于ROUND_UP，反之则则等同于ROUND_DOWN
ROUND_HALF_UP：  四舍五入   如果被舍弃的数字大于等于0.5，则等同于ROUND_UP，反之则则等同于ROUND_DOWN

ROUND_UP ：   向上取值，  如果被舍弃的数字大于0，则进位
ROUND_DOWN ： 向下取值，  如果被舍弃的数字大于0，则进位为什么用ROUND_HALF_EVEN 

ROUND_CEILING     如果   BigDecimal   是正的，则做   ROUND_UP   操作；如果为负，则做   ROUND_DOWN   操作。    
ROUND_FLOOR		如果   BigDecimal   为正，则作  ROUND_DOWN   ；如果为负，则作   ROUND_UP    。

尽管习惯上把4.5四舍五入到5，但事实上，4.5并不比4更接近5(两者相差0.5)。在处理大量的科学或统计数据时(趋势很重要)，传统的四舍五入会使数据略微向上倾斜。在一组大的数据中，或者在像数字信号处理中那样执行许多后续舍入操作时，四舍五入规则往往会减少总的舍入误差，(平均而言)有相同比例的数字向上舍入而向下舍入。这通常减少了结果的向上倾斜

```
