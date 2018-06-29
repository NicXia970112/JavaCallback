

public class Demo {

    public static void main(String args[]) {
        Value v = new Value();
        v.setSum(0);
        v.getSUM();
        for(int i = 0; i < 100; i++) {
            System.out.println("执行后续任务"  + " " + i + "%");
        }
        while(v.getSum() == 0) {
            try {
                Thread.sleep(1000);
                System.out.println(v.getSum());
            } catch (InterruptedException e) {
            }
        }
        System.out.print(v.getSum());
    }
}

interface Callback {
    public void setSUM(int sum);

    public void getSUM();
}

class Value implements Callback{

    private int sum;


    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public void setSUM(int sum) {
        this.sum = sum;
    }

    @Override
    public void getSUM() {
        Method m = new Method();
        Callback callback = this;

        new Thread(new Runnable() {
            @Override
            public void run() {
                m.sum(callback);
            }
        }).start(); //异步回调

        //m.sum(callback);　//同步回调
    }
}

class Method {
    public void sum(Callback callback) {
        int sum = 0;
        for(int i = 0; i < 100; i++) {
            sum += i;
            System.out.println("正在异步进行运算" + " " + i + "%");
        }
        callback.setSUM(sum);
    }
}


