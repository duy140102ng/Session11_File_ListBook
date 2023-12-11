package ra;

import ra.imp.Book;

import java.io.Serializable;
import java.util.Scanner;

public interface IBook extends Serializable {
    void inputData(Scanner scanner);

    void displayData();

    int compareTo(Book otherBook);
}
