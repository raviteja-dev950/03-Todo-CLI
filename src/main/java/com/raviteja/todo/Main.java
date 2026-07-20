package com.raviteja.todo;

import com.raviteja.todo.model.Task;
import com.raviteja.todo.service.TaskService;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskService service = new TaskService();

        System.out.println("=== RaviTeja's To-Do List | Project 3 ===");

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Mark Task as Done");
            System.out.println("4. Delete Task");
            System.out.println("5. View Pending Tasks");
            System.out.println("6. View Completed Tasks");
            System.out.println("7. Total Count");
            System.out.println("q. Quit");
            System.out.print("Choose option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Task Title: ");
                    String title = sc.nextLine();
                    Task added = service.addTask(title);
                    System.out.println("✅ Added: " + added);
                    break;

                case "2":
                    List<Task> all = service.getAllTasks();
                    if (all.isEmpty()) System.out.println("No tasks yet!");
                    else {
                        System.out.println("\n--- All Tasks ---");
                        all.forEach(System.out::println);
                    }
                    break;

                case "3":
                    System.out.print("Enter ID to mark done: ");
                    int doneId = Integer.parseInt(sc.nextLine());
                    if (service.markDone(doneId)) System.out.println("✅ Marked as Done!");
                    else System.out.println("❌ ID not found!");
                    break;

                case "4":
                    System.out.print("Enter ID to delete: ");
                    int delId = Integer.parseInt(sc.nextLine());
                    if (service.deleteTask(delId)) System.out.println("🗑 Deleted!");
                    else System.out.println("❌ ID not found!");
                    break;

                case "5":
                    List<Task> pending = service.getPendingTasks();
                    if (pending.isEmpty()) System.out.println("No pending tasks! Great job!");
                    else pending.forEach(System.out::println);
                    break;

                case "6":
                    List<Task> completed = service.getCompletedTasks();
                    if (completed.isEmpty()) System.out.println("No completed tasks yet!");
                    else completed.forEach(System.out::println);
                    break;

                case "7":
                    System.out.println("📊 Total Tasks: " + service.getTotalCount());
                    break;

                case "q":
                case "Q":
                    System.out.println("Bye Bye! Project 3 Done. Keep grinding!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}	