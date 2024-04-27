import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        WorkLogService workLogService = new WorkLogService();
        
        // Thêm các bản ghi log cho các nhân viên
        WorkLog workLog1 = new WorkLog(1, Date.valueOf("2024-04-01"), 8);
        WorkLog workLog2 = new WorkLog(2, Date.valueOf("2024-04-01"), 7);
        WorkLog workLog3 = new WorkLog(1, Date.valueOf("2024-04-02"), 6);

        workLogService.addWorkLog(workLog1);
        workLogService.addWorkLog(workLog2);
        workLogService.addWorkLog(workLog3);

        // Thực hiện các phương thức khác như tính toán tổng số giờ làm việc và tổng kết lương ở đây
    }
}
