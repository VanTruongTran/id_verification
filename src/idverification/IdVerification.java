/**
 * project: Identification verification
 * date created: 13/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package idverification;

import java.util.Scanner;

public class IdVerification {
    private static final String[] TINH = new String[]{null, "Hà Nội", "Hà Giang", null, "Cao Bằng", null, "Bắc Kạn", null, "Tuyên Quang", null, "Lào Cai", "Điện Biên", "Lai Châu", null, "Sơn La", "Yên Bái", null, "Hòa Bình", null, "Thái Nguyên", "Lạng Sơn", null, "Quảng Ninh", null, "Bắc Giang", "Phú Thọ", "Vĩnh Phúc", "Bắc Ninh", null, null, "Hải Dương", "Hải Phòng", null, "Hưng Yên", "Thái Bình", "Hà Nam", "Nam Định", "Ninh Bình", "Thanh Hóa", null, "Nghệ An", null, "Hà Tĩnh", null, "Quảng Bình", "Quảng Trị", "Thừa Thiên Huế", null, "Đà Nẵng", "Quảng Nam", null, "Quảng Ngãi", "Bình Định", null, "Phú Yên", null, "Khánh Hòa", null, "Ninh Thuận", null, "Bình Thuận", null, "Kon Tum", null, "Gia Lai", null, "Đắk Lắk", "Đắk Nông", "Lâm Đồng", null, "Bình Phước", null, "Tây Ninh", null, "Bình Dương", "Đồng Nai", null, "Bà Rịa - Vũng Tàu", null, "Hồ Chí Minh", "Long An", null, "Tiền Giang", "Bến Tre", "Trà Vinh", null, "Vĩnh Long", "Đồng Tháp", null, "An Giang", null, "Kiên Giang", "Cần Thơ", "Hậu Giang", "Sóc Trăng", "Bạc Liêu", "Cà Mau"};
    private static final String VERSION = "V1.0.0";
    private static final Scanner SCANNER = new Scanner(System.in);

    private static String canCuocCongDan;

    //hàm main
    public static void main(String[] args) {
        //chọn chức năng giao diện
        switch (nhapMenuChucNangGiaoDien()) {
            case "1"://nhập số CCCD & xử lý menu CCCD
                //nhập mã xác thực
                nhapMaXacThuc();

                //nhập & kiểm tra CCCD hợp lệ
                if (nhapCanCuocCongDan())
                    xuLyMenuCanCuocCongDan();
                else
                    inThongBaoThoat();
                break;

            case "0"://thoát
                inThongBaoThoat();
                break;

            default:
                break;
        }
    }

    //hàm chọn chức năng giao diện
    private static String nhapMenuChucNangGiaoDien() {
        System.out.println("+------------------------------------------------+");
        System.out.printf("| %-46s |\n", String.format("ỨNG DỤNG XÁC MINH CĂN CƯỚC CÔNG DÂN | %s", VERSION));
        System.out.println("+------------------------------------------------+");
        System.out.printf("| %-46s |\n", "1. Nhập CCCD");
        System.out.printf("| %-46s |\n", "0. Thoát");
        System.out.println("+------------------------------------------------+");
        System.out.print("Chọn chức năng: ");
        String answer = SCANNER.nextLine();

        while (!answer.equals("1") && !answer.equals("0")) {
            System.out.print("Chức năng không hợp lệ, vui lòng chọn lại: ");
            answer = SCANNER.nextLine();
        }
        return answer;
    }

    //hàm in thông báo thoát
    private static void inThongBaoThoat() {
        System.out.println("+------------------------------------------------+");
        System.out.printf("| %-46s |\n", "Thoát thành công");
        System.out.println("+------------------------------------------------+");
    }

    //hàm nhập mã xác thực
    private static void nhapMaXacThuc() {
        String maXacThuc = taoMaXacThuc();

        System.out.printf("Mã xác thực: %s\n", maXacThuc);
        System.out.print("Nhập mã xác thực: ");
        String answer = SCANNER.nextLine();

        while (!answer.equals(maXacThuc)) {
            System.out.print("Mã xác thực không hợp lệ, vui lòng nhập lại: ");
            answer = SCANNER.nextLine();
        }
    }

    //hàm tạo ngẫu nhiên mã xác thực
    private static String taoMaXacThuc() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String maXacThuc = "";

        for (int i = 0; i < 6; i++) {
            char kyTuNgauNhien = alphabet.charAt((int) (Math.random() * alphabet.length()));
            maXacThuc = maXacThuc.concat("" + kyTuNgauNhien);
        }
        return maXacThuc;
    }

    //hàm nhập mã số CCCD
    private static boolean nhapCanCuocCongDan() {
        System.out.print("Nhập số CCCD: ");
        canCuocCongDan = SCANNER.nextLine();

        while (!kiemTraCanCuocCongDanHopLe()) {
            System.out.print("Số CCCD không hợp lệ,\nVui lòng nhập lại hoặc nhập 'No' để thoát: ");
            canCuocCongDan = SCANNER.nextLine();
            if (canCuocCongDan.equalsIgnoreCase("No"))
                return false;
        }
        return true;
    }

    //hàm kiểm tra CCCD hợp lệ
    private static boolean kiemTraCanCuocCongDanHopLe() {
        //kiểm tra kích thước chuỗi = 12
        if (canCuocCongDan.length() != 12)
            return false;

        //kiểm tra chuỗi có phải chuỗi số
        for (int i = 0; i < canCuocCongDan.length(); i++) {
            try {
                Integer.parseInt(canCuocCongDan.substring(i, i + 1));
            } catch (Exception errror) {
                return false;
            }
        }

        //kiểm tra mã tỉnh trong số CCCD có hợp lệ hay không
        int maTinh = Integer.parseInt(canCuocCongDan.substring(0, 3));
        if (maTinh >= TINH.length)
            return false;

        return TINH[maTinh] != null;
    }

    //hàm xử lý menu chức năng CCCD
    private static void xuLyMenuCanCuocCongDan() {
        boolean flag = true;
        do {
            switch (nhapMenuCanCuocCongDan()) {
                case "1"://hiển thị nơi sinh
                    hienThiNoiSinh();
                    break;

                case "2"://hiển thị năm sinh & giới tính
                    hienThiNamSinhVaGioiTinh();
                    break;

                case "3"://hiển thị số ngẫu nhiên
                    hienThiSoNgauNhien();
                    break;

                case "0"://thoát
                    inThongBaoThoat();
                    flag = false;
                    break;

                default:
                    break;
            }
        } while (flag);
    }

    //hàm nhập menu chức năng CCCD
    private static String nhapMenuCanCuocCongDan() {
        System.out.println("  | 1. Kiểm tra nơi sinh");
        System.out.println("  | 2. Kiểm tra năm sinh, giới tính");
        System.out.println("  | 3. Kiểm tra số ngẫu nhiên");
        System.out.println("  | 0. Thoát");
        System.out.print("Chọn chức năng: ");
        String answer = SCANNER.nextLine();

        while (!answer.equals("1") && !answer.equals("2") && !answer.equals("3") && !answer.equals("0")) {
            System.out.print("Chức năng không hợp lệ, vui lòng chọn lại: ");
            answer = SCANNER.nextLine();
        }
        return answer;
    }

    //phương thức hiển thị nơi sinh dựa trên CCCD
    private static void hienThiNoiSinh() {
        int maTinh = Integer.parseInt(canCuocCongDan.substring(0, 3));
        System.out.printf("Nơi sinh: %s\n", TINH[maTinh]);
    }

    //phương thức hiển thị năm sinh & giới tính dựa trên CCCD
    private static void hienThiNamSinhVaGioiTinh() {
        int maGioiTinh = Integer.parseInt(canCuocCongDan.substring(3, 4));

        String gioiTinh, namSinh;
        if (maGioiTinh % 2 == 0) gioiTinh = "Nam";
        else gioiTinh = "Nữ";

        if (maGioiTinh <= 1) namSinh = "19";
        else if (maGioiTinh <= 3) namSinh = "20";
        else if (maGioiTinh <= 5) namSinh = "21";
        else if (maGioiTinh <= 7) namSinh = "22";
        else namSinh = "23";

        namSinh += canCuocCongDan.substring(4, 6);
        System.out.printf("Giới tính: %s | Năm sinh: %s\n", gioiTinh, namSinh);
    }

    //phương thức hiển thị số ngẫu nhiên dựa trên CCCD
    private static void hienThiSoNgauNhien() {
        String soNgauNhien = canCuocCongDan.substring(6);
        System.out.printf("Số ngẫu nhiên: %s\n", soNgauNhien);
    }
}
