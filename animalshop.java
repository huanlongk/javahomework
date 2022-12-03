import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class animalshop {
    public static void main(String[] args) {
        System.out.println("欢迎店长进入宠物店");
        new Test();
    }
}
abstract class   Animal{
    protected String AnimalName;
    protected int age;
    protected String sex;
    protected double price;
    public Animal() {

    }
    public Animal(String AnimalName, int age, String sex){
        this.AnimalName = AnimalName;
        this.age = age;
        this.sex = sex;
    }

    public abstract String toString(String AnimalName, int age, String sex,double price);

    public String getAnimalName() {
        return AnimalName;
    }

    public void setAnimalName(String animalName) {
        AnimalName = animalName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
class Dog extends Animal{
    Boolean  isVaccineInjected;
    public Dog() {

    }
    public Dog(String AnimalName, int age, String sex, double price,Boolean  isVaccineInjected) {
        super(AnimalName, age, sex);
        this.price=100;
        this.isVaccineInjected=isVaccineInjected;
    }

    public Boolean getVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(Boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString(String AnimalName, int age, String sex, double price) {
        return "狗{" +
                "宠物名：'" + AnimalName + '\'' +
                ", 岁数：" + age +
                ", 性别：'" + sex + '\'' +
                ", 价格：" + price +
                ", 是否注射疫苗：" + isVaccineInjected +
                '}';
    }
}
class Cat extends Animal{
    public Cat() {

    }
    public Cat(String AnimalName, int age, String sex, double price) {
        super(AnimalName, age, sex);
        this.price=200;
    }

    @Override
    public String toString(String AnimalName, int age, String sex, double price) {
        return "猫{" +
                "宠物名：'" + AnimalName + '\'' +
                ", 岁数：" + age +
                ", 性别：'" + sex + '\'' +
                ", 价格：" + price +
                '}';
    }
}
class Customer{
    String Name;
    int num;
    LocalTime time;
    double spend;
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getSpend() {
        return spend;
    }

    public void setSpend(double spend) {
        this.spend = spend;
    }


    public String toString() {
        return "客人{" +
                "客人姓名：'" + this.Name + '\'' +
                ", 来店次数：" + this.num +
                ", 来店时间：" + this.time +
                ", 花费：" + this.spend +
                '}';
    }
}
interface AnimalShop1{
    double Buy(List<Animal> animals,double money);
    double Custorm(List<Customer> customers, List<Animal> animals, double money);
    void close(List<Customer> customers,double money,double money0);
}
class MyAnimalShop implements AnimalShop1{
    @Override
    public double Buy(List<Animal> Animals,double money) {
        System.out.println("(1)买狗，(2)买猫");
        Scanner input = new Scanner(System.in);
        int type=input.nextInt();
        if (type==1){
            Dog dog = new Dog();
            System.out.println("请输入狗名称");
            String AnimalName=input.next();
            dog.setAnimalName(AnimalName);
            System.out.println("请输入狗的岁数");
            int AnimalAge=input.nextInt();
            dog.setAge(AnimalAge);
            System.out.println("请输入狗性别");
            String AnimalSex=input.next();
            dog.setSex(AnimalSex);
            System.out.println("是否注射疫苗，（1）注射，（2）未注射");
            int a=input.nextInt();
            if(a==1){
                dog.setVaccineInjected(true);
            }
            else if(a==2){
                dog.setVaccineInjected(false);
            }
            System.out.println("请输入狗的价格（低于100元）");
            double AnimalPrice=input.nextDouble();
            dog.setPrice(AnimalPrice);
            try{
                if(money-AnimalPrice<0){
                    throw new InsufficientBalanceException();
                }
            }catch (InsufficientBalanceException e){
                System.out.println("余额不足，无法购买");
                return money;
            }
            Animals.add(dog);
            System.out.println("购买成功,宠物信息为：");
            System.out.println(dog.toString(AnimalName,AnimalAge,AnimalSex,AnimalPrice));
            return money-AnimalPrice;
        }
        if (type==2){
            Cat cat = new Cat();
            System.out.println("请输入猫名称");
            String AnimalName=input.next();
            cat.setAnimalName(AnimalName);
            System.out.println("请输入猫的岁数");
            int AnimalAge=input.nextInt();
            cat.setAge(AnimalAge);
            System.out.println("请输入猫性别");
            String AnimalSex=input.next();
            cat.setSex(AnimalSex);
            System.out.println("请输入猫的价格（低于200元）");
            double AnimalPrice=input.nextDouble();
            cat.setPrice(AnimalPrice);
            try{
                if(money-AnimalPrice<0){
                    throw new InsufficientBalanceException();
                }
            }catch (InsufficientBalanceException e){
                System.out.println("余额不足，无法购买");
                return money;
            }
            Animals.add(cat);
            System.out.println("购买成功,宠物信息为：");
            System.out.println(cat.toString(AnimalName,AnimalAge,AnimalSex,AnimalPrice));
            return money-AnimalPrice;
        }
        return money;
    }

    @Override
    public double Custorm(List<Customer> customers, List<Animal> Animals, double money) {
        Scanner input = new Scanner(System.in);
        Customer customer = new Customer();
        System.out.println("请输入顾客姓名");
        String CustomerName=input.next();
        customer.setName(CustomerName);
        System.out.println("请输入该客人的进入时间的小时数：");
        int Hour=input.nextInt();
        System.out.println("请输入该客人的进入时间的分钟数：");
        int Min=input.nextInt();
        LocalTime time= LocalTime.of(Hour,Min);
        customer.setTime(time);
        System.out.println("他是第几次来店里的客户呢？");
        int num=input.nextInt();
        customer.setNum(num);
        int i;
        System.out.println("当前可购买宠物:");
        try{
            if (Animals.size()==0){
                throw new AnimalNotFountException();
            }
        }catch (AnimalNotFountException e){
            System.out.println("店里没有宠物了，客人无奈的看看就走了");
            customer.setSpend(0);
            customers.add(customer);
            return money;
        }
        for(i=0;i<Animals.size();i++){
            System.out.println(i+"号："+Animals.get(i).getAnimalName() + "，性别：" + Animals.get(i).getSex() +"，岁数：" + Animals.get(i).getAge() +"，价格：" + Animals.get(i).getPrice());
        }
        System.out.println("请输入客人中意的宠物的编号，若只是逛逛，输入任意不存在编号");
        int number=input.nextInt();
        if(number<Animals.size()&&number>=0){
            System.out.println("成功购买"+number+"号："+Animals.get(number).getAnimalName() + "，性别：" + Animals.get(number).getSex() +"，岁数：" + Animals.get(number).getAge());
            double price=Animals.get(number).getPrice();
            customer.setSpend(Animals.get(number).getPrice());
            Animals.remove(number);
            customers.add(customer);
            return money+price;
        }
        else{
            System.out.println("慢走不送");
            customer.setSpend(0);
            customers.add(customer);
            return money;
        }
    }

    @Override
    public void close(List<Customer> customers,double money,double money0) {
        if(customers.size()==0){
            System.out.println("今天没有客人,啥时候下班也不重要了");
            System.out.println("今天的利润为："+(money-money0));
            return;
        }
        System.out.println("下班时间为为(PS:视最迟的顾客到店时间为下班时间)：");
        int i;
        LocalTime now;
        now=customers.get(0).getTime();
        for(i=0;i<customers.size()-1;i++){
            if(customers.get(i).getTime().isBefore(customers.get(i+1).getTime())){
                now=customers.get(i+1).getTime();
            }
        }
        System.out.println(now);
        System.out.println("在今日来到店里的客人有：");
        for(i=0;i<customers.size();i++){
            if(customers.get(i).getTime().equals(now)){
                System.out.println(customers.get(i).toString());
            }
        }
        System.out.println("今天的利润为："+(money-money0));

    }
}
class AnimalNotFountException extends RuntimeException{

}
class InsufficientBalanceException extends RuntimeException{

}
class Test{
    public Test(){
        double money=500;
        double money0=money;
        List<Animal> Animals = new ArrayList<>();
        Animals.add(new Cat("黄猫",2,"雌",200));
        Animals.add(new Cat("蓝猫",1,"雌",200)) ;
        Animals.add(new Cat("红猫",1,"雄",200)) ;
        Animals.add(new Dog("二哈",3,"雄",100,true)) ;
        Animals.add(new Dog("泰迪",3,"雄",100,true)) ;
        Animals.add(new Dog("金毛",1,"雌",100,true)) ;
        int i;
        System.out.println("当前店内宠物:");
        for(i=0;i<6;i++){
            System.out.println(i+"号："+Animals.get(i).getAnimalName() + "，性别：" + Animals.get(i).getSex() +"，岁数：" + Animals.get(i).getAge() +"，价格：" + Animals.get(i).getPrice());
        }
        System.out.println("当前店内余额："+money+"元");
        List<Customer> Customers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean flag=true;
        while (flag) {
            System.out.println("请选择你要进行的功能：（1）买入动物，（2）招待顾客，（3）歇业(结束本系统)");
            int choise = scanner.nextInt();
            MyAnimalShop myAnimalShop = new MyAnimalShop();
            switch (choise) {
                case 1 : {
                    money = myAnimalShop.Buy(Animals, money);
                    System.out.println("当前余额为" + money);
                    break;
                }
                case 2 : {
                    money = myAnimalShop.Custorm(Customers, Animals, money);
                    System.out.println("当前余额为" + money);
                    break;
                }
                case 3 : {
                    myAnimalShop.close(Customers, money, money0);
                    flag = false;
                }
            }
        }
        System.out.println("今天的宠物店工作结束了");
    }
}








































































