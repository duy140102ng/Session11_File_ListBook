package ra.run;

import ra.IBook;
import ra.imp.Book;

import java.io.*;
import java.util.*;

public class Main {
    public static List<Book> listBook = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Book> listBook = new ArrayList<>();
        Book book = new Book();
        listBook.add(book);
        writeDataToFile(listBook);
        List<Book> listBookRead = readDataFromFile();
        if (listBookRead == null) {
            System.err.println("Có lỗi trong quá trình đọc file");
        } else {
            System.out.println("Thông tin sinh viên vừa đọc: ");
            listBookRead.forEach(Book -> System.out.println(book));
        }
        Main main = new Main();
        int choice = 0;
        do {
            System.out.println("*********************************MENU********************************\n" +
                    "1. Nhập thông tin sách\n" +
                    "2. Hiển thị thông tin sách\n" +
                    "3. Cập nhật thông tin sách theo mã sách\n" +
                    "4. Xóa sách theo mã sách\n" +
                    "5. Sắp xếp sách theo giá bán tăng dần\n" +
                    "6. Thống kê sách theo khoảng giá (a-b nhập từ bàn phím)\n" +
                    "7. Tìm kiếm sách theo tên tác giả\n" +
                    "8. Thoát");
            System.out.println("Nhập lựa chọn của bạn: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Có lỗi: " + e);
            }
            switch (choice) {
                case 1:
                    main.inputBook(scanner);
                    break;
                case 2:
                    main.displayBook();
                    break;
                case 3:
                    main.updateBookById(scanner);
                    break;
                case 4:
                    main.deleteBookById(scanner);
                    break;
                case 5:
                    main.sortBookByPrice();
                    break;
                case 6:
                    main.statisticalBookByPrice(scanner);
                    break;
                case 7:
                    main.searchBookByAuthor(scanner);
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Mời bạn lựa chon từ 1-8");
            }
        } while (true);
    }

    public void inputBook(Scanner scanner) {
        System.out.println("Mời bạn nhập số lượng sách: ");
        try {
            int n = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < n; i++) {
                Book book = new Book();
                book.inputData(scanner);
                listBook.add(book);
            }
        } catch (Exception e) {
            System.err.println("Có lỗi: " + e);
        }
    }

    public void displayBook() {
        System.out.println("THÔNG TIN SÁCH: ");
        try {
            for (int i = 0; i < listBook.size(); i++) {
                Book book = listBook.get(i);
                book.displayData();
                System.out.println("-------------------------------------");
            }
        } catch (Exception e) {
            System.err.println("Có lỗi: " + e);
        }
    }

    public void updateBookById(Scanner scanner) {
        System.out.println("Mời bạn nhập mã sách cần cập nhật: ");
        try {
            int updateBookId = Integer.parseInt(scanner.nextLine());
            boolean found = false;//Kiểm tra mã sách cần cập nhật có tồn tại hay không
            for (int i = 0; i < listBook.size(); i++) {
                Book book = listBook.get(i);
                if (book.getBookId() == updateBookId) {
                    book.inputData(scanner);
                    System.out.println("Thông tin sách đã được cập nhật");
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.err.println("Không tìm thấy mã sách của bạn, vui lòng nhập lại");
            }
        } catch (Exception e) {
            System.err.println("Có lỗi " + e);
        }
    }

    public void deleteBookById(Scanner scanner) {
        System.out.println("Mời bạn nhập mã sách cần xóa: ");
        try {
            int deleteBookId = Integer.parseInt(scanner.nextLine());
            Iterator<Book> iterator = listBook.iterator();
            while (iterator.hasNext()){
                Book book = iterator.next();
                if (book.getBookId() == deleteBookId){
                    iterator.remove();
                    System.out.println("Thông tin sách đã xóa");
                    return;
                }
            }
            System.err.println("Không tìm thấy mã sách của bạn, vui lòng nhập lại");
        } catch (Exception e) {
            System.err.println("Có lỗi " + e);
        }
    }
    public void sortBookByPrice(){
        Collections.sort(listBook, Comparator.comparing(Book::getExportPrice));
    }
    public void statisticalBookByPrice(Scanner scanner){
        try {
            System.out.println("Mời bạn nhập khoảng giá đầu: ");
            Float a = Float.parseFloat(scanner.nextLine());
            System.out.println("Mời bạn nhập khoảng giá cuối: ");
            Float b = Float.parseFloat(scanner.nextLine());
            if (a > b){
                System.err.println("Gía cuối không được nhỏ hơn giá đầu, vui lòng nhập lại!");
                return;
            }
            int count = 0;
            for (int i = 0; i < listBook.size(); i++) {
                Book book = listBook.get(i);
                float priceBook = book.getExportPrice();
                if (priceBook >= a && priceBook <= b){
                    System.out.println(book);
                    count++;
                }
            }
            if (count == 0){
                System.out.println("Không có sách nào trong khoảng giá từ " +a + "-" +b);
            }else {
                System.out.println("Tổng số sách trong khoảng giá từ " +a + "-"+b+" là: " +count);
            }
        }catch (Exception e){
            System.err.println("Gía không hợp lệ, vui lòng nhập lại");
            System.err.println("Có lỗi: " +e);
        }
    }
    public void searchBookByAuthor(Scanner scanner){
        System.out.println("Mời bạn nhập tên tác giả: ");
        try {
            String searchAuthor = scanner.nextLine();
            boolean found = false;
            for (int i = 0; i < listBook.size(); i++) {
                Book book = listBook.get(i);
                if (book.getAuthor().equalsIgnoreCase(searchAuthor)){//Không phân biệt chữ hoa với chữ thường
                    System.out.println(book);
                }
            }
            if (!found){
                System.err.println("Không tìm thấy tên tác giả, vui lòng nhập lại!");
            }
        }catch (Exception e){
            System.err.println("Có lỗi: " +e);
        }
    }

    public static void writeDataToFile(List<Book> listBook) {
        File file = new File("books.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listBook);
            oos.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static List<Book> readDataFromFile() {
        List<Book> listBookRead = null;
        File file = new File("books.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            listBookRead = (List<Book>) ois.readObject();
            return listBookRead;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }
}