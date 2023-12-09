package ra.imp;

import ra.IBook;
import ra.run.Main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Book implements IBook {
    private int bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private String author;
    private Date created;
    private String descriptions;

    public Book() {
    }

    public Book(int bookId, String bookName, float importPrice, float exportPrice, String author, Date created, String descriptions) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.author = author;
        this.created = created;
        this.descriptions = descriptions;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.bookId = inputBookId(Main.listBook);
        this.bookName = inputBookName(scanner, Main.listBook);
        this.importPrice = inputImportPriceBook(scanner);
        this.exportPrice = inputExportPriceBook(scanner);
        this.author = inputAuthorBook(scanner);
        this.created = inputCreated(scanner);
        this.descriptions = inputDescriptionsBook(scanner);

    }

    public int inputBookId(List<Book> listBook) {
        try {
            if (listBook.size() == 0) {
                return 1;
            } else {
                int max = listBook.get(0).getBookId();
                for (int i = 0; i < listBook.size(); i++) {
                    if (listBook.get(i).getBookId() > max) {
                        max = listBook.get(i).getBookId();
                    }
                }
                return max + 1;
            }
        } catch (Exception e) {
            System.err.println("Có lỗi: " + e);
        }
        return 1;
    }

    public String inputBookName(Scanner scanner, List<Book> listBook) {
        System.out.println("Mời bạn nhập tên sách: ");
        do {
            String bookName = scanner.nextLine();
            if (bookName.length() == 4) {
                if (bookName.charAt(0) == 'B') {
                    boolean isExit = false;
                    for (int i = 0; i < listBook.size(); i++) {
                        if (listBook.get(i).getBookName().equals(bookName)) {
                            isExit = true;
                            break;
                        }
                    }
                    if (isExit) {
                        System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại!");
                    } else {
                        return bookName;
                    }
                } else {
                    System.err.println("Tên danh mục bắt đầu là B, vui lòng nhập lại!");
                }
            } else {
                System.err.println("Tên danh mục phải có 4 ký tự, vui lòng nhập lại!");
            }
        } while (true);
    }

    public float inputImportPriceBook(Scanner scanner) {
        System.out.println("Mời bạn nhập giá nhập:");
        try {
            do {
                float bookImprortPrice = Float.parseFloat(scanner.nextLine());
                if (bookImprortPrice > 0) {
                    return bookImprortPrice;
                } else {
                    System.err.println("Gía nhập phải lớn hơn 0, vui lòng nhập lại!");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e);
        }
        return 1;
    }

    public float inputExportPriceBook(Scanner scanner) {
        System.out.println("Mời bạn nhập giá xuất: ");
        try {
            do {
                float bookExportPrice = Float.parseFloat(scanner.nextLine());
                if (bookExportPrice > this.importPrice) {
                    return bookExportPrice;
                } else {
                    System.err.println("Gía bán phải lớn hơn giá nhập, vui lòng nhập lại!");
                }
            } while (true);
        } catch (Exception e) {
            System.err.println("Có lỗi: " + e);
        }
        return 2;
    }

    public String inputAuthorBook(Scanner scanner) {
        System.out.println("Mời bạn nhập tác giả: ");
        do {
            String bookAthor = scanner.nextLine();
            if (bookAthor.length() >= 6 && bookAthor.length() <= 50) {
                return bookAthor;
            } else {
                System.err.println("Tên tác giả có độ dài từ 6 - 50 ký tự, vui lòng nhập lại!");
            }
        } while (true);
    }

    public Date inputCreated(Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Nhập vào ngày tạo sản phẩm:");
        do {
            try {
                Date created = sdf.parse(scanner.nextLine());
                return created;
            } catch (Exception ex) {
                System.err.println("Không đúng định dạng ngày tháng, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputDescriptionsBook(Scanner scanner) {
        System.out.println("Nhập mô tả của sách: ");
        do {
            String bookDescriptions = scanner.nextLine();
            if (bookDescriptions.length() <= 500) {
                return bookDescriptions;
            } else {
                System.err.println("Mô tả sách chỉ có độ dài tối đa 500 ký tự, vui lòng nhập lại!");
            }
        } while (true);
    }

    @Override

    public void displayData() {
        System.out.printf("Mã sách: %s - Tên sách: %s - Tác giả: %s - Mô tả sách: %s\n" +
                "Gía nhập: %s - Gía xuất: %s - Ngày xuất bản: %s\n", this.bookId, this.bookName, this.author, this.descriptions, this.importPrice, this.exportPrice, this.created);
    }

    @Override
    public int compareTo(Book otherBoook){
        return Float.compare(this.exportPrice, otherBoook.exportPrice);
    }

}
