```
 public static void main(String[] args) {
        System.out.println(tt());
    }

    static int tt() {
        int a = 0;
        try {
            throw new HsmException("asf");
        } catch (Exception e) {
            return a++;
        } finally {
            return a++;
        }
    }
```
