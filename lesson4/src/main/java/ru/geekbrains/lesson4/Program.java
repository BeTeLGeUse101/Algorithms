package ru.geekbrains.lesson4;

public class Program {

    public static void main(String[] args) {

        Employee employee1 = new Employee("AAA", 30);

        HashMap<String, String> hashMap = new HashMap<>(4);

        hashMap.put("+79005551122", "Александр");
        hashMap.put("+79005551123", "Сергей");
        hashMap.put("+79005551123", "Алексей");
        hashMap.put("+79005551124", "Александр1");
        hashMap.put("+79005551125", "Александр2");
        hashMap.put("+79005551126", "Александр3");
        hashMap.put("+79005551127", "Александр4");
        hashMap.put("+79005551128", "Александр5");

        hashMap.remove("+79005551127");

        for (String value : hashMap) {
            System.out.println(value);
        }
    }
}