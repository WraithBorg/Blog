# 常用验签算法
```
签名算法

{
    "appid":"T1212",  // 开发者appid
    "data":"refund_sheet_id=THRK-T1212-PL12345678-D321654&order_id=PL12345678&sku_id=D321654",  // 消息数据，注意data内容是经过url encode（UTF-8）编码的消息对象。
    "id":"5f20f201db88bf7d09260296",  // 消息唯一id
    "msg_type":"refund_sheet_submit",  // 消息类型
    "nonce_str":"14941817736b446e",  // 随机字符串
    "seq_id":1234579,  // 序列id
    "sign":"CE8A52E1F25C7CE9753FEB66D0517EA3",  // 签名
    "station_id":"T1212",  // 消息发出方的站点id
    "ts":1595994625.353  // 消息发出时刻的时间戳
}
将所有参数（除sign外）按照字典序排列成key1=value1&key2=value2的形式。
例如以上消息体转化成：appid=APPID&data=order_id=PL8988070&id=5db549153c364496424ab0b3&msg_type=order_create&nonce_str=c95eb4272b884410&station_id=T12345&ts=1572172611.975702
将密钥secret参数以加在最后，组成key1=value1&key2=value2&scret=SECRET的格式。即：appid=APPID&data=order_id=PL8988070&id=5db549153c364496424ab0b3&msg_type=order_create&nonce_str=c95eb4272b884410&station_id=T12345&ts=1572172611.975702&secret=SECRET
将以上生成的字符串MD5加密取大写
```
