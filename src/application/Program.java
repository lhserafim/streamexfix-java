package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main(String args[]) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: "); //  /Users/lhserafim/IdeaProjects/StreamExFix-java/in.txt
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Employee> list = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0],fields[1],Double.parseDouble(fields[2])));
                line = br.readLine();
            }
            System.out.print("Enter salary: ");
            double salary = sc.nextDouble();

            List<String> email = list.stream()
                    .filter(x -> x.getSalary() > salary)
                    .map(x -> x.getEmail())
                    .sorted()
                    .collect(Collectors.toList());

            System.out.println("Email of people whose salary is more than 2000.00:");
            email.forEach(System.out::println);

            double sum = list.stream()
                    .filter(x -> x.getName().toUpperCase().charAt(0) == 'M')
                    .map(x -> x.getSalary())
                    .reduce(0.0,(x,y) -> x + y);
            System.out.println("Sum of salary from people whose name starts with 'M': " + String.format("%.2f", sum));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
