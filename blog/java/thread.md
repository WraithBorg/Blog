# Thread
#### 启动线程注意事项
```
    启动线程必须设置name,方便排错诊断和系统监控
    要及时响应线程中断
         Thread tt = new Thread() {
            @Override
            public void run() {
                super.run();
                for (;;){
                    System.out.println("1");
                    if (Thread.interrupted()){
                        System.out.println("线程中断");
                        break;
                    }
                    System.out.println("2");
                }
            }
        };
        tt.setName("自定义线程");
        tt.start();
        Thread.sleep(1000);
        tt.interrupt();
```