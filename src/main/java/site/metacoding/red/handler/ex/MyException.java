package site.metacoding.red.handler.ex;

public class MyException extends RuntimeException{ // 그냥 익셉션이면 try-catch 적어야 되니까 런타임으로.. 어차피 런타임오류들임
    public MyException(String msg){
        super(msg); // 상속에 의해서 쭉쭉 올라가서 결국 익셉션의 메시지에 박힘
    }
}
