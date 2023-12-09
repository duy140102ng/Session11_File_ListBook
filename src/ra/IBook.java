package ra;

import ra.imp.Book;

import java.util.Scanner;

public interface IBook {
    void inputData(Scanner scanner);
    void displayData();
    int compareTo(Book otherBoook);
}
