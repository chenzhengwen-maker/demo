package com.example.demo.designpattern.builder.v2;

public class BuilderTest2 {
    public static void main(String[] args) {
        Product product = new Product.Builder().buildPart1("1").buildPart2("2").buildPart3("3").buildPart4("4").build();
        System.out.println(product);

    }
}
class Product{
    private String part1;
    private String part2;
    private String part3;
    private String part4;

    public Product(String part1, String part2, String part3, String part4) {
        this.part1 = part1;
        this.part2 = part2;
        this.part3 = part3;
        this.part4 = part4;
    }

    @Override
    public String toString() {
        return "Product{" +
                "part1='" + part1 + '\'' +
                ", part2='" + part2 + '\'' +
                ", part3='" + part3 + '\'' +
                ", part4='" + part4 + '\'' +
                '}';
    }

    static class Builder{
        private String part1;
        private String part2;
        private String part3;
        private String part4;
        public Builder buildPart1(String part1){
            this.part1 = part1;
            return this;
        }
        public Builder buildPart2(String part2){
            this.part2 = part2;
            return this;
        }
        public Builder buildPart3(String part3){
            this.part3 = part3;
            return this;
        }
        public Builder buildPart4(String part4){
            this.part4 = part4;
            return this;
        }
        public Product build(){
            return new Product(part1, part2, part3, part4);
        }
    }
}
