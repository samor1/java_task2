import java.util.*;

class Employee {
    String fullName;
    int age;
    double salary;

    public Employee(String fullName, int age, double salary) {
        this.fullName = fullName;
        this.age = age;
        this.salary = salary;
    }
}

class Department {
    String name;
    List<Employee> employees;

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public double getTotalSalary() {
        double totalSalary = 0;
        for (Employee employee : employees) {
            totalSalary += employee.salary;
        }
        return totalSalary;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}

public class Company {
    Map<String, Department> departments;

    public Company() {
        this.departments = new HashMap<>();
    }

    public void addDepartment(String name) {
        departments.put(name, new Department(name));
    }

    public void removeDepartment(String name) {
        departments.remove(name);
    }

    public void addEmployeeToDepartment(String departmentName, Employee employee) {
        Department department = departments.get(departmentName);
        if (department != null) {
            department.addEmployee(employee);
        }
    }

    public void removeEmployeeFromDepartment(String departmentName, Employee employee) {
        System.out.println("Из "+departmentName+" удалён сотрудник "+ employee.fullName );
        Department department = departments.get(departmentName);
        if (department != null) {
            department.removeEmployee(employee);
        }
    }

    public void printAllDepartments() {
        for (Department department : departments.values()) {
            System.out.println("Отдел: " + department.name);
        }
    }

    public void printAllEmployeesInDepartment(String departmentName) {
        System.out.println("Сотрудники из: " + departmentName);
        Department department = departments.get(departmentName);
        if (department != null) {
            for (Employee employee : department.getEmployees()) {
                System.out.println(employee.fullName);
            }
        }
    }

    public double getTotalSalaryInDepartment(String departmentName) { // получить общую зарплату по отделу
        Department department = departments.get(departmentName);
        if (department != null) {
            return department.getTotalSalary();
        }
        return 0;
    }

    public static void main(String[] args) {
        Company company = new Company();

        // Добавление отделов и сотрудников
        company.addDepartment("Отдел разработки");
        company.addDepartment("Отдел продаж");

        Employee employee1 = new Employee("Иванов Иван", 30, 5000);
        Employee employee2 = new Employee("Петров Петр", 25, 4000);
        Employee employee3 = new Employee("Сидоров Сидор", 35, 6000);

        company.addEmployeeToDepartment("Отдел разработки", employee1);
        company.addEmployeeToDepartment("Отдел разработки", employee2);
        company.addEmployeeToDepartment("Отдел продаж", employee3);

        // Вывод информации
        //company.printAllDepartments();

        company.printAllEmployeesInDepartment("Отдел разработки");
        company.printAllEmployeesInDepartment("Отдел продаж");

        double totalSalary = company.getTotalSalaryInDepartment("Отдел разработки");
        System.out.println("Общая зарплата 'Отдел разработки': " + totalSalary);

        // Удаление сотрудника из отдела
        company.removeEmployeeFromDepartment("Отдел разработки", employee1);

        totalSalary = company.getTotalSalaryInDepartment("Отдел разработки");
        System.out.println("Общая зарплата 'Отдел разработки' после удаления сотрудника: " + totalSalary);
    }
}