package com.example.demo.designpattern.chainofresponsibility;

import org.springframework.context.annotation.Bean;

public class ChainOfResponsibilityTest {
    public static void main(String[] args) {
        Request request = new Request.RequestBuilder().frequentOk(true).loggedOn(true).build();
        RequestFrequentHandler requestFrequentHandler = new RequestFrequentHandler(new LoggingHandler(null));
        if (requestFrequentHandler.process( request )) {

            System.out.println(" 正常业务处理");
        }else{
            System.out.println(" 访问异常");
        }

    }
}
class Request{
    private boolean loggedOn;
    private boolean frequentOk;
    private boolean isPermits;
    private boolean containsSensitiveWords;
    private String requestBody;

    public Request(boolean loggedOn, boolean frequentOk, boolean isPermits, boolean containsSensitiveWords) {
        this.loggedOn = loggedOn;
        this.frequentOk = frequentOk;
        this.isPermits = isPermits;
        this.containsSensitiveWords = containsSensitiveWords;
    }
    static class RequestBuilder{
        private boolean loggedOn;
        private boolean frequentOk;
        private boolean isPermits;
        private boolean containsSensitiveWords;
        RequestBuilder loggedOn(Boolean loggedOn){
            this.loggedOn = loggedOn;
            return this;
        }
        RequestBuilder frequentOk(Boolean frequentOk){
            this.frequentOk = frequentOk;
            return this;
        }
        RequestBuilder isPermits(Boolean isPermits){
            this.isPermits = isPermits;
            return this;
        }
        RequestBuilder containsSensitiveWords(Boolean containsSensitiveWords){
            this.containsSensitiveWords = containsSensitiveWords;
            return this;
        }
        public Request build(){
            return new Request(loggedOn,frequentOk,isPermits,containsSensitiveWords);
        }
    }

    public boolean isLoggedOn() {
        return loggedOn;
    }

    public void setLoggedOn(boolean loggedOn) {
        this.loggedOn = loggedOn;
    }

    public boolean isFrequentOk() {
        return frequentOk;
    }

    public void setFrequentOk(boolean frequentOk) {
        this.frequentOk = frequentOk;
    }

    public boolean isPermits() {
        return isPermits;
    }

    public void setPermits(boolean permits) {
        isPermits = permits;
    }

    public boolean isContainsSensitiveWords() {
        return containsSensitiveWords;
    }

    public void setContainsSensitiveWords(boolean containsSensitiveWords) {
        this.containsSensitiveWords = containsSensitiveWords;
    }
}

abstract class Handler{
    Handler next;
    public Handler(Handler next){
        this.next = next;
    }
    public Handler getNext(){
        return next;
    }
    public void setNext(Handler next){
        this.next = next;
    }
    public abstract boolean process(Request request);
}
class RequestFrequentHandler extends Handler{

    public RequestFrequentHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean process(Request request) {
        System.out.println("访问频率控制.");
        if(request.isFrequentOk()){
            Handler next = getNext();
            if(next == null){
                return true;
            }
            if(!next.process(request)){
                return false;
            }else{
                return true;
            }
        }
        return false;
    }
}

class LoggingHandler extends Handler{

    public LoggingHandler(Handler next) {
        super( next );
    }

    @Override
    public boolean process(Request request) {
        System.out.println(" 登录验证");
        if (request.isLoggedOn()){
            Handler next=getNext();
            if (null==next){
                return true;
            }
            if (!next.process( request )) {
                return false;
            }else{
                return true;
            }
        }
        return false;
    }
}
